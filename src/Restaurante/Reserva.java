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
import java.util.Random;
import java.util.Scanner;

/**
 * La clase Restaurante.Reserva tiene como campos su id, momento de realizacion, dia de reserva,
 * hora de reserva, cliente, mesa y cantidad de personas
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
 * otros metodos: convertir la reserva a formato JSON y viceversa
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */

public class Reserva {
    private final Random random = new Random();
    private int id;
    private LocalDateTime momento;
    private LocalDate dia;
    private LocalTime hora;
    private int cliente;
    private int mesa;
    private int cantPersonas;
    private boolean estado;
    private GestionDeCliente gestionDeCliente;

    public Reserva() {
    }

    public Reserva(LocalDate dia, LocalTime hora, int idCliente, int mesa, int cantPersonas) {
        this.id = random.nextInt(1000000)+100;;
        this.momento = LocalDateTime.now();
        this.dia = dia;
        this.hora = hora;
        this.cliente = idCliente;
        this.mesa = mesa;
        this.cantPersonas = cantPersonas;
        this.estado = true;
        this.gestionDeCliente = new GestionDeCliente();
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

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
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
            jsonObject.put("cliente", e.getCliente());
            jsonObject.put("mesa", e.getMesa());
            jsonObject.put("cantPersonas", e.getCantPersonas());
            jsonObject.put("estado", e.getEstado());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

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
                reservaLeida.setCliente(json.getInt("cliente"));
                reservaLeida.setMesa(json.getInt("mesa"));
                reservaLeida.setCantPersonas(json.getInt("cantPersonas"));
                reservaLeida.setEstado(json.getBoolean("estado"));
            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un Users.Cliente");
            }

        }catch (JSONException e) {
        e.printStackTrace();
        throw new FormatoIncorrectoException("Error al parsear el JSON: " + e.getMessage());
        } catch (DateTimeParseException e) {
        e.printStackTrace();
        throw new FormatoIncorrectoException("Error en el formato de fecha: " + e.getMessage());
        }
        return reservaLeida;
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
