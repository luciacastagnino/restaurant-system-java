package Users;

import Archivos.FormatoIncorrectoException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * La clase Users.Empleado tiene como campos su nombre de usuario, contrasenia, nombre,
 * apellido, dni, telefono, direccion, email, estado, sueldo y aniosAtiguedad
 * Tiene un constructor con todos los atributos
 * Hereda de la clase abstracta Empleado
 * Metodos: getters, setters, toString.
 * Otros metodos: toJson, JsonToEmpleadoTC, calcularSueldo
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoTiempoCompleto extends Empleado implements Comparable{
    private int aniosAntiguedad;

    public EmpleadoTiempoCompleto(){

    }

    public EmpleadoTiempoCompleto(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, int aniosAntiguedad) {
        super(nombre, apellido, dni, telefono, direccion, email, sueldo);
        this.aniosAntiguedad = aniosAntiguedad;
    }

    ///Getters y Setters
    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAntiguedad) {
        this.aniosAntiguedad = aniosAntiguedad;
    }

    ///ToString

    @Override
    public String toString() {
        return "EmpleadoTiempoCompleto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", aniosAntiguedad=" + aniosAntiguedad +
                ", sueldo=" + sueldo +
                '}';
    }

    //EMPLEADO TO JSON

    /**
     * toJson es un metodo que permite transformar un objeto EmpleadoTiempoCompleto a un JSONObject, recibe un
     * EmpleadoTiempoCompleto y retorna un JSONObject.
     * @param e
     * @return jsonObject
     */
    public JSONObject toJson (EmpleadoTiempoCompleto e){

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
            jsonObject.put("aniosAntiguedad", e.getAniosAntiguedad());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return jsonObject;
    }

    //JSON TO EMPLEADO

    /**
     * jsonToEmpleadoTC es un metodo que tranforma un JSONObject en un objeto EmpleadoTiempoCompleto
     * recibe un JSONObject y retorna un EmpleadoTiempoCompleto, lanza un FormatoIncorrectoException
     * si el formato del JSONObject no tiene los parametros de un EmpleadoTiempoCompleto.
     * @param json
     * @return empleadoLeido
     * @throws FormatoIncorrectoException
     */
    public EmpleadoTiempoCompleto jsonToEmpleadoTC (JSONObject json) throws FormatoIncorrectoException {

        EmpleadoTiempoCompleto empleadoLeido = new EmpleadoTiempoCompleto();
        try {
            if(json.has("id") && json.has("username") && json.has("contrasenia") &&
                    json.has("nombre") && json.has("apellido") && json.has("dni") &&
                    json.has("telefono") && json.has("direccion") && json.has("email") &&
                    json.has("estado") && json.has("aniosAntiguedad")){
                empleadoLeido.setId(json.getInt("id"));
                empleadoLeido.setUsername(json.getString("username"));
                empleadoLeido.setContrasenia(json.getString("contrasenia"));
                empleadoLeido.setNombre(json.getString("nombre"));
                empleadoLeido.setApellido(json.getString("apellido"));
                empleadoLeido.setDni(json.getString("dni"));
                empleadoLeido.setTelefono(json.getString("telefono"));
                empleadoLeido.setDireccion(json.getString("direccion"));
                empleadoLeido.setEmail(json.getString("email"));
                empleadoLeido.setEstado(json.getBoolean("estado"));
                empleadoLeido.setAniosAntiguedad(json.getInt("aniosAntiguedad"));
            }
            else {
                throw new FormatoIncorrectoException("El formato JSON no corresponde a un empleado de tiempo completo");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return empleadoLeido;
    }

    /**
     * El metodo calcular sueldo se encarga de sumar el sueldo normal del trabajador a
     * tiempo completo y las horas extra en caso de tenerlas.
     *
     * @param horasExtra
     * @param precioXhora
     * @return Total del sueldo
     */

    public double calcularSueldo (double horasExtra, double precioXhora){
        return sueldo + (horasExtra * precioXhora);
    }

    @Override
    public int compareTo(Object o) {
        return this.nombre.compareTo(((EmpleadoTiempoCompleto) o).getNombre());
    }
}
