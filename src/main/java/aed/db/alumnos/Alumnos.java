package aed.db.alumnos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Alumnos {
    private IntegerProperty IdAlumno = new SimpleIntegerProperty();
    private StringProperty nombreAlumno = new SimpleStringProperty();
    private StringProperty apellidosAlumno = new SimpleStringProperty();
    private StringProperty cialAlumno = new SimpleStringProperty();
    private StringProperty cursoAlumno = new SimpleStringProperty();
    private StringProperty numSSAlumno = new SimpleStringProperty();
    private IntegerProperty IdTutor = new SimpleIntegerProperty();

    public Alumnos(IntegerProperty idAlumno, StringProperty nombreAlumno, StringProperty apellidosAlumno, StringProperty cialAlumno, StringProperty cursoAlumno, StringProperty numSSAlumno, IntegerProperty idTutor) {
        this.IdAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.apellidosAlumno = apellidosAlumno;
        this.cialAlumno = cialAlumno;
        this.cursoAlumno = cursoAlumno;
        this.numSSAlumno = numSSAlumno;
        this.IdTutor = idTutor;
    }

    public Alumnos() {}

    public Alumnos(String nombre, String apellidos) {
    }

    public int getIdAlumno() {
        return IdAlumno.get();
    }

    public IntegerProperty idAlumnoProperty() {
        return IdAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.IdAlumno.set(idAlumno);
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

    public String getApellidosAlumno() {
        return apellidosAlumno.get();
    }

    public StringProperty apellidosAlumnoProperty() {
        return apellidosAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno.set(apellidosAlumno);
    }

    public String getCialAlumno() {
        return cialAlumno.get();
    }

    public StringProperty cialAlumnoProperty() {
        return cialAlumno;
    }

    public void setCialAlumno(String cialAlumno) {
        this.cialAlumno.set(cialAlumno);
    }

    public String getCursoAlumno() {
        return cursoAlumno.get();
    }

    public StringProperty cursoAlumnoProperty() {
        return cursoAlumno;
    }

    public void setCursoAlumno(String cursoAlumno) {
        this.cursoAlumno.set(cursoAlumno);
    }

    public String getNumSSAlumno() {
        return numSSAlumno.get();
    }

    public StringProperty numSSAlumnoProperty() {
        return numSSAlumno;
    }

    public void setNumSSAlumno(String numSSAlumno) {
        this.numSSAlumno.set(numSSAlumno);
    }

    public int getIdTutor() {
        return IdTutor.get();
    }

    public IntegerProperty idTutorProperty() {
        return IdTutor;
    }

    public void setIdTutor(int idTutor) {
        this.IdTutor.set(idTutor);
    }

    @Override
    public String toString() {
        return getNombreAlumno() + " " + getApellidosAlumno();
    }
}
