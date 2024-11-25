package aed.ui.controller;

import aed.db.visitas.Visitas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitasController implements Initializable {

    // view

    @FXML
    private TextField apellidosText;

    @FXML
    private TextArea comentarioText;

    @FXML
    private ListView<Visitas> fechaList;

    @FXML
    private SplitPane root;

    @FXML
    private DatePicker visitaDate;

    public VisitasController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VisitasView.fxml"));
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
