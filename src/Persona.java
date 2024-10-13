/**
 * La clase Persona tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor ...
 * metodos:
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String telefono;
    protected String direccion;
    protected String email;

    public Persona(String nombre, String apellido, String dni, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }
}
