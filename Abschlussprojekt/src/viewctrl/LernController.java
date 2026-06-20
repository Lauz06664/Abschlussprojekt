package viewctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.ViewManager;
import model.Deck;
import model.LernHistorie;
import model.LernSession;

import java.io.IOException;

public class LernController {

    @FXML private Label lblFrage;
    @FXML private Label lblAntwort;
    @FXML private Label lblFortschritt;
    @FXML private Button btnZeigen;
    @FXML private Button btnRichtig;
    @FXML private Button btnFalsch;

    private ViewManager viewManager;
    private LernSession session;
    private LernHistorie historie;

    public void setViewManager(ViewManager vm) {
        this.viewManager = vm;
    }

    public void setHistorie(LernHistorie historie) {
        this.historie = historie;
    }

    // Wird vom ViewManager aufgerufen, sobald das Deck feststeht.
    public void starteSession(Deck deck) {
        session = new LernSession(deck);
        zeigeNaechste();
    }

    private void zeigeNaechste() {
        if (!session.hatKarten()) {
            lblFrage.setText("Geschafft! Keine Karten mehr offen.");
            lblAntwort.setText("");
            lblFortschritt.setText("");
            setBewertungSichtbar(false);
            btnZeigen.setVisible(false);
            return;
        }

        lblFrage.setText(session.naechsteKarte().getFrage());
        lblAntwort.setText("");
        lblFortschritt.setText("Noch " + session.anzahlOffen() + " Karten");

        // Erst Frage zeigen, Bewertung kommt nach "Antwort zeigen".
        btnZeigen.setVisible(true);
        setBewertungSichtbar(false);
    }

    @FXML
    void antwortZeigen() {
        if (!session.hatKarten()) return;
        lblAntwort.setText(session.naechsteKarte().getAntwort());
        btnZeigen.setVisible(false);
        setBewertungSichtbar(true);
    }

    @FXML
    void richtig() {
        session.richtig();
        historie.addRichtig();
        zeigeNaechste();
    }

    @FXML
    void falsch() {
        session.falsch();
        historie.addFalsch();
        zeigeNaechste();
    }

    @FXML
    void zurueck() throws IOException {
        viewManager.showMain();
    }

    private void setBewertungSichtbar(boolean sichtbar) {
        btnRichtig.setVisible(sichtbar);
        btnFalsch.setVisible(sichtbar);
    }
}
