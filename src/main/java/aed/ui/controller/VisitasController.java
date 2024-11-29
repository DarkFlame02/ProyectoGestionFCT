package aed.ui.controller;

import aed.db.practicas.crud.Actualizar_Practicas;
import aed.db.tutor.Tutor;
import aed.db.tutor.crud.Borrar_Tutor;
import aed.db.visitas.Visitas;
import aed.db.visitas.crud.Actualizar_Visitas;
import aed.db.visitas.crud.Borrar_Visitas;
import aed.db.visitas.crud.Fecha_Visita;
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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class VisitasController implements Initializable {

    // model

    private final ObjectProperty<Visitas> visita = new SimpleObjectProperty<>();
    private final ListProperty<Visitas> visitas = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    visita -> new Observable[] { visita.fechaVisitaProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Visitas> selectedVisita = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField idVisitaText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextArea comentarioText;

    @FXML
    private ListView<Visitas> visitaList;

    @FXML
    private BorderPane root;

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
        ObservableList<Visitas> visitasList = FXCollections.observableArrayList(Fecha_Visita.listarVisitas());

        visitas.set(visitasList);

        // bindings

        visitaList.itemsProperty().bind(visitas);

        visitaList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                visita.set(newValue);
            }
        });

        selectedVisita.bind(visitaList.getSelectionModel().selectedItemProperty());
        visita.addListener(this::onVisitaChanged);

        idVisitaText.setDisable(true);
    }

    private void onVisitaChanged(ObservableValue<? extends Visitas> o, Visitas oldValue, Visitas newValue) {
        if (oldValue != null) {
            try {
                if (!idVisitaText.getText().isEmpty()) {
                    oldValue.setIdVisita(Integer.parseInt(idVisitaText.getText()));
                }
                if (visitaDate.getValue() != null) { // Verifica que visitaDate no sea nulo
                    oldValue.setFechaVisita(Date.valueOf(visitaDate.getValue()));
                }
                oldValue.setNombreAlumno(nombreText.getText());
                oldValue.setComentario(comentarioText.getText());
            } catch (Exception e) {
                System.err.println("Error actualizando oldValue: " + e.getMessage());
            }
        }

        if (newValue != null) {
            idVisitaText.setText(String.valueOf(newValue.getIdVisita()));
            if (newValue.getFechaVisita() != null) {
                visitaDate.setValue(newValue.getFechaVisita().toLocalDate());
            } else {
                visitaDate.setValue(null);
            }
            nombreText.setText(newValue.getNombreAlumno());
            comentarioText.setText(newValue.getComentario());
        } else {
            idVisitaText.clear();
            visitaDate.setValue(null);
            nombreText.clear();
            comentarioText.clear();
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
        if (selectedVisita.get() != null) {
            Visitas visitaBorrada = selectedVisita.get();

            Borrar_Visitas borrador = new Borrar_Visitas();
            try {
                borrador.borrarVisitas(
                        visitaBorrada.getIdVisita()
                );
                System.out.println("Empresa borrado correctamente.");

                visitas.remove(visitaBorrada);

                idVisitaText.clear();
                nombreText.clear();
                visitaDate.setValue(null);
                comentarioText.clear();

            } catch (Exception e) {
                System.err.println("Error al borrar el empresa: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún empresa para borrar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarVisitaDialog dialog = new BuscarVisitaDialog();
        dialog.showAndWait().ifPresent(buscar -> {
            System.out.println(buscar.getIdVisita());
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedVisita.get() != null) {
            Visitas visitaActualizada = selectedVisita.get();

            visitaActualizada.setFechaVisita(Date.valueOf(visitaDate.getValue()));
            visitaActualizada.setNombreAlumno(nombreText.getText());
            visitaActualizada.setComentario(comentarioText.getText());

            Actualizar_Visitas actualizador = new Actualizar_Visitas();
            try {
                actualizador.actualizarVisitas(
                        visitaActualizada.getFechaVisita(),
                        visitaActualizada.getIdAlumno(),
                        visitaActualizada.getComentario(),
                        visitaActualizada.getIdVisita()
                );
                System.out.println("Visitas actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el visita: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún visita para actualizar.");
        }
    }
}
