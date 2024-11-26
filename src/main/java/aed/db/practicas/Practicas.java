package aed.db.practicas;

import javafx.beans.property.IntegerProperty;

public class Practicas {
    private IntegerProperty IdAlumno;
    private IntegerProperty IdTutorE;

    public Practicas(IntegerProperty idAlumno, IntegerProperty idTutorE) {
        IdAlumno = idAlumno;
        IdTutorE = idTutorE;
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

    public int getIdTutorE() {
        return IdTutorE.get();
    }

    public IntegerProperty idTutorEProperty() {
        return IdTutorE;
    }

    public void setIdTutorE(int idTutorE) {
        this.IdTutorE.set(idTutorE);
    }
}
