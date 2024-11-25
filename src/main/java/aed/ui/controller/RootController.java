package aed.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // controllers

    private AlumnoController alumnoController = new AlumnoController();
    private EmpresaController empresaController = new EmpresaController();
    private TutorController tutorController = new TutorController();

    // view

    @FXML
    private Tab alumnoTab;

    @FXML
    private Tab tutorTab;

    @FXML
    private Tab comentTab;

    @FXML
    private Tab empresaTab;

    @FXML
    private Tab practicasTab;

    @FXML
    private BorderPane root;

    @FXML
    private TabPane selectionTabPane;

    @FXML
    private Tab visitasTab;

    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alumnoTab.setContent(alumnoController.getRoot());
        empresaTab.setContent(empresaController.getRoot());
        tutorTab.setContent(tutorController.getRoot());
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {

    }

    @FXML
    void onCloseAction(ActionEvent event) {

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
