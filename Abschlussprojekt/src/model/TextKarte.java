package model;

// Einfachste Kartenart: Vorderseite (Frage) und Rueckseite (Antwort) als Text.
public class TextKarte extends Lernkarte {

    private String antwort;

    public TextKarte(String id, String frage, String antwort) {
        super(id, frage);
        this.antwort = antwort;
    }

    @Override
    public String getAntwort() {
        return antwort;
    }
}
