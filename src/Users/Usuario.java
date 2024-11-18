package Users;

import java.util.Objects;
import java.util.Random;

/**
 * La clase Users.Usuario es abstracta y tiene como campos su id, nombre de usuario, contrasenia, nombre,
 * apellido, dni, telefono, direccion, email y estado.
 * Tiene un constructor con todos los atributos, otro null y otro por defecto
 * metodos: getters y setters, toString, equals, hashcode
 *
 * @author Brenda
 * @since 2024
 * @version 2
 */
public abstract class Usuario {
    protected int id;
    protected String username;
    protected String contrasenia;
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String telefono;
    protected String direccion;
    protected String email;
    protected boolean estado;
    protected Random random=new Random();

    /// Constructor ////////////

    public Usuario() {
    }

    public Usuario(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email) {
        this.id = random.nextInt(1000000)+100;
        this.username = username;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.estado = true;
    }

    public Usuario(String nombre, String apellido, String dni, String telefono, String direccion, String email) {
        this.id = random.nextInt(1000000)+100;
        this.username = dni;
        this.contrasenia = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.estado = true;
    }


    ///Getters y Setters ////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /// ToString
    @Override
    public String toString() {
        return "Users.Usuario{" +
                "username='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                '}';
    }

    //Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username) && Objects.equals(contrasenia, usuario.contrasenia) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(username, contrasenia, nombre, apellido, dni);
    }

}
