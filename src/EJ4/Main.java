package EJ4;

import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*Añade el primer curso de la FP Básica con el tutor Daniel Ayala Soriano
    y las siguientes asignaturas. Utiliza sentencias preparadas. */
        String sqlCurso = "INSERT INTO curso VALUES(?,?,?)";
        String sqlAsignatura = "INSERT INTO asignatura VALUES(?,?,?,?)";
        String sqlReparto = "INSERT INTO reparto VALUES(?,?,?,?)";
        String codOe = "FPB", codCurso = "1A", codTutor = "DAS", codProf = "MGD",
        codAsig = "OACE", nomASignatura = "Operaciones auxiliares para la configuración y la explotación",
                codAsig2 = "MOSC", nomASignatura2 = "Montaje y mantenimiento de sistemas y componentes informáticos";
        int horasSemanales = 7, horasTotales = 245, horasSemanales2  = 9, horasTotales2 = 315;
        String tableCurso = "curso", tableAsignatura = "asignatura", tableReparto = "reparto";
        ArrayList<Object> datos = new ArrayList<Object>();
        JDBCUtils utils = JDBCUtils.getInstance();
        utils.startConnection();

        // Agregamos los datos al arrayList
        datos.add(codOe);
        datos.add(codCurso);
        datos.add(codTutor);

        // Insertamos datos en la BBDD

        // Insertamos el curso
        utils.insertPreparedStatement(sqlCurso,tableCurso,datos);
        // Preparamos los datos para las asignaturas
        datos.clear();
        datos.add(codAsig);
        datos.add(nomASignatura);
        datos.add(horasSemanales);
        datos.add(horasTotales);
        // Insertamos las asignaturas
        utils.insertPreparedStatement(sqlAsignatura,tableAsignatura,datos);
        // Preparamos para la ultima asignatura
        datos.clear();
        datos.add(codAsig2);
        datos.add(nomASignatura2);
        datos.add(horasSemanales2);
        datos.add(horasTotales2);
        utils.insertPreparedStatement(sqlAsignatura,tableAsignatura,datos);
        // Por último, insertamos los repartos
        datos.clear();
        datos.add(codOe);
        datos.add(codCurso);
        datos.add(codAsig);
        datos.add(codTutor);
        utils.insertPreparedStatement(sqlReparto,tableReparto,datos);

        datos.clear();
        datos.add(codOe);
        datos.add(codCurso);
        datos.add(codAsig2);
        datos.add(codProf);
        utils.insertPreparedStatement(sqlReparto,tableReparto,datos);
        utils.closeConnection();
    }
}
