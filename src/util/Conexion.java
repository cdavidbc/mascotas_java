package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/veterinaria";
    private static final String USER = "root";
    private static final String PASSWORD = "esme2015"; // ← Cambia por tu contraseña real

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegura que el driver esté cargado
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: driver MySQL no encontrado.");
        } catch (SQLException e) {
            System.out.println("Error de conexión a BD: " + e.getMessage());
        }
        return null;
    }
}
