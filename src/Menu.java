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
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion){
            case -1:
                menuAdmin();
                break;
            case 1:
                menuEmpleado();
                break;
            case 2:
                menuCliente();
                break;
            case 3:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                break;
        }

    }

    public void menuAdmin(){
        System.out.println("--------- Menu de Administrador ---------");
        System.out.println("1. Registrarse.");
        System.out.println("2. Iniciar sesion.");
        int op = scanner.nextInt();
        scanner.nextLine();
        switch (op){
            case 1:
                registroUser.registroAdmin();
                break;
            case 2:
                try {
                    Administrador admin = logIn.inicioSesionAdmin("administradores.json");
                    System.out.println("Bienvenido/a " + admin.getNombre() + " " + admin.getApellido());
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Opción incorrecta. Por favor, selecciona una opción válida.");
                break;
        }
    }

    public void menuEmpleado(){
        System.out.println("--------- Menu de Empleado ---------");
        try {
            logIn.inicioSesionEmpleado("empleados.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuCliente(){
        System.out.println("--------- Menu de Cliente ---------");
        try {
            logIn.inicioSesionCliente("clientes.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
