package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.*;
import java.util.Date;

public class Actualizar_Visitas {

    public void actualizarVisitas() {
        String query = "UPDATE visitas SET idAlumno = ?, comentario = ?  WHERE  idVisita = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            String date = "2014-12-10";
            java.sql.Date sqlFecha = java.sql.Date.valueOf(date);

            stmt.setDate(1, sqlFecha);
            stmt.setInt(2, 1);
            stmt.setInt(3, 1);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
