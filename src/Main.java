public class Main {
    public static void main(String[] args) {

        LogInRegistroUser logInRegistroUser = new LogInRegistroUser();
        logInRegistroUser.registro();

        if (logInRegistroUser.registro()==false){
            System.out.println("Hubo un problema al crear el usuario.");
        }else {
            System.out.println("Registro exitoso");
        }

    }
}