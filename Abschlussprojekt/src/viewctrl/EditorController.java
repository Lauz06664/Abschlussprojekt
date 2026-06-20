package viewctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Deck;
import model.MultipleChoiceKarte;
import model.TextKarte;

import java.util.ArrayList;
import java.util.List;

// Modaler Dialog zum Anlegen einer neuen Karte (Text oder Multiple Choice).
public class EditorController {

    @FXML private ChoiceBox<String> typBox;
    @FXML private TextField txtFrage;

    // Bereich fuer Textkarten
    @FXML private VBox boxText;
    @FXML private TextField txtAntwort;

    // Bereich fuer Multiple-Choice-Karten
    @FXML private VBox boxMc;
    @FXML private TextField txtA1;
    @FXML private TextField txtA2;
    @FXML private TextField txtA3;
    @FXML private ChoiceBox<String> korrektBox;

    @FXML private Label lblInfo;

    private Deck deck;

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    @FXML
    public void initialize() {
        typBox.getItems().addAll("Text", "Multiple Choice");
        typBox.getSelectionModel().select("Text");

        korrektBox.getItems().addAll("Antwort 1", "Antwort 2", "Antwort 3");
        korrektBox.getSelectionModel().select(0);

        // Felder je nach gewaehltem Kartentyp ein-/ausblenden.
        typBox.getSelectionModel().selectedItemProperty().addListener(
                (obs, alt, neu) -> updateFelder());
        updateFelder();
    }

    private void updateFelder() {
        boolean istText = typBox.getValue().equals("Text");
        // setManaged(false): der ausgeblendete Bereich soll auch keinen Platz mehr im Layout belegen.
        boxText.setVisible(istText);
        boxText.setManaged(istText);
        boxMc.setVisible(!istText);
        boxMc.setManaged(!istText);

        // Fenster an die neue Hoehe anpassen, damit bei Multiple Choice nichts abgeschnitten wird.
        // (beim ersten Aufruf gibt es noch keine Scene, deshalb die null-Pruefung)
        if (typBox.getScene() != null) {
            ((Stage) typBox.getScene().getWindow()).sizeToScene();
        }
    }

    @FXML
    void speichern(ActionEvent event) {
        String frage = txtFrage.getText().trim();
        if (frage.isEmpty()) {
            lblInfo.setText("Bitte eine Frage eingeben.");
            return;
        }

        // Einfache, eindeutige ID ueber den Zeitstempel.
        String id = "karte_" + System.currentTimeMillis();

        if (typBox.getValue().equals("Text")) {
            String antwort = txtAntwort.getText().trim();
            if (antwort.isEmpty()) {
                lblInfo.setText("Bitte eine Antwort eingeben.");
                return;
            }
            deck.addKarte(new TextKarte(id, frage, antwort));
        } else {
            List<String> antworten = new ArrayList<>();
            antworten.add(txtA1.getText().trim());
            antworten.add(txtA2.getText().trim());
            antworten.add(txtA3.getText().trim());
            int korrekt = korrektBox.getSelectionModel().getSelectedIndex();
            deck.addKarte(new MultipleChoiceKarte(id, frage, antworten, korrekt));
        }

        // Dialog schliessen.
        ((Stage) txtFrage.getScene().getWindow()).close();
    }
}
