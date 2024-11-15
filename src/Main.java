import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("prueba");
        //para mostrar el logo, no se si lo podremos hacer de esta forma :)
        try{
            File file = new File("logo.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
                Thread.sleep(20);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        Menu menu = new Menu();
        menu.MenuPrincipal();

    }
}