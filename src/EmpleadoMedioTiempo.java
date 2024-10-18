/**
 * La clase EmpleadoMedioTiempo tiene como campos su nombre, apellido, dni, telefono,
 * direccion, email y horas trabajadas
 * tiene un constructor con todos los atributos
 * metodos: getters y setters, ToString
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

