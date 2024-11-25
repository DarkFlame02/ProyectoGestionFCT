package aed.db.alumnos.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leer_Registro {

    public void listarAlumnos() {
        String query = "SELECT * FROM alumnos";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String carrera = rs.getString("carrera");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Carrera: " + carrera);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}