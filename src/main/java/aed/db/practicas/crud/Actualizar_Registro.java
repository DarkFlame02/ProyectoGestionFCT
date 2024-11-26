package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Registro {

    public void actualizarPracticas() {
        String query = "UPDATE asignacion SET IdAlumno = ?, IdTutorE = ? WHERE IdAlumno = ? AND IdTutorE = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1, 2); //IdAlumno Mod
            stmt.setInt(2, 2); //IdTutorE Mod
            stmt.setInt(3, 1); //IdAlumno OG
            stmt.setInt(4, 1); //IdTutorE OG

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
