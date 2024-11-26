package aed.db.empresas.crud;

import aed.db.conexionHCP.ConexionHCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crear_Registro {

    public void registrarEmpresa() {
        String query = "INSERT INTO empresa (nifEmpresa, nombreEmpresa, direccionEmpresa, tipoEmpresa) VALUES (?, ?, ?, ?); SET @lastId = LAST_INSERT_ID(); INSERT INTO TutorE_Primaria (nombreTutorE, apellidosTutorE, telefonoContacto, emailTutorE, idEmpresa) VALUES (?, ?, ?, ?, @lastId);\n";

        try (Connection conn = ConexionHCP.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            //Empresa
            stmt.setString(1,"Empresa1");
            stmt.setString(2,"Empresa1SL");
            stmt.setString(3,"no tiene");
            stmt.setString(4,"SA");
            //tutorEmpresa
            stmt.setString(5,"Pepe");
            stmt.setString(6,"Pérez");
            stmt.setString(7,"123456789");
            stmt.setString(8,"Pp@gmail.com");

            int filasCreadas = stmt.executeUpdate();
            System.out.println(filasCreadas+" fila(s) creadas(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
