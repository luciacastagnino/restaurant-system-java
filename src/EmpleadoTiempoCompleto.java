/**
 * La clase Empleado tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor con todos los atributos
 * metodos: getters y setters
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoTiempoCompleto extends Empleado{
    private int aniosAntiguedad;

    public EmpleadoTiempoCompleto(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, int horasExtra, int aniosAntiguedad) {
        super(nombre, apellido, dni, telefono, direccion, email, sueldo, horasExtra);
        this.aniosAntiguedad = aniosAntiguedad;
    }

    ///Getters y Setters
    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAntiguedad) {
        this.aniosAntiguedad = aniosAntiguedad;
    }
}
