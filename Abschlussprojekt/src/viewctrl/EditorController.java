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

    // Frage: Buchstaben (inkl. Umlaute), Ziffern, Leerzeichen, Bindestrich, Punkt; 2 bis 50 Zeichen.
    private static final String REGEX_KARTENNAME = "[A-Za-zÄÖÜäöüß0-9\\s\\-\\.\\?]{2,50}";

    @FXML private ChoiceBox<String> typBox;
    @FXML private TextField txtFrage;

    // Bereich für Textkarten
    @FXML private VBox boxText;
    @FXML private TextField txtAntwort;

    // Bereich für Multiple-Choice-Karten
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

        // Felder je nach gewähltem Kartentyp ein-/ausblenden.
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

        // Fenster an die neue Höhe anpassen, damit bei Multiple Choice nichts abgeschnitten wird.
        // (beim ersten Aufruf gibt es noch keine Scene, deshalb die null-Prüfung)
        if (typBox.getScene() != null) {
            ((Stage) typBox.getScene().getWindow()).sizeToScene();
        }
    }

    @FXML
    void speichern(ActionEvent event) {
        String frage = txtFrage.getText().trim();
        // Frage per Regex prüfen (2-50 erlaubte Zeichen).
        if (!frage.matches(REGEX_KARTENNAME)) {
            lblInfo.setText("Ungültige Frage (2-50 Zeichen, keine Sonderzeichen).");
            return;
        }

        // Einfache, eindeutige ID über den Zeitstempel.
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
