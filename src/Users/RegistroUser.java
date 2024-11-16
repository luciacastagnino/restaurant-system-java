package Users;

import Users.Administrador;

import java.util.Scanner;
/**
 * La clase Users.RegistroUser tiene como atributo un Scanner para utilizar en los metodos
 * Tiene un constructor que inicializa el Scanner
 * Metodos: registroAdmin, registroEmpleadoMT, registroEmpleadoTC, registroCliente
 *
 * @author Brenda
 * @since 2024
 * @version 2
 */
public final class RegistroUser{

    private Scanner scanner;

    public RegistroUser() {
        this.scanner = new Scanner(System.in);
    }

    // REGISTRO DE USUARIO ADMIN

    /**
     * registroAdmin es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * administrador.
     * @return nuevo Administrador
     */
    public Administrador registroAdmin (){

        System.out.println("Complete con sus datos:\n");

        String username = "";
        boolean usernameValido = false;

        while (!usernameValido){
            System.out.println("Username: ");
            username = scanner.nextLine();
            try {
                Validaciones.validarNombreUsuario(username);
                usernameValido=true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente.");
            }
        }

        String contrasenia = "";
        boolean contraseniaValida = false;

        while (!contraseniaValida){
            System.out.println("Contraseña: ");
            contrasenia = scanner.nextLine();

            try {
                Validaciones.validarContrasenia(contrasenia);
                contraseniaValida = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente.");
            }
        }

        String nombre = "";
        boolean nombreValido = false;

        while (!nombreValido){
            System.out.println("Nombre: ");
            nombre = scanner.nextLine();

            try {
                Validaciones.validarCadenas(nombre);
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
                Validaciones.validarCadenas(apellido);
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
                Validaciones.validarDNI(dni);
                dniValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        String telefono = "";
        boolean telefonoValido = false;
        while (!telefonoValido) {
            System.out.println("Telefono: ");
            telefono = scanner.nextLine();
            try {
                Validaciones.validarTelefono(telefono);
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
                Validaciones.validarDireccion(direccion);
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
                Validaciones.validarEmail(email);
                emailValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        boolean estado = true;

        Administrador admin = new Administrador(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);

        return admin;
    }

    /**
     * registroEmpleadoTC es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * EmpleadoTiempoCompleto.
     * @return nuevo EmpleadoTiempoCompleto
     */
    public EmpleadoTiempoCompleto registroEmpleadoTC (){

        System.out.println("Complete con sus datos:\n");

        String dni = "";
        boolean dniValido = false;
        while (!dniValido){
            System.out.println("DNI: ");
            dni = scanner.nextLine();

            try {
                Validaciones.validarDNI(dni);
                dniValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        boolean estado = true;

        EmpleadoTiempoCompleto empleTC = new EmpleadoTiempoCompleto(dni, dni, "", "", dni, "", "", "", true, 0, 0);

        return empleTC;
    }

    /**
     * registroEmpleadoMT es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * EmpleadoMedioTiempo.
     * @return nuevo EmpleadoMedioTiempo
     */
    public EmpleadoMedioTiempo registroEmpleadoMT (){

        System.out.println("Complete con sus datos:\n");

        String dni = "";
        boolean dniValido = false;
        while (!dniValido){
            System.out.println("DNI: ");
            dni = scanner.nextLine();

            try {
                Validaciones.validarDNI(dni);
                dniValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        boolean estado = true;

        EmpleadoMedioTiempo empleMT = new EmpleadoMedioTiempo(dni, dni, "", "", dni, "", "", "", true, 0.0, 0, 0.0);
        return empleMT;
    }

    /**
     * registroCliente es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * Cliente.
     * @return nuevo Cliente
     */
    public Cliente registroCliente (){

        System.out.println("Complete con sus datos:\n");

        String dni = "";
        boolean dniValido = false;
        while (!dniValido){
            System.out.println("DNI: ");
            dni = scanner.nextLine();

            try {
                Validaciones.validarDNI(dni);
                dniValido = true;
            }catch (DatoInvalidoException e){
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        boolean estado = true;

        boolean tipoClienteValido = false;

        Cliente cliente = null;
        while (!tipoClienteValido){

            System.out.println("\nTipo de cliente: ");
            System.out.println("1. Estandar.");
            System.out.println("2. Premium.");
            System.out.println("3. VIP.");
            int tipoCliente = scanner.nextInt();
            scanner.nextLine();

            if (tipoCliente==1){
                cliente = new Cliente(dni, dni, "", "", dni, "", "", "", estado);
                tipoClienteValido = true;
            }else if (tipoCliente == 2){
                cliente = new Cliente(dni, dni, "", "", dni, "", "", "", estado);
                tipoClienteValido = true;
            }else if (tipoCliente == 3){
                cliente = new Cliente(dni, dni, "", "", dni, "", "", "", estado);
                tipoClienteValido = true;
            }else {
                System.out.println("Opcion invalida. Por favor, ingrese 1, 2 o 3.");
            }
        }

        return cliente;
    }
    
}
