package aed.db.comentarios.crud;

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
                int id = rs.getInt("idAlumno");
                String nombre = rs.getString("nombreAlumno");
                String apellidos = rs.getString("apellidosAlumno");
                String cial = rs.getString("cialAlumno");
                String curso = rs.getString("cursoAlumno");
                String numSS = rs.getString("numSSAlumno");
                String IdTutor = rs.getString("IdTutor");

                System.out.println("ID: " + id + ", \nNombre: " + nombre + ", \nApellidos: " + apellidos + ", \nCial: " + cial + ", \nCurso: " + curso + ", \nNúmeroSS: " + numSS + ", \nIdTutor: " + IdTutor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}