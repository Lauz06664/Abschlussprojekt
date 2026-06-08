package model;

import java.util.List;

// Karte mit mehreren Antwortmoeglichkeiten, von denen genau eine richtig ist.
public class MultipleChoiceKarte extends Lernkarte {

    private List<String> antworten;
    private int korrektIndex;

    public MultipleChoiceKarte(String id, String frage, List<String> antworten, int korrektIndex) {
        super(id, frage);
        this.antworten = antworten;
        this.korrektIndex = korrektIndex;
    }

    public List<String> getAntwortenListe() {
        return antworten;
    }

    public int getKorrektIndex() {
        return korrektIndex;
    }

    // Liefert die richtige Antwort (Polymorphismus: gleiche Methode wie bei TextKarte).
    @Override
    public String getAntwort() {
        return antworten.get(korrektIndex);
    }
}
