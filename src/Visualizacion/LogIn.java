package Visualizacion;

import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * La clase Visualizacion.LogIn permite gestionar el inicio de sesión de diferentes tipos de usuarios
 * del sistema (administradores, empleados y clientes).
 *
 * - Tiene como campos un scanner para la lectura de entradas del usuario.
 *
 * - Posee un constructor vacío que inicializa el scanner.
 *
 * - Incluye como métodos principales los inicios de sesion del cliente, empleado y administrador.
 *
 * - Valida las credenciales del usuario ingresadas con las almacenadas en los archivos JSON correspondientes.
 * - Permite reintentar el inicio de sesión en caso de error o salir del proceso según la elección del usuario.
 * - Interactúa con las clases `GestionJSON` y las entidades de usuario (`Administrador`, `Cliente`, `Empleado`).
 *
 * @author Melina
 * @since 2024
 * @version 1
 */


public class LogIn {

    private Scanner scanner;

    public LogIn() {
        this.scanner = new Scanner(System.in);
    }


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
                boolean adminEstado = adminObj.getBoolean("estado");

                if (adminUsername.equals(username) && adminContra.equals(contrasenia) && adminEstado==true) {
                    adminLeido = new Administrador();
                    adminLeido = adminLeido.jsonToAdmin(adminObj);
                    return adminLeido;
                }else if (adminUsername.equals(username) && adminContra.equals(contrasenia) && adminEstado==false){
                    System.out.println("Su cuenta se encuentra dada de baja.");
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
                boolean estado = clienteObj.getBoolean("estado");

                if (clienteUsername.equals(username) && clienteContra.equals(contrasenia) && estado==true) {
                    clienteLeido = new Cliente();
                    clienteLeido = clienteLeido.jsonToCliente(clienteObj);
                    return clienteLeido;
                }else if (clienteUsername.equals(username) && clienteContra.equals(contrasenia) && estado==false){
                    System.out.println("El cliente se encuentra dado de baja.");
                } else if (clienteUsername.equals(username) || clienteContra.equals(contrasenia)){
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
                boolean empleadoEstado = empleadoObj.getBoolean("estado");
                if (empleadoUsername.equals(username) && empleadoContra.equals(contrasenia) && empleadoEstado==true) {
                    if (empleadoObj.has("aniosAntiguedad")){
                        empleadoLeido = new EmpleadoTiempoCompleto().jsonToEmpleadoTC(empleadoObj);
                        return empleadoLeido;
                    }else if (empleadoObj.has("precioPorHora") && empleadoObj.has("horasTrabajadas")){
                        empleadoLeido = new EmpleadoMedioTiempo().jsonToEmpleadoMT(empleadoObj);
                        return empleadoLeido;
                    }
                } else if (empleadoUsername.equals(username) && empleadoContra.equals(contrasenia) && empleadoEstado==false) {
                    System.out.println("El usuario se encuentra dado de baja.");
                } else if (empleadoUsername.equals(username) || empleadoContra.equals(contrasenia)){
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
