package aed.ui.controller;

import aed.db.alumnos.Alumnos;
import aed.db.alumnos.crud.*;
import aed.ui.dialog.BuscarAlumnoDialog;
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

public class AlumnoController implements Initializable {

    //model
    private Alumnos newAlumno = new Alumnos();

    private final ObjectProperty<Alumnos> alumno = new SimpleObjectProperty<>();
    private final ListProperty<Alumnos> alumnos = new SimpleListProperty<>(
            FXCollections.observableArrayList(
                    alumno -> new Observable[] { alumno.nombreAlumnoProperty() } // indicamos que properties de cada bean son observables dentro de la lista
            )
    );
    private final ObjectProperty<Alumnos> selectedAlumno = new SimpleObjectProperty<>();

    // view

    @FXML
    private ListView<Alumnos> alumnoList;

    @FXML
    private TextField apellidosText;

    @FXML
    private TextField cialText;

    @FXML
    private TextField cursoText;

    @FXML
    private TextField idText;

    @FXML
    private TextField idTutorText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField numssText;

    @FXML
    private BorderPane root;

    public AlumnoController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Alumnos> alumnosList = FXCollections.observableArrayList(Nombre_Alumno.listarAlumnos());

        alumnos.set(alumnosList);

        // bindings

        alumnoList.itemsProperty().bind(alumnos);

