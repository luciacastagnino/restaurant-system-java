public interface MetodosBasicos <T>{
    public String agregarElemento(T t);
    public T eliminarElemento(T t); //dar de baja, puede cambiar el param por nombre o algo asi
    public T modificarElemento(T t);
    public void mostrarElemento(T t);
}
