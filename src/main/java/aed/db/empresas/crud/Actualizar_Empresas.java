package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Empresas {

    public void actualizarEmpresas() {
        String query = "UPDATE empresa AS e JOIN tutorempresa AS t ON e.IdEmpresa = t.idEmpresa SET e.nifEmpresa = ?, e.nombreEmpresa = ?, e.direccionEmpresa = ?, e.tipoEmpresa = ?, t.nombreTutorE = ?, t.apellidosTutorE = ?, t.telefonoContacto = ?, t.emailTutorE = ? WHERE e.IdEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "123456789009");
            stmt.setString(2, "PepeSL");
            stmt.setString(3, "en tus sueños");
            stmt.setString(4, "SL");
            stmt.setString(5, "Pepe");
            stmt.setString(6, "Pérez");
            stmt.setString(7, "123456789");
            stmt.setString(8, "Pp@gmail.com");
            stmt.setInt(9, 1);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
