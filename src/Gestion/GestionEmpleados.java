package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Users.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

/**
 * La clase Gestion.GestionEmpleados gestiona un conjunto de empleados de la aplicación.
 *
 *  - Tiene como campos un Set de Empleados (`listaEmpleados`), un objeto `registroUser` para gestionar el registro de nuevos empleados,
 *  y un `scanner` para la lectura de entradas del usuario.
 *  - tiene un constructor vacío
 * - Incluye métodos para agregar, modificar, buscar y listar empleados.
 * - Interactúa con un archivo JSON para guardar y cargar datos.
 * - Permite modificar atributos de un empleado y gestionar su estado (activo/inactivo).
 * - Diferencia entre empleados de medio tiempo y tiempo completo.
 *
 * @author Melina
 * @since 2024
 * @version 1
 */

public class GestionEmpleados implements MetodosBasicosGestion<Empleado> {
    private Set<Empleado> listaEmpleados;
    private RegistroUser registroUser;
    private Scanner scanner;

    public GestionEmpleados() {
        this.listaEmpleados = new TreeSet<Empleado>();
        this.registroUser = new RegistroUser();
        GestionJSON.crearArchivoJSON("empleados.json");
        this.scanner = new Scanner(System.in);
    }

    public void ingresarUsuario() {

        System.out.println("\nSeleccione el tipo de empleado que desea ingresar: ");
        System.out.println("1. Empleado tiempo completo.");
        System.out.println("2. Empleado medio tiempo.");

        int tipoEmpleado = scanner.nextInt();
        scanner.nextLine();
        if (tipoEmpleado == 1) {
            EmpleadoTiempoCompleto aux = registroUser.registroEmpleadoTC();
            agregarYguardar(aux);
            System.out.println("\nEmpleado/a " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");
        } else if (tipoEmpleado == 2) {
            EmpleadoMedioTiempo aux = registroUser.registroEmpleadoMT();
            agregarYguardar(aux);
            System.out.println("\nEmpleado/a " + aux.getNombre() + " " + aux.getApellido() + " agregado con exito!");
        } else {
            System.out.println("Opcion invalida. Por favor, seleccione 1 o 2.");
        }

    }

    /**
     * cargarArrayConArchivo carga los datos de empleados desde un archivo JSON
     * y los convierte en objetos EmpleadoTiempoCompleto o EmpleadoMedioTiempo según corresponda.
     *
     * @return un conjunto de objetos Empleado cargados desde el archivo "empleados.json".
     */
    public Set<Empleado> cargarArrayConArchivo() {
        JSONTokener aux = GestionJSON.leer("empleados.json");

        try {

            JSONArray arreglo = new JSONArray(aux);
            for (int i = 0; i < arreglo.length(); i++) {
                JSONObject aux1 = arreglo.getJSONObject(i);
                if (aux1.has("aniosAntiguedad")) {
                    EmpleadoTiempoCompleto empleadoTiempoCompleto = new EmpleadoTiempoCompleto();
                    empleadoTiempoCompleto = empleadoTiempoCompleto.jsonToEmpleadoTC(aux1);
                    listaEmpleados.add(empleadoTiempoCompleto);
                } else if (aux1.has("precioPorHora") && aux1.has("horasTrabajadas")) {
                    EmpleadoMedioTiempo empleadoMedioTiempo = new EmpleadoMedioTiempo();
                    empleadoMedioTiempo = empleadoMedioTiempo.jsonToEmpleadoMT(aux1);
                    listaEmpleados.add(empleadoMedioTiempo);
                } else {
                    System.out.println("Ocurrio un error. Por favor, intentelo de nuevo.");
                }
            }
        } catch (JSONException e) {
            System.out.println("Ocurrio un error al convertir JSONObject a Empleado.");
        }

        return listaEmpleados;
    }

    public void agregarYguardar(Empleado nuevoEmpleado) {
        cargarArrayConArchivo();
        listaEmpleados.add(nuevoEmpleado);
        cargarArchivoConArreglo(listaEmpleados);
    }

    /**
     * cargarArchivoConArreglo guarda los datos de los empleados en un archivo JSON,
     * separando los empleados en dos tipos: EmpleadoTiempoCompleto y EmpleadoMedioTiempo.
     * Si el tipo de empleado es desconocido, se muestra un mensaje de error.
     *
     * @param listaEmpleados un conjunto de objetos Empleado a guardar en el archivo "empleados.json".
     */
    public void cargarArchivoConArreglo(Set<Empleado> listaEmpleados) {
        JSONArray arreglo = new JSONArray();
        JSONObject json = null;
        try {
            for (Empleado empleado : listaEmpleados) {
                try {
                    if (empleado instanceof EmpleadoTiempoCompleto) {
                        json = ((EmpleadoTiempoCompleto) empleado).toJson((EmpleadoTiempoCompleto) empleado);
                        arreglo.put(json);
                        GestionJSON.agregarElemento("empleados.json", arreglo);
                    } else if (empleado instanceof EmpleadoMedioTiempo) {
                        json = ((EmpleadoMedioTiempo) empleado).toJson((EmpleadoMedioTiempo) empleado);
                        arreglo.put(json);
                        GestionJSON.agregarElemento("empleados.json", arreglo);
                    } else {
                        System.out.println("Error: Tipo de empleado desconocido.");
                    }
                } catch (FormatoIncorrectoException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e) {
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void mostrarDatosUsuario(Empleado a) {

        listaEmpleados = cargarArrayConArchivo();

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId() == a.getId()) {
                System.out.println();
                System.out.println("--------------------------------------------");
                System.out.println("PERFIL DE EMPLEADO: " + a.getNombre() + " " + a.getApellido());
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
                System.out.println("Sueldo: " + a.getSueldo());
                if (empleado instanceof EmpleadoTiempoCompleto){
                    System.out.println("Años de antiguedad: " + ((EmpleadoTiempoCompleto) empleado).getAniosAntiguedad());
                }else if (empleado instanceof EmpleadoMedioTiempo){
                    System.out.println("Horas trabajadas: " + ((EmpleadoMedioTiempo) empleado).getHorasTrabajadas());
                    System.out.println("Precio por hora: " + ((EmpleadoMedioTiempo) empleado).getPrecioXhora());
                }
                System.out.println("--------------------------------------------");

                return;
            }
        }

        System.out.printf("No se encontro al usuario.");

    }

    @Override
    public Empleado modificarUsuario(Empleado empleado) {

        if (empleado instanceof EmpleadoMedioTiempo) {
            empleado = modificarEmpleadoMT((EmpleadoMedioTiempo) empleado);
            return empleado;
        } else if (empleado instanceof EmpleadoTiempoCompleto) {
            empleado = modificarEmpleadoTC((EmpleadoTiempoCompleto) empleado);
            return empleado;
        } else {
            throw new IllegalArgumentException("El tipo de empleado no es valido.");
        }
    }

    public EmpleadoMedioTiempo modificarEmpleadoMT(EmpleadoMedioTiempo c) {

        listaEmpleados = cargarArrayConArchivo();
        boolean salir = false;

        for (Empleado empleado : listaEmpleados){
            if (empleado.getId() == c.getId()){
                listaEmpleados.remove(empleado);
                c= (EmpleadoMedioTiempo) empleado;
                while (!salir){
                    System.out.println("\n Que desea modificar?");
                    System.out.println("1. Username.");
                    System.out.println("2. Contraseña.");
                    System.out.println("3. Nombre.");
                    System.out.println("4. Apellido.");
                    System.out.println("5. DNI.");
                    System.out.println("6. Telefono.");
                    System.out.println("7. Direccion.");
                    System.out.println("8. Email.");
                    System.out.println("9. Sueldo.");
                    System.out.println("10. Horas Trabajadas.");
                    System.out.println("11. Precio por hora.");
                    System.out.println("12. Salir.");
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
                                    if (!Validaciones.existeDni(dni, "empleado")){
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

                            boolean sueldoValido = false;
                            while (!sueldoValido){
                                System.out.println("Ingrese el nuevo sueldo:");
                                double sueldo = scanner.nextDouble();
                                scanner.hasNextLine();
                                if (sueldo <= 0){
                                    System.out.println("El sueldo no puede ser menor o igual a 0.");
                                }else {
                                    c.setSueldo(sueldo);
                                    sueldoValido=true;
                                }
                            }

                            break;
                        case 10:

                            boolean horasTrabajadasValidas = false;

                            while (!horasTrabajadasValidas) {
                                System.out.println("Ingrese sus horas trabajadas:");
                                int horasTrabajadas = scanner.nextInt();
                                scanner.nextLine();
                                if (horasTrabajadas <= 0) {
                                    System.out.println("Las horas trabajadas no pueden ser menores o igual a 0.");
                                } else {
                                    c.setHorasTrabajadas(horasTrabajadas);
                                    horasTrabajadasValidas = true;
                                }
                            }

                            break;
                        case 11:

                            boolean precioPorHoraValido = false;

                            while (!precioPorHoraValido) {
                                System.out.println("Ingrese el precio por hora trabajada:");
                                double precioPorHora = scanner.nextDouble();
                                scanner.nextLine();
                                if (precioPorHora <= 0) {
                                    System.out.println("El precio por hora no puede ser menores o igual a 0.");
                                } else {
                                    c.setPrecioXhora(precioPorHora);
                                    precioPorHoraValido = true;
                                }
                            }

                            break;
                        case 12:
                            System.out.println("Saliendo del menu de modificacion de usuario...");
                            salir=true;
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                }
                listaEmpleados.add(c);
                cargarArchivoConArreglo(listaEmpleados);
                System.out.println("¡Cambios guardados con exito!");
            }
        }
        return null;
    }

    public EmpleadoTiempoCompleto modificarEmpleadoTC (EmpleadoTiempoCompleto c){

        listaEmpleados = cargarArrayConArchivo();
        boolean salir = false;

        for (Empleado empleado : listaEmpleados){
            if (c.getId() == empleado.getId()){
                listaEmpleados.remove(empleado);
                c= (EmpleadoTiempoCompleto) empleado;
                while(!salir){
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
                    System.out.println("10. Sueldo.");
                    System.out.println("11 Salir.");
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
                                    if (!Validaciones.existeDni(dni, "empleado")){
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

                            boolean añosAntiguedadValidos = false;

                            while (!añosAntiguedadValidos) {
                                System.out.println("Ingrese sus años de antiguedad:");
                                int añosAntiguedad = scanner.nextInt();
                                scanner.nextLine();
                                if (añosAntiguedad <= 0) {
                                    System.out.println("Los años de antiguedad no pueden ser menores o igual a 0.");
                                } else {
                                    c.setAniosAntiguedad(añosAntiguedad);
                                    añosAntiguedadValidos = true;
                                }
                            }
                            break;
                        case 10:

                            boolean sueldoValido = false;
                            while (!sueldoValido){
                                System.out.println("Ingrese el nuevo sueldo:");
                                double sueldo = scanner.nextDouble();
                                scanner.hasNextLine();
                                if (sueldo <= 0){
                                    System.out.println("El sueldo no puede ser menor o igual a 0.");
                                }else {
                                    c.setSueldo(sueldo);
                                    sueldoValido=true;
                                }
                            }

                            break;
                        case 11:
                            System.out.println("Saliendo del menu de modificacion de usuario...");
                            salir=true;
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                            break;
                    }
                }
                listaEmpleados.add(c);
                cargarArchivoConArreglo(listaEmpleados);
                System.out.println("¡Cambios guardados con exito!");
                return c;
            }
        }
        return null;
    }

    @Override
    public Empleado encontrarUsuario(String dni) {
        if (listaEmpleados.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaEmpleados.stream()
                .filter(empleado -> empleado.getDni().equals(dni))
                .findFirst()
                .map(empleado ->{
                    if(empleado instanceof EmpleadoMedioTiempo){
                        return (EmpleadoMedioTiempo) empleado;
                    }
                    else if (empleado instanceof EmpleadoTiempoCompleto) {
                        return (EmpleadoTiempoCompleto) empleado;
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public Empleado encontrarUsuario(int id) {
        if (listaEmpleados.isEmpty()) {
            cargarArrayConArchivo();
        }

        return listaEmpleados.stream()
                .filter(empleado -> empleado.getId() == id)
                .findFirst()
                .map(empleado ->{
                    if(empleado instanceof EmpleadoMedioTiempo){
                        return (EmpleadoMedioTiempo) empleado;
                    }
                    else if (empleado instanceof EmpleadoTiempoCompleto) {
                        return (EmpleadoTiempoCompleto) empleado;
                    }
                    return null;
                })
                .orElse(null);
    }

    @Override
    public void listarUsuarios(String nombre) {
        if (listaEmpleados.isEmpty()) {
            cargarArrayConArchivo();
        }
        boolean en = false;

        en = listaEmpleados.stream()
                .filter(empleado -> empleado.getNombre().equals(nombre))
                .peek(empleado -> mostrarDatosUsuario(empleado))
                .count() > 0;

        if(!en){
            System.out.println("No se encontraron empleados con ese nombre.");
        }
    }

    @Override
    public void listarUsuarios(boolean aux) {
        if (listaEmpleados.isEmpty()) {
            cargarArrayConArchivo();
        }
        boolean en = false;
        en = listaEmpleados.stream()
                .filter(empleado -> empleado.getEstado() == aux)
                .peek(empleado -> mostrarDatosUsuario(empleado))
                .count() > 0;

        if(!en){
            System.out.println("No se encontraron empleados con ese nombre.");
        }
    }

    @Override
    public void darDeAltaUsuario(Empleado a) {
        listaEmpleados = cargarArrayConArchivo();

        for (Empleado empleado : listaEmpleados) {
            String opcion = null;
            if (a.equals(empleado)) {
                System.out.println("¿Esta seguro de dar de alta al Empleado? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setEstado(true);
                    System.out.println("Empleado agregado con exito.");
                    cargarArchivoConArreglo(listaEmpleados);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }
        System.out.println("No se encontro al Empleado.");
    }

    public void darDeBajaUsuarioAdmin(Empleado a) {
        listaEmpleados = cargarArrayConArchivo();

        for (Empleado empleado : listaEmpleados) {
            String opcion = null;
            if (a.equals(empleado)) {
                System.out.println("¿Esta seguro de eliminar al Empleado? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setEstado(false);
                    System.out.println("Empleado eliminado con exito.");
                    cargarArchivoConArreglo(listaEmpleados);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro al Empleado.");
    }

    @Override
    public void darDeBajaUsuario (Empleado a){
        listaEmpleados = cargarArrayConArchivo();
        for (Empleado empleado : listaEmpleados) {
            if (a.equals(empleado)) {
                System.out.println("¿Esta seguro de eliminar el usuario? SI o NO.");
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")) {
                    int intentosContra = 0;
                    while (intentosContra < 3) {
                        System.out.println("Ingrese su contraseña para eliminar su cuenta:");
                        String contraseña = scanner.nextLine();
                        if (contraseña.equals(a.getContrasenia())) {
                            a.setEstado(false);
                            System.out.println("Cuenta eliminada con exito.");
                            cargarArchivoConArreglo(listaEmpleados);
                            return;
                        } else {
                            intentosContra++;
                            System.out.println("Contraseña incorrecta, intentelo nuevamente.");
                        }
                    }
                    System.out.println("Se supero el numero maximo de intentos. El usuario no fue dado de baja.");
                } else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }
        System.out.println("No se encontro al usuario.");
    }


    public void mostrarColeccion () {
        if (listaEmpleados.isEmpty()) {
            cargarArrayConArchivo();
        }
        listaEmpleados.forEach(empleado -> mostrarDatosUsuario(empleado));
    }
}