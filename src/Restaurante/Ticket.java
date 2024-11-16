package Restaurante;

import Gestion.GestionEmpleados;
import Gestion.GestionReserva;
import Users.Cliente;
import Users.Empleado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Ticket {

    private static int contador = 100000;
    private int id;
    private Reserva reserva;
    private Empleado empleado;
    private LocalDateTime horaEmision;
    private List<Plato> platos;
    private double precio;
    private TipoPago tipoPago;
    private double propina;
    private Cliente cliente;
    private GestionReserva gestionReserva;
    private GestionEmpleados gestionEmpleados;
    private Scanner scanner;

    public Ticket (int id, Reserva reserva, Empleado empleado, LocalDateTime horaEmision, List<Plato> platos, Cliente cliente double precio, TipoPago tipoPago, double propina) {
        this.id = id;
        this.reserva = reserva;
        this.empleado = empleado;
        this.horaEmision = horaEmision;
        this.platos = platos;
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.propina = propina;
        this.cliente = cliente;
        this.gestionReserva = new GestionReserva();
        this.gestionEmpleados = new GestionEmpleados();
        this.scanner = new Scanner(System.in);
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(LocalDateTime horaEmision) {
        this.horaEmision = horaEmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }

    public Ticket ingresarTicket() {

        System.out.println("Complete con los datos:\n");

        Cliente cliente = null;
        Reserva res = null;

        boolean resValida = false;
        while (!resValida) {
            System.out.println("Por favor, ingresa ID de la reserva:");
            int id = scanner.nextInt();
            res = gestionReserva.encontrarUsuario(id);
            cliente = res.getCliente();
            if(cliente != null && res!=null){
                resValida = true;
            }else {
                System.out.println("No se encontro la reserva, intentelo nuevamente.");
            }
        }

        Empleado empleado = null;
        boolean empleadoValido = false;
        while (!empleadoValido){
            System.out.println("Ingrese el DNI del empleado: ");
            String dni = scanner.nextLine();
            empleado = gestionEmpleados.encontrarUsuario(dni);
            if (empleado != null){
                empleadoValido=true;
            }else {
                System.out.println("No se encontro el empleado.");
            }
        }



        this.platos = new HashSet<>();
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.propina = propina;

        LocalTime hora = LocalTime.now();
        boolean horaValida = false;
        while (!horaValida) {
            if (hora!=null){
                horaValida=true;
            }else {
                System.out.println("Hubo un problema al generar la hora.");
            }
        }

        Cliente cliente = null;
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
                    cliente = gestionDeCliente.encontrarUsuario(dni);
                    if (cliente != null) {
                        valido = true;
                    } else {
                        System.out.println("No se encontró el cliente.");
                    }
                    break;
                case 2:
                    gestionDeCliente.ingresarUsuario();
                    System.out.println("Ingrese el DNI del cliente recién ingresado: ");
                    String dni2 = scanner.nextLine();
                    cliente = gestionDeCliente.encontrarUsuario(dni2);
                    if (cliente != null) {
                        valido = true;
                    } else {
                        System.out.println("No se encontró el cliente.");
                    }
                    break;
                default:
                    System.out.println("Opción incorrecta, ingrese una opción válida.");
            }
        }
    }

    public void mostrarTicket (Ticket t){

        System.out.println("--------------------------------------");
        System.out.println("RESTAURANTE GRASTROLAB S.A");
        System.out.println("Emitido en: Av. Dorrego 281, Mar del Plata, Argentina");
        System.out.println();
        System.out.println("Users.Empleado: " + empleado.getNombre() + " " + empleado.getApellido());
        System.out.println("T" + id + "                   " + horaEmision);
        System.out.println("------------------------------------------");
        int cant = mostrarPlatos();
        System.out.println("------------------------------------------");
        System.out.println(cant + "productos                      TOTAL: $" + precio);

        if (tipoPago == TipoPago.EFECTIVO){
            System.out.println("                                Pago en efectivo: $" + precio);
        } else if (tipoPago == TipoPago.DEBITO){
            System.out.println("                                Pago con debito: $" + precio);
        }else if(tipoPago == TipoPago.CREDITO){
            System.out.println("                                Impuesto 20%: $" + calcularInteres(precio));
            System.out.println("                                Pago con credito: $" + calcularPrecioInteres(precio));
        }
        System.out.println("**************************************");
        System.out.println("▌│█║▌║▌║║▌║▌║█│▌║▌║▌║█│▌");
        System.out.println("*** GRACIAS POR SSU VISITA ***");
        System.out.println("--------------------------------------");

    }

    public int mostrarPlatos (){

        int cant = 0;

        for (Plato p : platos){
            System.out.println(p.getNombre() + "       $ " + p.getPrecio());
            cant++;
        }

        return cant;
    }

    public double calcularInteres (double precioTotal){
        return precioTotal*0.2;
    }

    public double calcularPrecioInteres(double precioTotal){

        double interes = calcularInteres(precioTotal);

        return precioTotal+interes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Double.compare(precio, ticket.precio) == 0 && Double.compare(propina, ticket.propina) == 0 && Objects.equals(reserva, ticket.reserva) && Objects.equals(empleado, ticket.empleado) && Objects.equals(horaEmision, ticket.horaEmision) && Objects.equals(platos, ticket.platos) && tipoPago == ticket.tipoPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reserva, empleado, horaEmision, platos, precio, tipoPago, propina);
    }

    @Override
    public String toString() {
        return "Restaurante.Ticket{" +
                "id=" + id +
                ", reserva=" + reserva +
                ", empleado=" + empleado +
                ", horaEmision=" + horaEmision +
                ", precio=" + precio +
                ", propina=" + propina +
                '}';
    }
}
