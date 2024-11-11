import java.util.Objects;

public class Administrador extends Usuario {

    private static int contadorId = 0;
    private int id;

    public Administrador(String username, String contrasenia, String nombre, String apellido, String dni, String telefono, String direccion, String email, boolean estado) {
        super(username, contrasenia, nombre, apellido, dni, telefono, direccion, email, estado);
        this.id = contadorId++;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Administrador.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                "} " + super.toString();
    }
}
