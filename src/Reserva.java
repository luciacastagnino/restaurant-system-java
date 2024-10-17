import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * La clase Reserva tiene como campos su id, momento de realizacion, dia de reserva,
 * hora de reserva, cliente, mesa y cantidad de personas
 * tiene un constructor con todos los atributos
 * metodos:
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
    private int estado;

    public Reserva(LocalDate dia, LocalTime hora, Cliente cliente, int mesa, int cantPersonas) {
        this.id = ++contador;
        this.momento = LocalDateTime.now();
        this.dia = dia;
        this.hora = hora;
        this.cliente = cliente;
        this.mesa = mesa;
        this.cantPersonas = cantPersonas;
        this.estado = 1;
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

    public LocalDateTime getMomento() {
        return momento;
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
}

