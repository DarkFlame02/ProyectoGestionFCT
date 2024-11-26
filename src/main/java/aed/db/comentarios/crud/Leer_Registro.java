package aed.db.comentarios.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Registro {

    public void listarComentarios() {
        String query = "SELECT * FROM comentarioscaptacion WHERE IdEmpresa = ? AND IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1,1);
            stmt.setInt(2,1);

            while (rs.next()) {
                int idEmpresa = rs.getInt("idEmpresa");
                int idTutor = rs.getInt("idTutor");
                String comentario = rs.getString("comentario");

                System.out.println("ID Empresa: " + idEmpresa + ", \nID Tutor: " + idTutor + ", \nComentario: " + comentario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}