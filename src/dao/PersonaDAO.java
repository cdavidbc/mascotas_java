package dao;

import util.Conexion;
import model.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public static String registrar(Persona p) {
        String sql = "INSERT INTO persona (documento, nombre, telefono) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getDocumento());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTelefono());

            int filas = ps.executeUpdate();
            return filas > 0 ? "Persona registrada correctamente." : "No se pudo registrar.";

        } catch (SQLException e) {
            return "Error al registrar persona: " + e.getMessage();
        }
    }

    public static String actualizar(Persona p) {
        String sql = "UPDATE persona SET nombre = ?, telefono = ? WHERE documento = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getDocumento());

            int filas = ps.executeUpdate();
            return filas > 0 ? "Persona actualizada correctamente." : "No se pudo actualizar.";

        } catch (SQLException e) {
            return "Error al actualizar persona: " + e.getMessage();
        }
    }

    public static String eliminar(String documento) {
        String sql = "DELETE FROM persona WHERE documento = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documento);
            int filas = ps.executeUpdate();
            return filas > 0 ? "Persona eliminada correctamente." : "No se pudo eliminar.";

        } catch (SQLException e) {
            return "Error al eliminar persona: " + e.getMessage();
        }
    }

    public static List<Persona> listar() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM persona";

        try (Connection conn = Conexion.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Persona p = new Persona(
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("telefono")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar personas: " + e.getMessage());
        }

        return lista;
    }
}