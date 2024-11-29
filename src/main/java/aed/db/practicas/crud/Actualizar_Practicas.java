package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Practicas {

    public void actualizarPracticas(int idTutorE, int idAlumno) {
        String query = "UPDATE asignacion SET idTutorE = ? WHERE idAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idTutorE);
            stmt.setInt(2, idAlumno);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
