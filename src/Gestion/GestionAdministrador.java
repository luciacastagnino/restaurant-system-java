package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;
import java.util.stream.Collectors;

/**
 * La clase Gestion.GestionAdministrador gestiona un conjunto de usuarios administradores.
 *
 * - Tiene como campos un Set de administradores (`listaAdmins`), un objeto `registroUser` para gestionar el registro de nuevos administradores,
 * y un `scanner` para la lectura de entradas del usuario.
 * - tiene un constructor vacío
 * - métodos: agregar, modificar, eliminar, buscar y listar administradores
 * - interactúa con un archivo JSON para persistencia
 *
 * @author Melina
 * @since 2024
 * @version 2
 */

public class GestionAdministrador implements MetodosBasicosGestion<Administrador> {

    private Set<Administrador> listaAdmins;
    private RegistroUser registroUser;
    private Scanner scanner;

    public GestionAdministrador() {
        this.listaAdmins = new TreeSet<Administrador>();
        GestionJSON.crearArchivoJSON("administrador.json");
        this.registroUser = new RegistroUser();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Metodo para registrar un nuevo administrador en el sistema
     * Llama al metodo {@code registroAdmin} de la clase {@code registroUser} para crear un
     * objeto administrador con los datos ingresados.
     */

    public void ingresarUsuario(){
            System.out.println();
            Administrador aux = registroUser.registroAdmin();
            agregarYguardar(aux);
            System.out.println("\nAdministrador/a " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");

    }

    /**
     * Metodo para cargar colecciones de tipo Set con la informacion almacenada en el archivo.
     * Genera un {@code JSONArray} con la informacion del archivo JSON, generando y guardando
     * administradores en el Set.
     * En caso de que haya un error al convertir los {@code JSONObject} a administrador,
     * se captura una excepcion {@code JSONException} e imprime un mensaje de error.
     * @return {@code Set<Administrador>}
     */

    public Set<Administrador> cargarArrayConArchivo() {
        JSONTokener aux = GestionJSON.leer("administrador.json");

        listaAdmins.clear();

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

        return listaAdmins;
    }

    /**
     * Este metodo se utiliza para agregar elementos al archivo, sin que haya perdida de
     * informacion.
     * @param nuevoAdmin
     */

    public void agregarYguardar (Administrador nuevoAdmin){
        cargarArrayConArchivo();
        listaAdmins.add(nuevoAdmin);
        cargarArchivoConArreglo(listaAdmins);
    }

    /**
     * cargarArchivoConArreglo carga un archivo JSON con un arreglo de administradores.
     * Convierte cada administrador a JSON y lo agrega al archivo "administrador.json".
     *
     * @param listaAdmins conjunto de administradores a procesar.
     *
     * Manejo de excepciones:
     * - Captura errores de formato y de manipulación JSON, mostrando mensajes específicos.
     */
    public void cargarArchivoConArreglo(Set<Administrador> listaAdmins){
        JSONArray arreglo = new JSONArray();
        try {

            for (Administrador admin : listaAdmins){
                try {
                    JSONObject json = admin.toJson(admin);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("administrador.json", arreglo);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    /**
     * mostrarDatosUsuario muestra los datos de un administrador si existe en la lista.
     * @param a administrador cuyos datos serán buscados y mostrados.
     */

    public void mostrarDatosUsuario (Administrador a){

        listaAdmins = cargarArrayConArchivo();

        for (Administrador admin : listaAdmins){
            if (admin.getId() == a.getId()){
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE ADMINISTRADOR: " + a.getNombre() + " " + a.getApellido());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + a.getId());
                System.out.println("Username: " + a.getUsername());
                System.out.println("Contraseña: **********");
                System.out.println("Nombre: " + a.getNombre());
                System.out.println("Apellido: " + a.getApellido());
                System.out.println("DNI: " + a.getDni());
                System.out.println("Telefono: " + a.getTelefono());
                System.out.println("Direccion: " + a.getDireccion());
                System.out.println("Email: " + a.getEmail());
                System.out.println("--------------------------------------------");

                return;
            }
        }

        System.out.printf("No se encontro al usuario.");

    }

    /**
     * Este metodo se utiliza para la modificacion de usuarios.
     * Encuentra a los usuarios a modificar mediante el ID, y luego le
     * ofrece una lista de opciones para modificar.
     * @param c
     * @return el objeto {@code Administrador} modificado.
     */

    public Administrador modificarUsuario (Administrador c) {

        listaAdmins = cargarArrayConArchivo();
        boolean salir = false;

        for (Administrador administrador : listaAdmins) {
            if (c.getId() == administrador.getId()) {
                listaAdmins.remove(administrador);
                c=administrador;
                while (!salir) {
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
                    switch (op) {
                        case 1:

                            String username = "";
                            boolean usernameValido = false;

                            while (!usernameValido) {
                                System.out.println("Ingrese su nuevo username: ");
                                username = scanner.nextLine();
                                try {
                                    Validaciones.validarNombreUsuario(username);
                                    if (!Validaciones.existeUser(username)){
                                        c.setUsername(username);
                                        usernameValido = true;
                                    }else {
                                        System.out.println("El username ya existe en el sistema.");
                                    }
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 2:

                            String contrasenia = "";
                            boolean contraseniaValida = false;

                            System.out.println("Ingrese su contraseña actual:");
                            String contraseñaActual = scanner.nextLine();
                            if (c.getContrasenia().equals(contraseñaActual)){
                                while (!contraseniaValida) {
                                    System.out.println("Ingrese su nueva contrasenia: ");
                                    contrasenia = scanner.nextLine();
                                    try {
                                        Validaciones.validarContrasenia(contrasenia);
                                        c.setContrasenia(contrasenia);
                                        contraseniaValida = true;
                                    } catch (DatoInvalidoException e) {
                                        System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                    }
                                }
                            }

                            break;
                        case 3:

                            String nombre = "";
                            boolean nombreValido = false;

                            while (!nombreValido) {
                                System.out.println("Ingrese su nuevo nombre: ");
                                nombre = scanner.nextLine();
                                try {
                                    Validaciones.validarCadenas(nombre);
                                    c.setNombre(nombre);
                                    nombreValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 4:

                            String apellido = "";
                            boolean apellidoValido = false;

                            while (!apellidoValido) {
                                System.out.println("Ingrese su nuevo apellido: ");
                                apellido = scanner.nextLine();
                                try {
                                    Validaciones.validarCadenas(apellido);
                                    c.setApellido(apellido);
                                    apellidoValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 5:

                            String dni = "";
                            boolean dniValido = false;

                            while (!dniValido) {
                                System.out.println("Ingrese su nuevo DNI: ");
                                dni = scanner.nextLine();
                                try {
                                    Validaciones.validarDNI(dni);
                                    if (!Validaciones.existeDni(dni, "administrador")){
                                        c.setDni(dni);
                                        dniValido = true;
                                    }else {
                                        System.out.println("El DNI ya existe en el sistema.");
                                    }
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 6:

                            String telefono = "";
                            boolean telefonoValido = false;

                            while (!telefonoValido) {
                                System.out.println("Ingrese su nuevo telefono: ");
                                telefono = scanner.nextLine();
                                try {
                                    Validaciones.validarTelefono(telefono);
                                    c.setTelefono(telefono);
                                    telefonoValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 7:

                            String direccion = "";
                            boolean direccionValido = false;

                            while (!direccionValido) {
                                System.out.println("Ingrese su nueva direccion: ");
                                direccion = scanner.nextLine();
                                try {
                                    Validaciones.validarDireccion(direccion);
                                    c.setDireccion(direccion);
                                    direccionValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 8:

                            String email = "";
                            boolean emailValido = false;

                            while (!emailValido) {
                                System.out.println("Ingrese su nuevo email: ");
                                email = scanner.nextLine();
                                try {
                                    Validaciones.validarEmail(email);
                                    c.setEmail(email);
                                    emailValido = true;
                                } catch (DatoInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                                }
                            }

                            break;
                        case 9:
                            System.out.println("Saliendo del menu de modificacion de usuario...");
                            salir = true;
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                }
                listaAdmins.add(c);
                cargarArchivoConArreglo(listaAdmins);
                System.out.println("¡Cambios guardados con exito!");
                return c;
            }
        }
            return null;
    }

    /**
     * Este metodo da de baja usuarios, en el caso de que se trate de un Administrador,
     * Cliente o Empleado le solicita que ingrese la contraseña para garantizar
     * que la cuenta pertenezca al usuario.
     * @param a
     */

    @Override
    public void darDeBajaUsuario(Administrador a) {
        listaAdmins = cargarArrayConArchivo();

        for (Administrador admin : listaAdmins){
            if (a.equals(admin)){
                System.out.println("¿Esta seguro de eliminar el usuario? SI o NO.");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    int intentosContra = 0;
                    while (intentosContra < 3){
                        System.out.println("Ingrese su contraseña para eliminar su cuenta:");
                        String contraseña = scanner.nextLine();
                        if (contraseña.equals(a.getContrasenia())){
                            admin.setEstado(false);
                            System.out.println("Cuenta eliminada con exito.");
                            cargarArchivoConArreglo(listaAdmins);
                            return;
                        }else {
                            intentosContra++;
                            System.out.println("Contraseña incorrecta, intentelo nuevamente.");
                        }
                    }
                    System.out.println("Se supero el numero maximo de intentos. El usuario no fue dado de baja.");
                }else if (opcion.equalsIgnoreCase("no")){
                    System.out.println("Operacion cancelada.");
                    return;
                }else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro al usuario.");
    }

    /**
     * El metodo muestra a todos los {@code Administradores} cargados en el sistema.
     */

    public void mostrarColeccion () {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }
        listaAdmins.forEach(admin -> mostrarDatosUsuario(admin));
    }

    /**
     * El metodo lo que hace es buscar usuarios segun el DNI de este.
     * @param dni
     * @return {@code Administrador} buscado.
     */

    @Override
    public Administrador encontrarUsuario(String dni) {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaAdmins.stream()
                .filter(admin -> admin.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    /**
     * El metood encuentra a el usuario segun el ID.
     * @param id
     * @return {@code Administrador} buscado.
     */

    @Override
    public Administrador encontrarUsuario(int id) {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaAdmins.stream()
                .filter(admin -> admin.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * El metodo muestra a los usuarios que posean el nombre que se mando por parametro.
     * @param nombre
     */

    @Override
    public void listarUsuarios(String nombre) {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }

        boolean encontrado = false;

         encontrado = listaAdmins.stream()
                 .filter(admin -> admin.getNombre().equals(nombre))
                 .peek(admin -> mostrarDatosUsuario(admin))
                 .count() > 0;

         if(!encontrado){
             System.out.println("No se encontraron administradores con ese nombre.");
         }
    }

    /**
     * El metodo lista a los usuarios segun el estado en el que se encuentren.
     * Se utiliza para filtrar aquellos usuarios que esten activos e inactivos.
     * @param aux
     */

    @Override
    public void listarUsuarios(boolean aux) {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }

        boolean encontrado = false;

        encontrado = listaAdmins.stream()
                .filter(admin -> admin.getEstado() == aux)
                .peek(admin -> mostrarDatosUsuario(admin))
                .count() > 0;

        if(!encontrado){
            System.out.println("No se encontraron administradores.");
        }
    }

    /**
     * Este metodo da de alta usuarios, en el caso de que se trate de un Administrador,
     * Cliente o Empleado le solicita que ingrese la contraseña para garantizar
     * que la cuenta pertenezca al usuario.
     * @param a
     */

    @Override
    public void darDeAltaUsuario(Administrador a) {
            listaAdmins = cargarArrayConArchivo();

            for (Administrador admin : listaAdmins){
                if (a.equals(admin)){
                    System.out.println("¿Esta seguro de dar de alta el usuario? SI o NO.");
                    String opcion = scanner.nextLine();
                    if (opcion.toLowerCase().equals("si")){
                        int intentosContra = 0;
                        while (intentosContra < 3){
                            System.out.printf("Ingrese su contraseña dar de alta su cuenta:");
                            String contraseña = scanner.nextLine();
                            if (contraseña.equals(a.getContrasenia())){
                                admin.setEstado(true);
                                System.out.printf("Cuenta agregada con exito.");
                                cargarArchivoConArreglo(listaAdmins);
                                return;
                            }else {
                                intentosContra++;
                                System.out.printf("Contraseña incorrecta, intentelo nuevamente.");
                            }
                        }
                        System.out.printf("Se supero el numero maximo de intentos. El usuario no fue dado de alta.");
                    }else if (opcion.toLowerCase().equals("no")){
                        System.out.printf("Operacion cancelada.");
                        return;
                    }else {
                        System.out.printf("Opcion invalida.");
                    }
                }
            }

            System.out.println("No se encontro al usuario.");
    }

}



