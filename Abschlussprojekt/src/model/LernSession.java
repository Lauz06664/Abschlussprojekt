package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Eine Lernrunde fuer ein Deck.
// Die PriorityQueue gibt immer die Karte mit dem fruehesten
// Wiederholungsdatum zuerst zurueck (das Herzstueck des Spaced-Repetition).
public class LernSession {

    private PriorityQueue<Lernkarte> warteschlange;
    private List<Lernkarte> heuteGelernt;

    public LernSession(Deck deck) {
        warteschlange = new PriorityQueue<>(
                Comparator.comparing(Lernkarte::getNaechsteWiederholung));
        warteschlange.addAll(deck.getKarten());
        heuteGelernt = new ArrayList<>();
    }

    public boolean hatKarten() {
        return !warteschlange.isEmpty();
    }

    public int anzahlOffen() {
        return warteschlange.size();
    }

    // Naechste faellige Karte (ohne sie zu entfernen).
    public Lernkarte naechsteKarte() {
        return warteschlange.peek();
    }

    // Karte richtig beantwortet: Streak erhoehen, naechstes Intervall verdoppeln,
    // Karte ist fuer diese Runde erledigt.
    public void richtig() {
        Lernkarte karte = warteschlange.poll();
        if (karte == null) return;

        karte.setRichtigStreak(karte.getRichtigStreak() + 1);
        int tage = berechneIntervall(karte.getRichtigStreak());
        karte.setNaechsteWiederholung(LocalDate.now().plusDays(tage));
        heuteGelernt.add(karte);
    }

    // Karte falsch beantwortet: Streak zurueck auf 0 und sofort wieder einreihen.
    public void falsch() {
        Lernkarte karte = warteschlange.poll();
        if (karte == null) return;

        karte.setRichtigStreak(0);
        karte.setNaechsteWiederholung(LocalDate.now());
        warteschlange.add(karte);
    }

    // Rekursive Berechnung des Wiederholungsintervalls in Tagen.
    // Basisfall: streak 0 -> 1 Tag. Rekursionsschritt: Verdoppelung.
    // Ergebnis: 0->1, 1->2, 2->4, 3->8 ...
    private int berechneIntervall(int richtigStreak) {
        if (richtigStreak <= 0) return 1;
        return berechneIntervall(richtigStreak - 1) * 2;
    }

    public List<Lernkarte> getHeuteGelernt() {
        return heuteGelernt;
    }
}
