package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrar_Alumnos {

    public void borrarAlumnos() {
        String query = "DELETE FROM alumnos WHERE idAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1, 1);
            int filasEliminadas = stmt.executeUpdate();
            System.out.println(filasEliminadas+" fila(s) eliminada(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
