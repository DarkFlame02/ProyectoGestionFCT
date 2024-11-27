package aed.db.comentarios.crud;

import aed.db.comentarios.Comentarios;
import aed.db.conexionHCP.ConexionHCP;
import aed.db.empresas.Empresas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comentarios_Empresa {

    public static ObservableList<Comentarios> listarComentarios() {

        ObservableList<Comentarios> listaComentarios = FXCollections.observableArrayList();
        String query = "SELECT e.nombreEmpresa, c.idTutor, c.comentario FROM comentarioscaptacion c JOIN empresa e ON e.idEmpresa=c.idEmpresa;";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombreEmpresa = rs.getString("nombreEmpresa");
                int idTutor = rs.getInt("idTutor");
                String comentarios = rs.getString("comentario");
                Comentarios comentario = new Comentarios(nombreEmpresa, idTutor, comentarios);
                listaComentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaComentarios;
    }
}

