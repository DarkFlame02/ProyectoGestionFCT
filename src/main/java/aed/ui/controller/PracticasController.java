package aed.ui.controller;

import aed.db.empresas.crud.Crear_Empresas;
import aed.db.practicas.Practicas;
import aed.db.practicas.crud.*;
import aed.ui.dialog.BuscarPracticasDialog;
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

public class PracticasController implements Initializable {

    // model
    Practicas newPractica = new Practicas();

    private final ObjectProperty<Practicas> practica = new SimpleObjectProperty<>();
    private final ListProperty<Practicas> practicas = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    practica -> new Observable[] { practica.nombreAlumnoProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Practicas> selectedPractica = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField idAlumnoText;

    @FXML
    private TextField idTutorEText;

    @FXML
    private TextField nombreAlumnoText;

    @FXML
    private TextField nombreTutorEText;

    @FXML
    private ListView<Practicas> practicaList;

    @FXML
    private BorderPane root;

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

        selectedPractica.bind(practicaList.getSelectionModel().selectedItemProperty());
        practica.addListener(this::onPracticaChanged);

        nombreAlumnoText.setDisable(true);
        nombreTutorEText.setDisable(true);

    }

    private void onPracticaChanged(ObservableValue<? extends Practicas> o, Practicas oldValue, Practicas newValue) {
        if (oldValue != null) {
            if (!idAlumnoText.getText().isEmpty()) {
                oldValue.setIdAlumno(Integer.parseInt(idAlumnoText.getText()));
            }
            oldValue.setNombreAlumno(nombreAlumnoText.getText());

            if (!idTutorEText.getText().isEmpty()) {
                oldValue.setIdTutorE(Integer.parseInt(idTutorEText.getText()));
            }
            oldValue.setNombreTutorE(nombreTutorEText.getText());
        }

        if (newValue != null) {
            idAlumnoText.setText(String.valueOf(newValue.getIdAlumno()));
            nombreAlumnoText.setText(newValue.getNombreAlumno());
            idTutorEText.setText(String.valueOf(newValue.getIdTutorE()));
            nombreTutorEText.setText(newValue.getNombreTutorE());
        }
    }


    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewAction(ActionEvent event) {

        newPractica.setNombreAlumno("");
        newPractica.setNombreTutorE("");

        practicas.add(newPractica);

    }

    @FXML
    void onAddAction(ActionEvent event) {

        if (idAlumnoText.getText().isEmpty() || idTutorEText.getText().isEmpty()){

            System.out.println("Todos los campos deben estar completos.");
            return;
        }

        try {

            Practicas nuevaPractica = new Practicas();
            nuevaPractica.setIdAlumno(Integer.parseInt(idAlumnoText.getText()));
            nuevaPractica.setNombreAlumno("Nuevo Alumno");
            nuevaPractica.setIdTutorE(Integer.parseInt(idTutorEText.getText()));

            Crear_Practicas creador = new Crear_Practicas();
            creador.registrarPractica(
                    nuevaPractica.getIdAlumno(),
                    nuevaPractica.getIdTutorE()

            );

            practicas.add(nuevaPractica);
            practicas.remove(newPractica);

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
        if (selectedPractica.get() != null) {
            Practicas practicaBorrada = selectedPractica.get();

            Borrar_Practicas borrador = new Borrar_Practicas();
            try {
                borrador.borrarPractica(
                        practicaBorrada.getIdAlumno()
                );
                System.out.println("Practica borrada correctamente.");

                practicas.remove(practicaBorrada);

                idAlumnoText.clear();
                nombreAlumnoText.clear();
                idTutorEText.clear();
                nombreTutorEText.clear();


            } catch (Exception e) {
                System.err.println("Error al borrar la practica: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna practica para borrar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarPracticasDialog dialog = new BuscarPracticasDialog();
        dialog.showAndWait().ifPresent(buscar -> {

            int idAlumno = buscar.getIdAlumno();
            ObservableList<Practicas> resultado = Buscar_Practicas.buscarPractica(idAlumno);

            if (!resultado.isEmpty()) {
                Practicas practicaEncontrada = resultado.get(0);
                System.out.println("Practica encontrada: " + practicaEncontrada.getIdAlumno());

                practicas.clear();
                practicas.addAll(resultado);
                practicaList.getSelectionModel().select(practicaEncontrada);

            } else {
                System.out.println("No se encontró una practica con ID: " + idAlumno);
            }
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedPractica.get() != null) {
            Practicas practicaActualizada = selectedPractica.get();

            practicaActualizada.setIdTutorE(Integer.parseInt(idTutorEText.getText()));

            Actualizar_Practicas actualizador = new Actualizar_Practicas();
            try {
                actualizador.actualizarPracticas(
                        practicaActualizada.getIdTutorE(),
                        practicaActualizada.getIdAlumno()
                        );
                System.out.println("Practicas actualizadas correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar las practicas: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ninguna practica para actualizar.");
        }
    }
}
