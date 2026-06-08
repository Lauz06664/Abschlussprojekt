package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deck;
import model.DeckVerwaltung;
import viewctrl.EditorController;
import viewctrl.MainController;

import java.io.IOException;

// Koordiniert die Szenen (Hauptuebersicht + modaler Editor) wie in Projekt 10.
public class ViewManager {

    private Stage primaryStage;
    private DeckVerwaltung model;

    public ViewManager(Stage stage) {
        this.primaryStage = stage;
        this.model = new DeckVerwaltung();
    }

    public void showMain() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewctrl/main.fxml"));
        Parent root = loader.load();

        MainController controller = loader.getController();
        controller.setModel(model);
        controller.setViewManager(this);
        controller.zeigeDecks();

        primaryStage.setTitle("Lernkartei");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void openEditorModal(Deck deck) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewctrl/editor.fxml"));
        Parent root = loader.load();

        EditorController controller = loader.getController();
        controller.setDeck(deck);

        Stage dialog = new Stage();
        dialog.setTitle("Karte erstellen");
        dialog.initOwner(primaryStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setScene(new Scene(root, 400, 380));
        dialog.showAndWait();
    }
}
