import java.util.HashMap;
import java.util.Map;

public class GestionReserva {
    private Map<Cliente, Integer> reservasPorCliente;

    public GestionReserva() {
        this.reservasPorCliente = new HashMap<>();
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
        return reservasPorCliente.getOrDefault(cliente, 0);
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
