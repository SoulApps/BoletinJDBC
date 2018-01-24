package EJ5;

import utils.JDBCUtils;

public class Main {
    public static void main(String[] args) {
        /*Aumenta las horas semanales y las horas totales en
        un 10% de aquellas asignaturas de la FP Básica que empiecen por M.
         */
        JDBCUtils utils = JDBCUtils.getInstance();
        String sql  = "UPDATE Asignatura a JOIN Reparto r on r.codAsig = a.codAsig\n" +
                      "SET a.horasTotales = a.horasTotales * 1.1, a.horasSemanales = a.horasSemanales * 1.1\n" +
                      "WHERE r.codOe = 'FPB' AND a.nombre LIKE 'M%';";
        // Creamos la conexión
        utils.startConnection();
        utils.exeCuteSentence(sql);
        utils.closeConnection();
    }
}
