import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoModelo {
    public void agregarContacto(String nombre, String telefono, String email) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "INSERT INTO contactos (nombre, telefono, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, telefono);
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarContacto(int id,String nombre, String telefono, String email) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "UPDATE contactos SET nombre = ?, telefono = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, telefono);
            statement.setString(3, email);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarContacto(int id) {
        try (Connection conn = Conexion.getConnection()) {
            String sql = "DELETE FROM contactos WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contacto> listarContactos() {
        List<Contacto> contactos = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            String sql = "SELECT * FROM contactos";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contacto contacto = new Contacto();
                contacto.setId(resultSet.getInt("id"));
                contacto.setNombre(resultSet.getString("nombre"));
                contacto.setTelefono(resultSet.getString("telefono"));
                contacto.setTelefono(resultSet.getString("email"));
                contactos.add(contacto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }
}
