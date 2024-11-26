package aed.db.empresas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Empresas {

    //Empresa
    private  IntegerProperty IdEmpresa = new SimpleIntegerProperty();
    private  StringProperty nifEmpresa = new SimpleStringProperty();
    private  StringProperty nombreEmpresa = new SimpleStringProperty();
    private  StringProperty direccionEmpresa = new SimpleStringProperty();
    //TutorEmpresa
    private IntegerProperty IdTutorE = new SimpleIntegerProperty();
    private StringProperty nombreTutorE = new SimpleStringProperty();
    private StringProperty apellidosTutorE = new SimpleStringProperty();
    private StringProperty telefonoContacto = new SimpleStringProperty();
    private StringProperty emailTutorE = new SimpleStringProperty();

    public Empresas() {

    }

    public Empresas(IntegerProperty idEmpresa, StringProperty nifEmpresa, StringProperty nombreEmpresa, StringProperty direccionEmpresa, IntegerProperty idTutorE, StringProperty nombreTutorE, StringProperty apellidosTutorE, StringProperty telefonoContacto, StringProperty emailTutorE) {
        IdEmpresa = idEmpresa;
        this.nifEmpresa = nifEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        IdTutorE = idTutorE;
        this.nombreTutorE = nombreTutorE;
        this.apellidosTutorE = apellidosTutorE;
        this.telefonoContacto = telefonoContacto;
        this.emailTutorE = emailTutorE;
    }

    //Empresas

    public int getIdEmpresa() {
        return IdEmpresa.get();
    }

    public IntegerProperty idEmpresaProperty() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.IdEmpresa.set(idEmpresa);
    }

    public String getNifEmpresa() {
        return nifEmpresa.get();
    }

    public StringProperty nifEmpresaProperty() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa.set(nifEmpresa);
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

    public String getDireccionEmpresa() {
        return direccionEmpresa.get();
    }

    public StringProperty direccionEmpresaProperty() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa.set(direccionEmpresa);
    }


    //TutorEmpresa


    public int getIdTutorE() {
        return IdTutorE.get();
    }

    public IntegerProperty idTutorEProperty() {
        return IdTutorE;
    }

    public void setIdTutorE(int idTutorE) {
        this.IdTutorE.set(idTutorE);
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

    public String getApellidosTutorE() {
        return apellidosTutorE.get();
    }

    public StringProperty apellidosTutorEProperty() {
        return apellidosTutorE;
    }

    public void setApellidosTutorE(String apellidosTutorE) {
        this.apellidosTutorE.set(apellidosTutorE);
    }

    public String getTelefonoContacto() {
        return telefonoContacto.get();
    }

    public StringProperty telefonoContactoProperty() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto.set(telefonoContacto);
    }

    public String getEmailTutorE() {
        return emailTutorE.get();
    }

    public StringProperty emailTutorEProperty() {
        return emailTutorE;
    }

    public void setEmailTutorE(String emailTutorE) {
        this.emailTutorE.set(emailTutorE);
    }
}
