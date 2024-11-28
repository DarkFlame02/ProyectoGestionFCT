package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Alumnos {
    public void actualizarAlumnos(int idAlumno, String nombre, String apellidos, String cial, String curso, String numSS, int idTutor) {
        String query = "UPDATE alumnos SET nombreAlumno = ?, apellidosAlumno = ?, cialAlumno = ?, cursoAlumno = ?, numSSAlumno = ?, IdTutor = ? WHERE idAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Configurar los valores en el PreparedStatement
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setString(3, cial);
            stmt.setString(4, curso);
            stmt.setString(5, numSS);
            stmt.setInt(6, idTutor);
            stmt.setInt(7, idAlumno);

            // Ejecutar la actualización
            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas + " fila(s) actualizada(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

