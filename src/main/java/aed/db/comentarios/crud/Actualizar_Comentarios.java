package aed.db.comentarios.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Comentarios {

    public void actualizarComentarios() {
        String query = "UPDATE comentarioscaptacion SET comentario = ? WHERE IdTutor = ? AND IdEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "Mu bien");
            stmt.setInt(2, 1);
            stmt.setInt(3, 1);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
