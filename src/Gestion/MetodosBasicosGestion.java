package Gestion;

import Restaurante.MesaYaReservadaException;
import Users.Empleado;

/**
 * Interfaz que define los métodos básicos de todas las clases de gestion.
 * Las clases que implementen esta interfaz deben proporcionar la implementación de estos métodos
 * para gestionar de manera adecuada.
 *
 * @param <T> El tipo de objeto que representa segun la gestion utilizada.
 */

public interface MetodosBasicosGestion <T> {
    public void ingresarUsuario() throws MesaYaReservadaException;
    public void mostrarDatosUsuario(T t);
    public T modificarUsuario(T t) throws MesaYaReservadaException;
    public void agregarYguardar (T t);
    public void darDeBajaUsuario(T t);
    public void mostrarColeccion();
    public T encontrarUsuario(String dni);
    public T encontrarUsuario(int id);
    public void listarUsuarios(String nombre);
    public void listarUsuarios(boolean aux);
    public void darDeAltaUsuario(T t);
}
