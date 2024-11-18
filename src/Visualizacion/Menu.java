package Visualizacion;

import Gestion.*;
import Restaurante.Plato;
import Restaurante.Reserva;
import Restaurante.Ticket;
import Users.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private final MenuRestaurante menuRestaurante;
    private final GestionTickets gestionTickets;


    public Menu() {
        this.scanner = new Scanner(System.in);
        this.registroUser = new RegistroUser();
        this.logIn = new LogIn();
        this.gestionAdministrador = new GestionAdministrador();
        this.gestionDeCliente = new GestionDeCliente();
        this.gestionEmpleados = new GestionEmpleados();
        this.gestionReserva = new GestionReserva();
        this.menuRestaurante = new MenuRestaurante();
        this.gestionTickets = new GestionTickets();
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
            System.out.println("6. Gestion Tickets.");
            System.out.println("7. Salir.");
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
                        gestorDeReservasAdmin();
                        break;
                    case 5:
                        gestorDeMenuAdmin();
                        break;
                    case 6:
                        gestorDeTiketsAdmin();
                        break;
                    case 7:
                        System.out.println("Cerrando sesion...");
                        return;
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
                        System.out.println("Cerrando sesion...");
                        return;
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
            System.out.println("9. Dar de alta empleado.");
            System.out.println("10. Salir.");
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

                        Empleado aux = gestionEmpleados.encontrarUsuario(dni);
                        gestionEmpleados.darDeBajaUsuarioAdmin(aux);
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
                                System.out.println("Ingresar DNI del empleado:");
                                String d = scanner.nextLine();

                                Empleado empleado= gestionEmpleados.encontrarUsuario(d);

                                gestionEmpleados.modificarUsuario(empleado);
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
                                gestionEmpleados.mostrarDatosUsuario(aux1);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar ID del empleado:");
                                int id = scanner.nextInt();

                                Empleado aux1 = gestionEmpleados.encontrarUsuario(id);

                                gestionEmpleados.mostrarDatosUsuario(aux1);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 9:
                        System.out.println("Ingrese el dni del Empleado que quiere dar de alta");
                        String dni1 = scanner.nextLine();

                        Empleado aux1 = gestionEmpleados.encontrarUsuario(dni1);
                        gestionEmpleados.darDeAltaUsuario(aux1);
                        break;
                    case 10:
                        System.out.println("Saliendo...");
                        return;
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
            System.out.println("9. Mostrar reservas.");
            System.out.println("10. Dar de alta Cliente.");
            System.out.println("11. Salir.");
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
                        System.out.println("Ingrese el dni del Cliente que quiere dar de baja");
                        String dni = scanner.nextLine();

                        Cliente aux = gestionDeCliente.encontrarUsuario(dni);
                        gestionDeCliente.darDeBajaUsuarioAdmin(aux);
                        break;
                    case 4:
                        gestionDeCliente.listarUsuarios(true);
                        break;
                    case 5:
                        gestionDeCliente.listarUsuarios(false);
                        break;
                    case 6:
                        System.out.println("Ingrese el dni del Cliente:");
                        String dni1 = scanner.nextLine();

                        gestionTickets.listarUsuarios(dni1);
                        break;
                    case 7:
                        System.out.println("Ingresar DNI del cliente:");
                        String d = scanner.nextLine();

                        Cliente cliente= gestionDeCliente.encontrarUsuario(d);

                        gestionDeCliente.modificarUsuario(cliente);
                        break;
                    case 8:
                        System.out.println("1. Buscar por DNI.");
                        System.out.println("2. Buscar por ID.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar DNI del cliente:");
                                String dni2 = scanner.nextLine();

                                Cliente aux1 = gestionDeCliente.encontrarUsuario(dni2);
                                gestionDeCliente.mostrarDatosUsuario(aux1);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar ID del cliente:");
                                int id = scanner.nextInt();

                                Cliente aux1 = gestionDeCliente.encontrarUsuario(id);
                                gestionDeCliente.mostrarDatosUsuario(aux1);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 9:
                        System.out.println("Ingrese el DNI del Cliente para ver sus reservas:");
                        String dni3 = scanner.nextLine();

                        gestionReserva.listarUsuarios(dni3);
                        break;
                    case 10:
                        System.out.println("Ingrese el dni del Cliente que quiere dar de alta");
                        String dni4 = scanner.nextLine();

                        Cliente aux4 = gestionDeCliente.encontrarUsuario(dni4);
                        gestionDeCliente.darDeAltaUsuario(aux4);
                        break;
                    case 11:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        } while (op != 11);

    }

    public void gestorDeReservasAdmin(){
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mostrar Reservas.");
            System.out.println("2. Ingresar Reserva.");
            System.out.println("3. Dar de baja Reserva.");
            System.out.println("4. Modificar Reserva.");
            System.out.println("5. Buscar Reserva.");
            System.out.println("6. Dar de alta Reserva.");
            System.out.println("7. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        System.out.println("Seleccione una opcion:");
                        System.out.println("1. Todas las reservas.");
                        System.out.println("2. Reservas Activas");
                        System.out.println("3. Reservas inactivas.");
                        System.out.println("4. Reservas por Dia.");
                        System.out.println("5. Reservas por Hora.");
                        System.out.println("6. Reservas por dia y hora.");
                        System.out.println("7. Reservas por Cliente.");
                        System.out.println("8. Salir");

                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                gestionReserva.mostrarColeccion();
                            }
                            else if (op1 == 2) {
                                gestionReserva.listarUsuarios(true);
                            }
                            else if (op1 == 3){
                                gestionReserva.listarUsuarios(false);
                            }
                            else if (op1 == 4){
                                System.out.println("Ingrese el dia:");
                                String aux = scanner.nextLine();
                                LocalDate dia = LocalDate.parse(aux);

                                gestionReserva.listarUsuarios(dia);
                            }
                            else if (op1 == 5) {
                                System.out.println("Ingrese la hora:");
                                String aux = scanner.nextLine();
                                LocalTime hs = LocalTime.parse(aux);

                                gestionReserva.listarUsuarios(hs);
                            }
                            else if (op1 == 6) {
                                System.out.println("Ingrese la hora:");
                                String aux = scanner.nextLine();
                                LocalTime hs = LocalTime.parse(aux);

                                System.out.println("Ingrese el dia:");
                                String aux1 = scanner.nextLine();
                                LocalDate dia = LocalDate.parse(aux1);

                                gestionReserva.listarUsuarios(dia, hs);
                            }
                            else if (op1 == 7) {
                                System.out.println("Ingrese el DNI del Cliente:");
                                String aux = scanner.nextLine();

                               gestionReserva.listarUsuarios(aux);
                            }
                            else {
                                System.out.println("Saliendo...");
                                return;
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        gestionReserva.ingresarUsuario();
                        break;
                    case 3:
                        System.out.println("Ingrese el ID de la reserva a dar de baja:");
                        int id = scanner.nextInt();

                        Reserva aux = gestionReserva.encontrarUsuario(id);
                        gestionReserva.darDeBajaUsuario(aux);
                        break;
                    case 4:
                        System.out.println("Ingrese el ID de la reserva a modificar:");
                        int id1 = scanner.nextInt();

                        Reserva aux1 = gestionReserva.encontrarUsuario(id1);
                        gestionReserva.modificarUsuario(aux1);
                        break;
                    case 5:
                        System.out.println("1. Buscar Reserva por ID.");
                        System.out.println("2. Buscar Reserva por fecha y cliente.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar ID de la Reserva:");
                                int id2 = scanner.nextInt();
                                Reserva aux2 = gestionReserva.encontrarUsuario(id2);
                                gestionReserva.mostrarDatosUsuario(aux2);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar DNI del Cliente:");
                                String dni1 = scanner.nextLine();

                                System.out.println("Ingrese la hora:");
                                String auxiliar = scanner.nextLine();
                                LocalTime hs = LocalTime.parse(auxiliar);

                                System.out.println("Ingrese el dia:");
                                String auxiliar1 = scanner.nextLine();
                                LocalDate dia = LocalDate.parse(auxiliar1);

                                Reserva reserva = gestionReserva.encontrarUsuario(dni1, dia, hs);
                                gestionReserva.mostrarDatosUsuario(reserva);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Ingrese el ID de la reserva a dar de alta:");
                        int id2 = scanner.nextInt();

                        Reserva aux2 = gestionReserva.encontrarUsuario(id2);
                        gestionReserva.darDeAltaUsuario(aux2);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        } while (op != 7);
    }

    public void gestorDeMenuAdmin(){
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mostrar menu.");
            System.out.println("2. Ingresar Plato.");
            System.out.println("3. Dar de baja Plato.");
            System.out.println("4. Modificar Plato.");
            System.out.println("5. Buscar Plato.");
            System.out.println("6. Dar de alta Plato.");
            System.out.println("7. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        System.out.println("Seleccione una opcion:");
                        System.out.println("1. Menu Completo.");
                        System.out.println("2. Bebidas");
                        System.out.println("3. Desayuno y Merienda.");
                        System.out.println("4. Brunch.");
                        System.out.println("5. Entradas.");
                        System.out.println("6. Almuerzo.");
                        System.out.println("7. Cena.");
                        System.out.println("8. Postre.");
                        System.out.println("9. Salir");

                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                menuRestaurante.mostrarColeccion();
                            }
                            else if (op1 == 2) {
                                menuRestaurante.mostrarBebida();
                            }
                            else if (op1 == 3){
                                menuRestaurante.mostrarDesayunoMerienda();
                            }
                            else if (op1 == 4){
                                menuRestaurante.mostrarBrunch();
                            }
                            else if (op1 == 5) {
                                menuRestaurante.mostrarEntradas();
                            }
                            else if (op1 == 6) {
                                menuRestaurante.mostrarAlmuerzo();
                            }
                            else if (op1 == 7) {
                                menuRestaurante.mostrarCena();
                            }
                            else if (op1 == 8) {
                                menuRestaurante.mostrarPostre();
                            }
                            else {
                                System.out.println("Saliendo...");
                                break;
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        menuRestaurante.ingresarUsuario();
                        break;
                    case 3:
                        System.out.println("Ingrese el ID del Plato a dar de baja:");
                        int id = scanner.nextInt();

                        Plato aux = menuRestaurante.encontrarUsuario(id);
                        menuRestaurante.darDeBajaUsuario(aux);
                        break;
                    case 4:
                        System.out.println("Ingrese el ID del Plato a modificar:");
                        int id1 = scanner.nextInt();

                        Plato aux1 = menuRestaurante.encontrarUsuario(id1);
                        menuRestaurante.modificarUsuario(aux1);
                        break;
                    case 5:
                        System.out.println("1. Buscar Plato por ID.");
                        System.out.println("2. Buscar Plato por nombre.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar ID del Plato:");
                                int id2 = scanner.nextInt();
                                Plato aux2 = menuRestaurante.encontrarUsuario(id2);
                                menuRestaurante.mostrarDatosUsuario(aux2);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar nombre del Plato:");
                                String nombre = scanner.nextLine();

                                Plato plato = menuRestaurante.encontrarUsuario(nombre);
                                menuRestaurante.mostrarDatosUsuario(plato);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Ingrese el ID del Plato a dar de alta:");
                        int id2 = scanner.nextInt();

                        Plato aux2 = menuRestaurante.encontrarUsuario(id2);
                        menuRestaurante.darDeAltaUsuario(aux2);
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        } while (op != 7);
    }

    public void gestorDeTiketsAdmin(){
        int op = 0;
        do {
            System.out.println();
            System.out.println("¿Que desea hacer?");
            System.out.println("1. Mostrar Tickets.");
            System.out.println("2. Ingresar Ticket.");
            System.out.println("3. Modificar Ticket.");
            System.out.println("4. Buscar Ticket.");
            System.out.println("5. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        System.out.println("Seleccione una opcion:");
                        System.out.println("1. Todos los Tickets.");
                        System.out.println("2. Tickets Activos");
                        System.out.println("3. Tickets inactivos.");
                        System.out.println("4. Tickets por Cliente.");
                        System.out.println("5. Salir");

                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                gestionTickets.mostrarColeccion();
                            }
                            else if (op1 == 2) {
                                gestionTickets.listarUsuarios(true);
                            }
                            else if (op1 == 3){
                                gestionTickets.listarUsuarios(false);
                            }
                            else if (op1 == 4){
                                System.out.println("Ingrese el DNI del Cliente:");
                                String aux = scanner.nextLine();

                                gestionTickets.listarUsuarios(aux);
                            }
                            else {
                                System.out.println("Saliendo...");
                                break;
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        gestionTickets.ingresarUsuario();
                        break;
                    case 3:
                        System.out.println("Ingrese el ID del Ticket a modificar");
                        int id1 = scanner.nextInt();

                        Ticket aux1 = gestionTickets.encontrarUsuario(id1);
                        gestionTickets.modificarUsuario(aux1);
                        break;
                    case 4:
                        System.out.println("1. Buscar Ticket por ID.");
                        System.out.println("2. Buscar Ticket por cliente.");
                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                System.out.println("Ingresar ID del Ticket:");
                                int id2 = scanner.nextInt();
                                Ticket aux2 = gestionTickets.encontrarUsuario(id2);

                                System.out.println(aux2);
                            }
                            else if (op1 == 2) {
                                System.out.println("Ingresar DNI del Cliente:");
                                String dni1 = scanner.nextLine();

                                Ticket ticket = gestionTickets.encontrarUsuario(dni1);
                                System.out.println(ticket);
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        } while (op != 5);
    }

    public void menuEmpleado() {
        System.out.println("-----------------------------------------");
        System.out.println("   M E N U  D E  E M P L E A D O   ");
        System.out.println("-----------------------------------------");
        try {
            Empleado empleado = logIn.inicioSesionEmpleado("empleados.json");
            if(!empleado.equals(null)){
                menuInicioSesionEmpleado(empleado);
            }
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
            System.out.println("2. Gestion clientes.");
            System.out.println("3. Gestion reservas.");
            System.out.println("4. Gestion menu/platos.");
            System.out.println("5. Gestion Tickets.");
            System.out.println("6. Eliminar Cuenta.");
            System.out.println("7. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionEmpleados.mostrarDatosUsuario(empleado);
                        break;
                    case 2:
                        gestorDeClientesAdmin();
                        break;
                    case 3:
                        gestorDeReservasAdmin();
                        break;
                    case 4:
                        gestorDeMenuAdmin();
                        break;
                    case 5:
                        gestorDeTiketsAdmin();
                        break;
                    case 6:
                        gestionEmpleados.darDeBajaUsuario(empleado);
                        break;
                    case 7:
                        System.out.println("Cerrando sesion...");
                        return;
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
            System.out.println("2. Ver tickets");
            System.out.println("3. Ver reservas.");
            System.out.println("4. Ver menu.");
            System.out.println("5. Eliminar Cuenta.");
            System.out.println("6. Salir.");
            try {
                System.out.printf("Selecciona una opcion: ");
                op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:
                        gestionDeCliente.mostrarDatosUsuario(cliente);
                        break;
                    case 2:
                        gestionReserva.listarUsuarios(cliente.getDni());
                        break;
                    case 3:
                        System.out.println("LISTA DE RESERVAS:");
                        gestionReserva.listarUsuarios(cliente.getDni());
                        break;
                    case 4:
                        System.out.println("Seleccione una opcion:");
                        System.out.println("1. Menu Completo.");
                        System.out.println("2. Bebidas");
                        System.out.println("3. Desayuno y Merienda.");
                        System.out.println("4. Brunch.");
                        System.out.println("5. Entradas.");
                        System.out.println("6. Almuerzo.");
                        System.out.println("7. Cena.");
                        System.out.println("8. Postre.");
                        System.out.println("9. Salir");

                        try {
                            System.out.printf("Selecciona una opcion: ");
                            int op1 = scanner.nextInt();
                            scanner.nextLine();

                            if(op1 == 1){
                                menuRestaurante.mostrarMenuCompleto();
                            }
                            else if (op1 == 2) {
                                menuRestaurante.mostrarBebida();
                            }
                            else if (op1 == 3){
                                menuRestaurante.mostrarDesayunoMerienda();
                            }
                            else if (op1 == 4){
                                menuRestaurante.mostrarBrunch();
                            }
                            else if (op1 == 5) {
                                menuRestaurante.mostrarEntradas();
                            }
                            else if (op1 == 6) {
                                menuRestaurante.mostrarAlmuerzo();
                            }
                            else if (op1 == 7) {
                                menuRestaurante.mostrarCena();
                            }
                            else if (op1 == 8) {
                                menuRestaurante.mostrarPostre();
                            }
                            else {
                                System.out.println("Saliendo...");
                                break;
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        gestionDeCliente.darDeBajaUsuario(cliente);
                        break;
                    case 6:
                        System.out.println("Cerrando sesion...");
                        return;
                    default:
                        System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Por favor, introduzca un numero.");
                scanner.nextLine();
            }
        } while (op != 5);
    }
}
