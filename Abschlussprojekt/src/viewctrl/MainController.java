package viewctrl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Deck;
import model.Lernkarte;
import model.TextKarte;

public class MainController {

    @FXML private ListView<Deck> deckView;
    @FXML private ListView<Lernkarte> kartenView;

    // Die Decks, die in der Uebersicht angezeigt werden.
    private ObservableList<Deck> decks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Ein paar Test-Decks anlegen, damit wir etwas sehen.
        Deck sew = new Deck("SEW Grundlagen");
        sew.addKarte(new TextKarte("k1", "Was ist Polymorphismus?",
                "Gleiche Methode, unterschiedliches Verhalten je nach Objekt."));
        sew.addKarte(new TextKarte("k2", "Wofuer steht MVC?",
                "Model - View - Controller"));
        sew.addKarte(new TextKarte("k3", "Was macht eine PriorityQueue?",
                "Gibt immer das Element mit der hoechsten Prioritaet zuerst zurueck."));

        Deck englisch = new Deck("Englisch Vokabeln");
        englisch.addKarte(new TextKarte("k4", "to inherit", "erben"));
        englisch.addKarte(new TextKarte("k5", "queue", "Warteschlange"));

        decks.add(sew);
        decks.add(englisch);

        deckView.setItems(decks);

        // Wenn ein Deck ausgewaehlt wird, die Karten rechts anzeigen.
        deckView.getSelectionModel().selectedItemProperty().addListener(
                (obs, altesDeck, neuesDeck) -> zeigeKarten(neuesDeck));
    }

    private void zeigeKarten(Deck deck) {
        if (deck == null) {
            kartenView.getItems().clear();
            return;
        }
        kartenView.setItems(FXCollections.observableArrayList(deck.getKarten()));
    }
}
