package Restaurante;

import Archivos.FormatoIncorrectoException;
import Gestion.GestionEmpleados;
import Gestion.GestionReserva;
import Gestion.MenuRestaurante;
import Users.Cliente;
import Users.Empleado;
import Users.EmpleadoMedioTiempo;
import Users.EmpleadoTiempoCompleto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.FileAlreadyExistsException;
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
    private Cliente cliente;
    private GestionReserva gestionReserva;
    private GestionEmpleados gestionEmpleados;
    private MenuRestaurante menuRestaurante;
    private Scanner scanner;

    public Ticket() {
    }

    public Ticket (Reserva reserva, Empleado empleado, LocalDateTime horaEmision, List<Plato> platos,
                   Cliente cliente, double precio, TipoPago tipoPago) {
        this.id = id;
        this.reserva = reserva;
        this.empleado = empleado;
        this.horaEmision = horaEmision;
        this.platos = platos;
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.cliente = cliente;
        this.gestionReserva = new GestionReserva();
        this.gestionEmpleados = new GestionEmpleados();
        this.menuRestaurante = new MenuRestaurante();
        this.scanner = new Scanner(System.in);
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Ticket.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    //TICKET TO JSON

    public JSONObject toJson (Ticket t){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("id", t.getId());
            JSONObject reserva = t.getReserva().toJson(t.getReserva());
            jsonObject.put("reserva", reserva);
            Empleado empleado = t.getEmpleado();
            if (empleado!=null){
                JSONObject empleadoJSON = new JSONObject();
                if (empleado instanceof EmpleadoTiempoCompleto){
                    empleadoJSON = ((EmpleadoTiempoCompleto) empleado).toJson((EmpleadoTiempoCompleto) empleado);
                }else if (empleado instanceof EmpleadoMedioTiempo){
                    empleadoJSON = ((EmpleadoMedioTiempo) empleado).toJson((EmpleadoMedioTiempo) empleado);
                }
                jsonObject.put("empleado", empleadoJSON);
            }
            jsonObject.put("hora", t.getHoraEmision());
            JSONArray platosArray = new JSONArray();
            for (Plato p : platos){
                JSONObject platoJson = p.toJson(p);
                platosArray.put(platoJson);
            }
            jsonObject.put("platos", platosArray);
            jsonObject.put("precio", t.getPrecio());
            jsonObject.put("tipoPago", t.getTipoPago());
            Cliente cliente = t.getCliente();
            JSONObject clienteJSON = cliente.toJson(cliente);
            jsonObject.put("cliente", clienteJSON);
        }catch (
                JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //JSON TO TICKET

    /**
     * jsonToTicket es un metodo que tranforma un JSONObject en un objeto Ticket recibe un
     * JSONObject y retorna un Ticket, lanza un FormatoIncorrectoException si el formato del
     * JSONObject no tiene los parametros de un Ticket.
     * @param json
     * @return ticketLeido
     * @throws FormatoIncorrectoException
     */

    public Ticket jsonToTicket (JSONObject json) throws FormatoIncorrectoException {

        Ticket ticketLeido = new Ticket();
        try {
            if(json.has("id") &&json.has("reserva") && json.has("empleado") &&
                    json.has("hora") && json.has("precio") && json.has("platos")
                    && json.has("tipoPago") && json.has("cliente")){

                ticketLeido.setId(json.getInt("id"));
                JSONObject reservaJson = json.getJSONObject("reserva");
                Reserva reserva = new Reserva().jsonToReserva(reservaJson);
                ticketLeido.setReserva(reserva);
                JSONObject empleadoJson = json.getJSONObject("empleado");
                Empleado empleado = null;
                if (empleadoJson.has("aniosAntiguedad")) {
                    empleado = new EmpleadoTiempoCompleto().jsonToEmpleadoTC(empleadoJson);
                } else if (empleadoJson.has("horasTrabajadas") && empleadoJson.has("precioPorHora")) {
                    empleado = new EmpleadoMedioTiempo().jsonToEmpleadoMT(empleadoJson);
                }
                ticketLeido.setEmpleado(empleado);
                ticketLeido.setHoraEmision((LocalDateTime) json.get("hora"));
                JSONArray platosArray = json.getJSONArray("platos");
                List<Plato> platos = new ArrayList<>();
                for (int i = 0; i < platosArray.length(); i++) {
                    JSONObject platoJson = platosArray.getJSONObject(i);
                    Plato plato = new Plato().jsonToPlato(platoJson);
                    platos.add(plato);
                }
                ticketLeido.setPlatos(platos);
                ticketLeido.setPrecio(json.getDouble("precio"));
                ticketLeido.setTipoPago((TipoPago) json.get("tipoPago"));
                JSONObject clienteJson = json.getJSONObject("cliente");
                Cliente cliente = new Cliente().jsonToCliente(clienteJson);
                ticketLeido.setCliente(cliente);
            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un ticket.");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return ticketLeido;
    }

    public Ticket ingresarTicket() {

        System.out.println("Complete con los datos:\n");

        Reserva res = new Reserva();
        boolean resValida = false;
        while (!resValida) {
            System.out.println("Por favor, ingresa ID de la reserva:");
            int id = scanner.nextInt();
            res = gestionReserva.encontrarUsuario(id);
            gestionReserva.darDeBajaUsuario(res);
            Cliente cliente = res.getCliente();
            if(cliente != null && res != null){
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

        platos = new ArrayList<>();
        boolean salir = false;
        while (!salir){
            System.out.println("1. Agregar plato.");
            System.out.println("2. Salir.");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    menuRestaurante.listarPlatosTicket();
                    System.out.println("Ingrese el ID del plato:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Plato plato = menuRestaurante.encontrarUsuario(id);
                    platos.add(plato);
                    break;
                case 2:
                    System.out.println("Platos cargados con exito.");
                    salir=true;
                    break;
            }

            for (Plato p : platos){
                precio += p.getPrecio();
            }

            TipoPago tipoPago = null;
            boolean tipoPagoValido = false;
            while (!tipoPagoValido){
                System.out.println("Seleccione el tipo de pago: ");
                System.out.println("1. Efectivo.");
                System.out.println("2. Debito.");
                System.out.println("3. Credito");
                int opTipoPago = scanner.nextInt();
                scanner.nextLine();
                switch (opTipoPago){
                    case 1:
                        tipoPago = TipoPago.EFECTIVO;
                        break;
                    case 2:
                        tipoPago = TipoPago.DEBITO;
                        break;
                    case 3:
                        tipoPago = TipoPago.CREDITO;
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;
                }
            }


        }

        horaEmision = LocalDateTime.now();

        Ticket ticket = new Ticket(res, empleado, horaEmision, platos, cliente, precio, tipoPago);
        return ticket;
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
        System.out.println("*** GRACIAS POR SU VISITA ***");
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
        return id == ticket.id && Double.compare(precio, ticket.precio) == 0 && Objects.equals(reserva, ticket.reserva) && Objects.equals(empleado, ticket.empleado) && Objects.equals(horaEmision, ticket.horaEmision) && Objects.equals(platos, ticket.platos) && tipoPago == ticket.tipoPago && Objects.equals(cliente, ticket.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reserva, empleado, horaEmision, platos, precio, tipoPago, cliente);
    }

    @Override
    public String toString() {
        return "Restaurante.Ticket{" +
                "id=" + id +
                ", reserva=" + reserva +
                ", empleado=" + empleado +
                ", horaEmision=" + horaEmision +
                ", precio=" + precio +
                '}';
    }
}
