package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.*;

public class Crear_Visita {

    public void registrarVisitas(Date fecha, int id, String comentario) {
        String query = "INSERT INTO visitas(fechaVisita, idAlumno, comentario) VALUES(?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, fecha);
            stmt.setInt(2,id);
            stmt.setString(3,comentario);

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
