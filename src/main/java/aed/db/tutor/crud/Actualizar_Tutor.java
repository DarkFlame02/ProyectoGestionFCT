package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Tutor{

    public void actualizarTutor() {
        String query = "UPDATE tutor SET nombreTutor = ?, apellidosTutor = ?, emailTutor = ?  WHERE  IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "");
            stmt.setString(2, "");
            stmt.setString(3, "");
            stmt.setInt(4, 1);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
