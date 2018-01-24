package EJ8;

import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        /* Mostrar todos los datos de los profesores y de los cursos
        de los que son tutores. Si no son tutores, que aparezca “Este profesor no es tutor”
         en lugar de los datos del curso*/
        ResultSet result;
        JDBCUtils utils = JDBCUtils.getInstance();
        String sqlProfesores = "\n" +
                "SELECT * FROM Profesor p \n" +
                "LEFT JOIN Curso c on c.codTutor = p.codProf";

        utils.startConnection();
        try {
            // Parte A
            result = utils.executeSelect(sqlProfesores);
            while (result.next()){
                System.out.printf("[CodProf: %s] [Nombre: %s] [Apellidos: %s] [FechaAlta: %s]",
                        result.getString(1), result.getString(2),
                        result.getString(3), result.getTimestamp(4));
                if(result.getString(5) == null){
                    System.out.printf(" Este profesor no es tutor %n");
                }else{
                    System.out.printf(" Datos del curso: [codOe: %s] [codCurso: %s]%n",
                            result.getString(5), result.getString(6));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        utils.closeConnection();
    }
}
