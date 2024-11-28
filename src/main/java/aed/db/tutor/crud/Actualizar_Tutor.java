package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Tutor{

    public void actualizarTutor(int id, String nombre, String apellidos, String email) {
        String query = "UPDATE tutor SET nombreTutor = ?, apellidosTutor = ?, emailTutor = ?  WHERE  IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)){


            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, email);
            stmt.setInt(4, id);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
