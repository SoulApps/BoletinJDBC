package EJ4;

import utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*Añade el primer curso de la FP Básica con el tutor Daniel Ayala Soriano
    y las siguientes asignaturas. Utiliza sentencias preparadas. */
        String sqlCurso = "INSERT INTO curso VALUES(?,?,?)";
        String sqlAsignatura = "INSERT INTO ASIGNATURA VALUES(?,?,?,?)";
        String codOe = "FPB", codCurso = "1A", codTutor = "DAS",
        codAsig = "OACE", nomASignatura = "Operaciones auxiliares para la configuración y la explotación";
        int horasSemanales = 7, horasTotales = 245;
        String tableCurso = "curso", tableAsignatura = "asignatura";
        ArrayList<Object> datos = new ArrayList<Object>();
        JDBCUtils utils = JDBCUtils.getInstance();

        // Agregamos los datos al arrayList
        datos.add(codOe);
        datos.add(codCurso);
        datos.add(codTutor);

        // Insertamos datos en la BBDD
        utils.startConnection();
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
        codAsig = "MOSC";
        nomASignatura = "Montaje y mantenimiento de sistemas y componentes informáticos";
        horasSemanales = 9;
        horasTotales = 315;
        datos.add(codAsig);
        datos.add(nomASignatura);
        datos.add(horasSemanales);
        datos.add(horasTotales);
        // Por último, acabamos
        utils.insertPreparedStatement(sqlAsignatura,tableAsignatura,datos);
        utils.closeConnection();
    }
}
