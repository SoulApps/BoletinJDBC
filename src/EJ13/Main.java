package EJ13;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       /* Mostrar el horario de un curso en modo tabla. Añadir un asterisco en aquellos tramos
            horarios donde haya desdoble.*/
        //TODO mirar esto
        ResultSet result;
        boolean entra = false;
        int contador = 0;
        String codOe = "DAM";
        String codCurso = "1A";
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlHorario = String.format("SELECT th.horaInicio, th.horaFin, h.codAsig, h.codTramo, th.dia\n" +
                "FROM Horario h JOIN TramoHorario th on h.codTramo = th.codTramo\n" +
                "WHERE h.codCurso = '%s' AND h.codOe = '%s'\n" +
                "ORDER BY th.horaInicio, th.dia, h.codAsig DESC;", codCurso, codOe);
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlHorario);
            System.out.printf("%-20s %11s  %11s  %11s  %11s  %11s%n",
                    "Horas", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes");
            while (result.next()) {
                if (contador == 0) {
                    System.out.printf("%s / %s", result.getString(1), result.getString(2), " ");
                }
                if (contador < 5) {
                    if (result.getString(3).startsWith("@")) {
                        contador++;
                        entra = true;
                    } else {
                        if(entra){
                            System.out.printf("%13s", result.getString(3).concat("*"));
                            entra = false;
                            contador ++;
                        }else{
                            System.out.printf("%13s", result.getString(3));
                            contador++;
                        }
                    }

                } else {
                    System.out.println();
                    contador = 0;
                }
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
