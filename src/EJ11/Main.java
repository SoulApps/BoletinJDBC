package EJ11;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /*Mostrar cuándo se imparte una asignatura en un curso concreto. */
        ResultSet result;
        String oferta ="DAM", codAsig = "SGE";
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = String.format("SELECT h.codOe, h.codCurso, a.nombre, th.dia," +
                "th.horaInicio,\n th.horaFin FROM Horario h  \n" +
                "JOIN Asignatura a ON a.codAsig = h.codAsig\n" +
                "JOIN TramoHorario th ON h.codTramo = th.codTramo WHERE a.codAsig = '%s' \n" +
                "AND h.codOe = '%s'\n" +
                "ORDER BY th.dia;",codAsig, oferta);
        String cabecera = "------------------------------------------------------------------";
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlProfesores);
            while (result.next()){
                System.out.println(cabecera);
                System.out.printf("%nCodigo de la oferta: %s %nCurso: %s %nNombre de la asignatura: %s  %nDia: %s %nHora de inicio: %s %nHora de fin: %s %n",
                        result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getString(5), result.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
