package Backgammon;

import java.util.Scanner;

public class Message {
    public void displayMessage(String playerOneName, String playerTwoName, int matchLength) {
        System.out.println("Welcome to Backgammon Game!");
        System.out.println(playerOneName + " vs " + playerTwoName);
        System.out.println("Match Length: " + matchLength);
    }

    public String getPlayerName(int playerNumber, Scanner scanner) {
        System.out.println("Enter player " + playerNumber + " name: ");
        return scanner.nextLine();
    }
}
