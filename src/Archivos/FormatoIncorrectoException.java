/**
 * Exception lanzada cuando un JSON no se puede convertir a objeto porque no tiene el formato correcto
 * Casos de uso: pasar datos de archivo JSON a Users.Cliente, Users.Empleado, Restaurante.Reserva, etc
 *
 *  * @author Brenda
 *  * @since 2024
 *  * @version 1
 */
package Archivos;

/**
 * Exception lanzada al detectar un formato incorrecto en los datos ingresados o procesados.
 * Casos de uso:
 * - Formato incorrecto de JSON para objetos como Users.Cliente, plato, ticket o cliente.
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
