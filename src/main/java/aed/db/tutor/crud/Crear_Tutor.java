package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Tutor {

    public void registrarTutor() {
        String query = "INSERT INTO tutor(nombreTutor, apellidosTutor, emailTutor) VALUES(?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setString(1,"");
            stmt.setString(2,"");
            stmt.setString(3,"");

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}