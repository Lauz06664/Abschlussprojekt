package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import main.ViewManager;
import model.Deck;
import model.DeckVerwaltung;
import model.LernHistorie;

import java.io.IOException;
import java.time.LocalDate;

public class StatistikController {

    @FXML private LineChart<String, Number> lineChart;
    @FXML private BarChart<String, Number> barChart;

    private ViewManager viewManager;
    private DeckVerwaltung model;
    private LernHistorie historie;

    public void setViewManager(ViewManager vm) {
        this.viewManager = vm;
    }

    public void setModel(DeckVerwaltung model) {
        this.model = model;
    }

    public void setHistorie(LernHistorie historie) {
        this.historie = historie;
    }

    // Beide Diagramme mit Daten füllen.
    public void anzeigen() {
        zeigeFortschritt();
        zeigeKartenProDeck();
    }

    // LineChart: richtige/falsche Antworten der letzten 7 Tage.
    private void zeigeFortschritt() {
        lineChart.getData().clear();

        XYChart.Series<String, Number> richtigReihe = new XYChart.Series<>();
        richtigReihe.setName("Richtig");
        XYChart.Series<String, Number> falschReihe = new XYChart.Series<>();
        falschReihe.setName("Falsch");

        for (int i = 6; i >= 0; i--) {
            LocalDate tag = LocalDate.now().minusDays(i);
            String key = tag.toString();

            int richtig = historie.getRichtigProTag().getOrDefault(key, 0);
            int falsch = historie.getFalschProTag().getOrDefault(key, 0);

            richtigReihe.getData().add(new XYChart.Data<>(key, richtig));
            falschReihe.getData().add(new XYChart.Data<>(key, falsch));
        }

        lineChart.getData().add(richtigReihe);
        lineChart.getData().add(falschReihe);
    }

    // BarChart: Anzahl Karten pro Deck.
    private void zeigeKartenProDeck() {
        barChart.getData().clear();

        XYChart.Series<String, Number> reihe = new XYChart.Series<>();
        reihe.setName("Karten pro Deck");

        for (Deck deck : model.getSortierteDeckListe()) {
            reihe.getData().add(new XYChart.Data<>(deck.getName(), deck.getKarten().size()));
        }

        barChart.getData().add(reihe);
    }

    @FXML
    void zurueck() throws IOException {
        viewManager.showMain();
    }
}
