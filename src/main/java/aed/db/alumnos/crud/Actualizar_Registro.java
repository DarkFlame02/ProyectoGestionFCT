package aed.db.alumnos.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Actualizar_Registro {

    public void actualizarAlumnos() {
        String query = "UPDATE alumnos SET nombreAlumno = ?, apellidosAlumno = ?, cialAlumno = ?, cursoAlumno = ?, numSSAlumno = ?, IdTutor = ? WHERE ID = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1, "Pepe");
            stmt.setString(2,"Pérez");
            stmt.setString(3,"no tiene");
            stmt.setString(4,"2Dam");
            stmt.setString(5, "123456789012");
            stmt.setString(6, "1");

            int filasActuañizadas = stmt.executeUpdate();
            System.out.println(filasActuañizadas+" fila(s) actualizadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
