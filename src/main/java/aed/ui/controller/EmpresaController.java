package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.alumnos.crud.Nombre_Alumno;
import aed.db.empresas.Empresas;
import aed.db.empresas.crud.Nombre_Empresa;
import javafx.beans.Observable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    // model

    private final ObjectProperty<Empresas> empresa = new SimpleObjectProperty<>();
    private final ListProperty<Empresas> empresas = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    empresa -> new Observable[] { empresa.nombreEmpresaProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Empresas> selectedEmpresa = new SimpleObjectProperty<>();

    // view

    @FXML
    private TextField apellidosTutorEtext;

    @FXML
    private TextField direccionEmpresaText;

    @FXML
    private TextField emailTutorEtext;

    @FXML
    private ListView<Empresas> empresaList;

    @FXML
    private TextField nifText;

    @FXML
    private TextField nombreEmpresaText;

    @FXML
    private TextField nombreTutorEText;

    @FXML
    private SplitPane root;

    @FXML
    private TextField telefonoTutorEText;

    @FXML
    private TextField tipoEmpresaText;

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
        ObservableList<Empresas> empresasList = FXCollections.observableArrayList(Nombre_Empresa.listarEmpresas());

        empresas.set(empresasList);

        // bindings

        empresaList.itemsProperty().bind(empresas);

        empresaList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                empresa.set(newValue);
            }
        });

        selectedEmpresa.bind(empresaList.getSelectionModel().selectedItemProperty());
        empresa.addListener(this::onEmpresaChanged);
    }

    private void onEmpresaChanged(ObservableValue<? extends Empresas> o, Empresas oldValue, Empresas newValue) {
        if (oldValue != null) {
            oldValue.setNifEmpresa(nifText.getText());
            oldValue.setNombreEmpresa(nombreEmpresaText.getText());
            oldValue.setDireccionEmpresa(direccionEmpresaText.getText());
            oldValue.setTipoEmpresa(tipoEmpresaText.getText());
            oldValue.setNombreTutorE(nombreTutorEText.getText());
            oldValue.setApellidosTutorE(apellidosTutorEtext.getText());
            oldValue.setTelefonoContacto(telefonoTutorEText.getText());
            oldValue.setEmailTutorE(emailTutorEtext.getText());
        }

        if (newValue != null) {
            nifText.setText(newValue.getNifEmpresa());
            nombreEmpresaText.setText(newValue.getNombreEmpresa());
            direccionEmpresaText.setText(newValue.getDireccionEmpresa());
            tipoEmpresaText.setText(newValue.getTipoEmpresa());
            nombreTutorEText.setText(newValue.getNombreTutorE());
            apellidosTutorEtext.setText(newValue.getApellidosTutorE());
            telefonoTutorEText.setText(newValue.getTelefonoContacto());
            emailTutorEtext.setText(newValue.getEmailTutorE());
        }

    }

    public SplitPane getRoot() {
        return root;
    }
}
