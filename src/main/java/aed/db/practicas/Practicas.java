package aed.db.practicas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Practicas {
    private IntegerProperty idAlumno = new SimpleIntegerProperty();
    private IntegerProperty idTutorE = new SimpleIntegerProperty();
    private StringProperty nombreAlumno = new SimpleStringProperty();
    private StringProperty nombreTutorE = new SimpleStringProperty();

    public Practicas(){

    }

    public Practicas(IntegerProperty idAlumno, IntegerProperty idTutorE) {
        idAlumno = idAlumno;
        idTutorE = idTutorE;
    }

    public Practicas(String nombreAlumno, int idAlumno,  String nombreTutorE, int idTutorE) {
        this.nombreAlumno.set(nombreAlumno);
        this.idAlumno.set(idAlumno);
        this.idTutorE.set(idTutorE);
        this.nombreTutorE.set(nombreTutorE);
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

    public String getNombreTutorE() {
        return nombreTutorE.get();
    }

    public StringProperty nombreTutorEProperty() {
        return nombreTutorE;
    }

    public void setNombreTutorE(String nombreTutorE) {
        this.nombreTutorE.set(nombreTutorE);
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

    public int getIdTutorE() {
        return idTutorE.get();
    }

    public IntegerProperty idTutorEProperty() {
        return idTutorE;
    }

    public void setIdTutorE(int idTutorE) {
        this.idTutorE.set(idTutorE);
    }

    @Override
    public String toString() {
        return getIdAlumno() + " - " + getNombreAlumno();
    }
}
