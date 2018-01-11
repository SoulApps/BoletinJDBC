package EJ1;

import utils.JDBCUtils;
import utils.Teclado;

public class Main {
    public static void main(String[] args) {
        // Realiza un programa que ejecute el script de la BD Horario.
        String path;

        path = Teclado.next("Indique la ruta del archivo sql");
        // Abrimos conexión
        JDBCUtils.getInstance().startConnection();
        // Ejecutamos el script
        JDBCUtils.getInstance().executeScript(path);
        // Cerramos la conexión
        JDBCUtils.getInstance().closeConnection();
    }
}
