import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ticket {

    private static int contador = 100000;
    private int id;
    private Reserva reserva;
    private Empleado empleado;
    private LocalDateTime horaEmision;
    private Set<Plato> platos;
    private double precio;
    private TipoPago tipoPago;
    private double propina;

    public Ticket(Reserva reserva, Empleado empleado, LocalDateTime horaEmision, double precio, TipoPago tipoPago, double propina) {
        this.id = contador++;
        this.reserva = reserva;
        this.empleado = empleado;
        this.horaEmision = horaEmision;
        this.platos = new HashSet<>();
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.propina = propina;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDateTime getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(LocalDateTime horaEmision) {
        this.horaEmision = horaEmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }

    public void mostrarTicket (Ticket t){

        System.out.println("--------------------------------------");
        System.out.println("RESTAURANTE GRASTROLAB S.A");
        System.out.println("Emitido en: Av. Dorrego 281, Mar del Plata, Argentina");
        System.out.println();
        System.out.println("Empleado: " + empleado.getNombre() + " " + empleado.getApellido());
        System.out.println("T" + id + "                   " + horaEmision);
        System.out.println("------------------------------------------");
        int cant = mostrarPlatos();
        System.out.println("------------------------------------------");
        System.out.println(cant + "productos                      TOTAL: $" + precio);

        if (tipoPago == TipoPago.EFECTIVO){
            System.out.println("                                Pago en efectivo: $" + precio);
        } else if (tipoPago == TipoPago.DEBITO){
            System.out.println("                                Pago con debito: $" + precio);
        }else if(tipoPago == TipoPago.CREDITO){
            System.out.println("                                Impuesto 20%: $" + calcularInteres(precio));
            System.out.println("                                Pago con credito: $" + calcularPrecioInteres(precio));
        }
        System.out.println("**************************************");
        System.out.println("▌│█║▌║▌║ ║▌║▌║█│▌║▌║▌║█│▌");
        System.out.println("*** GRACIAS POR SSU VISITA ***");
        System.out.println("--------------------------------------");

    }

    public int mostrarPlatos (){

        int cant = 0;

        for (Plato p : platos){
            System.out.println(p.getNombre() + "       $ " + p.getPrecio());
            cant++;
        }

        return cant;
    }

    public double calcularInteres (double precioTotal){
        return precioTotal*0.2;
    }

    public double calcularPrecioInteres(double precioTotal){

        double interes = calcularInteres(precioTotal);

        return precioTotal+interes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Double.compare(precio, ticket.precio) == 0 && Double.compare(propina, ticket.propina) == 0 && Objects.equals(reserva, ticket.reserva) && Objects.equals(empleado, ticket.empleado) && Objects.equals(horaEmision, ticket.horaEmision) && Objects.equals(platos, ticket.platos) && tipoPago == ticket.tipoPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reserva, empleado, horaEmision, platos, precio, tipoPago, propina);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", reserva=" + reserva +
                ", empleado=" + empleado +
                ", horaEmision=" + horaEmision +
                ", precio=" + precio +
                ", propina=" + propina +
                '}';
    }
}
