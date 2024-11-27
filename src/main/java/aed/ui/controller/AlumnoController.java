package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.alumnos.crud.Nombre_Alumno;
import aed.ui.dialog.BuscarAlumnoDialog;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    private BorderPane root;

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
        ObservableList<Alumnos> alumnosList = FXCollections.observableArrayList(Nombre_Alumno.listarAlumnos());

        alumnos.set(alumnosList);

        // bindings

        alumnoList.itemsProperty().bind(alumnos);

        alumnoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                alumno.set(newValue);
            }
        });

        selectedAlumno.bind(alumnoList.getSelectionModel().selectedItemProperty());
        alumno.addListener(this::onAlumnoChanged);

    }

    private void onAlumnoChanged(ObservableValue<? extends Alumnos> o, Alumnos oldValue, Alumnos newValue) {
        if (oldValue != null) {
            oldValue.setNombreAlumno(nombreText.getText());
            oldValue.setApellidosAlumno(apellidosText.getText());
            oldValue.setCialAlumno(cialText.getText());
            oldValue.setCursoAlumno(cursoText.getText());
            oldValue.setNumSSAlumno(numssText.getText());
        }

        if (newValue != null) {
            nombreText.setText(newValue.getNombreAlumno());
            apellidosText.setText(newValue.getApellidosAlumno());
            cialText.setText(newValue.getCialAlumno());
            cursoText.setText(newValue.getCursoAlumno());
            numssText.setText(newValue.getNumSSAlumno());
        }

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
    void onUpdateAction(ActionEvent event) {

    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarAlumnoDialog dialog = new BuscarAlumnoDialog();
        dialog.showAndWait().ifPresent(buscar -> {
            System.out.println(buscar.getIdAlumno());
        });
    }

}
