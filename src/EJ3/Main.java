package EJ3;

import utils.JDBCUtils;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        /*
        Insertar la siguiente oferta educativa:
            cod_OE: FPB
            nombre: FP Básica Informática y comunicaciones
            descripción: La formación profesional básica de informática y comunicaciones tiene una
            duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas de
            Formación en centros de trabajo (FCT) en empresas del Sector
         */

        // Vars
        String cod_OE = "FPB";
        String nombre = "FP Básica Informática y comunicaciones";
        String descripcion = "La formación profesional básica de informática y comunicaciones " +
                "tiene una duración de 2000 horas repartidas entre dos cursos  académicos " +
                "incluyendo 240 horas de Formación en centros de trabajo (FCT) en empresas del Sector";
        String tipo = "FPB";
        String fecha = "2018/09/01";
        String sql = String.format("INSERT INTO ofertaeducativa VALUES ('%s', '%s', '%s', '%s','%s')",cod_OE,nombre,descripcion,tipo,fecha);

        // Creamos la conexión
        JDBCUtils.getInstance().startConnection();
        JDBCUtils.getInstance().insertData(sql);
        // Cerramos la conexión
        JDBCUtils.getInstance().closeConnection();

    }
}
