package model;

import java.time.LocalDate;

// Abstrakte Basisklasse fuer alle Lernkarten.
// Gemeinsame Daten: ID, Frage, wie oft hintereinander richtig (Streak)
// und das Datum der naechsten Wiederholung.
public abstract class Lernkarte {

    protected String id;
    protected String frage;
    protected int richtigStreak;
    protected LocalDate naechsteWiederholung;

    public Lernkarte(String id, String frage) {
        this.id = id;
        this.frage = frage;
        this.richtigStreak = 0;
        this.naechsteWiederholung = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public int getRichtigStreak() {
        return richtigStreak;
    }

    public LocalDate getNaechsteWiederholung() {
        return naechsteWiederholung;
    }

    public void setRichtigStreak(int richtigStreak) {
        this.richtigStreak = richtigStreak;
    }

    public void setNaechsteWiederholung(LocalDate naechsteWiederholung) {
        this.naechsteWiederholung = naechsteWiederholung;
    }

    // Jede Kartenart liefert ihre Antwort als Text (fuer die Anzeige im Lernmodus).
    public abstract String getAntwort();

    @Override
    public String toString() {
        return frage;
    }
}
