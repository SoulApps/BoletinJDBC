package utils;

import com.mysql.jdbc.Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static JDBCUtils instancia = null;
    // Vars
    private static Connection conexion;

    // Constructor privado
    private JDBCUtils() {
    }

    // Singleton
    public static JDBCUtils getInstance() {
        if (instancia == null) {
            instancia = new JDBCUtils();
        }
        return instancia;
    }

    // Función que inicia una conexión con la BBDD
    public Connection startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/horario", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }

        return conexion;
    }

    // Método que cierra la conexión con la BBDD
    public void closeConnection() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar un script
    public void executeScript(String path) {
        ScriptRunner runner = new ScriptRunner(conexion, false, false);
        try {
            runner.runScript(new BufferedReader(new FileReader(path)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para consultar los datos de una tabla
    public void showTableData(String table) {
        boolean exists = false;
        ResultSet resultSet;

    }
}
