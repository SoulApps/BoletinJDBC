package EJ7;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /*7. Mostrar todos los datos de los profesores ordenados por:
         a) Apellidos en orden ascendente.
         b) Fecha de alta en el instituto en orden descendente. */
        ResultSet result;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlApellidos = "SELECT * FROM profesor ORDER BY Apellidos";
        String sqlFechaAlta = "SELECT * FROM Profesor  ORDER BY FechaAlta DESC";
        utils.startConnection();
        try {
            // Parte A
            result = utils.executeSelect(sqlApellidos);
            while (result.next())
                System.out.printf("[CodProf: %s] [Nombre: %s] [Apellidos: %s] [FechaAlta: %s]%n", result.getString(1), result.getString(2), result.getString(3), result.getTimestamp(4));

            System.out.println();

            // Parte B
            result = utils.executeSelect(sqlFechaAlta);
            while (result.next())
                System.out.printf("[CodProf: %s] [Nombre: %s] [Apellidos: %s] [FechaAlta: %s]%n", result.getString(1), result.getString(2), result.getString(3), result.getTimestamp(4));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();

    }
}
