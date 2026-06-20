package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deck;
import model.DeckVerwaltung;
import viewctrl.DeckFileHandler;
import viewctrl.EditorController;
import viewctrl.LernController;
import viewctrl.MainController;

import java.io.File;
import java.io.IOException;

// Koordiniert die Szenen (Hauptuebersicht, Lernmodus, modaler Editor) wie in Projekt 10.
public class ViewManager {

    private static final String DATEINAME = "lernkartei.dat";

    private Stage primaryStage;
    private DeckVerwaltung model;
    private DeckFileHandler fileHandler;

    public ViewManager(Stage stage) {
        this.primaryStage = stage;
        this.model = new DeckVerwaltung();
        this.fileHandler = new DeckFileHandler(DATEINAME, model);

        // Beim Start: gespeicherte Daten laden, sonst Beispieldaten anlegen.
        File datei = new File(DATEINAME);
        if (datei.exists()) {
            fileHandler.laden();
        } else {
            model.beispielDatenLaden();
        }

        // Beim Schliessen des Fensters automatisch speichern.
        primaryStage.setOnCloseRequest(e -> speichern());
    }

    public void speichern() {
        fileHandler.schreiben();
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

    public void showLernmodus(Deck deck) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewctrl/lern.fxml"));
        Parent root = loader.load();

        LernController controller = loader.getController();
        controller.setViewManager(this);
        controller.starteSession(deck);

        primaryStage.setScene(new Scene(root));
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
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }
}
