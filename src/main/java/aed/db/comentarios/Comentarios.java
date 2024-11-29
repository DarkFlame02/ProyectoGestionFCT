package aed.db.comentarios;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comentarios {
    private IntegerProperty idEmpresa = new SimpleIntegerProperty();
    private StringProperty nombreEmpresa = new SimpleStringProperty();
    private IntegerProperty idTutor = new SimpleIntegerProperty();
    private StringProperty comentario = new SimpleStringProperty();

    public Comentarios() {

    }

    public Comentarios(IntegerProperty idEmpresa, StringProperty nombreEmpresa, IntegerProperty idTutor, StringProperty comentario) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.idTutor = idTutor;
        this.comentario = comentario;
    }

    public Comentarios(int idEmpresa, String nombreEmpresa, int idTutor, String comentarios) {
        this.idEmpresa.set(idEmpresa);
        this.nombreEmpresa.set(nombreEmpresa);
        this.idTutor.set(idTutor);
        this.comentario.set(comentarios);
    }

    public Integer getIdEmpresa() {
        return idEmpresa.get();
    }

    public IntegerProperty idEmpresaProperty() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa.set(idEmpresa);
    }

    public String getNombreEmpresa() {
        return nombreEmpresa.get();
    }

    public StringProperty nombreEmpresaProperty() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa.set(nombreEmpresa);
    }

    public int getIdTutor() {
        return idTutor.get();
    }

    public IntegerProperty idTutorProperty() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor.set(idTutor);
    }

    public String getComentario() {
        return comentario.get();
    }

    public StringProperty comentarioProperty() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario.set(comentario);
    }

    @Override
    public String toString() {
        return getIdEmpresa() + " - " + getNombreEmpresa();
    }
}
