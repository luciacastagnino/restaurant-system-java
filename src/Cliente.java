import Archivos.FormatoIncorrectoException;
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

    public Cliente() {
    }

    public Cliente(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, TipoCliente tipoCliente) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.tipoCliente = tipoCliente;
    }

    ///Getters y Setters

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    // CLIENTE TO JSON

    public JSONObject toJson (Cliente e){

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("username", e.getUsername());
            jsonObject.put("contrasenia", e.getContrasenia());
            jsonObject.put("nombre", e.getNombre());
            jsonObject.put("apellido", e.getApellido());
            jsonObject.put("dni", e.getDni());
            jsonObject.put("telefono", e.getTelefono());
            jsonObject.put("direccion", e.getDireccion());
            jsonObject.put("email", e.getEmail());
            jsonObject.put("estado", e.getEstado());
            jsonObject.put("tipoCliente", e.getTipoCliente());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //CLIENTE TO JSON

    public Cliente jsonToCliente (JSONObject json) throws FormatoIncorrectoException {

        Cliente clienteLeido = new Cliente();
        try {
            if(json.has("id") && json.has("username") && json.has("contrasenia") &&
                    json.has("nombre") && json.has("apellido") && json.has("dni") &&
                    json.has("telefono") && json.has("direccion") && json.has("email") &&
                    json.has("estado") && json.has("tipoCliente")){
                clienteLeido.setUsername(json.getString("username"));
                clienteLeido.setContrasenia(json.getString("contrasenia"));
                clienteLeido.setNombre(json.getString("nombre"));
                clienteLeido.setApellido(json.getString("apellido"));
                clienteLeido.setDni(json.getString("dni"));
                clienteLeido.setTelefono(json.getString("telefono"));
                clienteLeido.setDireccion(json.getString("direccion"));
                clienteLeido.setEmail(json.getString("email"));
                clienteLeido.setEstado(json.getBoolean("estado"));
                clienteLeido.setTipoCliente(json.getEnum(TipoCliente.class, "tipoCliente"));
            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un Cliente");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return clienteLeido;
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

}
