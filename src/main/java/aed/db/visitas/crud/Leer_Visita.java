package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.*;

public class Leer_Visita {

    public void listarVisita() {
        String query = "SELECT * FROM visitas WHERE IdVisita = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1,1);

            while (rs.next()) {
                int IdVisita = rs.getInt("IdVisita");
                Date fechaVisita = rs.getDate("fechaVisita");
                int IdAlumno = rs.getInt("idAlumno");
                String comentario = rs.getString("comentario");

                System.out.println("ID Visita: " + IdVisita + ", \nFecha Visita: " + fechaVisita + "ID Alumno: " + IdAlumno + ", \nComentario: " + comentario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}