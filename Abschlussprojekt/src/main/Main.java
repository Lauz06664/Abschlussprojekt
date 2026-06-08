package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewManager vm = new ViewManager(stage);
        vm.showMain();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
