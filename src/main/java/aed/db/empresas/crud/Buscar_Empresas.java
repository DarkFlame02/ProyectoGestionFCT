package aed.db.empresas.crud;

import aed.db.comentarios.Comentarios;
import aed.db.conexionHCP.ConexionHCP;
import aed.db.empresas.Empresas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_Empresas {

    public static ObservableList<Empresas> buscarEmpresa(int idEmpresa) {

        ObservableList<Empresas> listaEmpresas = FXCollections.observableArrayList();
        String query = "SELECT e.idEmpresa, e.nifEmpresa , e.nombreEmpresa, e.direccionEmpresa, e.tipoEmpresa, " +
                "t.nombreTutorE, t.apellidosTutorE, t.telefonoContacto, t.emailTutorE FROM empresa e JOIN tutorempresa t " +
                "on e.idEmpresa=t.idEmpresa WHERE e.idEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEmpresa);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idEmpresa");
                    String nifEmpresa = rs.getString("nifEmpresa");
                    String nombreEmpresa = rs.getString("nombreEmpresa");
                    String direccionEmpresa = rs.getString("direccionEmpresa");
                    String tipoEmpresa = rs.getString("tipoEmpresa");
                    String nombreTutorE = rs.getString("nombreTutorE");
                    String apellidosTutorE = rs.getString("apellidosTutorE");
                    String telefonoContacto = rs.getString("telefonoContacto");
                    String emailTutorE = rs.getString("emailTutorE");

                    Empresas empresa = new Empresas(id, nifEmpresa, nombreEmpresa, direccionEmpresa, tipoEmpresa, nombreTutorE, apellidosTutorE, telefonoContacto, emailTutorE);
                    listaEmpresas.add(empresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpresas;
    }
}