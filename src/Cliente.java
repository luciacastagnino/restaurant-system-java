import java.util.Objects;

/**
 * La clase Cliente tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class Cliente extends Persona{
    private TipoCliente  tipoCliente;

    public Cliente(String nombre, String apellido, String dni, String telefono, String direccion, String email, TipoCliente tipoCliente) {
        super(nombre, apellido, dni, telefono, direccion, email);
        this.tipoCliente = tipoCliente;
    }

    ///Getters y Setters

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    ///ToString

    @Override
    public String toString() {
        return "Cliente{" +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoCliente=" + tipoCliente +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    //Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(super.hashCode(), tipoCliente);
    }
}
