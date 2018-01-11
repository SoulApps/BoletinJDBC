package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

//created by Alejandro
public class Teclado {
    public static Scanner teclado = new Scanner(System.in);

    //close
    public static void close() {
        teclado.close();
    }

    //boolean
    public static boolean nextBoolean(String mensaje, String trueBoolean, String falseBoolean) {
        boolean error = false, bool = false;
        byte num;


        do {
            System.out.println(mensaje);
            num = Teclado.nextByte("1." + trueBoolean + " 2." + falseBoolean);
            if (num == 1) {
                bool = true;
                error = false;
            }
            else if (num == 2) {
                bool = false;
                error = false;
            }
            else
                error = true;
        } while (error);
        return bool;
    }

    //char
    public static char nextChar(String mensaje) {
        boolean error = false;
        char c;
        String string;

        do {
            System.out.println(mensaje);
            string = teclado.next();
            if (string.length() != 1)
                error = true;
            else
                error = false;
        } while (error);
        c = string.charAt(0);
        return c;
    }

    public static char nextChar(String mensaje, char limiteMenor, char limiteMayor, EnumRango tipo) {
        char c = 0;
        boolean error = false;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    c = teclado.next().charAt(0);
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (c >= limiteMenor && c < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (c > limiteMenor && c <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (c >= limiteMenor && c <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (c > limiteMenor && c < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return c;
        }
    }

    //String
    public static String next(String mensaje) {
        String string;
        System.out.println(mensaje);
        string = teclado.nextLine();
        return string;
    }

    public static String next(String mensaje, String matches) {
        String string;
        System.out.println(mensaje);
        do {
            string = teclado.nextLine();
            if (!string.matches(matches))
                System.out.println("¡Error patrón incorrecto!");
        } while(!string.matches(matches));
        return string;
    }

    //byte
    public static byte nextByte(String mensaje) {
        boolean error = false;
        byte b = 0;

        do {
            try {
                System.out.println(mensaje);
                b = teclado.nextByte();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return b;
    }

    public static byte nextByte(String mensaje, byte limite, EnumLimite tipo) {
        byte b = 0;
        boolean error = false;
        do {
            try {
                System.out.println(mensaje);
                b = teclado.nextByte();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (b < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (b > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (b <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (b >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return b;
    }

    public static byte nextByte(String mensaje, byte limiteMenor, byte limiteMayor, EnumRango tipo) {
        byte b = 0;
        boolean error = false;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    b = teclado.nextByte();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (b >= limiteMenor && b < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (b > limiteMenor && b <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (b >= limiteMenor && b <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (b > limiteMenor && b < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return b;
        }
    }

    //short
    public static short nextShort(String mensaje) {
        boolean error = false;
        short s = 0;
        do {
            try {
                System.out.println(mensaje);
                s = teclado.nextShort();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return s;
    }

    public static short nextShort(String mensaje, short limite, EnumLimite tipo) {
        boolean error = false;
        short s = 0;
        do {
            try {
                System.out.println(mensaje);
                s = teclado.nextShort();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (s < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (s > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (s <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (s >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return s;
    }

    public static short nextShort(String mensaje, short limiteMenor, short limiteMayor, EnumRango tipo) {
        boolean error = false;
        short s = 0;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    s = teclado.nextShort();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (s >= limiteMenor && s < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (s > limiteMenor && s <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (s >= limiteMenor && s <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (s > limiteMenor && s < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return s;
        }
    }

    //int
    public static int nextInt(String mensaje) {
        boolean error = false;
        int i = 0;
        do {
            try {
                System.out.println(mensaje);
                i = teclado.nextInt();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
                teclado.nextLine();
            }
        } while (error);
        return i;
    }

    public static int nextInt(String mensaje, int limite, EnumLimite tipo) {
        boolean error = false;
        int i = 0;
        do {
            try {
                System.out.println(mensaje);
                i = teclado.nextInt();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (i < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (i > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (i <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (i >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                    case IGUAL:
                        if(i == limite)
                            error = false;
                        else{
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return i;
    }

    public static int nextInt(String mensaje, int limiteMenor, int limiteMayor, EnumRango tipo) {
        boolean error = false;
        int i = 0;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    i = teclado.nextInt();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (i >= limiteMenor && i < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (i > limiteMenor && i <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (i >= limiteMenor && i <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (i > limiteMenor && i < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return i;
        }
    }

    //long
    public static long nextLong(String mensaje) {
        boolean error = false;
        long l = 0;
        do {
            try {
                System.out.println(mensaje);
                l = teclado.nextLong();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato longroducido incorrecto!");
                error = true;
                teclado.nextLine();
            }
        } while (error);
        return l;
    }

    public static long nextLong(String mensaje, long limite, EnumLimite tipo) {
        boolean error = false;
        long l = 0;
        do {
            try {
                System.out.println(mensaje);
                l = teclado.nextLong();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (l < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (l > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (l <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (l >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato longroducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return l;
    }

    public static long nextLong(String mensaje, long limiteMenor, long limiteMayor, EnumRango tipo) {
        boolean error = false;
        long l = 0;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    l = teclado.nextLong();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (l >= limiteMenor && l < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (l > limiteMenor && l <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (l >= limiteMenor && l <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (l > limiteMenor && l < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato longroducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return l;
        }
    }

    //float
    public static float nextFloat(String mensaje) {
        boolean error = false;
        float f = 0;
        do {
            try {
                System.out.println(mensaje);
                f = teclado.nextFloat();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return f;
    }

    public static float nextFloat(String mensaje, float limite, EnumLimite tipo) {
        boolean error = false;
        float f = 0;
        do {
            try {
                System.out.println(mensaje);
                f = teclado.nextFloat();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (f < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (f > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (f <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (f >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return f;
    }

    public static float nextFloat(String mensaje, float limiteMenor, float limiteMayor, EnumRango tipo) {
        boolean error = false;
        float f = 0;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    f = teclado.nextFloat();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (f >= limiteMenor && f < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (f > limiteMenor && f <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (f >= limiteMenor && f <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (f > limiteMenor && f < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return f;
        }
    }

    //double
    public static double nextDouble(String mensaje) {
        boolean error = false;
        double d = 0;
        do {
            try {
                System.out.println(mensaje);
                d = teclado.nextDouble();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return d;
    }

    public static double nextDouble(String mensaje, double limite, EnumLimite tipo) {
        boolean error = false;
        double d = 0;
        do {
            try {
                System.out.println(mensaje);
                d = teclado.nextDouble();
                error = false;

                switch (tipo) {
                    case MENOR:
                        if (d < limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYOR:
                        if (d > limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MENORIGUAL:
                        if (d <= limite)
                            error = false;
                        else
                            error = true;
                        break;
                    case MAYORIGUAL:
                        if (d >= limite)
                            error = false;
                        else {
                            error = true;
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("¡Error tipo de dato introducido incorrecto!");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error);
        return d;
    }

    public static double nextDouble(String mensaje, double limiteMenor, double limiteMayor, EnumRango tipo) {
        boolean error = false;
        double d = 0;
        if (limiteMenor > limiteMayor)
            throw new IllegalArgumentException("¡Error!¡El mínimo debe ser mayor que el máximo!");
        else {
            do {
                try {
                    System.out.println(mensaje);
                    d = teclado.nextDouble();
                    error = false;

                    switch (tipo) {
                        case MENORINCLUIDO:
                            if (d >= limiteMenor && d < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case MAYORINCLUIDO:
                            if (d > limiteMenor && d <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSINCLUIDOS:
                            if (d >= limiteMenor && d <= limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                        case AMBOSEXCLUIDOS:
                            if (d > limiteMenor && d < limiteMayor)
                                error = false;
                            else
                                error = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("¡Error tipo de dato introducido incorrecto!");
                    error = true;
                } finally {
                    teclado.nextLine();
                }
            } while (error);
            return d;
        }
    }
}
