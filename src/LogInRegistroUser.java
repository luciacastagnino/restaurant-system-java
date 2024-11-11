import java.util.HashMap;
import java.util.Scanner;

public class LogInRegistroUser {

    private HashMap<String, Usuario> usuarios;

    public LogInRegistroUser() {
        this.usuarios = new HashMap<>();
    }

    //Registro de usuario

    public void validarContrasenia (String contrasenia) throws ContraseniaInvalidaException{
        if (contrasenia.length()<10){
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos 10 caracteres.");
        }

        if(!contrasenia.matches(".*[0-9].*")){
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos un numero.");
        }

        if (!contrasenia.matches(".*[!@#$%^&*(),.?\":{}|<>].*")){
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos un caracter especial.");
        }
    }

    public void ingresoDatosRegistro (){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Complete con sus datos:\n");

        System.out.println("Username: ");
        String username = scanner.nextLine();

        String contrasenia = "";
        boolean contraseniaValida = false;

        while (!contraseniaValida){
            System.out.println("Contraseña: ");
            contrasenia = scanner.nextLine();

            try {
                validarContrasenia(contrasenia);
                contraseniaValida = true;
            }catch (ContraseniaInvalidaException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente.");
            }
        }

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.println("DNI: ");
        String dni = scanner.nextLine();

        System.out.println("Telefono: ");
        String telefono = scanner.nextLine();

        System.out.println("Direccion: ");
        String direccion = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        boolean estado = true;

        registro(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);

    }

    //no se si funca, despues pruebo
    public boolean registro (String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado){
        if (usuarios.containsKey(username)){
            System.out.println("El usuario ya existe.");
            return false;
        }

        Usuario nuevoUser = new Usuario(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        usuarios.put(username, contrasenia);
        System.out.println("¡Registro exitoso!");
        return true;
    }

    //Inicio de sesion

    public boolean logIn (String username, String contrasenia){
        if (!usuarios.containsKey(username)){
            System.out.println("Usuario no encontrado.");
            return false;
        }

        if (!usuarios.get(username).equals(contrasenia)){
            System.out.println("Contraseña incorrecta.");
            return false;
        }

        System.out.println("¡Inicio de sesion exitoso!");
        return true;
    }



}
