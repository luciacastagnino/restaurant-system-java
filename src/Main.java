import Visualizacion.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try{
            File file = new File("logo.txt");

            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
                Thread.sleep(10);
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