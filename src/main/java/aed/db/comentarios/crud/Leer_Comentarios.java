package aed.db.comentarios.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Comentarios {

    public void listarComentarios() {
        String query = "SELECT * FROM comentarioscaptacion WHERE nombreEmpresa = ? AND IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1,"");
            stmt.setInt(2,1);

            while (rs.next()) {
                String nombreEmpresa = rs.getString("nombreEmpresa");
                int idTutor = rs.getInt("idTutor");
                String comentario = rs.getString("comentario");

                System.out.println("Nombre Empresa: " + nombreEmpresa + ", \nID Tutor: " + idTutor + ", \nComentario: " + comentario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}