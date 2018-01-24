package utils;

public class ErrorUtils {
    private ErrorUtils(){}

    public static void showErrorNoResultInSQL(String table){
        System.out.printf("No hay datos para la sentencia de la tabla: %s%n%n", table);
    }
}
