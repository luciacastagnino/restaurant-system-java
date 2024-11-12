import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
public class GestionDeCliente {

    private List<Cliente> listaDeClientes;
    private RegistroUser registroUser;

    public GestionDeCliente() {
        this.listaDeClientes = new ArrayList<Cliente>();
        GestionJSON.crearArchivoJSON("clientes.json");
    }

    public void ingresarClientes(){
        char op = 's';
        Scanner scan = new Scanner(System.in);

        while (op == 's'){
            Cliente aux = registroUser.registroCliente();
            listaDeClientes.add(aux);

            System.out.println("Desea seguir ingresando Clientes?");
            op = scan.nextLine().charAt(0);
        }
    }

    public void cargarArrayConArchivo(){
        JSONTokener aux = GestionJSON.leer("clientes.json");

            try {
                JSONArray arreglo = new JSONArray(aux);

                for(int i = 0; i < arreglo.length(); i++){
                    JSONObject aux1 = arreglo.getJSONObject(i);
                    Cliente cliente = new Cliente(aux1);
                    listaDeClientes.add(cliente);
                }
            }
            catch (JSONException e){
                System.out.println("Ocurrio un error al convertir JSONObject a Cliente");
            }
    }

    public void cargarArchivoConArreglo(){
        JSONArray arreglo = null;
        try {
            arreglo = new JSONArray();
            JSONObject aux = null;

            for(int i = 0; i < listaDeClientes.size(); i++){
                aux = (listaDeClientes.get(i)).ClientetoJSONObject();
                arreglo.put(aux);
            }

            GestionJSON.agregarElemento("clientes.json", arreglo);
        }
        catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array");
        }
    }

    public void mostrarListaDeClientes(){
        if(listaDeClientes.isEmpty()){
            cargarArrayConArchivo();
        }
        Collections.sort(listaDeClientes);
        listaDeClientes.forEach(System.out::println);

    }
}

