package aed.db.visitas;

import javafx.beans.property.*;

import java.sql.Date;

public class Visitas {
    private IntegerProperty idVisita = new SimpleIntegerProperty();
    private ObjectProperty<Date> fechaVisita = new SimpleObjectProperty<>();
    private IntegerProperty idAlumno = new SimpleIntegerProperty();
    private StringProperty comentario = new SimpleStringProperty();
    private StringProperty nombreAlumno = new SimpleStringProperty();

    public Visitas() {}

    public Visitas(Date fechaVisita, int idAlumno,String nombreAlumno, String comentario, int idVisita) {
        this.fechaVisita.set(fechaVisita);
        this.idAlumno.set(idAlumno);
        this.nombreAlumno.set(nombreAlumno);
        this.comentario.set(comentario);
        this.idVisita.set(idVisita);
    }

    public Visitas(IntegerProperty idVisita, ObjectProperty<Date> fechaVisita, IntegerProperty idAlumno, StringProperty comentario, StringProperty nombreAlumno) {
        this.idVisita = idVisita;
        this.fechaVisita = fechaVisita;
        this.idAlumno = idAlumno;
        this.comentario = comentario;
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdVisita() {
        return idVisita.get();
    }

    public IntegerProperty idVisitaProperty() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita.set(idVisita);
    }

    public Date getFechaVisita() {
        return fechaVisita.get();
    }

    public ObjectProperty<Date> fechaVisitaProperty() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita.set(fechaVisita);
    }

    public int getIdAlumno() {
        return idAlumno.get();
    }

    public IntegerProperty idAlumnoProperty() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno.set(idAlumno);
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

    public String getNombreAlumno() {
        return nombreAlumno.get();
    }

    public StringProperty nombreAlumnoProperty() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno.set(nombreAlumno);
    }

    @Override
    public String toString() {
        return getFechaVisita() + " -- " + getNombreAlumno();
    }
}
