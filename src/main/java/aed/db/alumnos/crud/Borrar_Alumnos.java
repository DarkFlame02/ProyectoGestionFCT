package aed.db.alumnos.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrar_Alumnos {

    public void borrarAlumnos(String cial) {
        // Queries para eliminar registros relacionados y el alumno
        String deleteVisitas = "DELETE FROM visitas WHERE idAlumno = (SELECT idAlumno FROM alumnos WHERE cialAlumno = ?)";
        String deleteAsignacion = "DELETE FROM asignacion WHERE idAlumno = (SELECT idAlumno FROM alumnos WHERE cialAlumno = ?)";
        String deleteAlumno = "DELETE FROM alumnos WHERE cialAlumno = ?";

        try (Connection conn = ConexionHCP.getConnection()) {
            conn.setAutoCommit(false); // Inicia la transacción

            // Eliminar registros relacionados en 'visitas'
            try (PreparedStatement stmtVisitas = conn.prepareStatement(deleteVisitas)) {
                stmtVisitas.setString(1, cial);
                int filasVisitas = stmtVisitas.executeUpdate();
                System.out.println(filasVisitas + " registro(s) eliminado(s) en visitas.");
            }

            // Eliminar registros relacionados en 'asignacion'
            try (PreparedStatement stmtAsignacion = conn.prepareStatement(deleteAsignacion)) {
                stmtAsignacion.setString(1, cial);
                int filasAsignacion = stmtAsignacion.executeUpdate();
                System.out.println(filasAsignacion + " registro(s) eliminado(s) en asignacion.");
            }

            // Eliminar el registro en 'alumnos'
            try (PreparedStatement stmtAlumno = conn.prepareStatement(deleteAlumno)) {
                stmtAlumno.setString(1, cial);
                int filasAlumno = stmtAlumno.executeUpdate();
                System.out.println(filasAlumno + " registro(s) eliminado(s) en alumnos.");
            }

            conn.commit(); // Confirma la transacción
        } catch (SQLException e) {
            System.err.println("Error al eliminar el alumno con CIAL: " + cial);
            e.printStackTrace();
        }
    }
}
