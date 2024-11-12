import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GestionAdministrador {

    private Set<Administrador> listaAdmins;
    private RegistroUser registroUser;

    public GestionAdministrador() {
        this.listaAdmins = new TreeSet<Administrador>();
        GestionJSON.crearArchivoJSON("administrador.json");
    }

    public void ingresarAdmin(){
        char op = 's';
        Scanner scan = new Scanner(System.in);

        while (op == 's'){

            Administrador aux = registroUser.registroAdmin();
            listaAdmins.add(aux);

            System.out.println("\n¿Desea seguir ingresando empleados?");
            op = scan.nextLine().charAt(0);

        }
    }

    public void cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("administrador.json");

        try {

            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Administrador administrador = new Administrador(aux1); ///hay que hacer el constructor
                listaAdmins.add(administrador);
            }
        } catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Empleado");
        }
    }

    public void cargarArchivoConArreglo(){
        JSONArray arreglo = null;
        try {
            arreglo = new JSONArray();
            JSONObject aux = null;

            Iterator<Administrador> it = listaAdmins.iterator();
            while(it.hasNext()){
                arreglo.put(aux);
            }
            GestionJSON.agregarElemento("administrador.json", arreglo);
        } catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array.");
        }
    }

    public void mostrarListaDeAdmins(){
        if(listaAdmins.isEmpty()){
            cargarArrayConArchivo();
        }
        listaAdmins.forEach(System.out::println);
    }

}


