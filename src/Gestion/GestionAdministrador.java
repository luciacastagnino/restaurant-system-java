package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Users.Administrador;
import Users.DatoInvalidoException;
import Users.RegistroUser;
import Users.Validaciones;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

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

    public void ingresarUsuario(){
        Scanner scan = new Scanner(System.in);
            System.out.println();
            Administrador aux = registroUser.registroAdmin();
            agregarYguardar(aux);
            System.out.println("\nAdministrador/a " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");

    }

    public Set<Administrador> cargarArrayConArchivo(){
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

        return listaAdmins;
    }

    public void agregarYguardar (Administrador nuevoAdmin){
        cargarArrayConArchivo();
        listaAdmins.add(nuevoAdmin);
        cargarArchivoConArreglo(listaAdmins);
    }

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
                                    c.setUsername(username);
                                    usernameValido = true;
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
                                    c.setDni(dni);
                                    dniValido = true;
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

    @Override
    public void darDeBajaUsuario(Administrador a) {
        listaAdmins = cargarArrayConArchivo();

        for (Administrador admin : listaAdmins){
            if (a.equals(admin)){
                System.out.println("¿Esta seguro de eliminar el usuario? SI o NO.");
                String opcion = scanner.nextLine();
                if (opcion.toLowerCase().equals("si")){
                    int intentosContra = 0;
                    while (intentosContra < 3){
                        System.out.printf("Ingrese su contraseña para eliminar su cuenta:");
                        String contraseña = scanner.nextLine();
                        if (contraseña.equals(a.getContrasenia())){
                            a.setEstado(false);
                            System.out.printf("Cuenta eliminada con exito.");
                            cargarArchivoConArreglo(listaAdmins);
                            return;
                        }else {
                            intentosContra++;
                            System.out.printf("Contraseña incorrecta, intentelo nuevamente.");
                        }
                    }
                    System.out.printf("Se supero el numero maximo de intentos. El usuario no fue dado de baja.");
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


    public void mostrarColeccion () {
        if (listaAdmins.isEmpty()) {
            cargarArrayConArchivo();
        }
        listaAdmins.forEach(System.out::println);
    }

}



