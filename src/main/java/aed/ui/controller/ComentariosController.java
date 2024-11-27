package aed.ui.controller;

import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.Comentarios_Empresa;
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
    }

    private void onComentarioChanged(ObservableValue<? extends Comentarios> o, Comentarios oldValue, Comentarios newValue) {
        if (oldValue != null) {
            oldValue.setNombreEmpresa(empresaText.getText());
            oldValue.setIdTutor(Integer.parseInt(tutorText.getText()));
            oldValue.setComentario(comentarioText.getText());

        }

        if (newValue != null) {
            empresaText.setText(newValue.getNombreEmpresa());
            tutorText.setText(String.valueOf(newValue.getIdTutor()));
            comentarioText.setText(newValue.getComentario());
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

    }

    @FXML
    void onUpdateAction(ActionEvent event) {

    }
}
