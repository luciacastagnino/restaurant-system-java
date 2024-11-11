import java.security.PublicKey;
import java.util.HashMap;
import java.util.Scanner;

public class LogInRegistroUser {

    private HashMap<String, Usuario> usuarios;

    public LogInRegistroUser() {
        this.usuarios = new HashMap<>();
    }

    //VALIDACION INGRESO DE DATOS

    public void validarContrasenia(String contrasenia) throws ContraseniaInvalidaException {
        if (contrasenia.length() < 10) {
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos 10 caracteres.");
        }

        if (!contrasenia.matches(".*[0-9].*")) {
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos un numero.");
        }

        if (!contrasenia.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos un caracter especial.");
        }

    }

    public void validarCadenas(String cadena) throws DatoInvalidoException {

        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isDigit(cadena.charAt(i))) {
                throw new DatoInvalidoException("Formato incorrecto: no se permiten números en este campo.");
            }
        }
    }

    public void validarDNI(String dni) throws DatoInvalidoException {

        if (!dni.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("DNI inválido: solo debe contener números.");
        }

        int dniValidacion = Integer.parseInt(dni);
        if (dniValidacion < 1000000 || dniValidacion > 99000000) {
            throw new DatoInvalidoException("DNI inválido: debe estar entre 1.000.000 y 99.999.999.");
        }
    }

    public void validarTelefono (String telefono) throws DatoInvalidoException{

        if (!telefono.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("Teléfono inválido: solo debe contener números.");
        }

        if (telefono.length() < 10 || telefono.length() > 11){
            throw new DatoInvalidoException("Teléfono inválido: debe tener entre 10 y 11 dígitos.");
        }
    }

    public void validarDireccion (String direccion) throws DatoInvalidoException{
        if (direccion.length() < 5 || direccion.length() > 100){
            throw new DatoInvalidoException("La dirección debe tener entre 5 y 100 caracteres.");
        }

        if (direccion.matches(".*[!@#$%^&*()_+={}\\[\\]:\";'<>?].*")) {
            throw new DatoInvalidoException("La dirección contiene caracteres no válidos.");
        }

        if (!direccion.matches(".*[a-zA-Z].*") || !direccion.matches(".*[0-9].*")) {
            throw new DatoInvalidoException("La dirección debe contener al menos una letra y un número.");
        }
    }

    public void validarEmail (String email) throws DatoInvalidoException{
        if (email.length() < 5){
            throw new DatoInvalidoException("El correo electronico es demasiado corto.");
        }

        if (!email.contains("@")){
            throw new DatoInvalidoException("El correo electronico debe contener el simbolo '@'.");
        }
    }

    // REGISTRO DE USUARIO

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

        String nombre = "";
        boolean nombreValido = false;

        while (!nombreValido){
            System.out.println("Nombre: ");
            nombre = scanner.nextLine();

            try {
                validarCadenas(nombre);
                nombreValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        String apellido = "";
        boolean apellidoValido = false;

        while (!apellidoValido){
            System.out.println("Apellido: ");
            apellido = scanner.nextLine();

            try {
                validarCadenas(apellido);
                apellidoValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        String dni = "";
        boolean dniValido = false;
        while (!dniValido){
            System.out.println("DNI: ");
            dni = scanner.nextLine();

            try {
                validarDNI(dni);
                dniValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        String telefono = "";
        boolean telefonoValido = false;
        while (!telefonoValido0) {
            System.out.println("Telefono: ");
            telefono = scanner.nextLine();
            try {
                validarTelefono(telefono);
                telefonoValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        String direccion = "";
        boolean direccionValida = false;
        while (!direccionValida){
            System.out.println("Direccion: ");
            direccion = scanner.nextLine();
            try {
                validarDireccion(direccion);
                direccionValida = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }


        String email = "";
        boolean emailValido = false;
        while(!emailValido){
            System.out.println("Email: ");
            email = scanner.nextLine();
            try {
                validarEmail(email);
                emailValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

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
