package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Plato;
import Restaurante.Reserva;
import Restaurante.Ticket;
import Restaurante.TipoPago;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDateTime;
import java.util.*;

/**
 * La clase Gestion.GestionTickets gestiona un conjunto de tickets de la aplicación.
 *
 * - Tiene como campos una List de Tickets (`ticketSet`), un objeto `scanner` para la lectura de entradas del usuario
 *   y un objeto `GestionDeCliente` para manejar información de los clientes relacionados con los tickets.
 * - Tiene un constructor vacío.
 * - Interactúa con un archivo JSON para guardar y cargar datos.
 * - Incluye métodos para agregar, modificar, buscar y listar tickets.
 * - Permite modificar atributos de un ticket como la reserva, el empleado, la hora de emisión, los platos y el tipo de pago.
 *
 * @author Melina
 * @since 2024
 * @version 1
 */

public class GestionTickets{

    private List<Ticket> ticketSet;
    private Scanner scanner;
    private final GestionDeCliente gestionDeCliente;

    public GestionTickets() {
        this.ticketSet = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        GestionJSON.crearArchivoJSON("tickets.json");
        this.gestionDeCliente = new GestionDeCliente();
    }

    public void ingresarUsuario(){
        System.out.println();
        Ticket aux = new Ticket();
        aux=aux.ingresarTicket();
        agregarYguardar(aux);
        System.out.println("\nTicket cargado con exito!");
    }


    /**
     * cargarArrayConArchivo carga los tickets desde un archivo JSON y los convierte en objetos Ticket.
     *
     * @return una lista de objetos Ticket cargados desde el archivo JSON.
     */
    public List<Ticket> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("tickets.json");

        ticketSet.clear();

        try {
            JSONArray arreglo = new JSONArray(aux);
            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Ticket ticket = new Ticket();
                ticket = ticket.jsonToTicket(aux1);
                ticketSet.add(ticket);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Ticket.");
        }

