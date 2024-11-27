package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Tutor {

    public void listarTutor() {
        String query = "SELECT * FROM tutor WHERE IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1,1);

            while (rs.next()) {
                int IdTutor = rs.getInt("IdTutor");
                String nombreTutor = rs.getString("nombreTutor");
                String apellidosTutor = rs.getString("apellidosTutor");
                String emailTutor = rs.getString("emailTutor");

                System.out.println("ID Tutor: " + IdTutor + ", \nNombre Tutor: " + nombreTutor + "Apellidos: " + apellidosTutor + ", \n Email: " + emailTutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}