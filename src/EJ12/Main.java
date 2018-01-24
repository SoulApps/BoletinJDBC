package EJ12;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* Mostrar qué asignaturas imparte un profesor. */
        ResultSet result;
        int contador = 0;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlAsignaturas = "SELECT p.nombre, p.apellidos, a.codAsig, a.nombre FROM Reparto r\n" +
                               "LEFT JOIN Asignatura a on r.codAsig = a.codAsig\n" +
                               "LEFT JOIN Profesor p on p.codProf = r.codProf\n" +
                               "WHERE r.codProf = 'CJC';";
        String cabecera = "------------------------------------------------------------------";
        utils.startConnection();
        try {
            result = utils.executeSelect(sqlAsignaturas);
            while (result.next()){
                if(contador == 0){
                    System.out.print(cabecera);
                    System.out.printf("%nAsignaturas impartidas por: %s %s%n",result.getString(1), result.getString(2));
                    System.out.print(cabecera);
                    System.out.printf("%nCodigo de Asignatura: %s %nNombre de la asignatura: %s%n", result.getString(3), result.getString(4));
                }else{
                    System.out.print(cabecera);
                    System.out.printf("%nCodigo de Asignatura: %s %nNombre de la asignatura: %s%n", result.getString(3), result.getString(4));
                }
                contador++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
