/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sebas
 */
public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3307/sistema_registro";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection AbrirConexion() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex.getMessage());
            return null;
        }
    }

    public static void CerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar conexion: " + ex.getMessage());
            }
        }
    }
}
