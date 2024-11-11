package Archivos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.*;

public class GestionJSON {

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

    public static void agregarElemento(String nombreArchivo, JSONArray aux){
        try{
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(aux.toString(16));
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void modificar(JSONArray array, String nombreArchivo) throws IOException {
        try {
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public static JSONArray leerArchivo(String nombreArchivo){
        File file = new File(nombreArchivo);
        JSONArray arreglo = new JSONArray();

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(file));
            String lineaActual;

            while ((lineaActual = entrada.readLine()) != null){
                try {
                    JSONObject aux = new JSONObject(lineaActual);
                    arreglo.put(aux);
                }catch (JSONException e){
                    System.out.println("Error en la linea" + lineaActual);
                    e.printStackTrace();
                }
            }
            entrada.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arreglo;
    }

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
