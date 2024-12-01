package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Practicas {

    public void registrarPractica(int nombreAlumno, int nombreTutorE) {
        String query = "INSERT INTO asignacion(IdAlumno, IdTutorE) VALUES(?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1,nombreAlumno);
            stmt.setInt(2,nombreTutorE);

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
