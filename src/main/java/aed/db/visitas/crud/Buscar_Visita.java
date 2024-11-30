package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;
import aed.db.tutor.Tutor;
import aed.db.visitas.Visitas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Buscar_Visita {

    public static ObservableList<Visitas> buscarVisita(int idVisita) {

        ObservableList<Visitas> listaVisitas = FXCollections.observableArrayList();
        String query = "SELECT idVisita, fechaVisita, a.idAlumno, a.nombreAlumno, comentario FROM visitas v JOIN alumnos a ON v.idAlumno=a.idAlumno WHERE idVisita = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idVisita);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date fechaVisita = rs.getDate("fechaVisita");
                    int idAlumno = rs.getInt("idAlumno");
                    String nombreAlumno = rs.getString("nombreAlumno");
                    String comentario = rs.getString("comentario");
                    int id = rs.getInt("idVisita");
                    Visitas visitas = new Visitas(fechaVisita, idAlumno, nombreAlumno, comentario, id);
                    listaVisitas.add(visitas);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVisitas;

    }
}