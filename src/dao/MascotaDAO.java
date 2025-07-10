package dao;

import util.Conexion;
import model.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    public static String registrar(Mascota m) {
        String sql = "INSERT INTO mascota (codigo, nombre, tipo, edad, documento_duenio) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getTipo());
            ps.setInt(4, m.getEdad());
            ps.setString(5, m.getDocumentoDuenio());

            int filas = ps.executeUpdate();
            return filas > 0 ? "Mascota registrada correctamente." : "No se pudo registrar.";

        } catch (SQLException e) {
            return "Error al registrar mascota: " + e.getMessage();
        }
    }

    public static String actualizar(Mascota m) {
        String sql = "UPDATE mascota SET nombre = ?, tipo = ?, edad = ?, documento_duenio = ? WHERE codigo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setInt(3, m.getEdad());
            ps.setString(4, m.getDocumentoDuenio());
            ps.setString(5, m.getCodigo());

            int filas = ps.executeUpdate();
            return filas > 0 ? "Mascota actualizada correctamente." : "No se pudo actualizar.";

        } catch (SQLException e) {
            return "Error al actualizar mascota: " + e.getMessage();
        }
    }

    public static String eliminar(String codigo) {
        String sql = "DELETE FROM mascota WHERE codigo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filas = ps.executeUpdate();
            return filas > 0 ? "Mascota eliminada correctamente." : "No se pudo eliminar.";

        } catch (SQLException e) {
            return "Error al eliminar mascota: " + e.getMessage();
        }
    }

    public static List<Mascota> listar() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota";

        try (Connection conn = Conexion.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Mascota m = new Mascota(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("edad"),
                        rs.getString("documento_duenio")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mascotas: " + e.getMessage());
        }

        return lista;
    }
}
