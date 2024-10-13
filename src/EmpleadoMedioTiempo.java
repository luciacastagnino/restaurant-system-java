/**
 * La clase EmpleadoMedioTiempo tiene como campos su nombre, apellido, dni, telefono, direccion
 * email y horas trabajadas
 * tiene un constructor con todos los atributos
 * metodos: getters y setters
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
public class EmpleadoMedioTiempo extends Empleado{
    private int horasTrabajadas;

    public EmpleadoMedioTiempo(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, /*int horasExtra,*/ int horasTrabajadas) {
        super(nombre, apellido, dni, telefono, direccion, email, sueldo /*,horasExtra*/);
        this.horasTrabajadas = horasTrabajadas;
    }

    ///Getters y Setters

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}

