package viewctrl;

import model.Deck;
import model.DeckVerwaltung;
import model.Lernkarte;
import model.MultipleChoiceKarte;
import model.TextKarte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Speichert und lädt alle Decks typisiert binär (DataOutputStream/DataInputStream).
// Aufbau pro Karte: gemeinsame Daten, dann ein Typ-Marker (0 = Text, 1 = MC)
// und danach die typspezifischen Daten.
public class DeckFileHandler {

    private String filename;
    private DeckVerwaltung verwaltung;

    public DeckFileHandler(String filename, DeckVerwaltung verwaltung) {
        this.filename = filename;
        this.verwaltung = verwaltung;
    }

    public void schreiben() {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            dos.writeInt(verwaltung.getSortierteDeckListe().size());

            for (Deck deck : verwaltung.getSortierteDeckListe()) {
                dos.writeUTF(deck.getName());
                dos.writeInt(deck.getKarten().size());

                for (Lernkarte karte : deck.getKarten()) {
                    // Gemeinsame Daten jeder Karte
                    dos.writeUTF(karte.getId());
                    dos.writeUTF(karte.getFrage());
                    dos.writeInt(karte.getRichtigStreak());
                    dos.writeLong(karte.getNaechsteWiederholung().toEpochDay());

                    // Typ-Marker + typspezifische Daten
                    if (karte instanceof TextKarte) {
                        dos.writeInt(0);
                        dos.writeUTF(karte.getAntwort());
                    } else {
                        MultipleChoiceKarte mc = (MultipleChoiceKarte) karte;
                        dos.writeInt(1);
                        dos.writeInt(mc.getAntwortenListe().size());
                        for (String antwort : mc.getAntwortenListe()) {
                            dos.writeUTF(antwort);
                        }
                        dos.writeInt(mc.getKorrektIndex());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void laden() {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            int deckAnzahl = dis.readInt();

            for (int i = 0; i < deckAnzahl; i++) {
                Deck deck = new Deck(dis.readUTF());
                int kartenAnzahl = dis.readInt();

                for (int j = 0; j < kartenAnzahl; j++) {
                    String id = dis.readUTF();
                    String frage = dis.readUTF();
                    int streak = dis.readInt();
                    long epochDay = dis.readLong();
                    int typ = dis.readInt();

                    Lernkarte karte;
                    if (typ == 0) {
                        String antwort = dis.readUTF();
                        karte = new TextKarte(id, frage, antwort);
                    } else {
                        int anzahl = dis.readInt();
                        List<String> antworten = new ArrayList<>();
                        for (int k = 0; k < anzahl; k++) {
                            antworten.add(dis.readUTF());
                        }
                        int korrektIndex = dis.readInt();
                        karte = new MultipleChoiceKarte(id, frage, antworten, korrektIndex);
                    }

                    // Lernstand wiederherstellen
                    karte.setRichtigStreak(streak);
                    karte.setNaechsteWiederholung(LocalDate.ofEpochDay(epochDay));
                    deck.addKarte(karte);
                }

                verwaltung.addDeck(deck);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
