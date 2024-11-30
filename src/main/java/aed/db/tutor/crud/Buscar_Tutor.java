package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;
import aed.db.practicas.Practicas;
import aed.db.tutor.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_Tutor {

    public static ObservableList<Tutor> buscarTutor(int idTutor) {

        ObservableList<Tutor> listaTutores = FXCollections.observableArrayList();
        String query = "SELECT idTutor, nombreTutor, apellidosTutor, emailTutor FROM tutor WHERE IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idTutor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idTutor");
                    String nombreTutor = rs.getString("nombreTutor");
                    String apellidosTutor = rs.getString("apellidosTutor");
                    String emailTutor = rs.getString("emailTutor");

                    Tutor tutores = new Tutor(id, nombreTutor, apellidosTutor, emailTutor);
                    listaTutores.add(tutores);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTutores;
    }
}