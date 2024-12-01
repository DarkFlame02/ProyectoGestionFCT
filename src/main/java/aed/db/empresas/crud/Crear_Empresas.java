package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Empresas {

    public void registrarEmpresa(String nifEmpresa, String nombreEmpresa, String direccionEmpresa, String tipoEmpresa,
                                 String nombreTutorE, String apellidosTutorE, String telefonoContacto, String emailTutorE) {
        String query = "NSERT INTO empresa (nifEmpresa, nombreEmpresa, direccionEmpresa, tipoEmpresa) VALUES (?, ?, ?, ?); SET @lastId = LAST_INSERT_ID(); INSERT INTO TutorE_Primaria (nombreTutorE, apellidosTutorE, telefonoContacto, emailTutorE, idEmpresa) VALUES (?, ?, ?, ?, @lastId);";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nifEmpresa);
            stmt.setString(2, nombreEmpresa);
            stmt.setString(3, direccionEmpresa);
            stmt.setString(4, tipoEmpresa);
            stmt.setString(5, nombreTutorE);
            stmt.setString(6, apellidosTutorE);
            stmt.setString(7, telefonoContacto);
            stmt.setString(8, emailTutorE);

            stmt.executeUpdate();
            System.out.println("Registro insertado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al registrar la empresa: " + e.getMessage());
        }
    }

}
