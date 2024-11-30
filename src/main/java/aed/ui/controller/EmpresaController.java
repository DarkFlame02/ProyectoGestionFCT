package aed.ui.controller;

import aed.db.comentarios.Comentarios;
import aed.db.comentarios.crud.Borrar_Comentarios;
import aed.db.comentarios.crud.Buscar_Comentarios;
import aed.db.empresas.Empresas;
import aed.db.empresas.crud.Actualizar_Empresas;
import aed.db.empresas.crud.Borrar_Empresas;
import aed.db.empresas.crud.Buscar_Empresas;
import aed.db.empresas.crud.Nombre_Empresa;
import aed.ui.dialog.BuscarComentariosDialog;
import aed.ui.dialog.BuscarEmpresaDialog;
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
    private TextField idEmpresaText;

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
    private BorderPane root;

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
            try {
                if (!idEmpresaText.getText().isEmpty()) {
                    oldValue.setIdEmpresa(Integer.parseInt(idEmpresaText.getText()));
                }
                oldValue.setNifEmpresa(nifText.getText());
                oldValue.setNombreEmpresa(nombreEmpresaText.getText());
                oldValue.setDireccionEmpresa(direccionEmpresaText.getText());
                oldValue.setTipoEmpresa(tipoEmpresaText.getText());
                oldValue.setNombreTutorE(nombreTutorEText.getText());
                oldValue.setApellidosTutorE(apellidosTutorEtext.getText());
                oldValue.setTelefonoContacto(telefonoTutorEText.getText());
                oldValue.setEmailTutorE(emailTutorEtext.getText());
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir campos numéricos: " + e.getMessage());
            }
        }

        if (newValue != null) {
            idEmpresaText.setText(String.valueOf(newValue.getIdEmpresa()));
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
        if (selectedEmpresa.get() != null) {
            Empresas empresaBorrada = selectedEmpresa.get();

            Borrar_Empresas borrador = new Borrar_Empresas();
            try {
                borrador.borrarEmpresa(
                        empresaBorrada.getIdEmpresa()
                );
                System.out.println("Empresa borrado correctamente.");

                empresas.remove(empresaBorrada);

                idEmpresaText.clear();
                nombreEmpresaText.clear();
                nifText.clear();
                direccionEmpresaText.clear();
                tipoEmpresaText.clear();
                nombreTutorEText.clear();
                apellidosTutorEtext.clear();
                telefonoTutorEText.clear();
                emailTutorEtext.clear();

            } catch (Exception e) {
                System.err.println("Error al borrar el empresa: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún empresa para borrar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarEmpresaDialog dialog = new BuscarEmpresaDialog();
        dialog.showAndWait().ifPresent(buscar -> {

            int idEmpresa = buscar.getIdEmpresa();
            ObservableList<Empresas> resultado = Buscar_Empresas.buscarEmpresa(idEmpresa);

            if (!resultado.isEmpty()) {
                Empresas empresaEncontrada = resultado.get(0);
                System.out.println("Comentario encontrado: " + empresaEncontrada.getIdEmpresa());

                empresas.clear();
                empresas.addAll(resultado);
                empresaList.getSelectionModel().select(empresaEncontrada);

            } else {
                System.out.println("No se encontró un comentario con ID: " + idEmpresa);
            }
        });
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedEmpresa.get() != null) {
            Empresas empresaActualizada = selectedEmpresa.get();

            empresaActualizada.setNifEmpresa(nifText.getText());
            empresaActualizada.setNombreEmpresa(nombreEmpresaText.getText());
            empresaActualizada.setDireccionEmpresa(direccionEmpresaText.getText());
            empresaActualizada.setTipoEmpresa(tipoEmpresaText.getText());
            empresaActualizada.setNombreTutorE(nombreTutorEText.getText());
            empresaActualizada.setApellidosTutorE(apellidosTutorEtext.getText());
            empresaActualizada.setTelefonoContacto(telefonoTutorEText.getText());
            empresaActualizada.setEmailTutorE(emailTutorEtext.getText());

            Actualizar_Empresas actualizador = new Actualizar_Empresas();
            try {
                actualizador.actualizarEmpresas(
                        empresaActualizada.getNifEmpresa(),
                        empresaActualizada.getNombreEmpresa(),
                        empresaActualizada.getDireccionEmpresa(),
                        empresaActualizada.getTipoEmpresa(),
                        empresaActualizada.getNombreTutorE(),
                        empresaActualizada.getApellidosTutorE(),
                        empresaActualizada.getTelefonoContacto(),
                        empresaActualizada.getEmailTutorE(),
                        empresaActualizada.getIdEmpresa()

                );
                System.out.println("Empresa actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el empresa: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún empresa para actualizar.");
        }

    }

}
