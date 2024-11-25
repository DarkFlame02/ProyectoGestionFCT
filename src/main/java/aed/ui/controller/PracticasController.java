package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PracticasController implements Initializable {

    // view

    @FXML
    private TextField nombreAlumnoText;

    @FXML
    private ListView<Alumnos> practicasList;

    @FXML
    private SplitPane root;

    @FXML
    private ComboBox<?> tutorECombo;

    public PracticasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PracticasView.fxml"));
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
