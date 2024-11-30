package aed.db.comentarios.crud;

import aed.db.alumnos.Alumnos;
import aed.db.comentarios.Comentarios;
import aed.db.conexionHCP.ConexionHCP;
import aed.db.empresas.Empresas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buscar_Comentarios {

    public static ObservableList<Comentarios> buscarComentarios(int idEmpresa) {

        ObservableList<Comentarios> listaComentarios = FXCollections.observableArrayList();
        String query = "SELECT c.idEmpresa, e.nombreEmpresa, c.idTutor, c.comentario FROM comentarioscaptacion c JOIN empresa e ON e.idEmpresa=c.idEmpresa WHERE nombreEmpresa = ?;";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idEmpresa);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("idEmpresa");
                    String nombre = rs.getString("nombreEmpresa");
                    int idTutor = rs.getInt("idTutor");
                    String comentario = rs.getString("comentario");

                    Comentarios alumno = new Comentarios(id, nombre, idTutor, comentario);
                    listaComentarios.add(alumno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaComentarios;
    }
}