import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionDeCliente {

    private List<Cliente> listaDeClientes;

    public GestionDeCliente() {
        this.listaDeClientes = new ArrayList<Cliente>();
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

        scan.close();

        return new Cliente(nombre, apellido, dni, tel, dir, mail, TipoCliente.ESTANDAR);
    }

    //no se que estoy haciendo
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

    public void mostrarListaDeClientes(){
        listaDeClientes.forEach(System.out::println);
        //ojala funcione
    }



    }

