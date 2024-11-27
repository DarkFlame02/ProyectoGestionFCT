package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.Comentarios_Empresa;
import aed.db.practicas.Practicas;
import aed.db.practicas.crud.Practicas_Empresa;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    // model

    private final ObjectProperty<Practicas> practica = new SimpleObjectProperty<>();
    private final ListProperty<Practicas> practicas = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    practica -> new Observable[] { practica.nombreAlumnoProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Practicas> selectedComentario = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField nombreAlumnoText;

    @FXML
    private TextField nombreTutorEText;

    @FXML
    private ListView<Practicas> practicaList;

    @FXML
    private SplitPane root;

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
        ObservableList<Practicas> practicasList = FXCollections.observableArrayList(Practicas_Empresa.listarPracticas());

        practicas.set(practicasList);

        // bindings

        practicaList.itemsProperty().bind(practicas);

        practicaList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                practica.set(newValue);
            }
        });

        selectedComentario.bind(practicaList.getSelectionModel().selectedItemProperty());
        practica.addListener(this::onPracticaChanged);
    }

    private void onPracticaChanged(ObservableValue<? extends Practicas> o, Practicas oldValue, Practicas newValue) {
        if (oldValue != null) {
            oldValue.setNombreAlumno(nombreAlumnoText.getText());
            oldValue.setNombreTutorE(nombreTutorEText.getText());
        }

        if (newValue != null) {
            nombreAlumnoText.setText(newValue.getNombreAlumno());
            nombreTutorEText.setText(String.valueOf(newValue.getNombreTutorE()));
        }
    }

    public SplitPane getRoot() {
        return root;
    }
}
