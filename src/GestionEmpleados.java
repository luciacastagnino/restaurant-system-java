import Archivos.GestionJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.*;

public class GestionEmpleados {
    private Set<Empleado> listaEmpleados;
    private RegistroUser registroUser;

    public GestionEmpleados() {
        this.listaEmpleados = new TreeSet<Empleado>();
        GestionJSON.crearArchivoJSON("empleados.json");
    }

    public void ingresarEmpleados(){
        char op = 's';
        Scanner scan = new Scanner(System.in);

        while (op == 's'){
            System.out.println("\nSeleccione el tipo de empleado que desea ingresar: ");
            System.out.println("1. Empleado tiempo completo.");
            System.out.println("2. Empleado medio tiempo.");

            int tipoEmpleado = scan.nextInt();
            scan.nextLine();
            if (tipoEmpleado == 1){
                EmpleadoTiempoCompleto aux = registroUser.registroEmpleadoTC();
                listaEmpleados.add(aux);
            }else if (tipoEmpleado == 2){
                EmpleadoMedioTiempo aux = registroUser.registroEmpleadoMT();
                listaEmpleados.add(aux);
            }else {
                System.out.println("Opcion invalida. Por favor, seleccione 1 o 2.");
            }

            System.out.println("\n¿Desea seguir ingresando empleados?");
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

    public void mostrarListaDeEmpleados(){
        if(listaEmpleados.isEmpty()){
            cargarArrayConArchivo();
        }
        listaEmpleados.forEach(System.out::println);
    }

}
