package Users;

/**
 * La clase Users.Empleado es abstracta y tiene como campos su nombre, apellido, dni,
 * telefono, direccion, email, sueldo y horas extra
 * tiene un constructor ...
 * metodos: getters y setters
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public abstract class Empleado extends Usuario {
    protected double sueldo;

    public Empleado(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, double sueldo) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.sueldo = sueldo;
    }

    public Empleado() {
    }


    ///Getters y Setters
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "sueldo=" + sueldo +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                "} ";
    }
}
