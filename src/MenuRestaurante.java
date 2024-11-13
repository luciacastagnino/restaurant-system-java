import java.util.HashSet;
import java.util.Set;

public class MenuRestaurante {

    Set<Plato> platos;

    public MenuRestaurante () {
        this.platos = new HashSet<>();
    }

    public Set<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(Set<Plato> platos) {
        this.platos = platos;
    }

    public void mostrarDesayunoMerienda (){

        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.DESAYUNO) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarBrunch (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.BRUNCH) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarEntradas (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.ENTRADAS) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarAlmuerzo (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.ALMUERZO) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarCena (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.CENA) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarPostre (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.POSTRE) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarBebida (){
        for (Plato p : platos){
            if (p.getTipoPlato().equals(TipoPlato.BEBIDA) && p.isDisponibilidad() == true){
                System.out.println("- " + p.getNombre() + "       $" + p.getPrecio());
            }
        }
    }

    public void mostrarMenuCompleto (){

        System.out.println("========= MENÚ DEL DÍA =========");
        System.out.println();

        System.out.println(" DESAYUNO / MERIENDA");
        System.out.println("----------------------------");
        mostrarDesayunoMerienda();

        System.out.println();
        System.out.println(" BRUNCH");
        System.out.println("----------------------------");
        mostrarBrunch();

        System.out.println();
        System.out.println(" ENTRADAS");
        System.out.println("----------------------------");
        mostrarEntradas();

        System.out.println();
        System.out.println(" ALMUERZO");
        System.out.println("----------------------------");
        mostrarAlmuerzo();

        System.out.println();
        System.out.println(" CENA");
        System.out.println("----------------------------");
        mostrarCena();

        System.out.println();
        System.out.println(" POSTRES");
        System.out.println("----------------------------");
        mostrarCena();

        System.out.println();
        System.out.println(" BEBIDAS");
        System.out.println("----------------------------");
        mostrarCena();

        System.out.println();
        System.out.println("================================");

    }

}
