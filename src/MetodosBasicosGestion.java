public interface MetodosBasicosGestion<T>{
    public void ingresarUsuario();
    public T cargarArrayConArchivo(T t); //dar de baja, puede cambiar el param por nombre o algo asi
    public T modificarElemento(T t);
    public void mostrarElemento(T t);
}
