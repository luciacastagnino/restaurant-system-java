package Users;

import Archivos.FormatoIncorrectoException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * La clase Users.Cliente tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class Cliente extends Usuario implements Comparable<Cliente>{
    private TipoCliente tipoCliente = TipoCliente.ESTANDAR;
    private static int contadorId = 0;
    private int id;

    public Cliente() {
    }

    public Cliente(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.id = contadorId++;
    }


    ///Getters y Setters

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Cliente.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
            jsonObject.put("id", e.getId());
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
                clienteLeido.setId(json.getInt("id"));
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
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un Users.Cliente");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return clienteLeido;
    }

    //Equals


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && tipoCliente == cliente.tipoCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipoCliente, id);
    }

    @Override
    public int compareTo(Cliente o) {
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
                "tipoCliente=" + tipoCliente +
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
