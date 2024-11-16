package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Reserva;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                System.out.println("Cliente: " + a.getCliente().getNombre() + " " + a.getCliente().getApellido());
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

        for (Reserva reserva : reservasPorCliente.values()) {
            if (c.getId() == reserva.getId()) {
                reservasPorCliente.remove(reserva);
                c=reserva;
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

                            String username = "";
                            boolean usernameValido = false;

                            while (!usernameValido) {
                                System.out.println("Ingrese su nuevo username: ");
                                username = scanner.nextLine();
                                try {
                                    Validaciones.validarNombreUsuario(username);
                                    c.setUsername(username);
                                    usernameValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 2:

                            String contrasenia = "";
                            boolean contraseniaValida = false;

                            System.out.println("Ingrese su contraseña actual:");
                            String contraseñaActual = scanner.nextLine();
                            if (c.getContrasenia().equals(contraseñaActual)){
                                while (!contraseniaValida) {
                                    System.out.println("Ingrese su nueva contrasenia: ");
                                    contrasenia = scanner.nextLine();
                                    try {
                                        Validaciones.validarContrasenia(contrasenia);
                                        c.setContrasenia(contrasenia);
                                        contraseniaValida = true;
                                    } catch (DatoInvalidoException e) {
                                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                    }
                                }
                            }

                            break;
                        case 3:

                            String nombre = "";
                            boolean nombreValido = false;

                            while (!nombreValido) {
                                System.out.println("Ingrese su nuevo nombre: ");
                                nombre = scanner.nextLine();
                                try {
                                    Validaciones.validarCadenas(nombre);
                                    c.setNombre(nombre);
                                    nombreValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 4:

                            String apellido = "";
                            boolean apellidoValido = false;

                            while (!apellidoValido) {
                                System.out.println("Ingrese su nuevo apellido: ");
                                apellido = scanner.nextLine();
                                try {
                                    Validaciones.validarCadenas(apellido);
                                    c.setApellido(apellido);
                                    apellidoValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 5:

                            String dni = "";
                            boolean dniValido = false;

                            while (!dniValido) {
                                System.out.println("Ingrese su nuevo DNI: ");
                                dni = scanner.nextLine();
                                try {
                                    Validaciones.validarDNI(dni);
                                    c.setDni(dni);
                                    dniValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 6:

                            String telefono = "";
                            boolean telefonoValido = false;

                            while (!telefonoValido) {
                                System.out.println("Ingrese su nuevo telefono: ");
                                telefono = scanner.nextLine();
                                try {
                                    Validaciones.validarTelefono(telefono);
                                    c.setTelefono(telefono);
                                    telefonoValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 7:

                            String direccion = "";
                            boolean direccionValido = false;

                            while (!direccionValido) {
                                System.out.println("Ingrese su nueva direccion: ");
                                direccion = scanner.nextLine();
                                try {
                                    Validaciones.validarDireccion(direccion);
                                    c.setDireccion(direccion);
                                    direccionValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 8:

                            String email = "";
                            boolean emailValido = false;

                            while (!emailValido) {
                                System.out.println("Ingrese su nuevo email: ");
                                email = scanner.nextLine();
                                try {
                                    Validaciones.validarEmail(email);
                                    c.setEmail(email);
                                    emailValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 9:
                            System.out.println("Saliendo del menu de modificacion de usuario...");
                            salir = true;
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                }
                listaDeClientes.add(c);
                cargarArchivoConArreglo(listaDeClientes);
                System.out.println("¡Cambios guardados con exito!");
                return c;
            }
        }
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
