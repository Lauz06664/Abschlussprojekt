package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

// Verwaltet alle Decks.
// HashMap: schneller Zugriff per Deck-Name (O(1)).
// TreeSet: immer alphabetisch sortierte Deck-Liste.
public class DeckVerwaltung {

    private HashMap<String, Deck> deckIndex = new HashMap<>();
    private TreeSet<Deck> sortierteDeckListe = new TreeSet<>();

    // Startet leer. Beispieldaten bzw. das Laden aus der Datei
    // übernimmt der ViewManager beim Programmstart.
    public DeckVerwaltung() {
    }

    public void addDeck(Deck deck) {
        deckIndex.put(deck.getName(), deck);
        sortierteDeckListe.add(deck);
    }

    public Deck getDeck(String name) {
        return deckIndex.get(name);
    }

    public boolean existiert(String name) {
        return deckIndex.containsKey(name);
    }

    public TreeSet<Deck> getSortierteDeckListe() {
        return sortierteDeckListe;
    }

    // Ein paar Beispieldecks für den ersten Start (wenn noch keine Datei da ist).
    public void beispielDatenLaden() {
        Deck sew = new Deck("SEW Grundlagen");
        sew.addKarte(new TextKarte("k1", "Was ist Polymorphismus?",
                "Gleiche Methode, unterschiedliches Verhalten je nach Objekt."));
        sew.addKarte(new TextKarte("k2", "Wofür steht MVC?",
                "Model - View - Controller"));

        List<String> antworten = new ArrayList<>();
        antworten.add("Liste");
        antworten.add("Warteschlange mit Priorität");
        antworten.add("Stapel");
        sew.addKarte(new MultipleChoiceKarte("k3", "Was ist eine PriorityQueue?", antworten, 1));

        Deck englisch = new Deck("Englisch Vokabeln");
        englisch.addKarte(new TextKarte("k4", "to inherit", "erben"));
        englisch.addKarte(new TextKarte("k5", "queue", "Warteschlange"));

        addDeck(sew);
        addDeck(englisch);
    }
}
