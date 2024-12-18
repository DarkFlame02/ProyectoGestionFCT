package aed.ui.controller;

import aed.db.practicas.crud.Crear_Practicas;
import aed.db.tutor.Tutor;
import aed.db.tutor.crud.*;
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
    private Tutor newTutor = new Tutor();

    private final ObjectProperty<Tutor> tutor = new SimpleObjectProperty<>();
    private final ListProperty<Tutor> tutores = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    tutor -> new Observable[] { tutor.nombreTutorProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Tutor> selectedTutor = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField idTutorText;

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

        idTutorText.setDisable(true);

    }

    private void onTutorChanged(ObservableValue<? extends Tutor> o, Tutor oldValue, Tutor newValue) {
        if (oldValue != null) {
            if (!idTutorText.getText().isEmpty()) {
                oldValue.setIdTutor(Integer.parseInt(idTutorText.getText()));
            }
            oldValue.setNombreTutor(nombreText.getText());
            oldValue.setApellidosTutor(apellidosText.getText());
            oldValue.setEmailTutor(emailText.getText());
        }

        if (newValue != null) {
            idTutorText.setText(String.valueOf(newValue.getIdTutor()));
            nombreText.setText(newValue.getNombreTutor());
            apellidosText.setText(newValue.getApellidosTutor());
            emailText.setText(newValue.getEmailTutor());
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewAction(ActionEvent event) {
        newTutor.setNombreTutor("Nuevo");
        newTutor.setApellidosTutor("Tutor");
        newTutor.setEmailTutor("");

        tutores.add(newTutor);
    }

    @FXML
    void onAddAction(ActionEvent event) {
        if (nombreText.getText().isEmpty() || apellidosText.getText().isEmpty()|| emailText.getText().isEmpty()){

            System.out.println("Todos los campos deben estar completos.");
            return;
        }

        try {

            Tutor nuevoTutor = new Tutor();
            nuevoTutor.setNombreTutor(nombreText.getText());
            nuevoTutor.setApellidosTutor(apellidosText.getText());
            nuevoTutor.setEmailTutor(emailText.getText());

            Crear_Tutor creador = new Crear_Tutor();
            creador.registrarTutor(
                    nuevoTutor.getNombreTutor(),
                    nuevoTutor.getApellidosTutor(),
                    nuevoTutor.getEmailTutor()

            );

            tutores.add(nuevoTutor);
            tutores.remove(newTutor);

            System.out.println("Practicas añadida correctamente.");

        } catch (Exception e) {
            System.err.println("Error al añadir la practicas: " + e.getMessage());
        }
    }

    @FXML
    void onCloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        if (selectedTutor.get() != null) {
            Tutor tutorBorrado = selectedTutor.get();

            Borrar_Tutor borrador = new Borrar_Tutor();
            try {
                borrador.borrarTutor(
                        tutorBorrado.getIdTutor()
                );
                System.out.println("Tutor borrado correctamente.");

                tutores.remove(tutorBorrado);

                idTutorText.clear();
                nombreText.clear();
                apellidosText.clear();
                emailText.clear();

            } catch (Exception e) {
                System.err.println("Error al borrar el tutor: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún tutor para borrar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarTutorDialog dialog = new BuscarTutorDialog();
        dialog.showAndWait().ifPresent(buscar -> {

            int idTutor = buscar.getIdTutor();
            ObservableList<Tutor> resultado = Buscar_Tutor.buscarTutor(idTutor);

            if (!resultado.isEmpty()) {
                Tutor tutorEncontrada = resultado.get(0);
                System.out.println("Tutor encontrado: " + tutorEncontrada.getIdTutor());

                tutores.clear();
                tutores.addAll(resultado);
                tutorList.getSelectionModel().select(tutorEncontrada);

            } else {
                System.out.println("No se encontró un tutor con ID: " + idTutor);
            }
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
