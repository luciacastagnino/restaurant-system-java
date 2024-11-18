package Users;

import Archivos.FormatoIncorrectoException;
import Users.Usuario;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
/**
 * La clase Users.Administrador tiene como campos su id, nombre de usuario, contrasenia, nombre,
 * apellido, dni, telefono, direccion e email.
 * Tiene un constructor con todos los atributos y uno null
 * Hereda de la clase abstracta Usuario e implementa la interfaz Comparable
 * Metodos: getters, setters, toString, equals, hashcode y compareTo
 * Otros metodos: toJson, jsonToAdministrador
 *
 * @author Brenda
 * @since 2024
 * @version 2
 */
public class Administrador extends Usuario implements Comparable<Administrador>{

    public Administrador() {
    }

    public Administrador(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email);
    }

    //ADMIN TO JSON

    /**
     * toJson es un metodo que permite transformar un objeto Administrador a un JSONObject, recibe un
     * Administrador y retorna un JSONObject.
     * @param e
     * @return jsonObject
     */
    public JSONObject toJson (Administrador e){

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
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    // JSON TO ADMIN

    /**
     * jsonToAdmin es un metodo que tranforma un JSONObject en un objeto Administrador recibe un
     * JSONObject y retorna un Administrador, lanza un FormatoIncorrectoException si el formato del
     * JSONObject no tiene los parametros de un Administrador.
     * @param json
     * @return adminLeido
     * @throws FormatoIncorrectoException
     */
    public Administrador jsonToAdmin (JSONObject json) throws FormatoIncorrectoException {

        Administrador adminLeido = new Administrador();
        try {
            if(json.has("id") && json.has("username") && json.has("contrasenia") &&
                    json.has("nombre") && json.has("apellido") && json.has("dni") &&
                    json.has("telefono") && json.has("direccion") && json.has("email") &&
                    json.has("estado")){
                adminLeido.setId(json.getInt("id"));
                adminLeido.setUsername(json.getString("username"));
                adminLeido.setContrasenia(json.getString("contrasenia"));
                adminLeido.setNombre(json.getString("nombre"));
                adminLeido.setApellido(json.getString("apellido"));
                adminLeido.setDni(json.getString("dni"));
                adminLeido.setTelefono(json.getString("telefono"));
                adminLeido.setDireccion(json.getString("direccion"));
                adminLeido.setEmail(json.getString("email"));
                adminLeido.setEstado(json.getBoolean("estado"));
            }
            else {
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un administrador");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return adminLeido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrador that = (Administrador) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public int compareTo(Administrador o) {
        return this.dni.compareTo(o.dni);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                "} " + super.toString();
    }
}
