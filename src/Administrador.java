import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Administrador extends Usuario implements Comparable<Administrador>{

    private static int contadorId = 0;
    private int id;

    public Administrador() {
    }

    public Administrador(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.id = contadorId++;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Administrador.contadorId = contadorId;
    }

    // es necesario el SET para cuando se pasa el JSON a Admin

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //ADMIN TO JSON

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

    public Administrador jsonToAdmin (JSONObject json){

        Administrador adminLeido = new Administrador();
        try {
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
        }catch (JSONException e){
            e.printStackTrace();
        }
        return adminLeido;
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
