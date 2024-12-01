package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Empresas {

    public void registrarEmpresa(String nifEmpresa, String nombreEmpresa, String direccionEmpresa, String tipoEmpresa,
                                 String nombreTutorE, String apellidosTutorE, String telefonoContacto, String emailTutorE) {

        String insertEmpresaQuery = "INSERT INTO empresa (nifEmpresa, nombreEmpresa, direccionEmpresa, tipoEmpresa) VALUES (?, ?, ?, ?)";
        String insertTutorQuery = "INSERT INTO tutorempresa (nombreTutorE, apellidosTutorE, telefonoContacto, emailTutorE, idEmpresa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionHCP.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement empresaStmt = conn.prepareStatement(insertEmpresaQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

                empresaStmt.setString(1, nifEmpresa);
                empresaStmt.setString(2, nombreEmpresa);
                empresaStmt.setString(3, direccionEmpresa);
                empresaStmt.setString(4, tipoEmpresa);
                empresaStmt.executeUpdate();

                ResultSet generatedKeys = empresaStmt.getGeneratedKeys();
                if (generatedKeys.next()) {

                    int empresaId = generatedKeys.getInt(1);

                    try (PreparedStatement tutorStmt = conn.prepareStatement(insertTutorQuery)) {
                        tutorStmt.setString(1, nombreTutorE);
                        tutorStmt.setString(2, apellidosTutorE);
                        tutorStmt.setString(3, telefonoContacto);
                        tutorStmt.setString(4, emailTutorE);
                        tutorStmt.setInt(5, empresaId);
                        tutorStmt.executeUpdate();
                    }
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para la empresa.");
                }

                conn.commit();
                
            } catch (SQLException e) {
                System.err.println("Error al registrar la empresa: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar la empresa: " + e.getMessage());
        }
    }
}