        return ticketSet;
    }

    public void agregarYguardar (Ticket ticket){
        cargarArrayConArchivo();
        ticketSet.add(ticket);
        cargarArchivoConArreglo(ticketSet);
    }

    /**
     * cargarArchivoConArreglo guarda los tickets de una lista en un archivo JSON.
     * Este método recorre la lista de tickets (`ticketSet`), convierte cada objeto Ticket
     * en un JSON utilizando el método `toJson` y agrega los objetos JSON a un arreglo.
     * El arreglo se guarda en el archivo `tickets.json` utilizando la clase `GestionJSON`.
     *
     * @param ticketSet la lista de objetos Ticket que se desean guardar en el archivo JSON.
     */
    public void cargarArchivoConArreglo(List<Ticket> ticketSet){
        JSONArray arreglo = new JSONArray();
        try {

            for (Ticket t : ticketSet){
                try {
                    JSONObject json = t.toJson(t);
                    arreglo.put(json);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
            GestionJSON.agregarElemento("tickets.json", arreglo);
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void mostrarDatosUsuario(Ticket ticket) {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            if (t.getId() == ticket.getId()){
                t.mostrarTicket(t);
            }
        }
    }

    public Ticket modificarUsuario (Ticket t) {

        ticketSet = cargarArrayConArchivo();
        boolean salir = false;

        for (Ticket ticket : ticketSet) {
            if (t.getId() == ticket.getId()) {
                ticketSet.remove(ticket);
                t=ticket;
                while (!salir) {
                    System.out.println("\n Que desea modificar?");
                    System.out.println("1. Reserva.");
                    System.out.println("2. Empleado.");
                    System.out.println("3. Hora emision.");
                    System.out.println("4. Platos.");
                    System.out.println("5. Tipo pago.");
                    System.out.println("6. Salir.");
                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1:

                            GestionReserva gestionReserva = new GestionReserva();
                            Reserva res = new Reserva();
                            int cliente = 0;
                            boolean resValida = false;
                            while (!resValida) {
                                System.out.println("Por favor, ingresa ID de la reserva:");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                res = gestionReserva.encontrarUsuario(id);
                                if (!t.validarReserva(res)){
                                    System.out.println("No se encontro la reserva en el sistema");
                                }else{
                                    System.out.println("Reserva encontrada.");
                                    gestionReserva.darDeBajaReservaTicket(res);
                                    cliente = res.getCliente();
                                    if (cliente!=0){
                                        resValida = true;
                                    }
                                }
                            }

                            break;
                        case 2:

                            Empleado empleado = null;
                            GestionEmpleados gestionEmpleados = new GestionEmpleados();
                            boolean empleadoValido = false;
                            while (!empleadoValido){
                                System.out.println("Ingrese el DNI del empleado: ");
                                String dni = scanner.nextLine();
                                empleado = gestionEmpleados.encontrarUsuario(dni);
                                if (empleado != null){
                                    empleadoValido=true;
                                    t.setEmpleado(empleado);
                                }else {
                                    System.out.println("No se encontro el empleado.");
                                }
                            }

                            break;
                        case 3:

                            LocalDateTime horaNow = LocalDateTime.now();
                            t.setHoraEmision(horaNow);

                            break;
                        case 4:

                            List<Plato>platos = new ArrayList<>();
                            double precio = 0;
                            boolean salir2 = false;
                            while (!salir2) {
                                System.out.println("1. Agregar plato.");
                                System.out.println("2. Salir.");
                                int opcion = scanner.nextInt();
                                scanner.nextLine();
                                if (opcion == 1) {
                                    MenuRestaurante menuRestaurante = new MenuRestaurante();
                                    menuRestaurante.listarPlatosTicket();
                                    System.out.println("Ingrese el ID del plato:");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    Plato plato = menuRestaurante.encontrarUsuario(id);
                                    platos.add(plato);
                                } else if (opcion == 2) {
                                    System.out.println("Platos cargados con exito.");
                                    t.setPlatos(platos);
                                    for (Plato p : platos) {
                                        precio += p.getPrecio();
                                    }
                                    if (precio != 0) {
                                        salir2 = true;
                                        t.setPrecio(precio);
                                    } else {
                                        System.out.println("Ocurrio un problema, intentelo de nuevo.");
                                    }
                                }
                            }
                            break;
                        case 5:

                            TipoPago tipoPago = null;
                            boolean tipoPagoValido = false;
                            while (!tipoPagoValido){
                                System.out.println("Seleccione el tipo de pago: ");
                                System.out.println("1. Efectivo.");
                                System.out.println("2. Debito.");
                                System.out.println("3. Credito");
                                int opTipoPago = scanner.nextInt();
                                scanner.nextLine();
                                if (opTipoPago==1){
                                    tipoPago = TipoPago.EFECTIVO;
                                    t.setTipoPago(tipoPago);
                                    tipoPagoValido = true;
                                } else if (opTipoPago==2) {
                                    tipoPago = TipoPago.DEBITO;
                                    t.setTipoPago(tipoPago);
                                    tipoPagoValido=true;
                                }else if (opTipoPago==3){
                                    tipoPago = TipoPago.CREDITO;
                                    t.setTipoPago(tipoPago);
                                    tipoPagoValido=true;
                                }else {
                                    System.out.println("Opcion invalida.");
                                }
                            }
                            break;
                        case 6:
                            System.out.println("Saliendo del menu de modificacion de usuario...");
                            salir = true;
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                }
                ticketSet.add(t);
                cargarArchivoConArreglo(ticketSet);
                System.out.println("¡Cambios guardados con exito!");
                return t;
            }
        }
        return null;
    }

    public void mostrarColeccion() {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            System.out.println();
            t.mostrarTicket(t);
            System.out.println();
        }
    }

    public Ticket encontrarUsuario(String dni) {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            if (gestionDeCliente.encontrarUsuario(t.getCliente()).getDni().equals(dni)){
                return t;
            }
        }
        return null;
    }

    public Ticket encontrarUsuario(int id) {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public void listarUsuarios(String dni) {
        if (ticketSet.isEmpty()) {
            cargarArrayConArchivo();
        }

        boolean encontrado = false;

        for (Ticket ticket : ticketSet){
            if (gestionDeCliente.encontrarUsuario(ticket.getCliente()).getDni().equals(dni)){
                encontrado=true;
                ticket.mostrarTicket(ticket);
            }
        }

        if (!encontrado){
            System.out.println("No se encontraron tickets del cliente.");
        }
    }
}
