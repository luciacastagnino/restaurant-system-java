import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * La clase Reserva tiene como campos su id, momento de realizacion, dia de reserva,
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

    public Reserva(LocalDate dia, LocalTime hora, Cliente cliente, int mesa, int cantPersonas) {
        this.id = ++contador;
        this.momento = LocalDateTime.now();
        this.dia = dia;
        this.hora = hora;
        this.cliente = cliente;
        this.mesa = mesa;
        this.cantPersonas = cantPersonas;
        this.estado = true;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //ToString

    @Override
    public String toString() {
        return "Reserva{" +
                "Nro de reserva=" + id +
                ", Fecha de realizacion=" + momento +
                ", dia=" + dia +
                ", hora=" + hora +
                ", cliente=" + cliente +
                ", mesa=" + mesa +
                ", cantPersonas=" + cantPersonas +
                '}';
    }

    public Reserva ingresarReserva() {

        System.out.println("Complete con los datos:\n");
        Scanner scanner = new Scanner(System.in);

        LocalDate dia = null;
        boolean diaValido = false;

        while (!diaValido) {
            System.out.println("Por favor, ingresa el día (formato: dd/MM):");
            String diaInput = scanner.nextLine();

            DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM");
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
            System.out.println("Ingrese el día (formato: dd/MM):");
            String horaInput = scanner.nextLine();

            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("hh:mm");
            try {
                dia = LocalDate.parse(horaInput, formatoHora);
                horaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: El formato de la hora no es correcto.");
            }
        }

        ///FALTA CLIENTE NUEVO O POR BUSQUEDA

        System.out.println("Ingrese la mesa:");
        int mesa = scanner.nextInt();

        System.out.println("Ingrese la cantidad de personas:");
        int cantPersonas = scanner.nextInt();


        return new Reserva(dia, hora, cliente ,mesa, cantPersonas);
    }

}
