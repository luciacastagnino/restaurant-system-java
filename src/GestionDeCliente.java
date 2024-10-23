import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import Archivos.GestionArchivos;
import org.json.JSONArray;
import org.json.JSONObject;

public class GestionDeCliente {

    private List<Cliente> listaDeClientes;

    public GestionDeCliente() {
        this.listaDeClientes = new ArrayList<Cliente>();
        GestionArchivos.crearArchivo("clientes.txt");
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
            GestionArchivos.agregarInformacion("clientes.txt", aux.ClientetoJSONObject().toString());

            System.out.println("Desea seguir ingresando Clientes?");
            op = scan.nextLine().charAt(0);
        }
    }

    //no se que hice
    public void cargarArray(){
        JSONArray arreglo = GestionArchivos.leerArchivo("clientes.txt");

        for (int i = 0; i < arreglo.length(); i++) {
            JSONObject aux1 = arreglo.getJSONObject(i);
            try {
                Cliente aux = Cliente.JSONObjectToCliente(aux1);
                listaDeClientes.add(aux);
            }
            catch (Exception e){
                System.out.println("Ocurrio un error al convertir JSONObject a Cliente");
            }

        }
    }
    
    public void mostrarListaDeClientes(){
        if(listaDeClientes.isEmpty()){
            cargarArray();
        }
        Collections.sort(listaDeClientes);
        listaDeClientes.forEach(System.out::println);
    }



    }

