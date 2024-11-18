package Visualizacion;

import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
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

    public Administrador inicioSesionAdmin(String nombreArch) throws FileNotFoundException {
        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray admins = new JSONArray(jsonTokener);
        Administrador adminLeido = null;
        while (adminLeido == null){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasenia = scanner.nextLine();


            for (int i = 0; i < admins.length(); i++) {
                JSONObject adminObj = admins.getJSONObject(i);

                String adminUsername = adminObj.getString("username");
                String adminContra = adminObj.getString("contrasenia");

                if (adminUsername.equals(username) && adminContra.equals(contrasenia)) {
                    adminLeido = new Administrador();
                    adminLeido = adminLeido.jsonToAdmin(adminObj);
                    return adminLeido;
                }else if (adminUsername.equals(username) || adminContra.equals(contrasenia)){
                    System.out.println("Username o contraseña incorrectos.");
                }
            }

            System.out.println("¿Desea salir del proceso o intentar nuevamente? Seleccione una opcion.");
            System.out.println("1. Salir.");
            System.out.println("2. Reintentar.");
            int op = scanner.nextInt();
            scanner.nextLine();
            if (op == 1){
                System.out.println("Saliendo del inicio de sesion...");
                break;
            }else if (op!=2){
                System.out.println("Opcion invalida. Se lo enviara al inicio de sesion.");
            }

        }

        return adminLeido;
    }

    public Cliente inicioSesionCliente(String nombreArch) throws FileNotFoundException {
        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray clientes = new JSONArray(jsonTokener);
        Cliente clienteLeido = null;
        while (clienteLeido == null){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasenia = scanner.nextLine();


            for (int i = 0; i < clientes.length(); i++) {
                JSONObject clienteObj = clientes.getJSONObject(i);

                String clienteUsername = clienteObj.getString("username");
                String clienteContra = clienteObj.getString("contrasenia");

                if (clienteUsername.equals(username) && clienteContra.equals(contrasenia)) {
                    clienteLeido = new Cliente();
                    clienteLeido = clienteLeido.jsonToCliente(clienteObj);
                    return clienteLeido;
                }else if (clienteUsername.equals(username) || clienteContra.equals(contrasenia)){
                    System.out.println("Username o contraseña incorrectos.");
                }
            }

            System.out.println("¿Desea salir del proceso o intentar nuevamente? Seleccione una opcion.");
            System.out.println("1. Salir.");
            System.out.println("2. Reintentar.");
            int op = scanner.nextInt();
            scanner.nextLine();
            if (op == 1){
                System.out.println("Saliendo del inicio de sesion...");
                break;
            }else if (op!=2){
                System.out.println("Opcion invalida. Se lo enviara al inicio de sesion.");
            }

        }

        return clienteLeido;
    }

    public Empleado inicioSesionEmpleado(String nombreArch) throws FileNotFoundException {
        if (nombreArch == null) {
            throw new FileNotFoundException("El archivo no existe.");
        }

        JSONTokener jsonTokener = GestionJSON.leer(nombreArch);
        JSONArray empleados = new JSONArray(jsonTokener);
        Empleado empleadoLeido = null;
        while (empleadoLeido == null){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String contrasenia = scanner.nextLine();

            for (int i =0; i<empleados.length(); i++) {
                JSONObject empleadoObj = empleados.getJSONObject(i);
                String empleadoUsername = empleadoObj.getString("username");
                String empleadoContra = empleadoObj.getString("contrasenia");
                if (empleadoUsername.equals(username) && empleadoContra.equals(contrasenia)) {
                    if (empleadoObj.has("aniosAntiguedad")){
                        empleadoLeido = new EmpleadoTiempoCompleto().jsonToEmpleadoTC(empleadoObj);
                        return empleadoLeido;
                    }else if (empleadoObj.has("precioPorHora") && empleadoObj.has("horasTrabajadas")){
                        empleadoLeido = new EmpleadoMedioTiempo().jsonToEmpleadoMT(empleadoObj);
                        return empleadoLeido;
                    }
                }else if (empleadoUsername.equals(username) || empleadoContra.equals(contrasenia)){
                    System.out.println("Users.Usuario o contraseña incorrectos.");
                }
            }

            System.out.println("¿Desea salir del proceso o intentar nuevamente? Seleccione una opcion.");
            System.out.println("1. Salir.");
            System.out.println("2. Reintentar.");
            int op = scanner.nextInt();
            scanner.nextLine();
            if (op == 1){
                System.out.println("Saliendo del inicio de sesion...");
                break;
            }else if (op!=2){
                System.out.println("Opcion invalida. Se lo enviara al inicio de sesion.");
            }

        }

        return empleadoLeido;
    }
}
