package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Reserva;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    private GestionDeCliente gestionDeCliente;
    private RegistroUser registroUser;

    public GestionReserva() {
        this.reservasPorCliente = new HashMap<>();
        GestionJSON.crearArchivoJSON("reservas.json");
        this.scanner=new Scanner(System.in);
        this.gestionDeCliente=new GestionDeCliente();
        this.registroUser = new RegistroUser();
    }

    @Override
    public void ingresarUsuario() {
        System.out.println();
        boolean valido = false;
        while (!valido){
            Reserva aux = null;
            aux = registroUser.ingresarReserva();
            if (verificarDisponibilidad(aux.getMesa(), aux.getDia(), aux.getHora())){
                agregarYguardar(aux);
                Cliente cliente = gestionDeCliente.encontrarUsuario(aux.getCliente());
                System.out.println("\nReserva " + aux.getId() + "de " + cliente.getNombre() + " " + cliente.getApellido() + " agregado con exito!");
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

    public Map<Integer, Reserva> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("reservas.json");

        try {

            JSONArray arreglo = new JSONArray(aux);
            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Reserva reserva = new Reserva();
                reserva = reserva.jsonToReserva(aux1);
                reservasPorCliente.put(reserva.getId(), reserva);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Reserva.");
        }

        return reservasPorCliente;
    }

    public void agregarYguardar (Reserva reserva){
        cargarArrayConArchivo();
        reservasPorCliente.put(reserva.getId(), reserva);
        cargarArchivoConArreglo(reservasPorCliente);
    }

    public void cargarArchivoConArreglo(Map<Integer, Reserva> listadeReservas){
        JSONArray arreglo = new JSONArray();
        try {
            for (Reserva reserva : listadeReservas.values()){
                try {
                    JSONObject json = reserva.toJson(reserva);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("reservas.json", arreglo);
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
    public void mostrarDatosUsuario(Reserva a) {
        reservasPorCliente = cargarArrayConArchivo();
        DateTimeFormatter diaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Reserva r: reservasPorCliente.values()){
            if (r.getId() == a.getId()){
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("RESERVA N°:" + a.getId());
                System.out.println("--------------------------------------------");
                System.out.println("ID: " + a.getId());
                System.out.println("Generado el: " + a.getMomento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                System.out.println("Fecha de reserva: " + a.getDia().format(diaFormatter));
                System.out.println("Hora de reserva: " + a.getHora().format(horaFormatter));
                Cliente cliente = gestionDeCliente.encontrarUsuario(a.getCliente());
                System.out.println("Cliente: " + cliente.getNombre() + " " + cliente.getApellido());
                System.out.println("Mesa: " + a.getMesa());
                System.out.println("Cantidad de personas: " + a.getCantPersonas());
                System.out.println("--------------------------------------------");

                return;
            }
        }

        System.out.printf("No se encontro al usuario.");
    }

    public Reserva modificarUsuario (Reserva c) {

        reservasPorCliente = cargarArrayConArchivo();
        boolean salir = false;

            if (reservasPorCliente.containsKey(c.getId())) {
                Reserva reserva = reservasPorCliente.get(c.getId());

                while (!salir) {
                    System.out.println("\n Que desea modificar?");
                    System.out.println("1. Dia de reserva.");
                    System.out.println("2. Hora de reserva.");
                    System.out.println("3. Cliente.");
                    System.out.println("4. Mesa.");
                    System.out.println("5. Cantidad de personas.");
                    System.out.println("6. Salir.");
                    int op = scanner.nextInt();
                    scanner.nextLine();
                    switch (op) {
                        case 1:
                            LocalDate dia = null;
                            boolean diaValido = false;
                            while (!diaValido) {
                                System.out.println("Por favor, ingresa el día (formato: dd/MM):");
                                String diaInput = scanner.nextLine();
                                DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM");
                                try {
                                    dia = LocalDate.parse(diaInput, formatoDia);
                                    c.setDia(dia);
                                    diaValido = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato del día no es correcto.");
                                }
                            }

                            break;
                        case 2:

                            LocalTime hora = null;
                            boolean horaValida = false;
                            while (!horaValida) {
                                System.out.println("Ingrese la hora (formato: HH:mm):");
                                String horaInput = scanner.nextLine();
                                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
                                try {
                                    hora = LocalTime.parse(horaInput, formatoHora);
                                    c.setHora(hora);
                                    horaValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("Error: El formato de la hora no es correcto.");
                                }
                            }

                            break;
                        case 3:

                            Cliente cliente = null;
                            boolean valido = false;
                            while (!valido) {
                                System.out.println();
                                System.out.println("¿Qué tipo de cliente desea ingresar?");
                                System.out.println("1. Cliente existente.");
                                System.out.println("2. Crear nuevo cliente.");
                                int opcion = scanner.nextInt();
                                scanner.nextLine();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Ingrese el DNI del cliente: ");
                                        String dni = scanner.nextLine();
                                        cliente = gestionDeCliente.encontrarUsuario(dni);
                                        if (cliente != null) {
                                            c.setCliente(cliente.getId());
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
                                            c.setCliente(cliente.getId());
                                            valido = true;
                                        } else {
                                            System.out.println("No se encontró el cliente.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Opción incorrecta, ingrese una opción válida.");
                                }
                            }

                            break;
                        case 4:
                            boolean estado = false;
                            while (!estado){
                                System.out.println("Ingrese la mesa:");
                                int mesa = scanner.nextInt();
                                estado = verificarDisponibilidad(mesa, c.getDia(), c.getHora());
                                if (!estado){
                                    System.out.println("La mesa no esta disponible, ingrese otra.");
                                }else {
                                    c.setMesa(mesa);
                                    estado=true;
                                }
                            }
                            break;
                        case 5:
                            boolean cantPersonasValido = false;
                            while (!cantPersonasValido){
                                System.out.println("Ingrese la cantidad de personas:");
                                int cantPersonas = scanner.nextInt();
                                if (cantPersonas<=0){
                                    System.out.println("La cantidad de personas no puede ser 0");
                                }else {
                                     c.setCantPersonas(cantPersonas);
                                     cantPersonasValido=true;
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

                reservasPorCliente.put(reserva.getId(), reserva);
                cargarArchivoConArreglo(reservasPorCliente);
                System.out.println("¡Cambios guardados con exito!");
                return reserva;
            }else {
                System.out.println("No se encontro la reserva.");
        }
        return null;
    }

    @Override
    public void darDeBajaUsuario(Reserva a) {
        reservasPorCliente = cargarArrayConArchivo();

        for (Reserva reserva : reservasPorCliente.values()) {
            String opcion = null;
            if (a.equals(reserva)) {
                System.out.println("¿Esta seguro de eliminar la reserva? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setEstado(false);
                    System.out.println("Reserva eliminada con exito.");
                    cargarArchivoConArreglo(reservasPorCliente);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro la reserva.");
    }

    @Override
    public void mostrarColeccion() {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }

        for (Reserva r : reservasPorCliente.values()){
            mostrarDatosUsuario(r);
        }
    }

    @Override
    public Reserva encontrarUsuario(String dni) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }

        Cliente cliente = null;
        for (Reserva reserva : reservasPorCliente.values()){
            if (gestionDeCliente.encontrarUsuario(reserva.getCliente()).getDni().equals(dni)){
                return reserva;
            }
        }

        System.out.println("No se encontro la reserva.");
        return null;
    }

    public Reserva encontrarUsuario(String dni, LocalDate dia, LocalTime hs) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }

        for (Reserva reserva : reservasPorCliente.values()){
            if (gestionDeCliente.encontrarUsuario(reserva.getCliente()).getDni().equals(dni) && reserva.getDia().equals(dia)
                    && reserva.getHora().equals(hs)){
                return reserva;
            }
        }

        System.out.println("No se encontro la reserva.");
        return null;
    }

    @Override
    public void listarUsuarios(String dni) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Reserva reserva : reservasPorCliente.values()){
            if (gestionDeCliente.encontrarUsuario(reserva.getCliente()).getDni().equals(dni)){
                mostrarDatosUsuario(reserva);
            }
        }
    }

    public void listarUsuarios(LocalDate dia) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Reserva reserva : reservasPorCliente.values()){
            if (reserva.getDia().equals(dia)){
                mostrarDatosUsuario(reserva);
            }
        }
    }

    public void listarUsuarios(LocalTime hora) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Reserva reserva : reservasPorCliente.values()){
            if (reserva.getHora().equals(hora)){
                mostrarDatosUsuario(reserva);
            }
        }
    }

    public void listarUsuarios(LocalDate dia, LocalTime hora) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }
        for (Reserva reserva : reservasPorCliente.values()){
            if (reserva.getHora().equals(hora) && reserva.getDia().equals(dia)){
                mostrarDatosUsuario(reserva);
            }
        }
    }

    @Override
    public void listarUsuarios(boolean aux) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }

        for (Reserva reserva : reservasPorCliente.values()){
            if (reserva.getEstado() == aux){
                mostrarDatosUsuario(reserva);
            }
        }
    }

    @Override
    public void darDeAltaUsuario(Reserva a) {
        reservasPorCliente = cargarArrayConArchivo();

        for (Reserva reserva : reservasPorCliente.values()) {
            String opcion = null;
            if (a.equals(reserva)) {
                System.out.println("¿Esta seguro de dar de alta la reserva? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setEstado(true);
                    System.out.println("Reserva agregada con exito.");
                    cargarArchivoConArreglo(reservasPorCliente);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro la reserva.");
    }

    @Override
    public Reserva encontrarUsuario(int id) {
        if (reservasPorCliente.isEmpty()) {
            cargarArrayConArchivo();
        }

        for (Reserva reserva : reservasPorCliente.values()){
            if (reserva.getId() == id){
                return reserva;
            }
        }

        System.out.println("No se encontro la reserva.");
        return null;
    }

    /// NO SE SI LO DE ABAJO FUNCIONA, ME TIRA ERROR

    /**
     * Agrega una reserva al registro y actualiza la cantidad de reservas del cliente.
     * Además, actualiza el tipo de cliente según el número de reservas.
     *
     * @param reserva la reserva que se va a agregar.
     */
    /*
    public void agregarReserva(Reserva reserva) {
        Cliente cliente = reserva.getCliente();

        // Contar las reservas del cliente
        int cantidadReservas = reservasPorCliente.getOrDefault(cliente, 0) + 1;
        reservasPorCliente.put(cliente, cantidadReservas);

        // Actualizar el tipo de cliente basado en la cantidad de reservas
        actualizarTipoCliente(cliente, cantidadReservas);
    }*/
/*
    /**
     * Actualiza el tipo de cliente según la cantidad de reservas realizadas.
     *
     * @param cliente el cliente cuya categoría se desea actualizar.
     * @param cantidadReservas la cantidad actual de reservas realizadas por el cliente.
     */
    /*private void actualizarTipoCliente(Cliente cliente, int cantidadReservas) {
        if (cantidadReservas >= 10) {
            cliente.setTipoCliente(TipoCliente.VIP);
        } else if (cantidadReservas >= 5) {
            cliente.setTipoCliente(TipoCliente.PREMIUM);
        } else {
            cliente.setTipoCliente(TipoCliente.ESTANDAR);
        }
    }

*/
    /**
     * Obtiene el tipo de cliente según la cantidad de reservas realizadas.
     *
     * @param cliente el cliente cuya categoría se desea determinar.
     * @return el tipo de cliente basado en el número de reservas.
     */
   /* public TipoCliente obtenerTipoCliente(Cliente cliente) {
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
            if(gestionDeCliente.encontrarUsuario(map.getValue().getCliente()).equals(cliente)){
                i++;
            }
        }
        return i;
    }

}
