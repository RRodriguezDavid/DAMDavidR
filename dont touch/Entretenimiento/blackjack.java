package proyectosextra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double playerMoney = 1000.0;  // Dinero inicial del jugador

        System.out.println("Bienvenido al Blackjack! Comienzas con " + playerMoney + " $");

        while (playerMoney > 0) {
            System.out.println("\nTienes " + playerMoney + " $. ¿Cuánto te gustaría apostar?");
            double bet = scanner.nextDouble();

            if (bet > playerMoney) {
                System.out.println("No tienes suficiente dinero para esa apuesta.");
                continue;
            }

            // Inicializamos el mazo y lo mezclamos
            List<Integer> deck = createDeck();
            Collections.shuffle(deck);

            // Mano del jugador y del dealer
            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();

            // Repartimos dos cartas a cada uno
            playerHand.add(drawCard(deck));
            playerHand.add(drawCard(deck));
            dealerHand.add(drawCard(deck));
            dealerHand.add(drawCard(deck));

            boolean playerTurn = true;
            while (playerTurn) {
                System.out.println("\nTu mano: " + playerHand + " (total: " + handValue(playerHand) + ")");
                System.out.println("Carta visible del dealer: " + dealerHand.get(0));

                if (handValue(playerHand) == 21) {
                    System.out.println("¡Blackjack! ¡Ganaste!");
                    playerMoney += bet * 1.5;  // Blackjack paga 1.5 veces la apuesta
                    break;
                }

                System.out.print("¿Quieres pedir una carta (P) o plantarte (L)? ");
                String choice = scanner.next().toUpperCase();

                if (choice.equals("P")) {
                    playerHand.add(drawCard(deck));
                    if (handValue(playerHand) > 21) {
                        System.out.println("Tu mano: " + playerHand + " (total: " + handValue(playerHand) + ")");
                        System.out.println("Te pasaste de 21. ¡Perdiste!");
                        playerMoney -= bet;
                        playerTurn = false;
                    }
                } else if (choice.equals("L")) {
                    playerTurn = false;
                } else {
                    System.out.println("Opción no válida. Intenta de nuevo.");
                }
            }

            // Si el jugador no se pasó, turno del dealer
            if (handValue(playerHand) <= 21) {
                System.out.println("\nTurno del dealer.");
                while (handValue(dealerHand) < 17) {
                    dealerHand.add(drawCard(deck));
                }

                System.out.println("Mano del dealer: " + dealerHand + " (total: " + handValue(dealerHand) + ")");

                // Determinamos el resultado final
                int playerTotal = handValue(playerHand);
                int dealerTotal = handValue(dealerHand);

                if (dealerTotal > 21 || playerTotal > dealerTotal) {
                    System.out.println("¡Ganaste!");
                    playerMoney += bet;
                } else if (playerTotal == dealerTotal) {
                    System.out.println("Es un empate. Recuperas tu apuesta.");
                } else {
                    System.out.println("El dealer gana. ¡Perdiste!");
                    playerMoney -= bet;
                }
            }

            // Verificar si el jugador tiene dinero para seguir jugando
            if (playerMoney <= 0) {
                System.out.println("Te has quedado sin dinero. ¡Juego terminado!");
                break;
            }

            System.out.print("¿Quieres jugar otra ronda? (S/N): ");
            String playAgain = scanner.next().toUpperCase();
            if (!playAgain.equals("S")) {
                System.out.println("Gracias por jugar. Terminas con $" + playerMoney);
                break;
            }
        }
    }

    // Crear el mazo de cartas
    private static List<Integer> createDeck() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {  // 1 a 13 para cada palo (4 palos)
            for (int j = 0; j < 4; j++) { // Cada palo tiene 4 cartas (corazones, diamantes, tréboles, picas)
                deck.add(i);
            }
        }
        return deck;
    }

    // Extraer una carta del mazo
    private static int drawCard(List<Integer> deck) {
        return deck.remove(0); // Tomamos la primera carta (por estar barajado)
    }

    // Calcular el valor de una mano
    private static int handValue(List<Integer> hand) {
        int value = 0;
        int aces = 0;

        for (int card : hand) {
            if (card > 10) {  // Cartas J, Q, K cuentan como 10
                value += 10;
            } else if (card == 1) {  // El As cuenta como 1 o 11
                aces++;
                value += 11;
            } else {
                value += card;
            }
        }

        // Ajustar el valor de los Ases si el total es mayor que 21
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }
        return value;
    }
}