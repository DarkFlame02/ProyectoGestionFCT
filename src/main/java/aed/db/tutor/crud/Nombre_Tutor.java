package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;
import aed.db.tutor.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nombre_Tutor {

    public static ObservableList<Tutor> listarTutores() {

        ObservableList<Tutor> listaTutores = FXCollections.observableArrayList();
        String query = "SELECT nombreTutor, apellidosTutor, emailTutor FROM tutor;";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreTutor = rs.getString("nombreTutor");
                String apellidosTutor = rs.getString("apellidosTutor");
                String emailTutor = rs.getString("emailTutor");
                Tutor tutores = new Tutor(nombreTutor, apellidosTutor, emailTutor);
                listaTutores.add(tutores);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTutores;
    }
}