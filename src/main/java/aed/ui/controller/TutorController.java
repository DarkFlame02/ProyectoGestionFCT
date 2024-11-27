package aed.ui.controller;

import aed.db.tutor.Tutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorController implements Initializable {

    // view

    @FXML
    private TextField apellidosText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField nombreText;

    @FXML
    private BorderPane root;

    @FXML
    private ListView<Tutor> tutorList;

    public TutorController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TutorView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {

    }

    @FXML
    void onCloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onDeleteAction(ActionEvent event) {

    }

    @FXML
    void onSearchAction(ActionEvent event) {

    }

    @FXML
    void onUpdateAction(ActionEvent event) {

    }
}
