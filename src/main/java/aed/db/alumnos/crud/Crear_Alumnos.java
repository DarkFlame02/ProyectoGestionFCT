package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Alumnos {

    public void registrarAlumnos(String nombreAlumno, String apellidosAlumno, String cialAlumno, String cursoAlumno, String numSSAlumno, int idTutor) {
        String query = "INSERT INTO alumnos(nombreAlumno, apellidosAlumno, cialAlumno, cursoAlumno, numSSAlumno, idTutor) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreAlumno);
            stmt.setString(2,apellidosAlumno);
            stmt.setString(3,cialAlumno);
            stmt.setString(4, cursoAlumno);
            stmt.setString(5, numSSAlumno);
            stmt.setInt(6, idTutor);

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
