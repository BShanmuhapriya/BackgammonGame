package backgammon;
import java.util.Random;
import java.util.Scanner;

public class Command {
    public static void cmdInput() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Dice Roll Game!");

        int currentPlayer = 1;
        boolean quitGame = false;

        while (!quitGame) {
            System.out.println("Player " + currentPlayer + ", enter a command (roll or quit):");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("roll")) {
                int dice1 = random.nextInt(6) + 1;
                int dice2 = random.nextInt(6) + 1;
                int total = dice1 + dice2;

                System.out.println("Player " + currentPlayer + " rolled " + dice1 + " and " + dice2 + " (Total: " + total + ")");
                // You can display this information on the board panel and log panel as needed.

                // Switch to the other player
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            } else if (command.equalsIgnoreCase("quit")) {
                System.out.println("Game over. Thanks for playing!");
                quitGame = true;
            } else {
                System.out.println("Invalid command. Please enter 'roll' or 'quit'.");
            }
        }
    }
}