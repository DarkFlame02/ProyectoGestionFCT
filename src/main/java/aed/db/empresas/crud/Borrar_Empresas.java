package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrar_Empresas {

    public void borrarEmpresa(int idEmpresa) {
        String deleteEmpresa = "DELETE FROM empresa WHERE idEmpresa = ?";
        String deleteTutorEmpresa = "DELETE FROM tutorempresa WHERE idEmpresa = ?";
        String deleteAsignacion = "DELETE FROM asignacion WHERE idTutorE IN (SELECT idTutorE FROM tutorempresa WHERE idEmpresa = ?)";
        String deleteComentariosCaptacion = "DELETE FROM comentarioscaptacion WHERE idEmpresa = ?";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmtComentariosCaptacion = conn.prepareStatement(deleteComentariosCaptacion);
             PreparedStatement stmtAsignacion = conn.prepareStatement(deleteAsignacion);
             PreparedStatement stmtTutorEmpresa = conn.prepareStatement(deleteTutorEmpresa);
             PreparedStatement stmtEmpresa = conn.prepareStatement(deleteEmpresa)) {

            // Eliminar en la tabla comentarioscaptacion
            stmtComentariosCaptacion.setInt(1, idEmpresa);
            int filasEliminadasComentarios = stmtComentariosCaptacion.executeUpdate();
            System.out.println(filasEliminadasComentarios + " fila(s) eliminada(s) de la tabla comentarioscaptacion.");

            // Eliminar en la tabla asignacion
            stmtAsignacion.setInt(1, idEmpresa);
            int filasEliminadasAsignacion = stmtAsignacion.executeUpdate();
            System.out.println(filasEliminadasAsignacion + " fila(s) eliminada(s) de la tabla asignacion.");

            // Eliminar en la tabla tutorempresa
            stmtTutorEmpresa.setInt(1, idEmpresa);
            int filasEliminadasTutorEmpresa = stmtTutorEmpresa.executeUpdate();
            System.out.println(filasEliminadasTutorEmpresa + " fila(s) eliminada(s) de la tabla tutorempresa.");

            // Eliminar en la tabla empresa
            stmtEmpresa.setInt(1, idEmpresa);
            int filasEliminadasEmpresa = stmtEmpresa.executeUpdate();
            System.out.println(filasEliminadasEmpresa + " fila(s) eliminada(s) de la tabla empresa.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
