import java.util.HashMap;
import java.util.Scanner;

public class LogInRegistroUser <Usuario> {

    private HashMap<String, Usuario> usuarios;

    public LogInRegistroUser() {
        this.usuarios = new HashMap<>();
    }

    //Registro de usuario

    public boolean registro (String username, String contrasenia, String nombreCompleto, String dni, String email){
        if (usuarios.containsKey(username)){
            System.out.println("El usuario ya existe.");
            return false;
        }

        T nuevoUser = new T(username, contrasenia, nombreCompleto, dni, email); //esto no se puede hacer jiji
        usuarios.put(username, nuevoUser);
        System.out.println("¡Registro exitoso!);
        return true;
    }

    public static void LogIn () {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido! Ingrese sus datos:");

        System.out.println("Nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();



    }
}