        alumnoList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                alumno.set(newValue);
            }
        });

        selectedAlumno.bind(alumnoList.getSelectionModel().selectedItemProperty());
        alumno.addListener(this::onAlumnoChanged);

        idText.setDisable(true);

    }

    private void onAlumnoChanged(ObservableValue<? extends Alumnos> o, Alumnos oldValue, Alumnos newValue) {
        if (oldValue != null) {
            oldValue.setIdAlumno(idText.getText().isEmpty() ? 0 : Integer.parseInt(idText.getText()));
            oldValue.setNombreAlumno(nombreText.getText() != null && !nombreText.getText().isEmpty() ? nombreText.getText() : "");
            oldValue.setApellidosAlumno(apellidosText.getText() != null && !apellidosText.getText().isEmpty() ? apellidosText.getText() : "");
            oldValue.setCialAlumno(cialText.getText() != null && !cialText.getText().isEmpty() ? cialText.getText() : "");
            oldValue.setCursoAlumno(cursoText.getText() != null && !cursoText.getText().isEmpty() ? cursoText.getText() : "");
            oldValue.setNumSSAlumno(numssText.getText() != null && !numssText.getText().isEmpty() ? numssText.getText() : "");
            oldValue.setIdTutor(idTutorText.getText().isEmpty() ? 0 : Integer.parseInt(idTutorText.getText()));
        }

        if (newValue != null) {
            idText.setText(String.valueOf(newValue.getIdAlumno()));
            nombreText.setText(newValue.getNombreAlumno());
            apellidosText.setText(newValue.getApellidosAlumno());
            cialText.setText(newValue.getCialAlumno());
            cursoText.setText(newValue.getCursoAlumno());
            numssText.setText(newValue.getNumSSAlumno());
            idTutorText.setText(String.valueOf(newValue.getIdTutor()));
        }
    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onAddAction(ActionEvent event) {

        if (nombreText.getText().isEmpty() || apellidosText.getText().isEmpty() ||
                cialText.getText().isEmpty() || cursoText.getText().isEmpty() ||
                numssText.getText().isEmpty()) {

            System.out.println("Todos los campos deben estar completos.");
            return;
        }

        Alumnos nuevoAlumno = new Alumnos();
        nuevoAlumno.setNombreAlumno(nombreText.getText());
        nuevoAlumno.setApellidosAlumno(apellidosText.getText());
        nuevoAlumno.setCialAlumno(cialText.getText());
        nuevoAlumno.setCursoAlumno(cursoText.getText());
        nuevoAlumno.setNumSSAlumno(numssText.getText());
        nuevoAlumno.setIdTutor(Integer.parseInt(idTutorText.getText()));

        Crear_Alumnos creador = new Crear_Alumnos();
        try {
            creador.registrarAlumnos(
                    nuevoAlumno.getNombreAlumno(),
                    nuevoAlumno.getApellidosAlumno(),
                    nuevoAlumno.getCialAlumno(),
                    nuevoAlumno.getCursoAlumno(),
                    nuevoAlumno.getNumSSAlumno(),
                    nuevoAlumno.getIdTutor()
            );
            System.out.println("Alumno agregado correctamente.");

            alumnos.add(nuevoAlumno);
            alumnos.remove(newAlumno); //Está hecho un poco a lo matao.

            nombreText.clear();
            apellidosText.clear();
            cialText.clear();
            cursoText.clear();
            numssText.clear();
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
        if (selectedAlumno.get() != null) {
            Alumnos alumnoBorrado = selectedAlumno.get();

            alumnoBorrado.setCialAlumno(cialText.getText());

            Borrar_Alumnos borrador = new Borrar_Alumnos();
            try {
                borrador.borrarAlumnos(
                        alumnoBorrado.getCialAlumno()
                );
                System.out.println("Alumno borrado correctamente.");

                alumnos.remove(alumnoBorrado);

                idText.clear();
                nombreText.clear();
                apellidosText.clear();
                cialText.clear();
                cursoText.clear();
                numssText.clear();
                idTutorText.clear();

            } catch (Exception e) {
                System.err.println("Error al borrar el alumno: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún alumno para borrar.");
        }
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        if (selectedAlumno.get() != null) {
            Alumnos alumnoActualizado = selectedAlumno.get();

            alumnoActualizado.setIdAlumno(Integer.parseInt(idText.getText()));
            alumnoActualizado.setNombreAlumno(nombreText.getText());
            alumnoActualizado.setApellidosAlumno(apellidosText.getText());
            alumnoActualizado.setCialAlumno(cialText.getText());
            alumnoActualizado.setCursoAlumno(cursoText.getText());
            alumnoActualizado.setNumSSAlumno(numssText.getText());
            alumnoActualizado.setIdTutor(Integer.parseInt(idTutorText.getText()));

            Actualizar_Alumnos actualizador = new Actualizar_Alumnos();
            try {
                actualizador.actualizarAlumnos(
                        alumnoActualizado.getIdAlumno(),
                        alumnoActualizado.getNombreAlumno(),
                        alumnoActualizado.getApellidosAlumno(),
                        alumnoActualizado.getCialAlumno(),
                        alumnoActualizado.getCursoAlumno(),
                        alumnoActualizado.getNumSSAlumno(),
                        alumnoActualizado.getIdTutor()
                );
                System.out.println("Alumno actualizado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al actualizar el alumno: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha seleccionado ningún alumno para actualizar.");
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        BuscarAlumnoDialog dialog = new BuscarAlumnoDialog();
        dialog.showAndWait().ifPresent(buscar -> {

            int idAlumno = buscar.getIdAlumno();
            ObservableList<Alumnos> resultado = Buscar_Alumnos.buscarAlumnos(idAlumno);

            if (!resultado.isEmpty()) {
                Alumnos alumnoEncontrado = resultado.get(0);
                System.out.println("Alumno encontrado: " + alumnoEncontrado.getNombreAlumno());

                alumnos.clear();
                alumnos.addAll(resultado);
                alumnoList.getSelectionModel().select(alumnoEncontrado);

            } else {
                System.out.println("No se encontró un alumno con ID: " + idAlumno);
            }
        });
    }


    @FXML
    void onNewAction(ActionEvent event) {

        newAlumno.setNombreAlumno("Nuevo");
        newAlumno.setApellidosAlumno("Alumno");
        newAlumno.setCialAlumno("");
        newAlumno.setCursoAlumno("");
        newAlumno.setNumSSAlumno("");
        newAlumno.setIdTutor(101);

        alumnos.add(newAlumno);
    }

}
