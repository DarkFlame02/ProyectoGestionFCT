package aed.db.visitas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Borrar_Visitas {

    public void borrarVisitas() {
        String query = "DELETE FROM visitas WHERE idVisita = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            stmt.setInt(1, 1);

            int filasEliminadas = stmt.executeUpdate();
            System.out.println(filasEliminadas+" fila(s) eliminada(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
