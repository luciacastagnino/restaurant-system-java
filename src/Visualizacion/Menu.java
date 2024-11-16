package Visualizacion;

import Gestion.GestionAdministrador;
import Gestion.GestionDeCliente;
import Gestion.GestionEmpleados;
import Gestion.GestionReserva;
import Users.*;

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

    public void MenuPrincipal() {

        System.out.println();
        System.out.println("Bienvenido a GastroLab");
        System.out.println("\nSeleccione su tipo de usuario:");
        System.out.println("\n1. Empleado");
        System.out.println("2. Cliente");
        System.out.println("3. Salir.");
        String opcion = scanner.nextLine();
        boolean valido = false;

        while (!valido) {
            switch (opcion.toLowerCase()) {
                case "admin":
                    valido = true;
                    menuAdmin();
                    break;
                case "1":
                    valido = true;
                    menuEmpleado();
                    break;
                case "2":
                    valido = true;
                    menuCliente();
                    break;
                case "3":
                    valido = true;
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

    public void menuInicioSesionAdmin(Administrador admin) {
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
                        gestorDeEmpleadosAdmin();
                        break;
                    case 3:
                        gestorDeClientesAdmin();
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
        } while (op != 6);
    }

    public void cuentaAdmin(Administrador admin) {
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
        } while (op != 6);

    }

    public void gestorDeEmpleadosAdmin() {
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mostrar todos los Empleados.");
            System.out.println("2. Ingresar Empleado.");
            System.out.println("3. Dar de baja empleado.");
            System.out.println("4. Mostrar Empleados Activos.");
            System.out.println("5. Mostrar Empleados Inactivos.");
            System.out.println("6. Calcular Sueldo.");
            System.out.println("7. Modificar Empleado.");
            System.out.println("8. Buscar Empleado.");
            System.out.println("9. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionEmpleados.mostrarColeccion();
                        break;
                    case 2:
                        gestionEmpleados.ingresarUsuario();
                        break;
                    case 3:
                        System.out.println("Ingrese el dni del Empleado que quiere dar de baja");
                        String dni = scanner.nextLine();

                        EmpleadoMedioTiempo aux = (EmpleadoMedioTiempo) gestionEmpleados.encontrarUsuario(dni);
                        gestionEmpleados.darDeBajaUsuario(aux);
                        break;
                    case 4:
                        gestionEmpleados.listarUsuarios(true);
                        break;
                    case 5:
                        gestionEmpleados.listarUsuarios(false);
                        break;
                    case 6:
                        System.out.println("Que desea hacer?");
                        System.out.println("1. Calcular sueldo de empleado medio tiempo.");
                        System.out.println("2. Calcular sueldo de empleado tiempo completo.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar DNI del empleado:");
                                String dni1 = scanner.nextLine();
                                EmpleadoMedioTiempo aux1 = (EmpleadoMedioTiempo) gestionEmpleados.encontrarUsuario(dni1);

                                System.out.println("Ingrese las horas extra del empleado (0 si no hay horas extra):");
                                int hs = scanner.nextInt();

                                double total = aux1.calcularSueldo(hs);

                                System.out.println("El sueldo de " + aux1.getNombre() + aux1.getApellido() + " es de: $" + total);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar DNI del empleado:");
                                String dni1 = scanner.nextLine();
                                EmpleadoTiempoCompleto aux1 = (EmpleadoTiempoCompleto) gestionEmpleados.encontrarUsuario(dni1);

                                System.out.println("Ingrese las horas extra del empleado (0 si no hay horas extra):");
                                int hs = scanner.nextInt();

                                System.out.println("Ingrese el precio por hora (0 si no hay horas extra):");
                                double pxh = scanner.nextInt();

                                double total = aux1.calcularSueldo(hs, pxh);
                                System.out.println("El sueldo de " + aux1.getNombre() + aux1.getApellido() + " es de: $" + total);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 7:
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar DNI del empleado:");
                                String d = scanner.nextLine();

                                Empleado empleado= gestionEmpleados.encontrarUsuario(d);

                                gestionEmpleados.modificarUsuario(empleado);
                        }
                        }
                        catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 8:
                        System.out.println("1. Buscar por DNI.");
                        System.out.println("2. Buscar por ID.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar DNI del empleado:");
                                String dni1 = scanner.nextLine();

                                Empleado aux1 = gestionEmpleados.encontrarUsuario(dni1);

                                if(aux1 instanceof EmpleadoMedioTiempo){
                                    System.out.println((EmpleadoMedioTiempo)aux1);
                                }
                                else {
                                    System.out.println((EmpleadoTiempoCompleto)aux1);
                                }
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar ID del empleado:");
                                int id = scanner.nextInt();

                                Empleado aux1 = gestionEmpleados.encontrarUsuario(id);

                                if(aux1 instanceof EmpleadoTiempoCompleto){
                                    System.out.println((EmpleadoTiempoCompleto)aux1);
                                }
                                else{
                                    System.out.println((EmpleadoMedioTiempo)aux1);
                                }
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 9:
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
        } while (op != 9);

    }

    public void gestorDeClientesAdmin() {
        ///SE PUEDE USAR PARA EMPLEADOS
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mostrar todos los Clientes.");
            System.out.println("2. Ingresar Cliente.");
            System.out.println("3. Dar de baja Cliente.");
            System.out.println("4. Mostrar Clientes Activos.");
            System.out.println("5. Mostrar Clientes Inactivos.");
            System.out.println("6. Mostrar Tikets de Cliente.");
            System.out.println("7. Modificar Cliente.");
            System.out.println("8. Buscar Cliente.");
            System.out.println("9.. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionDeCliente.mostrarColeccion();
                        break;
                    case 2:
                        gestionDeCliente.ingresarUsuario();
                        break;
                    case 3:
                        ///hay que hacer busqueda con id
                        System.out.println("Ingrese el dni del Cliente que quiere dar de baja");
                        String dni = scanner.nextLine();

                        Cliente aux = gestionDeCliente.encontrarUsuario(dni);
                        gestionDeCliente.darDeBajaUsuario(aux);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:

                        break;
                    case 9:
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
        } while (op != 9);

    }

    public void menuEmpleado() {
        System.out.println("-----------------------------------------");
        System.out.println("   M E N U  D E  E M P L E A D O   ");
        System.out.println("-----------------------------------------");
        try {
            Empleado empleado = logIn.inicioSesionEmpleado("empleados.json");
            menuInicioSesionEmpleado(empleado);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuInicioSesionEmpleado(Empleado empleado) {
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mi cuenta.");
            System.out.println("2. ");
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
                        gestionEmpleados.mostrarDatosUsuario(empleado);
                        break;
                    case 2:

                        break;
                    case 3:
                        gestorDeClientesAdmin();
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
        } while (op != 6);
    }

    public void menuCliente() {
        System.out.println("-----------------------------------------");
        System.out.println("     M E N U  D E  C L I E N T E S     ");
        System.out.println("-----------------------------------------");
        try {
            Cliente cliente = logIn.inicioSesionCliente("clientes.json");
            menuInicioSesionCliente(cliente);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void menuInicioSesionCliente(Cliente cliente) {
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mi cuenta.");
            System.out.println("2. Gestion tickets");
            System.out.println("3. Gestion reservas.");
            System.out.println("4. Ver menu.");
            System.out.println("5. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionDeCliente.mostrarDatosUsuario(cliente);
                        break;
                    case 2:

                        break;
                    case 3:
                        gestorDeClientesAdmin();
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
        } while (op != 6);

    }
}
