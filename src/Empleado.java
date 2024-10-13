/**
 * La clase Empleado es abstracta y tiene como campos su nombre, apellido, dni,
 * telefono, direccion, email, sueldo y horas extra
 * tiene un constructor ...
 * metodos: getters y setters
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public abstract class Empleado extends Persona {
    protected double sueldo;
    //protected int horasExtra; deberia ser parametro en calcularSueldo, porque puede variar cada vez

    public Empleado(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo) {
        super(nombre, apellido, dni, telefono, direccion, email);
        this.sueldo = sueldo;
    }

    ///Getters y Setters
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

}
