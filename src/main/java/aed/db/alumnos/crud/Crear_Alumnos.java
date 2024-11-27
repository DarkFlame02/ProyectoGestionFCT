package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Alumnos {

    public void registrarAlumnos() {
        String query = "INSERT INTO alumnos(nombreAlumno, apellidosAlumno, cialAlumno, cursoAlumno, numSSAlumno, IdTutor) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "Pepe");
            stmt.setString(2,"Pérez");
            stmt.setString(3,"no tiene");
            stmt.setString(4,"2Dam");
            stmt.setString(5, "123456789012");
            stmt.setString(6, "1");

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
