package EJ15;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        /* Mostrar dónde se encuentra un profesor ahora, es decir, en el momento actual. */
        ResultSet result;
        Locale spanish = new Locale("es", "ES");
        String dia, codProf = "PJM";
        LocalDate diaActual = LocalDate.now();
        dia = diaActual.getDayOfWeek().getDisplayName(TextStyle.FULL,spanish);
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesorNow = String.format("SELECT  p.nombre, p.apellidos, r.codProf, h.codOe,h.codCurso, h.codAsig, a.nombre FROM Horario h\n" +
                "JOIN Reparto r ON h.codAsig = r.codAsig \n" +
                "JOIN Asignatura a ON r.codAsig  = a.codAsig\n" +
                "JOIN Profesor p ON p.codProf = r.codProf\n" +
                "JOIN TramoHorario th ON h.codtramo = th.codTramo\n" +
                "WHERE r.codProf = '%s' AND th.dia = '%s' AND CURRENT_TIME BETWEEN th.horaInicio AND th.horaFin;",codProf,dia);
        // Obtener el dia actual

        utils.startConnection();
        try {
            result = utils.executeSelect(sqlProfesorNow);
            if(result.next()){
                System.out.printf("%n El profesor/a %s %s (%S) estará  en %s de %s en la asignatura %s (%s) ahora mismo. %n ",
                        result.getString(1), result.getString(2), result.getString(3), result.getString(5),
                        result.getString(4), result.getString(6), result.getString(7));
            }else{
                System.out.println("El profesor/a se está tomando un café en este momento.");
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
