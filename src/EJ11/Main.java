package EJ11;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /*Mostrar cuándo se imparte una asignatura en un curso concreto. */
        ResultSet result;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = "SELECT h.codOe, h.codCurso, a.nombre, th.dia, th.horaInicio, " +
                "th.horaFin FROM Horario h  RIGHT JOIN Asignatura a ON a.codAsig = h.codAsig " +
                "RIGHT JOIN TramoHorario th ON h.codTramo = th.codTramo WHERE a.codAsig = (?) " +
                "AND h.codOe = (?);";
        String cabecera = "------------------------------------------------------------------";
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlProfesores);
            while (result.next()){
                System.out.println(cabecera);
                System.out.printf("Nombre de la oferta: %s %nCodigo de la oferta: %s %nNombre del tutor: %s %s%n",
                        result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
