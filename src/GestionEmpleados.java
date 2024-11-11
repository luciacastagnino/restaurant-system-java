import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public class GestionEmpleados {
    private Set<Empleado> listaEmpleados;

    public GestionEmpleados() {
        this.listaEmpleados = new TreeSet<Empleado>();
        GestionJSON.crearArchivoJSON("empleados.json");
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
        JSONTokener aux = GestionJSON.leer("empleados.json");

        try {
            JSONArray arreglo = new JSONArray(aux);

            for(int i = 0; i < arreglo.length(); i++){
                JSONObject aux1 = arreglo.getJSONObject(i);
                Empleado empleado = new Empleado(aux1); ///hay que hacer el constructor
                listaEmpleados.add(empleado);
            }
        }
        catch (JSONException e){
            System.out.println("Ocurrio un error al convertir JSONObject a Empleado");
        }
    }

    public void cargarArchivoConArreglo(){
        JSONArray arreglo = null;
        try {
            arreglo = new JSONArray();
            JSONObject aux = null;

            Iterator<Empleado> it = listaEmpleados.iterator();
            while(it.hasNext()){

                arreglo.put(aux);
            }

            GestionJSON.agregarElemento("clientes.json", arreglo);
        }
        catch (JSONException e){
            System.out.println("Hubo un problema al cargar el archivo con array");
        }
    }

    public void mostrarListaDeClientes(){
        if(listaEmpleados.isEmpty()){
            cargarArrayConArchivo();
        }
        listaEmpleados.forEach(System.out::println);
    }

}
