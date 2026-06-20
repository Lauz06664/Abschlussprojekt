package model;

import java.time.LocalDate;
import java.util.HashMap;

// Zaehlt pro Tag, wie viele Karten richtig bzw. falsch beantwortet wurden.
// Schluessel ist das Datum als Text (z.B. "2026-06-01").
public class LernHistorie {

    private HashMap<String, Integer> richtigProTag = new HashMap<>();
    private HashMap<String, Integer> falschProTag = new HashMap<>();

    public void addRichtig() {
        String heute = LocalDate.now().toString();
        richtigProTag.put(heute, richtigProTag.getOrDefault(heute, 0) + 1);
    }

    public void addFalsch() {
        String heute = LocalDate.now().toString();
        falschProTag.put(heute, falschProTag.getOrDefault(heute, 0) + 1);
    }

    public HashMap<String, Integer> getRichtigProTag() {
        return richtigProTag;
    }

    public HashMap<String, Integer> getFalschProTag() {
        return falschProTag;
    }

    // Wird beim Laden aus der Datei verwendet.
    public void setTag(String tag, int richtig, int falsch) {
        richtigProTag.put(tag, richtig);
        falschProTag.put(tag, falsch);
    }
}
