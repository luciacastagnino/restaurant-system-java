import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public class GestionAdministrador {

    private Set<Administrador> listaAdmins;
    private RegistroUser registroUser;

    public GestionAdministrador() {
        this.listaAdmins = new TreeSet<Administrador>();
        GestionJSON.crearArchivoJSON("administrador.json");
        this.registroUser = new RegistroUser();
    }

    public void ingresarAdmin(){
        Scanner scan = new Scanner(System.in);

            Administrador aux = registroUser.registroAdmin();
            listaAdmins.add(aux);
            cargarArchivoConArreglo(listaAdmins);
            System.out.println("Administrador " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");

    }

    public Administrador modificarAdmin (Administrador c){

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Que desea modificar?");
        System.out.println("1. Username.");
        System.out.println("2. Contraseña.");
        System.out.println("3. Nombre.");
        System.out.println("4. Apellido.");
        System.out.println("5. DNI.");
        System.out.println("6. Telefono.");
        System.out.println("7. Direccion.");
        System.out.println("8. Email.");
        System.out.println("9. Salir.");
        int op = scanner.nextInt();
        scanner.nextLine();
        switch (op){
            case 1:

                String username = "";
                boolean usernameValido = false;

                while (!usernameValido){
                    System.out.println("Ingrese su nuevo username: ");
                    username = scanner.nextLine();
                    try {
                        Validaciones.validarNombreUsuario(username);
                        c.setUsername(username);
                        usernameValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 2:

                String contrasenia = "";
                boolean contraseniaValida = false;

                while (!contraseniaValida){
                    System.out.println("Ingrese su nueva contrasenia: ");
                    contrasenia = scanner.nextLine();
                    try {
                        Validaciones.validarContrasenia(contrasenia);
                        c.setContrasenia(contrasenia);
                        contraseniaValida = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 3:

                String nombre = "";
                boolean nombreValido = false;

                while (!nombreValido){
                    System.out.println("Ingrese su nuevo nombre: ");
                    nombre = scanner.nextLine();
                    try {
                        Validaciones.validarCadenas(nombre);
                        c.setNombre(nombre);
                        nombreValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 4:

                String apellido = "";
                boolean apellidoValido = false;

                while (!apellidoValido){
                    System.out.println("Ingrese su nuevo apellido: ");
                    apellido = scanner.nextLine();
                    try {
                        Validaciones.validarCadenas(apellido);
                        c.setApellido(apellido);
                        apellidoValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 5:

                String dni = "";
                boolean dniValido = false;

                while (!dniValido){
                    System.out.println("Ingrese su nuevo DNI: ");
                    dni = scanner.nextLine();
                    try {
                        Validaciones.validarDNI(dni);
                        c.setDni(dni);
                        dniValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 6:

                String telefono = "";
                boolean telefonoValido = false;

                while (!telefonoValido){
                    System.out.println("Ingrese su nuevo telefono: ");
                    telefono = scanner.nextLine();
                    try {
                        Validaciones.validarTelefono(telefono);
                        c.setTelefono(telefono);
                        telefonoValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 7:

                String direccion = "";
                boolean direccionValido = false;

                while (!direccionValido){
                    System.out.println("Ingrese su nueva direccion: ");
                    direccion = scanner.nextLine();
                    try {
                        Validaciones.validarDireccion(direccion);
                        c.setDireccion(direccion);
                        direccionValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 8:

                String email = "";
                boolean emailValido = false;

                while (!emailValido){
                    System.out.println("Ingrese su nuevo email: ");
                    email = scanner.nextLine();
                    try {
                        Validaciones.validarEmail(email);
                        c.setEmail(email);
                        emailValido = true;
                    }catch (DatoInvalidoException e){
                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                    }
                }

                break;
            case 9:
                System.out.println("Saliendo del menu de modificacion de usuario...");
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }

        return c;
    }

    public void cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("administrador.json");

        try {

            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Administrador administrador = new Administrador();
                administrador = administrador.jsonToAdmin(aux1);
                listaAdmins.add(administrador);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Administrador.");
        }
    }

    public void cargarArchivoConArreglo(Set<Administrador> listaAdmins){
        JSONArray arreglo = new JSONArray();
        try {

            for (Administrador admin : listaAdmins){
                JSONObject json = admin.toJson(admin);
                arreglo.put(json);
            }

            GestionJSON.agregarElemento("administrador.json", arreglo);
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void mostrarListaDeAdmins(){
        if(listaAdmins.isEmpty()){
            cargarArrayConArchivo();
        }
        listaAdmins.forEach(System.out::println);
    }

}


