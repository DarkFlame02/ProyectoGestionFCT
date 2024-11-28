package aed.db.comentarios.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Comentarios {

    public void actualizarComentarios(int idEmpresa, int idTutor, String comentario) {
        String query = "UPDATE comentarioscaptacion SET comentario = ? WHERE IdTutor = ? AND IdEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, comentario);
            stmt.setInt(2, idTutor);
            stmt.setInt(3, idEmpresa);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
