package utils;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static JDBCUtils instancia = null;
    // Vars
    private static Connection conexion;

    // Constructor privado
    private JDBCUtils(){}

    // Singleton
    public static JDBCUtils getInstance() {
        if (instancia == null) {
            instancia = new JDBCUtils();
        }
        return instancia;
    }

    // Función que inicia una conexión con la BBDD.
    public Connection startConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }

        return conexion;
    }

}
