/**
 * Exception lanzada cuando un JSON no se puede convertir a objeto porque no tiene el formato correcto
 * Casos de uso: pasar datos de archivo JSON a Cliente, Empleado, Reserva, etc
 *
 *  * @author Brenda
 *  * @since 2024
 *  * @version 1
 */
package Archivos;

public class FormatoIncorrectoException extends RuntimeException {
    public FormatoIncorrectoException(String message) {
        super(message);
    }
}
