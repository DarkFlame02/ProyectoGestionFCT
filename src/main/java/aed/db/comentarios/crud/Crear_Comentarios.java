package aed.db.comentarios.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Comentarios {

    public void registrarComentarios(Integer idEmpresa, int idTutor, String comentario) {
        String query = "INSERT INTO comentarioscaptacion(IdEmpresa, IdTutor, comentario) VALUES(?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEmpresa);
            stmt.setInt(2,idTutor);
            stmt.setString(3,comentario);

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
