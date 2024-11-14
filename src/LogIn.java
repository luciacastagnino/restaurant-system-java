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
        for (int i =0; i<admins.length(); i++) {
            if (admins.get(i).equals(username) && admins.get(i).equals(contrasenia)) {
                adminLeido = new Administrador();
                adminLeido = adminLeido.jsonToAdmin(admins.getJSONObject(i));
            }else if (admins.get(i).equals(username) || admins.get(i).equals(contrasenia)){
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

        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray clientes = new JSONArray(jsonTokener);

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();

        Cliente clienteLeido = null;
        for (int i =0; i<clientes.length(); i++) {
            if (clientes.get(i).equals(username) && clientes.get(i).equals(contrasenia)) {
                clienteLeido = new Cliente();
                clienteLeido = clienteLeido.jsonToCliente(clientes.getJSONObject(i));
            }else if (clientes.get(i).equals(username) || clientes.get(i).equals(contrasenia)){
                System.out.println("Usuario o contraseña incorrectos.");
            }
        }

        if (clienteLeido == null){
            System.out.println("Hubo un problema, ingrese correctamente las credenciales.");
            return null;
        }

        return clienteLeido;
    }

    public Empleado inicioSesionEmpleado (String nombreArch) throws FileNotFoundException {

        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray empleados = new JSONArray(jsonTokener);

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Contraseña: ");
        String contrasenia = scanner.nextLine();

        EmpleadoMedioTiempo empleadoMT = null;
        EmpleadoTiempoCompleto empleadoTC = null;
        for (int i =0; i<empleados.length(); i++) {
            JSONObject jsonObject = empleados.getJSONObject(i);
            if (empleados.get(i).equals(username) && empleados.get(i).equals(contrasenia)) {
                if (jsonObject.has("aniosAntiguedad")){
                    empleadoTC = new EmpleadoTiempoCompleto();
                    return empleadoTC.jsonToEmpleadoTC(jsonObject);
                }else if (jsonObject.has("precioPorHora") && jsonObject.has("horasTrabajadas")){
                    empleadoMT = new EmpleadoMedioTiempo();
                    return empleadoMT.jsonToEmpleadoMT(jsonObject);
                }
            }else if (empleados.get(i).equals(username) || empleados.get(i).equals(contrasenia)){
                System.out.println("Usuario o contraseña incorrectos.");
            }
        }

        if (empleadoTC == null){
            System.out.println("Hubo un problema, ingrese correctamente las credenciales.");
        }else if (empleadoMT == null){
            System.out.println("Hubo un problema, ingrese correctamente las credenciales.");
        }

        return null;
    }

}
