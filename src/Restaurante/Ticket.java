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

import java.time.LocalDateTime;
import java.util.*;

/**
 * La clase Restaurante.Ticket representa un comprobante generado tras una reserva en el restaurante.
 *
 * Permite gestionar la emisión y registro de tickets, incluyendo la conversión de objetos Ticket
 * a formato JSON y viceversa, así como la interacción con reservas, empleados y platos del menú del restaurante.
 *
 * Incluye un constructor vacio y uno con paramentros, métodos de entrada/salida de datos, y funcionalidades para la gestión
 * de pagos, impresión de recibos y cálculo de precios con interés para pagos con tarjeta.
 *
 * Sobrescribe los métodos `equals()`, `hashCode()` y `toString()` para comparaciones y representación textual.
 *
 * Interactúa con la gestión de reservas, empleados y el menú del restaurante para facilitar la administración
 * de tickets en el sistema.
 *
 * @author Melina
 * @since 2024
 * @version 1
 */



public class Ticket {

    private static Random random = new Random();
    private int id;
    private Reserva reserva;
    private Empleado empleado;
    private LocalDateTime horaEmision;
    private List<Plato> platos;
    private double precio;
    private TipoPago tipoPago;
    private int cliente;
    private GestionReserva gestionReserva;
    private GestionEmpleados gestionEmpleados;
    private MenuRestaurante menuRestaurante;
    private Scanner scanner;

    public Ticket() {
        this.id = random.nextInt(1000000)+100;
        this.gestionReserva = new GestionReserva();
        this.gestionEmpleados=new GestionEmpleados();
        this.scanner = new Scanner(System.in);
        this.menuRestaurante=new MenuRestaurante();
        this.platos=new ArrayList<>();
    }

