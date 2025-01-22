package proyectosextra;

import java.util.Scanner;

public class UndirLaFlota {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int n = 0;

        System.out.println("Vamos a juagr a undir la flota, ¿quieres?");
        String decision = teclado.nextLine();
        if (decision.equals("SI") || decision.equals("si") || decision.equals("Si")) {

        } else if (decision.equals("NO") || decision.equals("no") || decision.equals("No")) {
            System.out.println("CHAU");

        } else {
            return;
        }
    }
}