package aed.db.alumnos.crud;

import aed.db.alumnos.Alumnos;
import aed.db.conexionHCP.ConexionHCP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nombre_Alumno {

    public static ObservableList<Alumnos> listarAlumnos() {

        ObservableList<Alumnos> listaAlumnos = FXCollections.observableArrayList();
        String query = "SELECT idAlumno, nombreAlumno, apellidosAlumno, cialAlumno, cursoAlumno, numSSAlumno, idTutor FROM alumnos";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                String nombre = rs.getString("nombreAlumno");
                String apellidos = rs.getString("apellidosAlumno");
                String cial = rs.getString("cialAlumno");
                String curso = rs.getString("cursoAlumno");
                String numSS = rs.getString("numSSAlumno");
                int idTutor = rs.getInt("idTutor");
                Alumnos alumno = new Alumnos(idAlumno, nombre, apellidos, cial, curso, numSS, idTutor);
                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }
}
