package aed.ui.controller;

import aed.db.empresas.Empresas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ComentariosController implements Initializable {

    // view

    @FXML
    private TextArea comentarioText;

    @FXML
    private ListView<Empresas> empresaList;

    @FXML
    private TextField empresaText;

    @FXML
    private SplitPane root;

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

    }

    public SplitPane getRoot() {
        return root;
    }
}
