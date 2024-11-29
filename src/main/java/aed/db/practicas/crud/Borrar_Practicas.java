package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrar_Practicas {

    public void borrarPractica(int idAlumno) {
        String query = "DELETE FROM asignacion WHERE IdAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idAlumno);
            stmt.setInt(2, 1);

            int filasEliminadas = stmt.executeUpdate();
            System.out.println(filasEliminadas+" fila(s) eliminada(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
