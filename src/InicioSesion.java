import java.util.Scanner;

public class InicioSesion {

    private String USERNAME;
    private String PASSWORD;

    public static void LogIn () {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido! Ingrese sus datos:");

        System.out.println("Nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();



    }

}

