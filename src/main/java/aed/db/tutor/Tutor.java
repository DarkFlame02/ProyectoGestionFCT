package aed.db.tutor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tutor {
    private IntegerProperty IdTutor = new SimpleIntegerProperty();
    private StringProperty nombreTutor = new SimpleStringProperty();
    private StringProperty apellidosTutor = new SimpleStringProperty();
    private StringProperty emailTutor = new SimpleStringProperty();

    public Tutor() { }

    public Tutor(String nombreTutor, String apellidosTutor) {}

    public Tutor(IntegerProperty idTutor, StringProperty nombreTutor, StringProperty apellidosTutor, StringProperty emailTutor) {
        IdTutor = idTutor;
        this.nombreTutor = nombreTutor;
        this.apellidosTutor = apellidosTutor;
        this.emailTutor = emailTutor;
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

    public String getNombreTutor() {
        return nombreTutor.get();
    }

    public StringProperty nombreTutorProperty() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor.set(nombreTutor);
    }

    public String getApellidosTutor() {
        return apellidosTutor.get();
    }

    public StringProperty apellidosTutorProperty() {
        return apellidosTutor;
    }

    public void setApellidosTutor(String apellidosTutor) {
        this.apellidosTutor.set(apellidosTutor);
    }

    public String getEmailTutor() {
        return emailTutor.get();
    }

    public StringProperty emailTutorProperty() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor.set(emailTutor);
    }

    @Override
    public String toString() {
        return getNombreTutor() + " " + getApellidosTutor();
    }
}
