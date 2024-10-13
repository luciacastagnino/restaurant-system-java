/**
 * La clase Plato tiene como campos su nombre, descripcion y precio
 * tiene un constructor con todos los atributos, uno solo con nombre
 * y otro con nombre y descripcion
 * metodos:
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class Plato {
    private String nombre;
    private String descripcion;
    private double precio;

    public Plato(String nombre) {
        this.nombre = nombre;
        this.descripcion = null;
        this.precio = 0;
    }

    public Plato(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = 0;
    }

    public Plato(String nombre, String descripcion, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    ///Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
