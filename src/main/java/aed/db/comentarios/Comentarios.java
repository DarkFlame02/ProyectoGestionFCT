package aed.db.comentarios;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comentarios {
    private StringProperty nombreEmpresa = new SimpleStringProperty();
    private IntegerProperty idTutor = new SimpleIntegerProperty();
    private StringProperty comentario = new SimpleStringProperty();

    public Comentarios() {

    }

    public Comentarios(StringProperty nombreEmpresa, IntegerProperty idTutor, StringProperty comentario) {
        this.nombreEmpresa = nombreEmpresa;
        this.idTutor = idTutor;
        this.comentario = comentario;
    }

    public Comentarios(String nombreEmpresa, int idTutor, String comentarios) {
        this.nombreEmpresa.set(nombreEmpresa);
        this.idTutor.set(idTutor);
        this.comentario.set(comentarios);
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
        return getNombreEmpresa();
    }
}
