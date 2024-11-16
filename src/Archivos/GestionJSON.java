/**
 * La clase Archivos.GestionJSON no tiene atributos ni constructor, sirve para manejar archivos
 * metodos: crearArchivoJSON, agregarElemento, leer, todos son estaticos
 *
 * @author Brenda
 * @since 2024
 * @version 1
 */
package Archivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;

public class GestionJSON {

    /**
     * crearArchivoJSON, es un metodo estatico que crea un archivo vacio, recibe un String.
     * @param nombreArchivo
     */
    public static void crearArchivoJSON(String nombreArchivo){
        File file = new File(nombreArchivo);

        if(!file.exists()) {
            try {
                FileWriter archi = new FileWriter(nombreArchivo);
                archi.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * agregarElemento, es un metodo estatico que guarda un JSONArray en un archivo, recibe
     * un String, que es el nombre del archivo, y el JSONArray que se va a guardar.
     * @param nombreArchivo
     * @param aux
     */
    public static void agregarElemento(String nombreArchivo, JSONArray aux){
        try{
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(aux.toString(16));
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * leer, es un metodo estatico, retorna un JSONTokener que permite ver todos los datos del
     * archivo, recibe un String.
     * @param nombreArchivo
     * @return aux
     */
    public static JSONTokener leer(String nombreArchivo){
        JSONTokener aux = null;
        try {
            aux = new JSONTokener(new FileReader(nombreArchivo));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return aux;
    }

}
