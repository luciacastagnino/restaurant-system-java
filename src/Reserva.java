import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reserva {
    private static int contador = 0;
    private int id;
    private LocalDateTime momento;
    private LocalDate dia;
    private LocalTime hora;
    private Cliente cliente;
    private int mesa;
    private int cantPersonas;

    public Reserva(LocalDate dia, LocalTime hora, Cliente cliente, int mesa, int cantPersonas) {
        this.id = ++contador;
        this.momento = LocalDateTime.now();
        this.dia = dia;
        this.hora = hora;
        this.cliente = cliente;
        this.mesa = mesa;
        this.cantPersonas = cantPersonas;
    }
}

