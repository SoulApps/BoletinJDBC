package EJ16;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* Mostrar de cada asignatura el nombre, el número de horas a la semana, el número de cursos
distintos donde se imparte, el número de ofertas educativas distintas donde se imparte, de
aquellas asignaturas que tengan 3 o más horas a la semana. */
        ResultSet result;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = "SELECT a.nombre, a.horasSemanales,COUNT(*),COUNT(DISTINCT r.codOE)\n" +
                "FROM Asignatura a\n" +
                "JOIN Reparto r ON a.codAsig = r.codAsig\n" +
                "WHERE a.horasSemanales >= 3\n" +
                "GROUP BY a.codAsig\n" +
                "ORDER BY a.nombre ASC";
        String cabecera = "----------------------------------------------------------------------------------";
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlProfesores);
            while (result.next()) {
                System.out.println(cabecera);
                System.out.printf("%n    Asignatura:%s%n    Horas semanales: %d%n    Número de cursos donde se imparte: %d%n    Número de oferta educativas distintas: %d%n%n",
                        result.getString(1), result.getInt(2), result.getInt(3), result.getInt(4));
            }
            result.close();
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
