package aed.ui.dialog;

import aed.db.practicas.Practicas;
import aed.db.visitas.Visitas;
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

public class BuscarVisitaDialog  extends Dialog<Visitas> implements Initializable {
    // model

    private final StringProperty idVisita = new SimpleStringProperty();

    // view

    @FXML
    private TextField idText;

    @FXML
    private GridPane root;

    public BuscarVisitaDialog() {
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

        setTitle("Buscar visita");
        getDialogPane().setContent(root);
        getDialogPane().getButtonTypes().setAll(
                new ButtonType("Buscar", ButtonBar.ButtonData.OK_DONE),
                ButtonType.CANCEL
        );
        setResultConverter(this::onReSult);

        // bindings

        idVisita.bind(idText.textProperty());

    }

    private Visitas onReSult(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            Visitas visitas = new Visitas();
            visitas.setIdVisita(Integer.parseInt(idVisita.get()));
            return visitas;
        }
        return null;
    }
}

