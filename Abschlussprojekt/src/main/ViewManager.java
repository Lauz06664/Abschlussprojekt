package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deck;
import model.DeckVerwaltung;
import model.LernHistorie;
import viewctrl.DeckFileHandler;
import viewctrl.EditorController;
import viewctrl.HistorieFileHandler;
import viewctrl.LernController;
import viewctrl.MainController;
import viewctrl.StatistikController;

import java.io.File;
import java.io.IOException;

// Koordiniert die Szenen (Hauptübersicht, Lernmodus, Statistik, modaler Editor) wie in Projekt 10.
public class ViewManager {

    // Alle gespeicherten Dateien liegen gesammelt im Ordner "Speicher".
    private static final String SPEICHER_ORDNER = "Speicher";
    private static final String DATEINAME = SPEICHER_ORDNER + "/lernkartei.dat";
    private static final String HISTORIE_DATEINAME = SPEICHER_ORDNER + "/historie.txt";

    private Stage primaryStage;
    private DeckVerwaltung model;
    private LernHistorie historie;
    private DeckFileHandler fileHandler;
    private HistorieFileHandler historieHandler;

    public ViewManager(Stage stage) {
        this.primaryStage = stage;
        // Fenstergröße fix lassen: verhindert verschobene Layouts beim Vergrößern/Verkleinern.
        this.primaryStage.setResizable(false);
        // Ordner anlegen, falls er noch nicht existiert (mkdirs legt fehlende Ordner an).
        new File(SPEICHER_ORDNER).mkdirs();
        this.model = new DeckVerwaltung();
        this.historie = new LernHistorie();
        this.fileHandler = new DeckFileHandler(DATEINAME, model);
        this.historieHandler = new HistorieFileHandler(HISTORIE_DATEINAME, historie);

        // Beim Start: gespeicherte Daten laden, sonst Beispieldaten anlegen.
        File datei = new File(DATEINAME);
        if (datei.exists()) {
            fileHandler.laden();
        } else {
            model.beispielDatenLaden();
        }
        historieHandler.laden();

        // Beim Schliessen des Fensters automatisch speichern.
        primaryStage.setOnCloseRequest(e -> speichern());
    }

    public void speichern() {
        fileHandler.schreiben();
        historieHandler.schreiben();
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
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void showLernmodus(Deck deck) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewctrl/lern.fxml"));
        Parent root = loader.load();

        LernController controller = loader.getController();
        controller.setViewManager(this);
        controller.setHistorie(historie);
        controller.starteSession(deck);

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
    }

    public void showStatistik() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/viewctrl/statistik.fxml"));
        Parent root = loader.load();

        StatistikController controller = loader.getController();
        controller.setViewManager(this);
        controller.setModel(model);
        controller.setHistorie(historie);
        controller.anzeigen();

        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
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
