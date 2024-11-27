package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Alumnos {

    public void actualizarAlumnos() {
        String query = "UPDATE alumnos SET nombreAlumno = ?, apellidosAlumno = ?, cialAlumno = ?, cursoAlumno = ?, numSSAlumno = ?, IdTutor = ? WHERE idAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "Pepe");
            stmt.setString(2,"Pérez");
            stmt.setString(3,"no tiene");
            stmt.setString(4,"2Dam");
            stmt.setString(5, "123456789012");
            stmt.setString(6, "1");
            stmt.setInt(7, 1);

            int filasActualizadas = stmt.executeUpdate();
            System.out.println(filasActualizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
