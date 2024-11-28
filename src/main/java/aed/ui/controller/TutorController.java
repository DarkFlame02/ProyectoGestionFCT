package aed.ui.controller;

import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.Actualizar_Comentarios;
import aed.db.practicas.Practicas;
import aed.db.practicas.crud.Practicas_Empresa;
import aed.db.tutor.Tutor;
import aed.db.tutor.crud.Actualizar_Tutor;
import aed.db.tutor.crud.Nombre_Tutor;
import aed.ui.dialog.BuscarAlumnoDialog;
import aed.ui.dialog.BuscarTutorDialog;
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

public class TutorController implements Initializable {

    // model

    private final ObjectProperty<Tutor> tutor = new SimpleObjectProperty<>();
    private final ListProperty<Tutor> tutores = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    tutor -> new Observable[] { tutor.nombreTutorProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Tutor> selectedTutor = new SimpleObjectProperty<>();

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
        ObservableList<Tutor> tutoresList = FXCollections.observableArrayList(Nombre_Tutor.listarTutores());

        tutores.set(tutoresList);

        // bindings

        tutorList.itemsProperty().bind(tutores);

        tutorList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tutor.set(newValue);
            }
        });

        selectedTutor.bind(tutorList.getSelectionModel().selectedItemProperty());
        tutor.addListener(this::onTutorChanged);
    }

    private void onTutorChanged(ObservableValue<? extends Tutor> o, Tutor oldValue, Tutor newValue) {
        if (oldValue != null) {
            oldValue.setNombreTutor(nombreText.getText());
            oldValue.setApellidosTutor(apellidosText.getText());
            oldValue.setEmailTutor(emailText.getText());
        }

        if (newValue != null) {
            nombreText.setText(newValue.getNombreTutor());
            apellidosText.setText(newValue.getApellidosTutor());
            emailText.setText(newValue.getEmailTutor());
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
    void onSearchAction(ActionEvent event) {
        BuscarTutorDialog dialog = new BuscarTutorDialog();
        dialog.showAndWait().ifPresent(buscar -> {
            System.out.println(buscar.getIdTutor());
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedTutor.get() != null) {
            Tutor tutorActualizado = selectedTutor.get();

            tutorActualizado.setNombreTutor(nombreText.getText());
            tutorActualizado.setApellidosTutor(apellidosText.getText());
            tutorActualizado.setEmailTutor(emailText.getText());


            Actualizar_Tutor actualizador = new Actualizar_Tutor();
            try {
                actualizador.actualizarTutor(
                        tutorActualizado.getIdTutor(),
                        tutorActualizado.getNombreTutor(),
                        tutorActualizado.getApellidosTutor(),
                        tutorActualizado.getEmailTutor()

                );
                System.out.println("Tutor actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el tutor: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún tutor para actualizar.");
        }
    }
}
