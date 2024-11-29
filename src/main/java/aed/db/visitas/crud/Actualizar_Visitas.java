package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.*;

public class Actualizar_Visitas {

    public void actualizarVisitas(Date fecha, int idAlumno, String comentario, int idVisita) {
        String query = "UPDATE visitas SET fechaVisita = ?, idAlumno = ?, comentario = ?  WHERE  idVisita = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){

            java.sql.Date sqlFecha = java.sql.Date.valueOf(fecha.toLocalDate());

            stmt.setDate(1, sqlFecha);
            stmt.setInt(2, idAlumno);
            stmt.setString(3, comentario);
            stmt.setInt(4, idVisita);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
