package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Reserva;
import Users.Cliente;
import Users.RegistroUser;
import Users.TipoCliente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * La clase Gestion.GestionReserva maneja las reservas realizadas por los clientes.
 * Permite agregar reservas, actualizar el tipo de cliente en función de la cantidad
 * de reservas, obtener el tipo de cliente y la cantidad de reservas realizadas por cada cliente.
 *
 * Los métodos principales de esta clase incluyen la adición de reservas, el seguimiento de la cantidad
 * de reservas por cliente y la actualización del tipo de cliente (ESTANDAR, PREMIUM, VIP).
 *
 * @author Melina
 * @since 2024
 * @version 1
 */

public class GestionReserva implements MetodosBasicosGestion<Reserva>{
    private Map<Integer, Reserva> reservasPorCliente;
    private Scanner scanner;

    public GestionReserva() {
        this.reservasPorCliente = new HashMap<>();
        GestionJSON.crearArchivoJSON("reservas.json");
        this.scanner=new Scanner(System.in);
    }

    @Override
    public void ingresarUsuario() {
        System.out.println();
        boolean valido = false;
        while (!valido){
            Reserva aux = null;
            aux.ingresarReserva();
            if (verificarDisponibilidad(aux.getMesa(), aux.getDia(), aux.getHora())){
                agregarYguardar(aux);
                System.out.println("\nReserva " + aux.getId() + "de " + aux.getCliente().getNombre() + " " + aux.getCliente().getApellido() + " agregado con exito!");
                valido=true;
            }else {
                System.out.println("Hubo un problema, la mesa seleccionada ya esta ocupada.");
            }
        }
    }

    public boolean verificarDisponibilidad(int mesa, LocalDate dia, LocalTime hora) {
        for (Reserva reserva : reservasPorCliente.values()) {
            if (reserva.getMesa() == mesa &&
                    reserva.getDia().equals(dia) &&
                    reserva.getHora().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    public Map<Integer, Cliente> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("reservas.json");

        try {

            JSONArray arreglo = new JSONArray(aux);
            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Cliente cliente = new Cliente();
                cliente = cliente.jsonToCliente(aux1);
                listaDeClientes.add(cliente);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Cliente.");
        }

        return listaDeClientes;
    }

    public void agregarYguardar (Cliente nuevoCliente){
        cargarArrayConArchivo();
        listaDeClientes.add(nuevoCliente);
        cargarArchivoConArreglo(listaDeClientes);
    }

    public void cargarArchivoConArreglo(List<Cliente> listaDeClientes){
        JSONArray arreglo = new JSONArray();
        try {

            for (Cliente cliente : listaDeClientes){
                try {
                    JSONObject json = cliente.toJson(cliente);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("clientes.json", arreglo);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void agregarYguardar (Reserva nuevaReserva){
        //cargarMapConArchivo();
        //reservasPorCliente.put(nuevaReserva);
       // cargarArchivoConMap(reservasPorCliente);
    }


    @Override
    public void mostrarDatosUsuario(Reserva reserva) {

    }

    @Override
    public Reserva modificarUsuario(Reserva reserva) {
        return null;
    }

    @Override
    public void darDeBajaUsuario(Reserva reserva) {

    }

    @Override
    public void mostrarColeccion() {

    }

    @Override
    public Reserva encontrarUsuario(String dni) {
        return null;
    }

    @Override
    public Reserva encontrarUsuario(int id) {
        return null;
    }

    @Override
    public void listarUsuarios(String nombre) {

    }

    @Override
    public void listarUsuarios(boolean aux) {

    }

    /**
     * Agrega una reserva al registro y actualiza la cantidad de reservas del cliente.
     * Además, actualiza el tipo de cliente según el número de reservas.
     *
     * @param reserva la reserva que se va a agregar.
     */
    public void agregarReserva(Reserva reserva) {
        Cliente cliente = reserva.getCliente();

        // Contar las reservas del cliente
        int cantidadReservas = reservasPorCliente.getOrDefault(cliente, 0) + 1;
        reservasPorCliente.put(cliente, cantidadReservas);

        // Actualizar el tipo de cliente basado en la cantidad de reservas
        actualizarTipoCliente(cliente, cantidadReservas);
    }

    /**
     * Actualiza el tipo de cliente según la cantidad de reservas realizadas.
     *
     * @param cliente el cliente cuya categoría se desea actualizar.
     * @param cantidadReservas la cantidad actual de reservas realizadas por el cliente.
     */
    private void actualizarTipoCliente(Cliente cliente, int cantidadReservas) {
        if (cantidadReservas >= 10) {
            cliente.setTipoCliente(TipoCliente.VIP);
        } else if (cantidadReservas >= 5) {
            cliente.setTipoCliente(TipoCliente.PREMIUM);
        } else {
            cliente.setTipoCliente(TipoCliente.ESTANDAR);
        }
    }

    /**
     * Obtiene el tipo de cliente según la cantidad de reservas realizadas.
     *
     * @param cliente el cliente cuya categoría se desea determinar.
     * @return el tipo de cliente basado en el número de reservas.
     */
    public TipoCliente obtenerTipoCliente(Cliente cliente) {
        return cliente.getTipoCliente();  // Ya está actualizado al agregar la reserva
    }

    /**
     * Devuelve la cantidad de reservas realizadas por un cliente.
     *
     * @param cliente el cliente cuya cantidad de reservas se desea obtener.
     * @return la cantidad de reservas del cliente.
     */
    public int obtenerCantidadDeReservas(Cliente cliente) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }
        int i = 0;
        Iterator it = reservasPorCliente.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<Integer, Reserva> map = (Map.Entry<Integer, Reserva>) it.next();
            if(map.getValue().getCliente().equals(cliente)){
                i++;
            }
        }
        return i;
    }

    /**
     * Devuelve un mapa de todos los clientes con su cantidad de reservas.
     *
     * @return un mapa donde las claves son los clientes y los valores son sus cantidades de reservas.
     */
    public Map<Cliente, Integer> obtenerReservasPorCliente() {
        return new HashMap<>(reservasPorCliente);
    }
}
