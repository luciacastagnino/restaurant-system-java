package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public class GestionEmpleados implements MetodosBasicosGestion<Empleado>{
    private Set<Empleado> listaEmpleados;
    private RegistroUser registroUser;

    public GestionEmpleados() {
        this.listaEmpleados = new TreeSet<Empleado>();
        GestionJSON.crearArchivoJSON("empleados.json");
    }

    public void ingresarUsuario(){
        System.out.println();
        Cliente aux = registroUser.registroCliente();
        agregarYguardar(aux);
        System.out.println("\nCliente " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");
    }

    public List<Cliente> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("clientes.json");

        try {

            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Cliente cliente = new Cliente();
                cliente = cliente.jsonToCliente(aux1);
                listaDeClientes.add(cliente);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Cliente.");
        }

        return listaDeClientes;
    }

    public void agregarYguardar (Cliente nuevoCliente){
        cargarArrayConArchivo();
        listaDeClientes.add(nuevoCliente);
        cargarArchivoConArreglo(listaDeClientes);
    }

    public void cargarArchivoConArreglo(List<Cliente> listaDeClientes){
        JSONArray arreglo = new JSONArray();
        try {

            for (Cliente cliente : listaDeClientes){
                try {
                    JSONObject json = cliente.toJson(cliente);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("clientes.json", arreglo);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void mostrarDatosUsuario (Cliente a){

        listaDeClientes = cargarArrayConArchivo();

        for (Cliente cliente : listaDeClientes){
            if (cliente.getId() == a.getId()){
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
                listaDeClientes.add(c);
                cargarArchivoConArreglo(listaDeClientes);
                System.out.println("¡Cambios guardados con exito!");
                return c;
            }
        }
        return null;
    }

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
                            a.setEstado(false);
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


    public void mostrarColeccion () {
        if (listaDeClientes.isEmpty()) {
            cargarArrayConArchivo();
        }
        listaDeClientes.forEach(System.out::println);
    }

    /// /////////////////////////////////////////////////////////////

    public void ingresarUsuario(){
        char op = 's';
        Scanner scan = new Scanner(System.in);

        while (op == 's'){
            System.out.println("\nSeleccione el tipo de empleado que desea ingresar: ");
            System.out.println("1. Users.Empleado tiempo completo.");
            System.out.println("2. Users.Empleado medio tiempo.");

            int tipoEmpleado = scan.nextInt();
            scan.nextLine();
            if (tipoEmpleado == 1){
                EmpleadoTiempoCompleto aux = registroUser.registroEmpleadoTC();
                listaEmpleados.add(aux);
            }else if (tipoEmpleado == 2){
                EmpleadoMedioTiempo aux = registroUser.registroEmpleadoMT();
                listaEmpleados.add(aux);
            }else {
                System.out.println("Opcion invalida. Por favor, seleccione 1 o 2.");
            }

            System.out.println("\n¿Desea seguir ingresando empleados?");
            op = scan.nextLine().charAt(0);
        }
    }

    @Override
    public Empleado modificarUsuario(Empleado empleado) {

        if (empleado instanceof EmpleadoMedioTiempo){
            modificarEmpleadoMT((EmpleadoMedioTiempo) empleado);
            return empleado;
        }else if (empleado instanceof EmpleadoTiempoCompleto){
            modificarEmpleadoTC((EmpleadoTiempoCompleto) empleado);
            return empleado;
        }else {
            throw new IllegalArgumentException("El tipo de empleado no es valido.");
        }
    }

    public EmpleadoMedioTiempo modificarEmpleadoMT (EmpleadoMedioTiempo c){

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
        System.out.println("9. Horas Trabajadas.");
        System.out.println("10 Precio por hora.");
        System.out.println("11 Salir.");
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

                boolean horasTrabajadasValidas = false;

                while (!horasTrabajadasValidas){
                    System.out.println("Ingrese sus horas trabajadas:");
                    int horasTrabajadas = scanner.nextInt();
                    scanner.nextLine();
                    if (horasTrabajadas <= 0){
                        System.out.println("Las horas trabajadas no pueden ser menores o igual a 0.");
                    }else {
                        c.setHorasTrabajadas(horasTrabajadas);
                        horasTrabajadasValidas = true;
                    }
                }

                break;
            case 10:

                boolean precioPorHoraValido = false;

                while (!precioPorHoraValido){
                    System.out.println("Ingrese sus horas trabajadas:");
                    double precioPorHora = scanner.nextDouble();
                    scanner.nextLine();
                    if (precioPorHora <= 0){
                        System.out.println("El precio por hora no puede ser menores o igual a 0.");
                    }else {
                        c.setPrecioXhora(precioPorHora);
                        precioPorHoraValido = true;
                    }
                }

                break;
            case 11:
                System.out.println("Saliendo del menu de modificacion de usuario...");
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }

        return c;
    }

    public EmpleadoTiempoCompleto modificarEmpleadoTC (EmpleadoTiempoCompleto c){

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
        System.out.println("9. Años de antiguedad.");
        System.out.println("10 Salir.");
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

                boolean añosAntiguedadValidos = false;

                while (!añosAntiguedadValidos){
                    System.out.println("Ingrese sus años de antiguedad:");
                    int añosAntiguedad = scanner.nextInt();
                    scanner.nextLine();
                    if (añosAntiguedad <= 0){
                        System.out.println("Los años de antiguedad no pueden ser menores o igual a 0.");
                    }else {
                        c.setAniosAntiguedad(añosAntiguedad);
                        añosAntiguedadValidos = true;
                    }
                }

                break;
            case 10:
                System.out.println("Saliendo del menu de modificacion de usuario...");
                break;
            default:
                System.out.println("Opcion invalida.");
                break;
        }

        return c;
    }


    public void cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("empleados.json");

        try {
            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                if (aux1.has("aniosAntiguedad")) {
                    EmpleadoTiempoCompleto empleadoTiempoCompleto = new EmpleadoTiempoCompleto();
                    empleadoTiempoCompleto = empleadoTiempoCompleto.jsonToEmpleadoTC(aux1);
                    listaEmpleados.add(empleadoTiempoCompleto);
                }else if (aux1.has("precioPorHora") && aux1.has("horasTrabajadas")){
                    EmpleadoMedioTiempo empleadoMedioTiempo = new EmpleadoMedioTiempo();
                    empleadoMedioTiempo = empleadoMedioTiempo.jsonToEmpleadoMT(aux1);
                    listaEmpleados.add(empleadoMedioTiempo);
                }else {
                    System.out.println("Ocurrio un error. Por favor, intentelo de nuevo.");
                }
            }
        }
        catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Users.Empleado");
        }
    }

    public void cargarArchivoConArreglo(){
        JSONArray arreglo = null;
        try {
            arreglo = new JSONArray();
            JSONObject aux = null;

            Iterator<Empleado> it = listaEmpleados.iterator();
            while(it.hasNext()){

                arreglo.put(aux);
            }

            GestionJSON.agregarElemento("clientes.json", arreglo);
        }
        catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array");
        }
    }

    public void mostrarColeccionUsuario(){
        if(listaEmpleados.isEmpty()){
            cargarArrayConArchivo();
        }
        listaEmpleados.forEach(System.out::println);
    }

}
