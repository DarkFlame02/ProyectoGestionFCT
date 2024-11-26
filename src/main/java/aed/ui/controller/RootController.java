package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.ui.dialog.BuscarAlumnoDialog;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
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
    private PracticasController practicasController = new PracticasController();
    private VisitasController visitasController = new VisitasController();
    private ComentariosController comentariosController = new ComentariosController();

    // model



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
        practicasTab.setContent(practicasController.getRoot());
        visitasTab.setContent(visitasController.getRoot());
        comentTab.setContent(comentariosController.getRoot());

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
        BuscarAlumnoDialog dialog = new BuscarAlumnoDialog();
        dialog.showAndWait().ifPresent(buscar -> {
            System.out.println(buscar.getIdAlumno());
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {

    }

}
