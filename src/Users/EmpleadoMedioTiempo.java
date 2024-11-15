package Users;

import Archivos.FormatoIncorrectoException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * La clase Users.EmpleadoMedioTiempo tiene como campos su nombre, apellido, dni, telefono,
 * direccion, email y horas trabajadas
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoMedioTiempo extends Empleado {
    private int horasTrabajadas;
    private double precioXhora;

    public EmpleadoMedioTiempo(){

    }

    public EmpleadoMedioTiempo(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado, int horasTrabajadas, double precioXhora) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.horasTrabajadas = horasTrabajadas;
        this.precioXhora = precioXhora;
    }

    ///Getters y Setters

    public double getPrecioXhora() {
        return precioXhora;
    }

    public void setPrecioXhora(double precioXhora) {
        this.precioXhora = precioXhora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public JSONObject toJson (EmpleadoMedioTiempo e){

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
            jsonObject.put("horasTrabajadas", e.getHorasTrabajadas());
            jsonObject.put("precioPorHora", e.getPrecioXhora());
        }catch (JSONException ex){
            ex.printStackTrace();
        }

        return jsonObject;
    }

    //JSON TO EMPLEADO

    public EmpleadoMedioTiempo jsonToEmpleadoMT (JSONObject json) throws FormatoIncorrectoException {

        EmpleadoMedioTiempo empleadoLeido = new EmpleadoMedioTiempo();
        try {
            if(json.has("id") && json.has("username") && json.has("contrasenia") &&
                    json.has("nombre") && json.has("apellido") && json.has("dni") &&
                    json.has("telefono") && json.has("direccion") && json.has("email") &&
                    json.has("estado") && json.has("horasTrabajadas") && json.has("precioPorHora")){
                empleadoLeido.setUsername(json.getString("username"));
                empleadoLeido.setContrasenia(json.getString("contrasenia"));
                empleadoLeido.setNombre(json.getString("nombre"));
                empleadoLeido.setApellido(json.getString("apellido"));
                empleadoLeido.setDni(json.getString("dni"));
                empleadoLeido.setTelefono(json.getString("telefono"));
                empleadoLeido.setDireccion(json.getString("direccion"));
                empleadoLeido.setEmail(json.getString("email"));
                empleadoLeido.setEstado(json.getBoolean("estado"));
                empleadoLeido.setHorasTrabajadas(json.getInt("horasTrabajadas"));
                empleadoLeido.setPrecioXhora(json.getDouble("precioPorHora"));
            }
            else {
                throw new FormatoIncorrectoException("El formato JSON no corresponde a un empleado de medio tiempo");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return empleadoLeido;
    }

    /**
     * El metodo calcularSueldo multiplica las horas trabajadas por el precio por hora
     * y suma el total con el resultado del metodo calcularHorasExtra
     * @param horasExtra
     * @return (horasTrabajadas * precioXhora) + calcularHorasExtra(horasExtra);
     */

    public double calcularSueldo (double horasExtra){
        return (horasTrabajadas * precioXhora) + calcularHorasExtra(horasExtra);
    }

    /**
     *El metodo calcularHorasExtra multiplica las horas extra por el doble del
     * precio por hora, las horas extra se pagan doble
     * @param horasExtra
     * @return horasExtra * Math.pow(precioXhora, 2);
     */
    public double calcularHorasExtra (double horasExtra){
        return horasExtra * Math.pow(precioXhora, 2);
    }

}

