/**
 * La clase Empleado es abstracta y tiene como campos su nombre, apellido, dni,
 * telefono, direccion, email, sueldo y horas extra
 * tiene un constructor ...
 * metodos:
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public abstract class Empleado extends Persona {
    protected double sueldo;
    protected int horasExtra;

    public Empleado(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, int horasExtra) {
        super(nombre, apellido, dni, telefono, direccion, email);
        this.sueldo = sueldo;
        this.horasExtra = horasExtra;
    }
}
