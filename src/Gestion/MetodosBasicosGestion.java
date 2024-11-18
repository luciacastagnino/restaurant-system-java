package Gestion;

public interface MetodosBasicosGestion <T> {
    public void ingresarUsuario();
    public void mostrarDatosUsuario(T t);
    public T modificarUsuario(T t);
    public void agregarYguardar (T t);
    public void darDeBajaUsuario(T t);
    public void mostrarColeccion();
    public T encontrarUsuario(String dni);
    public T encontrarUsuario(int id);
    public void listarUsuarios(String nombre);
    public void listarUsuarios(boolean aux);
    public void darDeAltaUsuario(T t);
}
