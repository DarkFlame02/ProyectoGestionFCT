package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Empresas {

    public void actualizarEmpresas(String nif, String nombre, String direccion, String tipo, String nombreTutorE, String apellidosTutorE, String telefono, String emailTutorE, int idEmpresa) {
        String query = "UPDATE empresa AS e JOIN tutorempresa AS t ON e.IdEmpresa = t.idEmpresa " +
                "SET e.nifEmpresa = ?, e.nombreEmpresa = ?, e.direccionEmpresa = ?, e.tipoEmpresa = ?, " +
                "t.nombreTutorE = ?, t.apellidosTutorE = ?, t.telefonoContacto = ?, t.emailTutorE = ? WHERE e.IdEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nif);
            stmt.setString(2, nombre);
            stmt.setString(3, direccion);
            stmt.setString(4, tipo);
            stmt.setString(5, nombreTutorE);
            stmt.setString(6, apellidosTutorE);
            stmt.setString(7, telefono);
            stmt.setString(8, emailTutorE);
            stmt.setInt(9, idEmpresa);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
