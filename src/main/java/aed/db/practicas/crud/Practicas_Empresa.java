package aed.db.practicas.crud;

import aed.db.conexionHCP.ConexionHCP;
import aed.db.practicas.Practicas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Practicas_Empresa {

    public static ObservableList<Practicas> listarPracticas() {

        ObservableList<Practicas> listaPracticas = FXCollections.observableArrayList();
        String query = "SELECT al.nombreAlumno, a.idAlumno, t.nombreTutorE, a.idTutorE FROM asignacion a JOIN alumnos al ON a.idAlumno=al.idAlumno JOIN tutorempresa t ON a.idTutorE=t.idTutorE;";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreAlumno = rs.getString("nombreAlumno");
                int idAlumno = rs.getInt("idAlumno");
                String nombreTutorE = rs.getString("nombreTutorE");
                int idTutorE = rs.getInt("idTutorE");
                Practicas practicas = new Practicas(nombreAlumno, idAlumno, nombreTutorE, idTutorE);
                listaPracticas.add(practicas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPracticas;
    }
}
