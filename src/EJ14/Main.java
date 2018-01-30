package EJ14;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
     /* Mostrar dónde se encuentra un profesor en un tramo horario concreto. */
        ResultSet result;
        String codTramo ="M6", codProf = "CJC";
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = String.format("SELECT  p.nombre, p.apellidos, r.codProf, h.codOe,h.codCurso, h.codAsig, a.nombre,h.codTramo, th.dia, th.horaInicio, th.horaFin FROM Horario h\n" +
                "JOIN Reparto r ON h.codAsig = r.codAsig \n" +
                "JOIN Asignatura a ON r.codAsig  = a.codAsig\n" +
                "JOIN Profesor p ON p.codProf = r.codProf\n" +
                "JOIN TramoHorario th ON h.codtramo = th.codTramo\n" +
                "WHERE h.codTramo = '%s' AND r.codProf = '%s';",codTramo, codProf);
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlProfesores);
            if(result.next()){
                System.out.printf("%n El profesor/a %s %s (%S) estará  en %s de %s en la asignatura %s (%s) el %s de %s a %s %n ",
                        result.getString(1), result.getString(2), result.getString(3), result.getString(5),
                        result.getString(4), result.getString(6), result.getString(7),result.getString(9).toLowerCase(),
                        result.getString(10),result.getString(11));
            }else{
                System.out.println("El profesor se está tomando un café en este momento.");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
