package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.alumnos.crud.Actualizar_Alumnos;
import aed.db.alumnos.crud.Borrar_Alumnos;
import aed.db.alumnos.crud.Buscar_Alumnos;
import aed.db.alumnos.crud.Crear_Alumnos;
import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.*;
import aed.ui.dialog.BuscarAlumnoDialog;
import aed.ui.dialog.BuscarComentariosDialog;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComentariosController implements Initializable {

    // model
    private Comentarios newComentario = new Comentarios();

    private final ObjectProperty<Comentarios> comentario = new SimpleObjectProperty<>();
    private final ListProperty<Comentarios> comentarios = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    comentario -> new Observable[] { comentario.nombreEmpresaProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Comentarios> selectedComentario = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextArea comentarioText;

    @FXML
    private TextField idEmpresaText;

    @FXML
    private ListView<Comentarios> comentarioList;

    @FXML
    private TextField empresaText;

    @FXML
    private BorderPane root;

    @FXML
    private TextField tutorText;

    public ComentariosController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ComentariosView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Comentarios> comentariosList = FXCollections.observableArrayList(Comentarios_Empresa.listarComentarios());

        comentarios.set(comentariosList);

        // bindings
        comentarioList.itemsProperty().bind(comentarios);
        comentarioList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                comentario.set(newValue);
            }
        });

        selectedComentario.bind(comentarioList.getSelectionModel().selectedItemProperty());
        comentario.addListener(this::onComentarioChanged);

        empresaText.setDisable(true);
    }


    private void onComentarioChanged(ObservableValue<? extends Comentarios> o, Comentarios oldValue, Comentarios newValue) {
        if (oldValue != null) {
            try {
                if (!idEmpresaText.getText().isEmpty()) {
                    oldValue.setIdEmpresa(Integer.parseInt(idEmpresaText.getText()));
                }
                oldValue.setNombreEmpresa(empresaText.getText());
                if (!tutorText.getText().isEmpty()) {
                    oldValue.setIdTutor(Integer.parseInt(tutorText.getText()));
                }
                oldValue.setComentario(comentarioText.getText());
            } catch (NumberFormatException e) {
                System.err.println("Error al actualizar oldValue: " + e.getMessage());
            }
        }

        if (newValue != null) {
            idEmpresaText.setText(String.valueOf(newValue.getIdEmpresa()));
            empresaText.setText(newValue.getNombreEmpresa());
            tutorText.setText(String.valueOf(newValue.getIdTutor()));
            comentarioText.setText(newValue.getComentario());
        } else {
            idEmpresaText.clear();
            empresaText.clear();
            tutorText.clear();
            comentarioText.clear();
        }
    }



    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onNewAction(ActionEvent event) {

        newComentario.setIdEmpresa(1);
        newComentario.setIdTutor(101);
        newComentario.setComentario("");

        comentarios.add(newComentario);
    }

    @FXML
    void onAddAction(ActionEvent event) {
        if (comentarioText.getText().isEmpty() || idEmpresaText.getText().isEmpty() ||
                 tutorText.getText().isEmpty()) {

            System.out.println("Todos los campos deben estar completos.");
            return;
        }

        Comentarios nuevoComentario = new Comentarios();
        nuevoComentario.setIdEmpresa(Integer.parseInt(idEmpresaText.getText()));
        nuevoComentario.setNombreEmpresa("Nueva Empresa");
        nuevoComentario.setIdTutor(Integer.parseInt(tutorText.getText()));
        nuevoComentario.setComentario(comentarioText.getText());

        Crear_Comentarios creador = new Crear_Comentarios();
        try {
            creador.registrarComentarios(
                    nuevoComentario.getIdEmpresa(),
                    nuevoComentario.getIdTutor(),
                    nuevoComentario.getComentario()
            );
            System.out.println("Alumno agregado correctamente.");

            comentarios.add(nuevoComentario);
            comentarios.remove(newComentario);

        } catch (Exception e) {
            System.err.println("Error al agregar el alumno: " + e.getMessage());
        }
    }

    @FXML
    void onCloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        if (selectedComentario.get() != null) {
            Comentarios comentarioBorrado = selectedComentario.get();

            Borrar_Comentarios borrador = new Borrar_Comentarios();
            try {
                borrador.borrarComentario(
                        comentarioBorrado.getIdEmpresa()
                );
                System.out.println("Comentario borrado correctamente.");

                comentarios.remove(comentarioBorrado);

                idEmpresaText.clear();
                empresaText.clear();
                tutorText.clear();
                comentarioText.clear();

            } catch (Exception e) {
                System.err.println("Error al borrar el comentario: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún comentario para borrar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarComentariosDialog dialog = new BuscarComentariosDialog();
        dialog.showAndWait().ifPresent(buscar -> {

            int idEmpresa = buscar.getIdEmpresa();
            ObservableList<Comentarios> resultado = Buscar_Comentarios.buscarComentarios(idEmpresa);

            if (!resultado.isEmpty()) {
                Comentarios comentarioEncontrado = resultado.get(0);
                System.out.println("Comentario encontrado: " + comentarioEncontrado.getIdEmpresa());

                comentarios.clear();
                comentarios.addAll(resultado);
                comentarioList.getSelectionModel().select(comentarioEncontrado);

            } else {
                System.out.println("No se encontró un comentario con ID: " + idEmpresa);
            }
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedComentario.get() != null) {
            Comentarios comentarioActualizado = selectedComentario.get();

            comentarioActualizado.setComentario(comentarioText.getText());


            Actualizar_Comentarios actualizador = new Actualizar_Comentarios();
            try {
                actualizador.actualizarComentarios(
                        comentarioActualizado.getIdEmpresa(),
                        comentarioActualizado.getIdTutor(),
                        comentarioActualizado.getComentario()

                );
                System.out.println("Comentario actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el comentario: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún comentario para actualizar.");
        }
    }
}
