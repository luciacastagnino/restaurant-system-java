package Gestion;

import Archivos.FormatoIncorrectoException;
import Archivos.GestionJSON;
import Restaurante.Plato;
import Restaurante.TipoPlato;
import Users.Administrador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    private Set<Plato> platos;
    private Scanner scanner;

    /**
     * Constructor de la clase Gestion.MenuRestaurante.
     * Inicializa un conjunto vacío de platos que compondrán el menú del restaurante.
     *
     */
    public MenuRestaurante () {
        this.platos = new HashSet<>();
        GestionJSON.crearArchivoJSON("platos.json");
        this.scanner=new Scanner(System.in);
    }

    //Get y Set

    public Set<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(Set<Plato> platos) {
        this.platos = platos;
    }

    @Override
    public void ingresarUsuario() {
        System.out.println();
        Plato aux = aux.cargarPlato();
        agregarYguardar(aux);
        System.out.println("Plato agregado con exito.");
    }

    public Set<Plato> cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("platos.json");

        try {

            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Plato plato = new Plato();
                plato = plato.jsonToPlato(aux1);
                platos.add(plato);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Plato.");
        }

        return platos;
    }

    public void agregarYguardar (Plato plato){
        cargarArrayConArchivo();
        platos.add(plato);
        cargarArchivoConArreglo(platos);
    }

    public void cargarArchivoConArreglo(Set<Plato> listaPlatos){
        JSONArray arreglo = new JSONArray();
        try {

            for (Plato plato : listaPlatos){
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
        if (platos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : platos){
            if (p.equals(plato)){
                p.mostrarPlato();
            }
        }

    }

    @Override
    public Plato modificarUsuario(Plato plato) {
        return null;
    }

    @Override
    public void darDeBajaUsuario(Plato plato) {

    }

    @Override
    public void mostrarColeccion() {
       mostrarMenuCompleto();
    }

    @Override
    public Plato encontrarUsuario(int id) {
        if (platos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : platos){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public void listarUsuarios(String nombre) {
        if (platos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : platos){
            if (p.getNombre().contains(nombre)){
                p.mostrarPlato();
            }
        }

    }

    public void listarPlatosTicket (){
        if (platos.isEmpty()){
            cargarArrayConArchivo();
        }

        for (Plato p : platos){
            System.out.println("ID: " + p.getId() + " / Plato: " + p.getNombre());
        }
    }

    /**
     * Muestra los platos de la categoría desayuno/merienda que están disponibles.
     * Solo se muestran los platos cuya disponibilidad es verdadera.
     *
     */
    public void mostrarDesayunoMerienda () {
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
        for (Plato p : platos) {
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
