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

    public GestionDeCliente() {
        this.listaDeClientes = new ArrayList<Cliente>();
        GestionJSON.crearArchivoJSON("clientes.json");
    }

    public static Cliente crearCliente(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Ingrese nombre");
        String nombre = scan.nextLine();

        System.out.println("Ingrese apellido");
        String apellido = scan.nextLine();

        System.out.println("Ingrese DNI");
        String dni = scan.nextLine();

        System.out.println("Ingrese telefono");
        String tel = scan.nextLine();

        System.out.println("Ingrese direccion");
        String dir = scan.nextLine();

        System.out.println("Ingrese email");
        String mail = scan.nextLine();

        return new Cliente(nombre, apellido, dni, tel, dir, mail, TipoCliente.ESTANDAR);
    }

    public void ingresarClientes(){
        char op = 's';
        Scanner scan = new Scanner(System.in);

        while (op == 's'){
            Cliente aux = crearCliente();
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

    public void mostrarListaDeClientes(){
        if(listaDeClientes.isEmpty()){
            cargarArrayConArchivo();
        }
        Collections.sort(listaDeClientes);
        listaDeClientes.forEach(System.out::println);
    }



    }

