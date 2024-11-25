package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.tutor.Tutor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {

    // view

    @FXML
    private ListView<Alumnos> alumnoList;

    @FXML
    private TextField apellidosText;

    @FXML
    private TextField cialText;

    @FXML
    private TextField cursoText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField numssText;

    @FXML
    private ComboBox<Tutor> tutorCombo;

    @FXML
    private SplitPane root;

    public AlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public SplitPane getRoot() {
        return root;
    }


}
