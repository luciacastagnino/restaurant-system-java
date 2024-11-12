import Archivos.GestionJSON;
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

    public Usuario inicioSesion(String username, String contrasenia, int tipoUsuario) throws FileNotFoundException {

        String namepath = archivoSegunTipoUsuario(tipoUsuario);
        if (namepath==null){
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener = GestionJSON.leer(namepath);
        
    }

    private String archivoSegunTipoUsuario (int tipoUsuario){

        if (tipoUsuario == 1){
            return "administrador.json";
        }else if (tipoUsuario == 2){
            return "empleados.json";
        }else if (tipoUsuario == 3){
            return "clientes.json";
        }

        return null;
    }


}
