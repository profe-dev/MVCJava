package modelo;

import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/contactosdb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    //sobrecarga de metodos para el cierre de las conexiones
    //aca tenemos 2 opcines controlar la excepcion en la firma del metodo o mediante una sentecia trycatch
    public static void close(ResultSet rs)  {
        try {
            rs.close();
        } catch (SQLException e) {
           e.printStackTrace(System.out);
        }
    }

    //en este caso se controlo el error en la firma del metodo
    public static void close(Statement stmt) throws SQLException {//control de las excepciones en la firma el metodo
        stmt.close();
    }

    //metodo para cerrar los objetos de tipo PreparedStatement
    public static void close(PreparedStatement psmtm) throws SQLException{//control de las excepciones en la firma del metodo
	    psmtm.close();
    }

     //ahora cerramos la conexion
    public static void close(Connection conn) throws SQLException{
	    conn.close();
    }
}
