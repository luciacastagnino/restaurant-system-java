/**
 * La clase Cliente tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor con todos los atributos
 * metodos:
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
}
