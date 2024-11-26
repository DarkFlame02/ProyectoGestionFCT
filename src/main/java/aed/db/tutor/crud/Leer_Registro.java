package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Registro {

    public void listarPractica() {
        String query = "SELECT * FROM asignacion WHERE IdAlumno = ? AND IdTutorE = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1,1);
            stmt.setInt(2,1);

            while (rs.next()) {
                int IdAlumno = rs.getInt("IdAlumno");
                int IdTutorE = rs.getInt("IdTutorE");

                System.out.println("ID Alumno: " + IdAlumno + ", \nID Tutor Empresa: " + IdTutorE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}