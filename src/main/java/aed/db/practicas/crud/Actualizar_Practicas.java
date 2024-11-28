package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Practicas {

    public void actualizarPracticas(int idAlumno, int idTutorE, int idAlumnoOld, int idTutorEOld) {
        String query = "UPDATE asignacion SET idAlumno = ?, idTutorE = ? WHERE idAlumno = ? AND idTutorE = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setInt(1, idAlumno); //IdAlumno Mod
            stmt.setInt(2, idTutorE); //IdTutorE Mod
            stmt.setInt(3, idAlumnoOld); //IdAlumno OG
            stmt.setInt(4, idTutorEOld); //IdTutorE OG

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
