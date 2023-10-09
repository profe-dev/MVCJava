package modelo;

import modelo.Conexion;
import modelo.Contacto;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactoModelo {
    public void agregarContacto(String nombre, String telefono, String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try  {
            conn = Conexion.getConnection();
            String sql = "INSERT INTO contactos (nombre, telefono, email) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, telefono);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {/*el bloque finally siempre se ejecutara por lo que se usara para cerrar los objetos abiertos*/
            try {
                pstmt.close();
		        conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void actualizarContacto(int id,String nombre, String telefono, String email) {
       Connection conn = null;
        PreparedStatement pstmt = null;
        try  {
            conn= Conexion.getConnection();
            String sql = "UPDATE contactos SET nombre = ?, telefono = ?, email = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, telefono);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void eliminarContacto(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try  {
            conn = Conexion.getConnection();
            String sql = "DELETE FROM contactos WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public List<Contacto> listarContactos() {
        List<Contacto> contactos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try  {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM contactos";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Contacto contacto = new Contacto();
                contacto.setId(rs.getInt("id"));
                contacto.setNombre(rs.getString("nombre"));
                contacto.setTelefono(rs.getString("telefono"));
                contacto.setEmail(rs.getString("email"));//este era el problema por el cual no se completaban el campo email
                contactos.add(contacto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                Conexion.close(rs);//cierre de la ejecucion de la query
                Conexion.close(pstmt);//cierre de la sentencia sql
                Conexion.close(conn);//cierre de la conexion con la base de datos
            } catch (SQLException e) {
               e.printStackTrace(System.out);
            }
        }
        return contactos;
    }
}
