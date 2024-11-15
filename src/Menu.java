import Archivos.GestionJSON;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    RegistroUser registroUser;
    LogIn logIn;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.registroUser = new RegistroUser();
        this.logIn = new LogIn();
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

    public void menuAdmin(){

        System.out.println("-----------------------------------------");
        System.out.println("M E N U  D E  A D M I N I S T R A D O R");
        System.out.println("-----------------------------------------");

        System.out.println("1. Registrarse.");
        System.out.println("2. Iniciar sesion.");
        System.out.println("3. Atras.");
        GestionAdministrador gestionAdministrador = new GestionAdministrador();
        int op = scanner.nextInt();
        scanner.nextLine();
        do {
            switch (op){
                case 1:
                    gestionAdministrador.ingresarAdmin();
                    break;
                case 2:
                    try {
                        Administrador admin = logIn.inicioSesionAdmin("administrador.json");
                        if (admin == null){
                            System.out.println("El proceso de inicio de sesión ha sido cancelado.");
                            op = 3;
                            break;
                        }
                        System.out.println("\nBienvenido/a " + admin.getNombre() + " " + admin.getApellido());
                    } catch (FileNotFoundException e) {
                        System.out.println("No se encontro el archivo de administradores.");
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                    break;
            }
        }while (op!=3);

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
