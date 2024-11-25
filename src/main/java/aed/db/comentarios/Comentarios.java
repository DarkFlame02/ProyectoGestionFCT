package aed.db.comentarios;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Comentarios {
    private final IntegerProperty idEmpresa;
    private final IntegerProperty idTutor;
    private final StringProperty comentario;

    public Comentarios(IntegerProperty idEmpresa, IntegerProperty idTutor, StringProperty comentario) {
        this.idEmpresa = idEmpresa;
        this.idTutor = idTutor;
        this.comentario = comentario;
    }

    public int getIdEmpresa() {
        return idEmpresa.get();
    }

    public IntegerProperty idEmpresaProperty() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa.set(idEmpresa);
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
}
