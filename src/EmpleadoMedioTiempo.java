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
    private double precioXhora;

    public EmpleadoMedioTiempo(String nombre, String apellido, String dni, String telefono, String direccion, String email, double sueldo, int horasTrabajadas, double precioXhora) {
        super(nombre, apellido, dni, telefono, direccion, email, sueldo);
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

    /*
    public double calcularHorasExtra (double horasExtra){
        return horasExtra * Math.pow(precioXhora, 2);
    }

    public double calcularSueldo (double horasExtra){
        return (horasTrabajadas * precioXhora) + calcularHorasExtra(horasExtra);
    }
     */
}