    public Ticket (Reserva reserva, Empleado empleado, LocalDateTime horaEmision, List<Plato> platos,
                   int cliente, double precio, TipoPago tipoPago) {
        this.id = random.nextInt(1000000)+100;
        this.reserva = reserva;
        this.empleado = empleado;
        this.horaEmision = horaEmision;
        this.platos = platos != null ? platos : new ArrayList<>();
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.cliente = cliente;
        this.gestionReserva = new GestionReserva();
        this.gestionEmpleados = new GestionEmpleados();
        this.menuRestaurante = new MenuRestaurante();
        this.scanner=new Scanner(System.in);
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

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
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


    public Ticket ingresarTicket() {

        Ticket ticket = null;
        System.out.println("Complete con los datos:\n");

        Reserva res = new Reserva();
        int cliente = 0;
        boolean resValida = false;
        while (!resValida) {
            System.out.println("Por favor, ingresa ID de la reserva:");
            int id = scanner.nextInt();
            scanner.nextLine();
            res = gestionReserva.encontrarUsuario(id);
            if (res!=null){
                System.out.println("Reserva encontrada.");
                gestionReserva.darDeBajaReservaTicket(res);
                cliente = res.getCliente();
                if (cliente!=0){
                    resValida = true;
                }
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
        System.out.println("¿Que desea hacer?");
        boolean salir = false;
        while (!salir){
            System.out.println("1. Agregar plato.");
            System.out.println("2. Salir.");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("PLATOS DISPONIBLES: ");
                    menuRestaurante.listarPlatosTicket();
                    boolean valid = false;
                    while (!valid){
                        System.out.println("Ingrese el ID del plato:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Plato plato = menuRestaurante.encontrarUsuario(id);
                        if (plato!=null){
                            platos.add(plato);
                            valid=true;
                        }else {
                            System.out.println("Ingrese un ID valido.");
                        }
                    }

                    break;
                case 2:
                    if (platos.isEmpty()){
                        System.out.println("Debe agregar al menos un plato.");
                    }else {
                        System.out.println("Platos cargados con exito.");
                        salir=true;
                    }

                    break;
            }
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
                    tipoPagoValido=true;
                    break;
                case 2:
                    tipoPago = TipoPago.DEBITO;
                    tipoPagoValido=true;
                    break;
                case 3:
                    tipoPago = TipoPago.CREDITO;
                    tipoPagoValido=true;
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

        horaEmision = LocalDateTime.now();

        ticket = new Ticket(res, empleado, horaEmision, platos, cliente, precio, tipoPago);
        return ticket;
    }


    //TICKET TO JSON

    public JSONObject toJson (Ticket t){
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONObject();
            jsonObject.put("id", t.getId());
            Reserva reserva = t.getReserva();
            JSONObject reservaObj = t.getReserva().toJson(reserva);
            jsonObject.put("reserva", reservaObj);
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
            jsonObject.put("cliente", t.getCliente());
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
                Reserva reserva = new Reserva();
                reserva=reserva.jsonToReserva(reservaJson);
                ticketLeido.setReserva(reserva);
                JSONObject empleadoJson = json.getJSONObject("empleado");
                Empleado empleado = null;
                if (empleadoJson.has("aniosAntiguedad")) {
                    empleado = new EmpleadoTiempoCompleto().jsonToEmpleadoTC(empleadoJson);
                } else if (empleadoJson.has("horasTrabajadas") && empleadoJson.has("precioPorHora")) {
                    empleado = new EmpleadoMedioTiempo().jsonToEmpleadoMT(empleadoJson);
                }
                ticketLeido.setEmpleado(empleado);
                ticketLeido.setHoraEmision(LocalDateTime.parse(json.getString("hora")));
                JSONArray platosArray = json.getJSONArray("platos");
                List<Plato> platos = new ArrayList<>();
                for (int i = 0; i < platosArray.length(); i++) {
                    JSONObject platoJson = platosArray.getJSONObject(i);
                    Plato plato = new Plato().jsonToPlato(platoJson);
                    platos.add(plato);
                }
                ticketLeido.setPlatos(platos);
                ticketLeido.setPrecio(json.getDouble("precio"));
                ticketLeido.setTipoPago(TipoPago.valueOf(json.getString("tipoPago")));
                ticketLeido.setCliente(json.getInt("cliente"));
            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un ticket.");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return ticketLeido;
    }


    public void mostrarTicket (Ticket t){

        System.out.println("*******************************************************************");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("                  RESTAURANTE GRASTROLAB S.A");
        System.out.println("       Emitido en: Av. Dorrego 281, Mar del Plata, Argentina");
        System.out.println();
        System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido());
        System.out.println("T" + id + "                             " + horaEmision);
        System.out.println("-------------------------------------------------------------------");
        int cant = mostrarPlatos();
        System.out.println("-------------------------------------------------------------------");
        System.out.println(cant + " productos                                      TOTAL: $" + precio);

        if (tipoPago == TipoPago.EFECTIVO){
            System.out.println("                                        Pago en efectivo: $" + precio);
        } else if (tipoPago == TipoPago.DEBITO){
            System.out.println("                                        Pago con debito: $" + precio);
        }else if(tipoPago == TipoPago.CREDITO){
            System.out.println("                                        Impuesto 20%: $" + calcularInteres(precio));
            System.out.println("                                        Pago con credito: $" + calcularPrecioInteres(precio));
        }
        System.out.println("******************************************************************");
        System.out.println("             ▌│█║▌║▌║║▌║▌║█│▌║▌║▌║█│▌▌║█│▌║▌║▌║█│");
        System.out.println("                *** GRACIAS POR SU VISITA ***   ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("******************************************************************");

    }

    public int mostrarPlatos (){

        int cant = 0;

        for (Plato p : platos){
            System.out.println(p.getId() + " | " + p.getNombre() + "                   $ " + p.getPrecio());
            cant++;
        }

        return cant;
    }

    /**
     * calcularInteres calcula el interés adicional aplicable a un precio total en caso
     * de que el método de pago sea con tarjeta de crédito. El interés se calcula como
     * el 20% del precio total.
     *
     * @param precioTotal el precio total de los productos consumidos.
     * @return el monto correspondiente al interés calculado.
     */
    public double calcularInteres (double precioTotal){
        return precioTotal*0.2;
    }


    /**
     * calcularPrecioInteres calcula el precio total con el interés incluido. Utiliza
     * el método calcularInteres para determinar el monto del interés y lo suma al
     * precio total original.
     *
     * @param precioTotal el precio total de los productos consumidos.
     * @return el precio total incluyendo el interés calculado.
     */
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
        return "Ticket{" +
                "id=" + id +
                ", reserva=" + reserva +
                ", empleado=" + empleado +
                ", horaEmision=" + horaEmision +
                ", platos=" + platos +
                ", precio=" + precio +
                ", tipoPago=" + tipoPago +
                ", cliente=" + cliente +
                '}';
    }
}
