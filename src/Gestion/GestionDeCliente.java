package Gestion;

import java.util.*;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * La clase Gestion.GestionDeCliente gestiona un conjunto de clientes.
 *
 * - Tiene como campos un Set de Clientes (`listaDeClientes`), un objeto `registroUser` para gestionar el registro de nuevos clientes,
 * y un `scanner` para la lectura de entradas del usuario.
 * - tiene un constructor vacío
 * - incluye métodos para agregar, modificar, eliminar, buscar y listar clientes
 * - interactúa con un archivo JSON para guardar y cargar datos
 * - permite modificar atributos de un cliente y gestionar su estado (alta/baja)
 * - ajusta el tipo de cliente según la cantidad de reservas realizadas
 *
 * @author Melina
 * @since 2024
 * @version 1
 */

public class GestionDeCliente implements MetodosBasicosGestion<Cliente> {

    private Set<Cliente> listaDeClientes;
    private RegistroUser registroUser;
    private Scanner scanner;

    public GestionDeCliente() {
        this.listaDeClientes = new TreeSet<>();
        this.registroUser = new RegistroUser();
        GestionJSON.crearArchivoJSON("clientes.json");
        this.scanner = new Scanner(System.in);
    }

    public void ingresarUsuario() {
        System.out.println();
        Cliente aux = registroUser.registroCliente();
        agregarYguardar(aux);
        System.out.println("\nCliente " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");
    }

    /**
     * cargarArrayConArchivo carga los datos de clientes desde un archivo JSON y los convierte en objetos Cliente.
     *
     * @return un conjunto de objetos Cliente cargados desde el archivo "clientes.json".
     */
    public Set<Cliente> cargarArrayConArchivo() {
        JSONTokener aux = GestionJSON.leer("clientes.json");

        try {

            JSONArray arreglo = new JSONArray(aux);

            for (int i = 0; i < arreglo.length(); i++) {
                JSONObject aux1 = arreglo.getJSONObject(i);
                Cliente cliente = new Cliente();
                cliente = cliente.jsonToCliente(aux1);
                listaDeClientes.add(cliente);
            }
        } catch (JSONException e) {
            System.out.println("Ocurrio un error al convertir JSONObject a Cliente.");
        }

        return listaDeClientes;
    }

    /**
     * Este metodo se utiliza para agregar elementos al archivo, sin que haya perdida de
     * informacion.
     * @param nuevoCliente
     */

    public void agregarYguardar(Cliente nuevoCliente) {
        cargarArrayConArchivo();
        listaDeClientes.add(nuevoCliente);
        listaDeClientes = GuardarTipoDeCliente(); //no se si funciona
        cargarArchivoConArreglo(listaDeClientes);
    }

    /**
     * cargarArchivoConArreglo guarda un conjunto de clientes en un archivo JSON.
     * Convierte cada cliente a un objeto JSON y los almacena en "clientes.json".
     *
     * @param listaDeClientes conjunto de clientes que se guardarán en el archivo.
     */
    public void cargarArchivoConArreglo(Set<Cliente> listaDeClientes) {
        JSONArray arreglo = new JSONArray();
        try {

            for (Cliente cliente : listaDeClientes) {
                try {
                    JSONObject json = cliente.toJson(cliente);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("clientes.json", arreglo);
                } catch (FormatoIncorrectoException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e) {
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    /**
     * El metodo permite mostrar al usuario.
     * @param a
     */

    public void mostrarDatosUsuario(Cliente a) {

        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes) {
            if (cliente.getId() == a.getId()) {
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE CLIENTE: " + a.getNombre() + " " + a.getApellido());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + a.getId());
                System.out.println("Username: " + a.getUsername());
                System.out.println("Contraseña: " + a.getContrasenia());
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
     * El metodo permite mostrar al usuario, no permitiendo ver la contraseña,
     * se utiliza para mostrar los clientes a los empleados sin que tengan acceso a esta.
     * @param a
     */

    public void mostrarDatosUsuarioEmpleado (Cliente a) {

        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes) {
            if (cliente.getId() == a.getId()) {
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE CLIENTE: " + a.getNombre() + " " + a.getApellido());
                System.out.println("--------------------------------------------");

                System.out.println("ID: " + a.getId());
                System.out.println("Username: " + a.getUsername());
                System.out.println("Contraseña: **********" );
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
     * Se utiliza para modificar el nombre de usuario de un cliente
     * @return {@code String} con el nuevo nombre de usuario.
     */

    public String modificarUsername() {
        String username = "";
        boolean usernameValido = false;

        while (!usernameValido) {
            System.out.println("Ingrese su nuevo username: ");
            username = scanner.nextLine();
            try {
                Validaciones.validarNombreUsuario(username);
                if (!Validaciones.existeUser(username)){
                    usernameValido = true;
                }else {
                    System.out.println("El username ya existe en el sistema.");
                }
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        return username;
    }

    /**
     * El metodo le permite al usuario modificar la contraseña. Le pide al usuario
     * que ingrese la contraseña actual para asegurarse de que la cuenta pertenezca al
     * usuario.
     * @param c
     * @return {@code String} con la nueva contraseña.
     */

    public String modificarContraseña(Cliente c) {

        String contrasenia = "";
        boolean contraseniaValida = false;

        System.out.println("Ingrese su contraseña actual:");
        String contraseñaActual = scanner.nextLine();
        if (c.getContrasenia().equals(contraseñaActual)) {
            while (!contraseniaValida) {
                System.out.println("Ingrese su nueva contrasenia: ");
                contrasenia = scanner.nextLine();
                try {
                    Validaciones.validarContrasenia(contrasenia);
                    contraseniaValida = true;
                } catch (DatoInvalidoException e) {
                    System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
                }
            }
        }

        return contrasenia;
    }

    /**
     * El metodo permite al usuario ingresar un nuevo nombre .
     * @return {@code String} con el nuevo nombre.
     */

    public String modificarNombre() {
        String nombre = "";
        boolean nombreValido = false;

        while (!nombreValido) {
            System.out.println("Ingrese su nuevo nombre: ");
            nombre = scanner.nextLine();
            try {
                Validaciones.validarCadenas(nombre);
                nombreValido = true;
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }
        return nombre;
    }

    /**
     * El metodo permita que el usuario ingrese un nuevo apellido para modificarlo.
     * @return {@code String} con el nuevo apellido.
     */

    public String modificarApellido() {

        String apellido = "";
        boolean apellidoValido = false;

        while (!apellidoValido) {
            System.out.println("Ingrese su nuevo apellido: ");
            apellido = scanner.nextLine();
            try {
                Validaciones.validarCadenas(apellido);
                apellidoValido = true;
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }
        return apellido;
    }

    /**
     * Permite que el usuario ingrese un nuevo DNI.
     * @return {@code String} con el nuevo DNI.
     */

    public String modificarDni(){

        String dni = "";
        boolean dniValido = false;

        while (!dniValido) {
            System.out.println("Ingrese su nuevo DNI: ");
            dni = scanner.nextLine();
            try {
                Validaciones.validarDNI(dni);
                if (!Validaciones.existeDni(dni, "cliente")){
                    dniValido = true;
                }else {
                    System.out.println("El DNI ya existe en el sistema.");
                }
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }
        return dni;
    }

    /**
     * Permite modificar el telefono
     * @return {@code String} con el nuevo telefono.
     */

    public String modificarTelefono (){

        String telefono = "";
        boolean telefonoValido = false;

        while (!telefonoValido) {
            System.out.println("Ingrese su nuevo telefono: ");
            telefono = scanner.nextLine();
            try {
                Validaciones.validarTelefono(telefono);
                telefonoValido = true;
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }
        return telefono;
    }

    /**
     * Le permite al usuario ingresar una nueva direccion.
     * @return {@code String} con la nueva direccion.
     */

    public String modificarDireccion (){

        String direccion = "";
        boolean direccionValido = false;

        while (!direccionValido) {
            System.out.println("Ingrese su nueva direccion: ");
            direccion = scanner.nextLine();
            try {
                Validaciones.validarDireccion(direccion);
                direccionValido = true;
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }
        return direccion;
    }

    /**
     * Le permite al usuario ingresar una nueva direccion de email.
     * @return {@code String} con la nueva direccion de email.
     */

    public String modificarEmail (){
        String email = "";
        boolean emailValido = false;

        while (!emailValido) {
            System.out.println("Ingrese su nuevo email: ");
            email = scanner.nextLine();
            try {
                Validaciones.validarEmail(email);
                emailValido = true;
            } catch (DatoInvalidoException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, intente nuevamente");
            }
        }

        return email;
    }

    /**
     * Le permite al usuario modificar diversos datos de su cuenta.
     * @param c
     * @return {@code Cliente} modificado.
     */

    public Cliente modificarUsuario (Cliente c) {

        listaDeClientes = cargarArrayConArchivo();
        boolean salir = false;

        for (Cliente cliente : listaDeClientes) {
            if (c.getId() == cliente.getId()) {
                listaDeClientes.remove(cliente);
                c=cliente;
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

                            String username = modificarUsername();
                            c.setUsername(username);

                            break;
                        case 2:

                            String contrasenia = modificarContraseña(c);
                            c.setContrasenia(contrasenia);

                            break;
                        case 3:

                            String nombre = modificarNombre();
                            c.setNombre(nombre);

                            break;
                        case 4:

                            String apellido = modificarApellido();
                            c.setApellido(apellido);

                            break;
                        case 5:

                            String dni = modificarDni();
                            c.setDni(dni);

                            break;
                        case 6:

                            String telefono = modificarTelefono();
                            c.setTelefono(telefono);

                            break;
                        case 7:

                            String direccion = modificarDireccion();
                            c.setDireccion(direccion);

                            break;
                        case 8:

                            String email = modificarEmail();
                            c.setEmail(email);

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
                listaDeClientes.add(c);
                listaDeClientes = GuardarTipoDeCliente();
                cargarArchivoConArreglo(listaDeClientes);
                System.out.println("¡Cambios guardados con exito!");
                return c;
            }
        }
        return null;
    }

    /**
     * El metodo le permite al usuario darse de baja del sistema.
     * @param a
     */

    @Override
    public void darDeBajaUsuario(Cliente a) {
        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes){
            if (a.equals(cliente)){
                System.out.println("¿Esta seguro de eliminar el usuario? SI o NO.");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    int intentosContra = 0;
                    while (intentosContra < 3){
                        System.out.println("Ingrese su contraseña para eliminar su cuenta:");
                        String contraseña = scanner.nextLine();
                        if (contraseña.equals(a.getContrasenia())){
                            cliente.setEstado(false);
                            System.out.println("Cuenta eliminada con exito.");
                            cargarArchivoConArreglo(listaDeClientes);
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
     * El metodo muestra todos los clientes almacenados en el sistema.
     */

    public void mostrarColeccion () {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }
        listaDeClientes.forEach(cliente -> mostrarDatosUsuario(cliente));
    }

    /**
     * El metodo muestra todos los clientes almacenados en el sistema, sin mostrar la contraseña.
     */

    public void mostrarColeccionEmpleados (){
        if (listaDeClientes.isEmpty()){
            cargarArrayConArchivo();
        }

        listaDeClientes.forEach(cliente -> mostrarDatosUsuarioEmpleado(cliente));
    }

    /**
     * El metodo le permite al usuario encontrar un cliente por su DNI.
     * @param dni
     * @return {@code Cliente} encontrado.
     */

    @Override
    public Cliente encontrarUsuario(String dni) {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaDeClientes.stream()
                .filter(cliente -> cliente.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    /**
     * El metodo le permite al usuario encontrar un cliente por su ID.
     * @param id
     * @return {@code Cliente} encontrado.
     */

    @Override
    public Cliente encontrarUsuario(int id) {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaDeClientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * El metodo lista a los usuarios segun el apellido.
     * @param apellido
     */

    @Override
    public void listarUsuarios(String apellido) {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }
        boolean en = false;

        en = listaDeClientes.stream()
                .filter(cliente -> cliente.getApellido().equals(apellido))
                .peek(cliente -> mostrarDatosUsuario(cliente))
                .count() > 0;

        if(!en){
            System.out.println("No se encontraron clientes con ese apellido.");
        }
    }

    /**
     * EL metodo permite listar a los usuarios segun el estado de este (activo o inactivo).
     * @param aux
     */

    @Override
    public void listarUsuarios(boolean aux) {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }

        boolean en = false;

        en = listaDeClientes.stream()
                .filter(cliente -> cliente.getEstado() == aux)
                .peek(cliente -> mostrarDatosUsuario(cliente))
                .count() > 0;

        if(!en){
            System.out.println("No se encontraron clientes");
        }
    }

    /**
     * El metodo le permite al usuario darse de alta en el sistema.
     * @param a
     */

    @Override
    public void darDeAltaUsuario(Cliente a) {
        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes) {
            String opcion = null;
            if (a.equals(cliente)) {
                System.out.println("¿Esta seguro de dar de alta al Cliente? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    cliente.setEstado(true);
                    System.out.println("Cliente agregado con exito.");
                    cargarArchivoConArreglo(listaDeClientes);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro al Cliente.");
    }

    /**
     * El metodo se utiliza para dar de baja a los Clientes en caso de que seas un Administrador,
     * ya que no pide la credencial contraseña.
     * @param a
     */

    public void darDeBajaUsuarioAdmin(Cliente a) {
        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes) {
            String opcion = null;
            if (a.equals(cliente)) {
                System.out.println("¿Esta seguro de eliminar al Cliente? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setEstado(false);
                    System.out.println("Cliente eliminado con exito.");
                    cargarArchivoConArreglo(listaDeClientes);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro al Cliente.");
    }

    /**
     * El metodo actualiza y guarda el tipo de cliente segun la cantidad de reservas que tenga.
     * @return {@code Set <Cliente>} con el estado de los clientes actualizados.
     */

    public Set<Cliente> GuardarTipoDeCliente(){
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }

        for(Cliente cliente : listaDeClientes){
            GestionReserva gestionReserva = new GestionReserva();
            int contador = gestionReserva.obtenerCantidadDeReservas(cliente);

            if(contador < 2){
                cliente.setTipoCliente(TipoCliente.ESTANDAR);
            }
            else if (contador < 3) {
                cliente.setTipoCliente(TipoCliente.PREMIUM);
            }
            else if(contador < 5){
                cliente.setTipoCliente(TipoCliente.VIP);
            }
        }
        return listaDeClientes;
    }
}

