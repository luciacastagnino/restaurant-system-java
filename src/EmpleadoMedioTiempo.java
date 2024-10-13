/**
 * La clase EmpleadoMedioTiempo tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor ...
 * metodos:
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoMedioTiempo extends Empleado{
    private int horasTrabajadas;

    public EmpleadoMedioTiempo(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, int horasExtra, int horasTrabajadas) {
        super(nombre, apellido, dni, telefono, direccion, email, sueldo, horasExtra);
        this.horasTrabajadas = horasTrabajadas;
    }
}
