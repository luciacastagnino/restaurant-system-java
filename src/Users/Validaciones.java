package Users;

import Gestion.GestionAdministrador;
import Gestion.GestionDeCliente;
import Gestion.GestionEmpleados;

import java.util.HashSet;
import java.util.Set;

/**
 * La clase Users.Validaciones no tiene atributos, se encarga de verificar que los datos ingresados sean
 * correctos.
 * metodos: validarNombreUsuario, validarContrasenia, validarCadenas, validarDNI, validarTelefono,
 * validarDireccion, validarEmail.
 *
 * @author Lucia
 * @since 2024
 * @version 1
 */
public class Validaciones {

    public Validaciones() {
    }

    //VALIDACION INGRESO DE DATOS

    /**
     * validarNombreUsuario se encarga de verificar que el nombre de usuario tenga al menos 5 caracteres
     * y que no tenga espacios
     *
     * @param username
     * @throws DatoInvalidoException si el nombre de usuario tiene menos de 5 caracteres, mas de 30 caracteres
     * o espacios
     */

    public static void validarNombreUsuario (String username) throws DatoInvalidoException {
        if (username.length() < 5 || username.length() > 30){
            throw new DatoInvalidoException("El username debe tener al menos 5 caracteres.");
        }
        if (username.contains(" ")){
            throw new DatoInvalidoException("El username no puede contener espacios.");
        }
    }

    /**
     * validarContrasenia, recibe un String, verifica que la contrasenia tenga al menos 10 caracteres
     *
     * @param contrasenia
     * @throws DatoInvalidoException
     */
    public static void validarContrasenia(String contrasenia) throws DatoInvalidoException {
        if (contrasenia.length() < 10) {
            throw new DatoInvalidoException("La contraseña debe tener al menos 10 caracteres.");
        }

        if (!contrasenia.matches(".*[0-9].*")) {
            throw new DatoInvalidoException("La contraseña debe tener al menos un numero.");
        }

        if (!contrasenia.matches(".*[!@#$%^&*_(),.?\":{}|<>].*")) {
            throw new DatoInvalidoException("La contraseña debe tener al menos un caracter especial.");
        }

    }

