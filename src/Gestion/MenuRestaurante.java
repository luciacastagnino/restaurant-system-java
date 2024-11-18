package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Plato;
import Restaurante.Reserva;
import Restaurante.TipoPlato;
import Users.Administrador;
import Users.Cliente;
import Users.DatoInvalidoException;
import Users.Validaciones;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * La clase Gestion.MenuRestaurante gestiona el menú de un restaurante, permitiendo mostrar los platos disponibles
 * organizados por categorías como desayuno, brunch, entradas, almuerzo, cena, postre y bebidas.
 * Además, maneja la disponibilidad de los platos y sus precios.
 *
 * Los métodos de esta clase incluyen la visualización de las diferentes categorías de platos del menú
 * y la opción de mostrar todo el menú completo con sus respectivas categorías.
 *
 * @author Melina
 * @since 2024
 * @version 1
 */
public class MenuRestaurante implements MetodosBasicosGestion<Plato>{

    private Map<Integer, Plato> listaPlatos;
    private Scanner scanner;

    /**
     * Constructor de la clase Gestion.MenuRestaurante.
     * Inicializa un conjunto vacío de platos que compondrán el menú del restaurante.
     *
     */
    public MenuRestaurante () {
        this.listaPlatos = new HashMap<>();
        GestionJSON.crearArchivoJSON("platos.json");
        this.scanner=new Scanner(System.in);
    }

    //Get y Set

    public void ingresarUsuario(){
        Plato aux = new Plato();
        aux = aux.cargarPlato();
        agregarYguardar(aux);
    }

    public Map<Integer, Plato> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("platos.json");

        try {
            JSONArray arreglo = new JSONArray(aux);
            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Plato plato = new Plato();
                plato = plato.jsonToPlato(aux1);
                listaPlatos.put(plato.getId(), plato);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Plato.");
        }

