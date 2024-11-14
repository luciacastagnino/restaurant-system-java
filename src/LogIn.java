import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LogIn {

    private Scanner scanner;

    public LogIn() {
        this.scanner = new Scanner(System.in);
    }

    //segun el tipo de usuario, se abre el archivo correspondiente
    //tipos de usuario
    // 1. admin, 2. empleado, 3. cliente

    //A CHEQUEAR QUE ESTO FUNCIONE xdddd :')))

    public Administrador inicioSesionAdmin (String nombreArch) throws FileNotFoundException {

        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray admins = new JSONArray(jsonTokener);

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();

        Administrador adminLeido = null;
        for (Object admin : admins) {
            if (admin.equals(username) && admin.equals(contrasenia)) {
                adminLeido = new Administrador();
                adminLeido = adminLeido.jsonToAdmin((JSONObject) admin);
            }else if (admin.equals(username) || admin.equals(contrasenia)){
                System.out.println("Usuario o contraseña incorrectos.");
            }
        }

        if (adminLeido == null){
            System.out.println("Hubo un problema, ingrese correctamente las credenciales.");
            return null;
        }

        return adminLeido;
    }

    public Cliente inicioSesionCliente (String nombreArch) throws FileNotFoundException {

        if (nombreArch==null){
            throw new FileNotFoundException("El archivo no existe.");
        }

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();

        return ;
    }

    public Empleado inicioSesionEmpleado (String nombreArch) throws FileNotFoundException {

        if (nombreArch==null){
            throw new FileNotFoundException("El archivo no existe.");
        }

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();

        return ;
    }

}
