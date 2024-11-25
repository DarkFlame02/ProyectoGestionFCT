package aed.ui.controller;

import aed.db.empresas.Empresas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmpresaController implements Initializable {

    // view

    @FXML
    private TextField direccionText;

    @FXML
    private ListView<Empresas> empresaList;

    @FXML
    private TextField nifText;

    @FXML
    private TextField nombreText;

    @FXML
    private SplitPane root;

    @FXML
    private TextField tipoText;

    public EmpresaController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EmpresaView.fxml"));
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
