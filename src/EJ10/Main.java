package EJ10;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /*Mostrar de todos los cursos el nombre
        de la oferta educativa, la clave primaria del curso y el nombre del tutor.*/

        ResultSet result;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = "SELECT oe.nombre, c.codOe, p.Nombre, p.Apellidos \n" +
                                "FROM OfertaEducativa oe\n" +
                                "JOIN Curso c ON oe.codOe = c.codOe\n" +
                                "JOIN Profesor p ON c.codTutor = p.codProf;";
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
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
