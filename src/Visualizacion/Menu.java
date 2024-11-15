package Visualizacion;

import Gestion.GestionAdministrador;
import Gestion.GestionDeCliente;
import Gestion.GestionEmpleados;
import Gestion.GestionReserva;
import Users.Administrador;
import Users.RegistroUser;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private final RegistroUser registroUser;
    private final LogIn logIn;
    private final GestionAdministrador gestionAdministrador;
    private final GestionDeCliente gestionDeCliente;
    private final GestionEmpleados gestionEmpleados;
    private final GestionReserva gestionReserva;


    public Menu() {
        this.scanner = new Scanner(System.in);
        this.registroUser = new RegistroUser();
        this.logIn = new LogIn();
        this.gestionAdministrador = new GestionAdministrador();
        this.gestionDeCliente = new GestionDeCliente();
        this.gestionEmpleados = new GestionEmpleados();
        this.gestionReserva = new GestionReserva();
    }

    public void MenuPrincipal(){

        System.out.println();
        System.out.println("Bienvenido a GastroLab");
        System.out.println("\nSeleccione su tipo de usuario:");
        System.out.println("\n1. Empleado");
        System.out.println("2. Cliente");
        System.out.println("3. Salir.");
        String opcion = scanner.nextLine();
        boolean valido = false;

        while (!valido){
            switch (opcion.toLowerCase()){
                case "admin":
                    valido=true;
                    menuAdmin();
                    break;
                case "1":
                    valido=true;
                    menuEmpleado();
                    break;
                case "2":
                    valido=true;
                    menuCliente();
                    break;
                case "3":
                    valido=true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }

    public void menuAdmin() {
        System.out.println("-----------------------------------------");
        System.out.println("M E N U  D E  A D M I N I S T R A D O R");
        System.out.println("-----------------------------------------");

        int op = 0;

        do {
            System.out.println("1. Registrarse.");
            System.out.println("2. Iniciar sesión.");
            System.out.println("3. Atrás.");

            try {
                op = scanner.nextInt();
                scanner.nextLine();

                switch (op) {
                    case 1:
                        gestionAdministrador.ingresarUsuario();
                        break;
                    case 2:
                        try {
                            Administrador admin = logIn.inicioSesionAdmin("administrador.json");
                            if (admin == null) {
                                System.out.println("El proceso de inicio de sesión ha sido cancelado.");
                                op = 3;
                                break;
                            }
                            System.out.println("\nBienvenido/a " + admin.getNombre() + " " + admin.getApellido());
                            menuInicioSesionAdmin(admin);
                        } catch (FileNotFoundException e) {
                            System.out.println("No se encontró el archivo de administradores.");
                            throw new RuntimeException(e);
                        }
                        break;
                    case 3:
                        System.out.println("Volviendo al menú principal...");
                        MenuPrincipal();
                        break;
                    default:
                        System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opción inválida. Por favor, introduce un número.");
                scanner.nextLine();
                op = -1;
            }

        } while (op != 3);
    }

    public void menuInicioSesionAdmin (Administrador admin){
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mi cuenta.");
            System.out.println("2. Gestion empleados");
            System.out.println("3. Gestion clientes.");
            System.out.println("4. Gestion reservas.");
            System.out.println("5. Gestion menu/platos.");
            System.out.println("6. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        cuentaAdmin(admin);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        System.out.println("Cerrando sesion...");
                        break;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        }while (op!=6);
    }

    public void cuentaAdmin (Administrador admin){
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Ver mi perfil.");
            System.out.println("2. Modificar mi cuenta.");
            System.out.println("3. Eliminar cuenta.");
            System.out.println("4. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionAdministrador.mostrarDatosUsuario(admin);
                        break;
                    case 2:
                        admin = gestionAdministrador.modificarUsuario(admin);
                        break;
                    case 3:
                        gestionAdministrador.darDeBajaUsuario(admin);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        System.out.println("Cerrando sesion...");
                        break;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        }while (op!=6);

    }

    public void menuEmpleado(){
        System.out.println("-----------------------------------------");
        System.out.println("   M E N U  D E  E M P L E A D O   ");
        System.out.println("-----------------------------------------");
        try {
            logIn.inicioSesionEmpleado("empleados.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuCliente(){
        System.out.println("-----------------------------------------");
        System.out.println("     M E N U  D E  C L I E N T E S     ");
        System.out.println("-----------------------------------------");
        try {
            logIn.inicioSesionCliente("clientes.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
