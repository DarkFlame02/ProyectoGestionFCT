package aed.ui.controller;


import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.Actualizar_Comentarios;
import aed.db.empresas.Empresas;
import aed.db.empresas.crud.Borrar_Empresas;
import aed.db.practicas.Practicas;
import aed.db.practicas.crud.Actualizar_Practicas;
import aed.db.practicas.crud.Borrar_Practicas;
import aed.db.practicas.crud.Practicas_Empresa;
import aed.ui.dialog.BuscarPracticasDialog;
import aed.ui.dialog.BuscarVisitaDialog;
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
            System.out.println(buscar.getIdTutorE());
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
                System.out.println("Practicas actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el practicas: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún practica para actualizar.");
        }
    }
}
