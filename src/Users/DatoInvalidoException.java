package Users;

/**
 * Exception lanzada al ingresar datos invalidos
 * Casos de uso: contrasenia con cierta cantidad de caracteres, que tenga un numero, para aumentar la seguridad,
 * que no haya numeros en el nombre y apellido, tamanio de DNI
 *
 *  * @author Lucia
 *  * @since 2024
 *  * @version 1
 */

public class DatoInvalidoException extends Exception {
    public DatoInvalidoException(String message) {
        super(message);
    }
}
