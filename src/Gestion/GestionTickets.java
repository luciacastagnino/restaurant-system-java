package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Reserva;
import Restaurante.Ticket;
import Users.Administrador;
import Users.RegistroUser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GestionTickets implements MetodosBasicosGestion <Ticket>{

    private Set<Ticket> ticketSet;
    private Scanner scanner;

    public GestionTickets() {
        this.ticketSet = new HashSet<>();
        this.scanner = new Scanner(System.in);
        GestionJSON.crearArchivoJSON("tickets.json");
    }

    public void ingresarUsuario(){
        System.out.println();
        Ticket aux = new Ticket();
        aux.ingresarTicket();
        agregarYguardar(aux);
        System.out.println("\nTicket cargado con exito!");

    }

    public Set<Ticket> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("tickets.json");

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

    public void cargarArchivoConArreglo(Set<Ticket> ticketSet){
        JSONArray arreglo = new JSONArray();
        try {

            for (Ticket t : ticketSet){
                try {
                    JSONObject json = t.toJson(t);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("tickets.json", arreglo);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    @Override
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

    @Override
    public Ticket modificarUsuario(Ticket ticket) {
        return null;
    }

    @Override
    public void darDeBajaUsuario(Ticket ticket) {

    }

    @Override
    public void mostrarColeccion() {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            t.mostrarTicket(t);
        }
    }

    @Override
    public Ticket encontrarUsuario(String dni) {
        if (ticketSet.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Ticket t : ticketSet){
            if (t.getCliente().getDni().equals(dni)){
                return t;
            }
        }

        return null;
    }

    @Override
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

    @Override
    public void listarUsuarios(String dni) {
        if (ticketSet.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Ticket ticket : ticketSet){
            if (ticket.getCliente().getDni().equals(dni)){
                mostrarDatosUsuario(ticket);
            }
        }
    }

    @Override
    public void listarUsuarios(boolean aux) {
        if (ticketSet.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Ticket ticket : ticketSet){
            if (ticket.getReserva().getEstado() == aux){
                mostrarDatosUsuario(ticket);
            }
        }
    }
}
