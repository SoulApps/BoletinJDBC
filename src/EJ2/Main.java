package EJ2;

import com.mysql.jdbc.Connection;
import utils.JDBCUtils;
import utils.Teclado;

public class Main {
    public static void main(String[] args) {
        String table, from = "¿De qué tabla quiere consultar los datos?";
        // Creamos la conexión
        JDBCUtils.getInstance().startConnection();
        // Preguntamos al usuario de qué tabla quiere ver la información
        table = Teclado.next(from);

        JDBCUtils.getInstance().showTableData(table);
        JDBCUtils.getInstance().closeConnection();
    }
}
