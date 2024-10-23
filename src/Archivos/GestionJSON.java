package Archivos;

import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionJSON {
    public static void grabar(JSONArray array, String nombreArchivo) throws IOException {
        try {
            FileWriter file = new FileWriter(nombreArchivo);
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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
