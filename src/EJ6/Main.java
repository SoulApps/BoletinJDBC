package EJ6;

import utils.JDBCUtils;

public class Main {
    public static void main(String[] args) {
        /*La FP Básica desaparece del IES Saladillo. Borra de la base de datos todo lo que sea de
            dicha oferta educativa. */

        JDBCUtils utils = JDBCUtils.getInstance();
        String sql  = "DELETE FROM OfertaEducativa WHERE codOe = 'FPB';";
        // Creamos la conexión
        utils.startConnection();
        utils.exeCuteSentence(sql);
        utils.closeConnection();

    }
}
