package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Visita {

    public void registrarVisitas() {
        String query = "INSERT INTO visitas(fechaVisita, idAlumno, comentario) VALUES(?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            String date = "2014-12-10";
            java.sql.Date sqlFecha = java.sql.Date.valueOf(date);

            stmt.setDate(1, sqlFecha);
            stmt.setInt(2,1);
            stmt.setString(3,"");

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
