package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Registro {

    public void listarEmpresa() {
        String query = "SELECT * FROM empresa e JOIN tutorempresa t ON e.IdEmpresa = t.idEmpresa WHERE IdEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1,1);

            while (rs.next()) {
                int idEmpresa = rs.getInt("idEmpresa");
                String nifEmpresa = rs.getString("nifEmpresa");
                String nombreEmpresa = rs.getString("nombreEmpresa");
                String direccionEmpresa = rs.getString("direccionEmpresa");
                String tipoEmpresa = rs.getString("tipoEmpresa");

                int idTutorE = rs.getInt("idTutorE");
                String nombreTutorE = rs.getString("nombreTutorE");
                String apellidosTutorE = rs.getString("apellidosTutorE");
                String telefonoContacto = rs.getString("telefonoContacto");
                String emailTutorE = rs.getString("emailTutorE");

                System.out.println("ID Empresa: " + idEmpresa + ", \nNIF: " + nifEmpresa + ", \nNombre: " + nombreEmpresa+ ", \nDirección: " + direccionEmpresa+ ", \nTipo: " + tipoEmpresa +
                        "ID Tutor Empresa: " + idTutorE + ", \nNombre Tutor: " + nombreTutorE + ", \nApellidos Tutor: " + apellidosTutorE+ ", \nTelefono Tutor: " + telefonoContacto+ ", \nEmail: " + emailTutorE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}