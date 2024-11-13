import org.json.JSONException;
import org.json.JSONObject;

import java.security.cert.PKIXCertPathBuilderResult;

/**
 * La clase Empleado tiene como campos su nombre, apellido, dni, telefono, direccion e email
 * tiene un constructor con todos los atributos
 * metodos: getters y setters
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoTiempoCompleto extends Empleado{
    private int aniosAntiguedad;

    public EmpleadoTiempoCompleto(){

    }

    public EmpleadoTiempoCompleto(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, int aniosAntiguedad) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
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
                "aniosAntiguedad=" + aniosAntiguedad +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    //EMPLEADO TO JSON

    public JSONObject toJson (EmpleadoTiempoCompleto e){

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
            jsonObject.put("aniosAntiguedad", e.getAniosAntiguedad());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    //JSON TO EMPLEADO

    public EmpleadoTiempoCompleto jsonToEmpleadoTC (JSONObject json){

        EmpleadoTiempoCompleto empleadoLeido = new EmpleadoTiempoCompleto();
        try {
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
}
