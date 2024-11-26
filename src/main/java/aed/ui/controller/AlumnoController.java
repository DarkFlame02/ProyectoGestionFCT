package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.tutor.Tutor;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

    //model

    private final ObjectProperty<Alumnos> alumno = new SimpleObjectProperty<>();
    private final ListProperty<Alumnos> alumnos = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    alumno -> new Observable[] { alumno.nombreAlumnoProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Alumnos> selectedAlumno = new SimpleObjectProperty<>();

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

        alumno.addListener(this::onAlumnoChanged);

        // bindings

        alumnoList.itemsProperty().bind(alumnos);
        selectedAlumno.bind(alumnoList.getSelectionModel().selectedItemProperty());


    }

    private void onAlumnoChanged(ObservableValue<? extends Alumnos> o, Alumnos oldValue, Alumnos newValue) {
        if (newValue != null) {
            nombreText.textProperty().bindBidirectional(newValue.nombreAlumnoProperty());
            apellidosText.textProperty().bindBidirectional(newValue.apellidosAlumnoProperty());
            cialText.textProperty().bindBidirectional(newValue.cialAlumnoProperty());
            cursoText.textProperty().bindBidirectional(newValue.cursoAlumnoProperty());
            numssText.textProperty().bindBidirectional(newValue.numSSAlumnoProperty());
        }

        if (oldValue != null) {
            nombreText.textProperty().unbindBidirectional(oldValue.nombreAlumnoProperty());
            apellidosText.textProperty().unbindBidirectional(oldValue.apellidosAlumnoProperty());
            cialText.textProperty().unbindBidirectional(oldValue.cialAlumnoProperty());
            cursoText.textProperty().unbindBidirectional(oldValue.cursoAlumnoProperty());
            numssText.textProperty().unbindBidirectional(oldValue.numSSAlumnoProperty());
        }

    }

    public SplitPane getRoot() {
        return root;
    }

}
