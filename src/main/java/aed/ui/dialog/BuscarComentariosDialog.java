package aed.ui.dialog;

import aed.db.comentarios.Comentarios;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuscarComentariosDialog extends Dialog<Comentarios> implements Initializable {
    // model

    private final StringProperty idComentarios = new SimpleStringProperty();

    // view

    @FXML
    private TextField idText;

    @FXML
    private GridPane root;

    public BuscarComentariosDialog() {
        super();
        // load view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BuscarDialogView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // init dialog

        setTitle("Buscar comentario");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(
                new ButtonType("Buscar", ButtonBar.ButtonData.OK_DONE),
                ButtonType.CANCEL
        );
        setResultConverter(this::onReSult);

        // bindings

        idComentarios.bind(idText.textProperty());

    }

    private Comentarios onReSult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Comentarios comentarios = new Comentarios();
            comentarios.setIdEmpresa(Integer.parseInt(idComentarios.get()));
            return comentarios;
        }
        return null;
    }
}