    /**
     * validarCadena recibe un String, verifica que el String no contenga numeros,
     * por ejemplo en el nombre o apellido
     *
     * @param cadena
     * @throws DatoInvalidoException si el String contiene numeros
     */
    public static void validarCadenas(String cadena) throws DatoInvalidoException {

        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isDigit(cadena.charAt(i))) {
                throw new DatoInvalidoException("Formato incorrecto: no se permiten números en este campo.");
            }
        }
    }

    /**
     * validarDNI recibe un String, verifica que el DNI contenga solo numeros
     *
     * @param dni
     * @throws DatoInvalidoException si contiene simbolos o letras y si no se encuentra entre 1.000.000 y 99.999.999
     */
    public static void validarDNI(String dni) throws DatoInvalidoException {

        if (!dni.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("DNI inválido: solo debe contener números.");
        }

        int dniValidacion = Integer.parseInt(dni);
        if (dniValidacion < 10000000 || dniValidacion > 99000000) {
            throw new DatoInvalidoException("DNI inválido: debe estar entre 1.000.000 y 99.999.999.");
        }
    }

    /**
     * validarTelefono recibe un String, verifica que solo contenga numeros y que tenga entre 10 y 11 digitos
     *
     * @param telefono
     * @throws DatoInvalidoException si contiene letras o simbolos y si tiene menos o mas de 10 u 11 digitos
     */

    public static void validarTelefono (String telefono) throws DatoInvalidoException {

        if (!telefono.matches("^[0-9]+$")) {
            throw new DatoInvalidoException("Teléfono inválido: solo debe contener números.");
        }

        if (telefono.length() < 10 || telefono.length() > 11){
            throw new DatoInvalidoException("Teléfono inválido: debe tener entre 10 y 11 dígitos.");
        }
    }

    /**
     * validarDireccion recibe un String, verifica que la direccion tenga entre 5 y 100 caracteres,
     * que no contenga simbolos y que tenga si o si letras y numeros
     * @param direccion
     * @throws DatoInvalidoException
     */

    public static void validarDireccion (String direccion) throws DatoInvalidoException {
        if (direccion.length() < 5 || direccion.length() > 100){
            throw new DatoInvalidoException("La dirección debe tener entre 5 y 100 caracteres.");
        }

        if (direccion.matches(".*[!@#$%^&*()_+={}\\[\\]:\";'<>?].*")) {
            throw new DatoInvalidoException("La dirección contiene caracteres no válidos.");
        }

        if (!direccion.matches(".*[a-zA-Z].*") || !direccion.matches(".*[0-9].*")) {
            throw new DatoInvalidoException("La dirección debe contener al menos una letra y un número.");
        }
    }

    /**
     * validarEmail recibe un String, verifica que el correo tenga mas de 5 caracteres y que contenga el
     * simbolo @
     *
     * @param email
     * @throws DatoInvalidoException
     */
    public static void validarEmail (String email) throws DatoInvalidoException {
        if (email.length() < 5){
            throw new DatoInvalidoException("El correo electronico es demasiado corto.");
        }

        if (!email.contains("@")){
            throw new DatoInvalidoException("El correo electronico debe contener el simbolo '@'.");
        }
    }

    /**
     * existeDni es un metodo que verifica que el DNI de ningun usuario se repita, recibe dos Strings.
     * @param dni
     * @param tipoUsuario
     * @return si el DNI existe o no
     */
    public static boolean existeDni (String dni, String tipoUsuario){

        boolean existe = false;
        GestionAdministrador gestionAdministrador = new GestionAdministrador();
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        GestionDeCliente gestionDeCliente = new GestionDeCliente();

        if (tipoUsuario.equalsIgnoreCase("administrador")){
            Set <Administrador> listaAdmins = gestionAdministrador.cargarArrayConArchivo();
            for (Administrador administrador : listaAdmins){
                if (administrador.getDni().equals(dni)){
                    existe=true;
                }
            }
        }else if (tipoUsuario.equalsIgnoreCase("empleado")){
            Set<Empleado>listaEmpleado = gestionEmpleados.cargarArrayConArchivo();
            for (Empleado empleado : listaEmpleado){
                if (empleado.getDni().equals(dni)){
                    existe=true;
                }
            }
        }else if (tipoUsuario.equalsIgnoreCase("cliente")){
            Set<Cliente>listaClientes = gestionDeCliente.cargarArrayConArchivo();
            for (Cliente cliente : listaClientes){
                if (cliente.getDni().equals(dni)){
                    existe=true;
                }
            }
        }

        return existe;
    }

    /**
     * existeUser verifica que los nombres de usuario no se repitan, recibe un String.
     *
     * @param username
     * @return si el username existe o no
     */
    public static boolean existeUser (String username){
        GestionAdministrador gestionAdministrador = new GestionAdministrador();
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        GestionDeCliente gestionDeCliente = new GestionDeCliente();

        boolean existe=false;

        Set<Administrador> setAdmin = new HashSet<>();
        Set<Cliente> clienteSet = new HashSet<>();
        Set<Empleado> empleadoSet = new HashSet<>();
        setAdmin = gestionAdministrador.cargarArrayConArchivo();
        clienteSet = gestionDeCliente.cargarArrayConArchivo();
        empleadoSet = gestionEmpleados.cargarArrayConArchivo();

        Set<String> nombreUsuario = new HashSet<>();
        setAdmin.forEach(administrador -> nombreUsuario.add(administrador.getUsername()));
        clienteSet.forEach(cliente -> nombreUsuario.add(cliente.getUsername()));
        empleadoSet.forEach(empleado -> nombreUsuario.add(empleado.getUsername()));

        for (String nombre : nombreUsuario){
            if (nombre.equals(username)){
                existe=true;
            }
        }
        return existe;
    }

}
