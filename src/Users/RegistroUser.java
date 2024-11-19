package Users;

import Gestion.GestionDeCliente;
import Restaurante.Reserva;
import Users.Administrador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    private Scanner scanner = new Scanner(System.in);

    public RegistroUser() {
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
        Administrador admin = new Administrador(username, contrasenia, nombre, apellido, dni, telefono, direccion, email);

        return admin;
    }

    /**
     * registroEmpleadoTC es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * EmpleadoTiempoCompleto.
     * @return nuevo EmpleadoTiempoCompleto
     */
    public EmpleadoTiempoCompleto registroEmpleadoTC (){

        System.out.println("Complete con sus datos:\n");

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

        double sueldo = 0;
        System.out.println("Sueldo: ");
        sueldo = scanner.nextDouble();

        int aniosAntguedad = 0;
        System.out.println("Anios de antiguedad: ");
        aniosAntguedad = scanner.nextInt();


        EmpleadoTiempoCompleto empleTC = new EmpleadoTiempoCompleto(nombre, apellido, dni, telefono, direccion, email, sueldo, aniosAntguedad);

        return empleTC;
    }

    /**
     * registroEmpleadoMT es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * EmpleadoMedioTiempo.
     * @return nuevo EmpleadoMedioTiempo
     */
    public EmpleadoMedioTiempo registroEmpleadoMT (){

        System.out.println("Complete con sus datos:\n");

        String nombre="";
        boolean nombreValido=false;

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

        int horasTrabajadas = 0;
        boolean horasValidas = false;
        while (!horasValidas){
            System.out.println("Horas trabajadas:");
            horasTrabajadas = scanner.nextInt();
            scanner.nextLine();
            if (horasTrabajadas>=0){
                horasValidas=true;
            }else {
                System.out.println("Las horas trabajadas no pueden ser menor o igual a 0.");
            }
        }


        double precioXhora = 0;
        boolean pxhValido = false;
        while (!pxhValido){
            System.out.println("Precio por hora:");
            precioXhora = scanner.nextDouble();
            scanner.nextLine();
            if (precioXhora>=0){
                pxhValido=true;
            }else {
                System.out.println("Las horas trabajadas no pueden ser menor o igual a 0-");
            }
        }

        double sueldo = horasTrabajadas*precioXhora;

        EmpleadoMedioTiempo empleMT = new EmpleadoMedioTiempo(nombre, apellido, dni, telefono, direccion, email, sueldo, horasTrabajadas, precioXhora);
        return empleMT;
    }

    /**
     * registroCliente es un metodo que pide al usuario que ingrese sus datos para crear un nuevo
     * Cliente.
     * @return nuevo Cliente
     */
    public Cliente registroCliente (){

        System.out.println("Complete con sus datos:\n");

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

        Cliente cliente = null;

        cliente = new Cliente(nombre, apellido, dni, telefono, direccion, email);

        return cliente;
    }

    public Reserva ingresarReserva() {
        Scanner scanner = new Scanner(System.in);
        GestionDeCliente gestionDeCliente = new GestionDeCliente();

        System.out.println("Complete con los datos:\n");

        LocalDate dia = null;
        boolean diaValido = false;
        while (!diaValido) {
            System.out.println("Por favor, ingresa el día (dd/mm/yyyy):");
            String diaInput = scanner.nextLine();
            DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                dia = LocalDate.parse(diaInput, formatoDia);
                diaValido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: El formato del día no es correcto.");
            }
        }

        LocalTime hora = null;
        boolean horaValida = false;
        while (!horaValida) {
            System.out.println("Ingrese la hora de la reserva (HH:mm):");
            String horaInput = scanner.nextLine();
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            try {
                hora = LocalTime.parse(horaInput, formatoHora);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: El formato de la hora no es correcto.");
            }
        }

        int cliente = 0;
        Cliente aux = new Cliente();
        boolean valido = false;
        while (!valido) {
            System.out.println();
            System.out.println("¿Qué tipo de cliente desea ingresar?");
            System.out.println("1. Cliente existente.");
            System.out.println("2. Crear nuevo cliente.");
            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    System.out.println("Ingrese el DNI del cliente: ");
                    String dni = scanner.nextLine();
                    aux = gestionDeCliente.encontrarUsuario(dni);
                    if (aux != null) {
                        cliente = aux.getId();
                        valido = true;
                    } else {
                        System.out.println("No se encontró el cliente.");
                    }
                    break;
                case 2:
                    gestionDeCliente.ingresarUsuario();
                    System.out.println("Ingrese el DNI del cliente recién ingresado: ");
                    String dni2 = scanner.nextLine();
                    aux = gestionDeCliente.encontrarUsuario(dni2);
                    if (aux != null) {
                        cliente = aux.getId();
                        valido = true;
                    } else {
                        System.out.println("No se encontró el cliente.");
                    }
                    break;
                default:
                    System.out.println("Opción incorrecta, ingrese una opción válida.");
            }
        }

        System.out.println("Ingrese la mesa:");
        int mesa = scanner.nextInt();

        System.out.println("Ingrese la cantidad de personas:");
        int cantPersonas = scanner.nextInt();

        return new Reserva(dia, hora, cliente, mesa, cantPersonas);
    }
    
}
