package aed;

import aed.ui.controller.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestionFCTApp extends Application {
    RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Gestión FCT");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
