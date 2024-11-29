package aed.db.tutor.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Borrar_Tutor {

    public void borrarTutor(int idTutor) {
        String queryDeleteComentarios = "DELETE FROM comentarioscaptacion WHERE idTutor = ?";
        String queryDeleteVisitas = "DELETE FROM visitas WHERE idAlumno IN (SELECT idAlumno FROM alumnos WHERE idTutor = ?)";
        String queryDeleteTutor = "DELETE FROM tutor WHERE IdTutor = ?";

        try (Connection conn = ConexionHCP.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtComentarios = conn.prepareStatement(queryDeleteComentarios);
                 PreparedStatement stmtVisitas = conn.prepareStatement(queryDeleteVisitas);
                 PreparedStatement stmtTutor = conn.prepareStatement(queryDeleteTutor)) {

                // Eliminar comentarios relacionados
                stmtComentarios.setInt(1, idTutor);
                int filasComentariosEliminadas = stmtComentarios.executeUpdate();
                System.out.println(filasComentariosEliminadas + " comentario(s) eliminado(s).");

                // Eliminar visitas relacionadas
                stmtVisitas.setInt(1, idTutor);
                int filasVisitasEliminadas = stmtVisitas.executeUpdate();
                System.out.println(filasVisitasEliminadas + " visita(s) eliminada(s).");

                // Eliminar tutor
                stmtTutor.setInt(1, idTutor);
                int filasTutorEliminadas = stmtTutor.executeUpdate();
                System.out.println(filasTutorEliminadas + " tutor(es) eliminado(s).");

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
