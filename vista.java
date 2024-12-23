import java.util.Scanner;

public class ConsolaVista {
    private Scanner scanner = new Scanner(System.in);

    public String solicitarDato(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

