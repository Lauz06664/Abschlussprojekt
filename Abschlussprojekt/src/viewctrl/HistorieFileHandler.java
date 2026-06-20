package viewctrl;

import model.LernHistorie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.TreeSet;

// Speichert die Lernhistorie als einfache Textdatei.
// Eine Zeile pro Tag im Format:  datum;richtig;falsch
public class HistorieFileHandler {

    private String filename;
    private LernHistorie historie;

    public HistorieFileHandler(String filename, LernHistorie historie) {
        this.filename = filename;
        this.historie = historie;
    }

    public void schreiben() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Alle vorkommenden Tage aus beiden Maps einsammeln.
            TreeSet<String> tage = new TreeSet<>();
            tage.addAll(historie.getRichtigProTag().keySet());
            tage.addAll(historie.getFalschProTag().keySet());

            for (String tag : tage) {
                int richtig = historie.getRichtigProTag().getOrDefault(tag, 0);
                int falsch = historie.getFalschProTag().getOrDefault(tag, 0);
                writer.write(tag + ";" + richtig + ";" + falsch);
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void laden() {
        File datei = new File(filename);
        if (!datei.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                String[] teile = zeile.split(";");
                if (teile.length == 3) {
                    String tag = teile[0];
                    int richtig = Integer.parseInt(teile[1]);
                    int falsch = Integer.parseInt(teile[2]);
                    historie.setTag(tag, richtig, falsch);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
