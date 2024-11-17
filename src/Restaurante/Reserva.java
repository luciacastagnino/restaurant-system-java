package Restaurante;

import Archivos.FormatoIncorrectoException;
import Gestion.GestionDeCliente;
import Users.Cliente;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * La clase Restaurante.Reserva tiene como campos su id, momento de realizacion, dia de reserva,
 * hora de reserva, cliente, mesa y cantidad de personas
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */

public class Reserva {
    private static int contador = 0;
    private int id;
    private LocalDateTime momento;
    private LocalDate dia;
    private LocalTime hora;
    private Cliente cliente;
    private int mesa;
    private int cantPersonas;
    private boolean estado;
    private GestionDeCliente gestionDeCliente;

    public Reserva() {
    }

    public Reserva(LocalDate dia, LocalTime hora, Cliente cliente, int mesa, int cantPersonas) {
        this.id = ++contador;
        this.momento = LocalDateTime.now();
        this.dia = dia;
        this.hora = hora;
        this.cliente = cliente;
        this.mesa = mesa;
        this.cantPersonas = cantPersonas;
        this.estado = true;
        this.gestionDeCliente = new GestionDeCliente();
    }

    ///Getters y Setters

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Reserva.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public void setMomento(LocalDateTime momento) {
        this.momento = momento;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public GestionDeCliente getGestionDeCliente() {
        return gestionDeCliente;
    }

    public void setGestionDeCliente(GestionDeCliente gestionDeCliente) {
        this.gestionDeCliente = gestionDeCliente;
    }

    // RESERVA TO JSON

    /**
     * toJson es un metodo que permite transformar un objeto RESERVA a un JSONObject, recibe una
     * Reserva y retorna u JSONObject.
     * @param e
     * @return jsonObject
     */
    public JSONObject toJson (Reserva e){

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("id", e.getId());
            jsonObject.put("momento", e.getMomento().toString());
            jsonObject.put("dia", e.getDia().toString());
            jsonObject.put("hora", e.getHora().toString());
            jsonObject.put("cliente", e.getCliente().toJson(e.cliente));
            jsonObject.put("mesa", e.getMesa());
            jsonObject.put("cantPersonas", e.getCantPersonas());
            jsonObject.put("estado", e.getEstado());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //RESERVA TO JSON

    /**
     * jsonToReserva es un metodo que tranforma un JSONObject en un objeto Reserva recibe un
     * JSONObject y retorna una Reserva, lanza un FormatoIncorrectoException si el formato del
     * JSONObject no tiene los parametros de un Reserva.
     * @param json
     * @return reservaLeida
     * @throws FormatoIncorrectoException
     */

    public Reserva jsonToReserva (JSONObject json) throws FormatoIncorrectoException {

        Reserva reservaLeida = new Reserva();
        try {
            if(json.has("id") && json.has("momento") && json.has("dia") &&
                    json.has("hora") && json.has("cliente") && json.has("mesa") &&
                    json.has("cantPersonas") && json.has("estado")){
                reservaLeida.setId(json.getInt("id"));
                reservaLeida.setMomento(LocalDateTime.parse(json.getString("momento")));
                reservaLeida.setDia(LocalDate.parse(json.getString("dia")));
                reservaLeida.setHora(LocalTime.parse(json.getString("hora")));
                JSONObject jsonObject = json.getJSONObject("cliente");
                Cliente cliente = new Cliente();
                cliente.jsonToCliente(jsonObject);
                reservaLeida.setCliente(cliente);
                reservaLeida.setMesa(json.getInt("mesa"));
                reservaLeida.setCantPersonas(json.getInt("cantPersonas"));
                reservaLeida.setEstado(json.getBoolean("estado"));
            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un Users.Cliente");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return reservaLeida;
    }

    public Reserva ingresarReserva() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Complete con los datos:\n");

        LocalDate dia = null;
        boolean diaValido = false;
        while (!diaValido) {
            System.out.println("Por favor, ingresa el día (formato: dd/MM/yyyy):");
            String diaInput = scanner.nextLine();
            DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                dia = LocalDate.parse(diaInput, formatoDia);
                diaValido = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: El formato del día no es correcto.");
            }
        }

        LocalTime hora = null;
        boolean horaValida = false;
        while (!horaValida) {
            System.out.println("Ingrese la hora (formato: HH:mm):");
            String horaInput = scanner.nextLine();
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
            try {
                hora = LocalTime.parse(horaInput, formatoHora);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: El formato de la hora no es correcto.");
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

        System.out.println("Ingrese la mesa:");
        int mesa = scanner.nextInt();

        System.out.println("Ingrese la cantidad de personas:");
        int cantPersonas = scanner.nextInt();

        return new Reserva(dia, hora, cliente, mesa, cantPersonas);
    }

    //ToString


    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", momento=" + momento +
                ", dia=" + dia +
                ", hora=" + hora +
                ", cliente=" + cliente +
                ", mesa=" + mesa +
                ", cantPersonas=" + cantPersonas +
                ", estado=" + estado +
                '}';
    }
}
