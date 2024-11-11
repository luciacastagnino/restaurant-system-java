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

    public EmpleadoTiempoCompleto(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, int aniosAntiguedad) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.aniosAntiguedad = aniosAntiguedad;
    }

    ///Getters y Setters
    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAntiguedad) {
        this.aniosAntiguedad = aniosAntiguedad;
    }

    ///ToString


    @Override
    public String toString() {
        return "EmpleadoTiempoCompleto{" +
                "aniosAntiguedad=" + aniosAntiguedad +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    /**
     * El metodo calcular sueldo se encarga de sumar el sueldo normal del trabajador a
     * tiempo completo y las horas extra en caso de tenerlas.
     *
     * @param horasExtra
     * @param precioXhora
     * @return Total del sueldo
     */

    public double calcularSueldo (double horasExtra, double precioXhora){
        return sueldo + (horasExtra * precioXhora);
    }
}
