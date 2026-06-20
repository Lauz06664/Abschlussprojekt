package viewctrl;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.ViewManager;
import model.Deck;
import model.DeckVerwaltung;
import model.Lernkarte;

import java.io.IOException;

public class MainController {

    @FXML private ListView<Deck> deckView;
    @FXML private ListView<Lernkarte> kartenView;
    @FXML private TextField txtNeuesDeck;
    @FXML private Label lblInfo;

    private DeckVerwaltung model;
    private ViewManager viewManager;

    public void setModel(DeckVerwaltung model) {
        this.model = model;
    }

    public void setViewManager(ViewManager vm) {
        this.viewManager = vm;
    }

    @FXML
    public void initialize() {
        // Wenn ein Deck ausgewaehlt wird, die Karten rechts anzeigen.
        deckView.getSelectionModel().selectedItemProperty().addListener(
                (obs, altesDeck, neuesDeck) -> zeigeKarten(neuesDeck));
    }

    // Fuellt die Deck-Liste aus dem TreeSet (alphabetisch sortiert).
    public void zeigeDecks() {
        deckView.setItems(FXCollections.observableArrayList(model.getSortierteDeckListe()));
    }

    private void zeigeKarten(Deck deck) {
        if (deck == null) {
            kartenView.getItems().clear();
            return;
        }
        kartenView.setItems(FXCollections.observableArrayList(deck.getKarten()));
    }

    @FXML
    void neuesDeck() {
        String name = txtNeuesDeck.getText().trim();
        if (name.isEmpty()) {
            lblInfo.setText("Bitte einen Deck-Namen eingeben.");
            return;
        }
        if (model.existiert(name)) {
            lblInfo.setText("Dieses Deck gibt es schon.");
            return;
        }
        model.addDeck(new Deck(name));
        txtNeuesDeck.clear();
        lblInfo.setText("");
        zeigeDecks();
    }

    @FXML
    void neueKarte() throws IOException {
        Deck deck = deckView.getSelectionModel().getSelectedItem();
        if (deck == null) {
            lblInfo.setText("Bitte zuerst ein Deck auswaehlen.");
            return;
        }
        viewManager.openEditorModal(deck);
        // Nach dem Schliessen des Editors die Anzeige aktualisieren.
        zeigeKarten(deck);
        deckView.refresh();
    }

    @FXML
    void speichern() {
        viewManager.speichern();
        lblInfo.setText("Gespeichert.");
    }

    @FXML
    void lernenStarten() throws IOException {
        Deck deck = deckView.getSelectionModel().getSelectedItem();
        if (deck == null) {
            lblInfo.setText("Bitte zuerst ein Deck auswaehlen.");
            return;
        }
        if (deck.getKarten().isEmpty()) {
            lblInfo.setText("Dieses Deck hat noch keine Karten.");
            return;
        }
        viewManager.showLernmodus(deck);
    }
}