        return listaPlatos;
    }

    public void agregarYguardar (Plato plato){
        cargarArrayConArchivo();
        listaPlatos.put(plato.getId(), plato);
        cargarArchivoConArreglo(listaPlatos);
    }

    public void cargarArchivoConArreglo(Map<Integer, Plato> listaPlatos){
        JSONArray arreglo = new JSONArray();
        try {
            for (Plato plato : listaPlatos.values()){
                try {
                    JSONObject json = plato.toJson(plato);
                    arreglo.put(json);
                    GestionJSON.agregarElemento("platos.json", arreglo);
                }
                catch (FormatoIncorrectoException e){
                    System.out.println(e.getMessage());
                }
            }
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    @Override
    public void mostrarDatosUsuario(Plato plato) {
        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : listaPlatos.values()){
            if (p.equals(plato)){
                p.mostrarPlato();
            }
        }

    }

    @Override
    public Plato modificarUsuario (Plato c) {

        listaPlatos = cargarArrayConArchivo();
        boolean salir = false;

        if (listaPlatos.containsKey(c.getId())) {
            Plato plato = listaPlatos.get(c.getId());

            while (!salir) {
                System.out.println("\n Que desea modificar?");
                System.out.println("1. Nombre.");
                System.out.println("2. Descripcion.");
                System.out.println("3. Precio.");
                System.out.println("4. Tipo de plato.");
                System.out.println("5. Salir.");
                int op = scanner.nextInt();
                scanner.nextLine();
                switch (op) {
                    case 1:

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
                    case 2:

                        boolean descripcionValida = false;

                        while (!descripcionValida){
                            System.out.println("Ingrese la nueva descripcion: ");
                            String descripcion = scanner.nextLine();
                            if (descripcion!=null){
                                c.setDescripcion(descripcion);
                                descripcionValida=true;
                            }else {
                                System.out.println("Hubo un problema, trate de ingresar una descripcion valida.");
                            }
                        }

                        break;
                    case 3:

                        boolean precioValido = false;
                        while (!precioValido){
                            System.out.println("Ingrese el nuevo precio: ");
                            double precio = scanner.nextDouble();
                            scanner.nextLine();
                            if (precio<=0){
                                System.out.println("El precio no puede ser menor o igual a cero.");
                            }else {
                                c.setPrecio(precio);
                                precioValido=true;
                            }
                        }

                        break;
                    case 4:

                        boolean tipoPValido = false;

                        while (!tipoPValido){
                            System.out.println("Seleccione una opcion: ");
                            System.out.println("1. Desayuno.");
                            System.out.println("2. Brunch.");
                            System.out.println("3. Entradas.");
                            System.out.println("4. Almuerzo.");
                            System.out.println("5. Cena.");
                            System.out.println("6. Postre.");
                            System.out.println("7. Bebida.");
                            int opTP = scanner.nextInt();
                            scanner.nextLine();
                            switch (opTP){
                                case 1:
                                    c.setTipoPlato(TipoPlato.DESAYUNO);
                                    tipoPValido = true;
                                    break;
                                case 2:
                                    c.setTipoPlato(TipoPlato.BRUNCH);
                                    tipoPValido = true;
                                    break;
                                case 3:
                                    c.setTipoPlato(TipoPlato.ENTRADAS);
                                    tipoPValido = true;
                                    break;
                                case 4:
                                    c.setTipoPlato(TipoPlato.ALMUERZO);
                                    tipoPValido = true;
                                    break;
                                case 5:
                                    c.setTipoPlato(TipoPlato.CENA);
                                    tipoPValido = true;
                                    break;
                                case 6:
                                    c.setTipoPlato(TipoPlato.POSTRE);
                                    tipoPValido = true;
                                    break;
                                case 7:
                                    c.setTipoPlato(TipoPlato.BEBIDA);
                                    tipoPValido = true;
                                    break;
                                default:
                                    System.out.println("Opcion invalida.");
                                    break;
                            }
                        }

                        break;
                    case 5:
                        System.out.println("Saliendo del menu de modificacion de plato...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                        break;
                }
            }

            listaPlatos.put(c.getId(), c);
            cargarArchivoConArreglo(listaPlatos);
            System.out.println("¡Cambios guardados con exito!");
            return c;
        }else {
            System.out.println("No se encontro la reserva.");
        }
        return null;
    }

    @Override
    public void darDeBajaUsuario(Plato a) {
        listaPlatos = cargarArrayConArchivo();

        for (Plato plato : listaPlatos.values()) {
            String opcion = null;
            if (a.equals(plato)) {
                System.out.println("¿Esta seguro de eliminar el plato? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setDisponibilidad(false);
                    System.out.println("Plato eliminado con exito.");
                    cargarArchivoConArreglo(listaPlatos);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro el plato.");
    }

    @Override
    public Plato encontrarUsuario(String descripcion) {

        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : listaPlatos.values()){
            if (p.getDescripcion().equals(descripcion)){
                return p;
            }
        }

        return null;
    }

    @Override
    public void listarUsuarios(boolean aux) {
        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : listaPlatos.values()){
            if (p.isDisponibilidad()==aux){
                p.mostrarPlato();
            }
        }
    }

    @Override
    public void darDeAltaUsuario(Plato a) {

        for (Plato plato : listaPlatos.values()) {
            String opcion = null;
            if (a.equals(plato)) {
                System.out.println("¿Esta seguro de dar de alta el plato? SI o NO.");
                opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("si")){
                    a.setDisponibilidad(true);
                    System.out.println("Plato dado de alta con exito.");
                    cargarArchivoConArreglo(listaPlatos);
                    return;
                }else if (opcion.equalsIgnoreCase("no")) {
                    System.out.println("Operacion cancelada.");
                    return;
                } else {
                    System.out.println("Opcion invalida.");
                }
            }
        }

        System.out.println("No se encontro el plato.");
    }


    @Override
    public void mostrarColeccion() {
       mostrarMenuCompleto();
    }

    @Override
    public Plato encontrarUsuario(int id) {
        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : listaPlatos.values()){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void listarUsuarios(String nombre) {
        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : listaPlatos.values()){
            if (p.getNombre().contains(nombre)){
                p.mostrarPlato();
            }
        }

    }

    public void listarPlatosTicket (){
        if (listaPlatos.isEmpty()){
            cargarArrayConArchivo();
        }
        for (Plato p : listaPlatos.values()){
            System.out.println("ID: " + p.getId() + " / Plato: " + p.getNombre());
        }
    }

    /**
     * Muestra los platos de la categoría desayuno/merienda que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarDesayunoMerienda () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.DESAYUNO) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría brunch que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarBrunch () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.BRUNCH) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría entradas que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarEntradas () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.ENTRADAS) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría almuerzo que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarAlmuerzo () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.ALMUERZO) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría cena que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarCena () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.CENA) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría postre que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarPostre () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.POSTRE) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra los platos de la categoría bebida que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarBebida () {
        for (Plato p : listaPlatos.values()) {
            if (p.getTipoPlato().equals(TipoPlato.BEBIDA) && p.isDisponibilidad()) {
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    /**
     * Muestra el menú completo del día, con todas las categorías de platos disponibles.
     * El menú se organiza en secciones de desayuno/merienda, brunch, entradas, almuerzo, cena, postres y bebidas.
     *
     * @since 2024
     * @version 1
     */
    public void mostrarMenuCompleto () {
        System.out.println("========= MENÚ DEL DÍA =========");
        System.out.println();

        System.out.println(" DESAYUNO / MERIENDA");
        System.out.println("----------------------------");
        mostrarDesayunoMerienda();

        System.out.println();
        System.out.println(" BRUNCH");
        System.out.println("----------------------------");
        mostrarBrunch();

        System.out.println();
        System.out.println(" ENTRADAS");
        System.out.println("----------------------------");
        mostrarEntradas();

        System.out.println();
        System.out.println(" ALMUERZO");
        System.out.println("----------------------------");
        mostrarAlmuerzo();

        System.out.println();
        System.out.println(" CENA");
        System.out.println("----------------------------");
        mostrarCena();

        System.out.println();
        System.out.println(" POSTRES");
        System.out.println("----------------------------");
        mostrarPostre();

        System.out.println();
        System.out.println(" BEBIDAS");
        System.out.println("----------------------------");
        mostrarBebida();

        System.out.println();
        System.out.println("================================");
    }
}
