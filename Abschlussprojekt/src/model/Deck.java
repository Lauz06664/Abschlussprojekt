package model;

import java.util.ArrayList;
import java.util.List;

// Ein Deck buendelt mehrere Lernkarten unter einem Namen.
// Comparable, damit Decks im TreeSet alphabetisch sortiert werden.
public class Deck implements Comparable<Deck> {

    private String name;
    private List<Lernkarte> karten;

    public Deck(String name) {
        this.name = name;
        this.karten = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Lernkarte> getKarten() {
        return karten;
    }

    public void addKarte(Lernkarte karte) {
        karten.add(karte);
    }

    // Sortierung im TreeSet nach Deck-Name (Gross-/Kleinschreibung egal).
    @Override
    public int compareTo(Deck other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + karten.size() + " Karten)";
    }
}
