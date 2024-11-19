package Restaurante;

/**
 * Exception lanzada al ingresar una mesa que ya se encuentra reservada en ese mismo dia a la misma hora
 * Casos de uso: Al ingresar nuevas reservas o modificarlas
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class MesaYaReservadaException extends Exception{
     public MesaYaReservadaException(String message) {
            super(message);
        }
    }
