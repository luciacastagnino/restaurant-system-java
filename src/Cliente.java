import org.json.JSONException;
import org.json.JSONObject;

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
public class Cliente extends Usuario implements Comparable{
    private TipoCliente  tipoCliente;

    public Cliente(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, TipoCliente tipoCliente) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.tipoCliente = tipoCliente;
    }

    /*public Cliente (JSONObject json) {
        try {
            this.username = json.getString("username");
            this.contrasenia = json.getString("contrasenia");
            this.nombre = json.getString("nombre");
            this.apellido = json.getString("apellido");
            this.dni= json.getString("dni");
            this.telefono = json.getString("telefono");
            this.direccion = json.getString("direccion");
            this.email = json.getString("email");
            this.estado = json.getBoolean("estado");
            String tipoClienteStr = json.getString("tipoCliente");
            this.tipoCliente = TipoCliente.valueOf(tipoClienteStr);
        }
        catch (JSONException e){
            System.out.println("Hubo un problema al crear un cliente con JSONObject");
            e.printStackTrace();
        }

    }*/

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


    public JSONObject ClientetoJSONObject() {
        JSONObject json = new JSONObject();
        json.put("nombre", this.nombre);
        json.put("apellido", this.apellido);
        json.put("dni", this.dni);
        json.put("tel", this.telefono);
        json.put("dir", this.direccion);
        json.put("mail", this.email);
        json.put("tipoCliente", this.tipoCliente);
        json.put("estado", this.estado);
        return json;
    }

    @Override
    public int compareTo(Object o) {
        int nombreComparison = this.nombre.compareTo(((Cliente) o).nombre);
        if (nombreComparison != 0) {
            return nombreComparison;
        }

        // Comparar por apellido
        int apellidoComparison = this.apellido.compareTo(((Cliente) o).apellido);
        if (apellidoComparison != 0) {
            return apellidoComparison;
        }

        // Comparar por DNI
        return this.dni.compareTo(((Cliente) o).dni);
    }
}
