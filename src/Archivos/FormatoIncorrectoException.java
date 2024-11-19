package Archivos;

/**
 * Exception lanzada al detectar un formato incorrecto en los datos ingresados o procesados.
 * Casos de uso:
 * - Formato incorrecto de JSON para objetos como Cliente, Empleado, Administrador, Plato, Ticket o Reserva.
 * - Error en el formato de fecha dentro del JSON.
 * - Error al intentar parsear un JSON.
 *
 * Esta excepción es útil para validar y manejar errores relacionados con el formato de los datos
 * durante el procesamiento o la comunicación entre sistemas.
 *
 * @author Melina
 * @since 2024
 * @version 1
 */
public class FormatoIncorrectoException extends RuntimeException {
    public FormatoIncorrectoException(String message) {
        super(message);
    }
}
