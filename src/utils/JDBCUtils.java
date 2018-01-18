package utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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

    // Método que comprueba si la tabla existe
    public boolean checkTable(String table) {
        boolean exists = false;
        String[] tipos = {"TABLE"};
        DatabaseMetaData dbmd;
        ResultSet result;
        try {
            dbmd = (DatabaseMetaData) conexion.getMetaData();
            result = dbmd.getTables(null, "horario", null, tipos);
            while (result.next())
                if (result.getString("TABLE_NAME").equals(table))
                    exists = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Método para consultar los datos de una tabla
    public void showTableData(String table) {
        DatabaseMetaData dbmd;
        ResultSet result = null;
        try {
            dbmd = (DatabaseMetaData) conexion.getMetaData();
            if (checkTable(table)) {
                // Información de la tabla
                System.out.println("DATOS DE LA TABLA");
                result = dbmd.getColumns(null, "horario", table, null);
                while (result.next())
                    System.out.printf("[Columna: %s] [Tipo: %s] [Tamaño: %s] [Null: %s]%n", result.getString("COLUMN_NAME"), result.getString("TYPE_NAME"), result.getString("COLUMN_SIZE"), result.getString("IS_NULLABLE"));

                System.out.println();

                // Claves primarias
                System.out.println("CLAVES PRIMARIAS");
                result = dbmd.getPrimaryKeys(null, "horario", table);
                while (result.next())
                    System.out.printf("Clave primaria: %s%n", result.getString("COLUMN_NAME"));


                // Claves ajenas exportadas
                System.out.println("\n CLAVES EXPORTADAS");
                result = dbmd.getExportedKeys(null, "horario", table);
                while (result.next())
                    System.out.printf("[Clave exportada: %s] [Clave primaria: %s] [Tabla origen:%s] [Tabla destino: %s]%n",
                            result.getString("FKCOLUMN_NAME"), result.getString("PKCOLUMN_NAME"), result.getString("PKTABLE_NAME"),
                            result.getString("FKTABLE_NAME"));

                // Claves ajenas importadas
                System.out.println("\n CLAVES IMPORTADAS");
                result = dbmd.getImportedKeys(null, "horario", table);
                while (result.next())
                    System.out.printf("[Clave exportada: %s] [Clave primaria: %s] [Tabla origen:%s] [Tabla destino: %s]%n",
                            result.getString("FKCOLUMN_NAME"), result.getString("PKCOLUMN_NAME"), result.getString("PKTABLE_NAME"),
                            result.getString("FKTABLE_NAME"));

            } else {
                System.out.println("No existe esa tabla");
            }
            // Close all
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ------------------- [ SENTENCIAS ] -------------------
    // Método para insertar datos
    public int insertData(String sql) {
        int result = 0;
        try {
            Statement sentence = conexion.createStatement();
            result = sentence.executeUpdate(sql);
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para insertar sentencias preparadas
    public int insertPreparedStatement(String sql, String table, ArrayList<Object> datos) {
        int result = 0;
        try {
            PreparedStatement sentence = conexion.prepareStatement(sql);
            switch (table){
                // Recogemos los datos en función de la tabla a la que queramos insertar
                case "curso":
                    sentence.setString(1, (String) datos.get(0));
                    sentence.setString(2, (String) datos.get(1));
                    sentence.setString(3, (String) datos.get(2));
                    break;
                case "asignatura":
                    sentence.setString(1, (String) datos.get(0));
                    sentence.setString(2, (String) datos.get(1));
                    sentence.setInt(3, (int) datos.get(2));
                    sentence.setInt(4, (int) datos.get(3));
                    break;
            }
            // Insertamos los datos en la tabla
            sentence.executeUpdate();
            // Cerramos la sentencia
            sentence.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para actualizar una tabla 
    public int updateTable(String sql){
        int result = 0;

        return result;
    }
    // ------------------- [ SENTENCIAS ] -------------------
}
