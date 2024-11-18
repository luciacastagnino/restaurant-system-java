package Restaurante;

import Archivos.FormatoIncorrectoException;
import Users.Cliente;
import Users.DatoInvalidoException;
import Users.TipoCliente;
import Users.Validaciones;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.management.PlatformLoggingMXBean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * La clase Restaurante.Plato tiene como campos su nombre, descripcion, disponibilidad y precio
 * tiene un constructor con todos los atributos, uno solo con nombre
 * y otro con nombre y descripcion
 * metodos:
 * otros metodos:
 *
 * @author Brenda
 * @since 2024
 * @version 2
 */
public class Plato {
    private static int contadorId = 100;
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;
    private TipoPlato tipoPlato;
    private Scanner scanner = new Scanner(System.in);

    public Plato() {
    }


    public Plato(String nombre, String descripcion, double precio, boolean disponibilidad, TipoPlato tipoPlato) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.tipoPlato = tipoPlato;
    }

    ///Getters y Setters

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Plato.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public TipoPlato getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(TipoPlato tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public Plato cargarPlato () {

        System.out.println("Cargando los datos del plato...");

        String nombre = "";
        boolean nombreValido = false;
        while (!nombreValido){
            System.out.println("Ingrese el nombre del plato: ");
            nombre = scanner.nextLine();
            if (nombre==null){
                System.out.println("El nombre no puede estar vacio.");
            }else {
                try {
                    Validaciones.validarCadenas(nombre);
                    nombreValido = true;
                }
                catch (DatoInvalidoException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        String desc = "";
        boolean descValida = false;
        while (!descValida) {
            System.out.println("Ingrese la descripcion del Plato:");
            desc = scanner.nextLine();
            try {
                Validaciones.validarCadenas(desc);
                descValida = true;
            } catch (DatoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }


        double precio = 0;
        boolean precioValido = false;
        while (!precioValido) {
            System.out.println("Ingrese el precio del plato:");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                scanner.nextLine();
                if (precio > 0) {
                    precioValido = true;
                } else {
                    System.out.println("El precio debe ser mayor a 0.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        }

        TipoPlato aux = null;
        boolean tipoValido = false;

        while (!tipoValido) {
            System.out.println("Ingrese el tipo de plato:");
            System.out.println("1. DESAYUNO");
            System.out.println("2. BRUNCH");
            System.out.println("3. ALMUERZO");
            System.out.println("4. CENA");
            System.out.println("5. POSTRE");
            System.out.println("6. BEBIDA");
            System.out.println("7. ENTRADAS");

            try {
                int op = scanner.nextInt();

                if (op == 1) {
                    aux = TipoPlato.DESAYUNO;
                } else if (op == 2) {
                    aux = TipoPlato.BRUNCH;
                } else if (op == 3) {
                    aux = TipoPlato.ALMUERZO;
                } else if (op == 4) {
                    aux = TipoPlato.CENA;
                } else if (op == 5) {
                    aux = TipoPlato.POSTRE;
                } else if (op == 6) {
                    aux = TipoPlato.BEBIDA;
                } else if (op == 7) {
                    aux = TipoPlato.ENTRADAS;
                } else {
                    System.out.println("Opción inválida. Intente nuevamente.");
                    continue;
                }

                tipoValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            }
        }

        if (aux == null) {
            throw new IllegalStateException("El tipo de plato no puede ser null.");
        }

        Plato plato = new Plato(nombre, desc, precio, true, aux);

        plato.mostrarPlato();
        return plato;
    }

    public JSONObject toJson (Plato p){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", p.getId());
            jsonObject.put("nombre", p.getNombre());
            jsonObject.put("descripcion", p.getDescripcion());
            jsonObject.put("precio", p.getPrecio());
            jsonObject.put("disponible", p.isDisponibilidad());
            jsonObject.put("tipoPlato", p.getTipoPlato());
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    public Plato jsonToPlato (JSONObject json) throws FormatoIncorrectoException {
        Plato platoLeido = new Plato();
        try {
            if(json.has("id") && json.has("nombre") && json.has("descripcion") &&
                    json.has("precio") && json.has("disponible") && json.has("tipoPlato")) {
                platoLeido.setId(json.getInt("id"));
                platoLeido.setNombre(json.getString("nombre"));
                platoLeido.setDescripcion(json.getString("descripcion"));
                platoLeido.setPrecio(json.getDouble("precio"));
                platoLeido.setDisponibilidad(json.getBoolean("disponible"));
                platoLeido.setTipoPlato(json.getEnum(TipoPlato.class, "tipoPlato"));

            }
            else{
                throw new FormatoIncorrectoException("El formato de JSON no corresponde a un plato.");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return platoLeido;
    }

    public void mostrarPlato (){

        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Precio: $" + precio);
        System.out.println("Tipo de plato: " + tipoPlato);
        System.out.println("Disponibilidad: " + isDisponibilidad());

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return id == plato.id && Double.compare(precio, plato.precio) == 0 && disponibilidad == plato.disponibilidad && Objects.equals(nombre, plato.nombre) && Objects.equals(descripcion, plato.descripcion) && tipoPlato == plato.tipoPlato && Objects.equals(scanner, plato.scanner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, precio, disponibilidad, tipoPlato, scanner);
    }

    ///ToString

    @Override
    public String toString() {
        return "Restaurante.Plato{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", disponibilidad=" + disponibilidad +
                '}';
        
    }
}
