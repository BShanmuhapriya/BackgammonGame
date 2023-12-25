package Backgammon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Command {
    private static final int STAKE = 1;
    int customDiceValue1 = 0;
    int customDiceValue2 = 0;
    private boolean doubleOffered = false;
    private boolean doubleAccepted = false;

    public void cmdInput(int[] boardState, int currentPlayer, int[] matchScore, int matchLength, Random random, Scanner scanner) {
        boolean quitGame = false;
        Board board = new Board();  // Create an instance of the Board class

        while (!quitGame && matchLength > 0) {
            System.out.println("Player " + currentPlayer + ", enter a command (roll, double, hint, dice <int> <int>, or quit):");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "roll":
                    rollDice(boardState, currentPlayer, random);
                    break;
                case "double":
                    // Feature: Offer a double to the other player
                    offerDouble(currentPlayer, scanner);
                    break;
                case "hint":
                    // Feature: Display a hint (implement logic here)
                    displayHint();
                    break;
                case "quit":
                    System.out.println("Game over. Thanks for playing!");
                    quitGame = true;
                    break;
                default:
                    if (command.toLowerCase().startsWith("dice")) {
                        // Parse the input to get the desired dice values
                        String[] tokens = command.split("\\s+");
                        if (tokens.length == 3) {
                            try {
                                int diceValue1 = Integer.parseInt(tokens[1]);
                                int diceValue2 = Integer.parseInt(tokens[2]);

                                // Set the dice values
                                setDiceValues(diceValue1, diceValue2);
                                System.out.println("Dice values set to: " + diceValue1 + " and " + diceValue2);

                                // Display the updated board after each move
                                board.displayBoard(boardState, currentPlayer, matchScore, matchLength, boardState);

                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input for dice command. Please enter valid integers.");
                            }
                        } else {
                            System.out.println("Invalid format for dice command. Please enter 'dice <int> <int>'.");
                        }
                    } else {
                        System.out.println("Invalid command. Please enter 'roll', 'double', 'hint', 'dice <int> <int>', or 'quit'.");
                    }
            }

            // Display the updated board after each move (excluding the dice command)
            if (!command.toLowerCase().startsWith("dice")) {
                board.displayBoard(boardState, currentPlayer, matchScore, matchLength, boardState);
            }

            // Feature: Check for the end of a match and update match score
            if (checkEndOfMatch(boardState)) {
                updateMatchScore(boardState, currentPlayer, matchScore, doubleOffered);
                boardState = new int[24];
                doubleOffered = false;
                doubleAccepted = false; // Reset the double acceptance
                currentPlayer = (currentPlayer == 1) ? 2 : 1; // Switch player after the end of a match
                boardState[currentPlayer - 1] = 1;
                matchLength--;
            } else {
                // Switch to the other player within the loop
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
    }

    public void processCommand(String command, int[] boardState, int currentPlayer, int[] matchScore, int matchLength, Random random, Scanner scanner) {
        // Process individual commands
        String[] tokens = command.split("\\s+");
        Board board = new Board();

        if (tokens.length > 0) {
            switch (tokens[0].toLowerCase()) {
                case "roll":
                    rollDice(boardState, currentPlayer, random);
                    break;
                case "double":
                    offerDouble(currentPlayer, scanner);
                    break;
                // Add other command cases as needed
                case "dice":
                    if (tokens[0].toLowerCase().startsWith("dice")) {
                        // Process dice command
                        if (tokens.length == 3) {
                            try {
                                int diceValue1 = Integer.parseInt(tokens[1]);
                                int diceValue2 = Integer.parseInt(tokens[2]);
                                setDiceValues(diceValue1, diceValue2);
                                System.out.println("Dice values set to: " + diceValue1 + " and " + diceValue2);
                                // Display the updated board after each move
                                board.displayBoard(boardState, currentPlayer, matchScore, matchLength, boardState);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input for dice command. Please enter valid integers.");
                            }
                        } else {
                            System.out.println("Invalid format for dice command. Please enter 'dice <int> <int>'.");
                        }
                    }
                    break;
                case "hint":
                    displayHint();
                    break;
                case "quit":
                    System.out.println("Game over. Thanks for playing!");
                    break;
                default:
                    System.out.println("Invalid command: " + tokens[0]);
                    break;
            }
        }
    }
    void setDiceValues(int diceValue1, int diceValue2) {
        customDiceValue1 = diceValue1;
        customDiceValue2 = diceValue2;
    }

    void rollDice(int[] boardState, int currentPlayer, Random random) {
        int dice1 = (customDiceValue1 != 0) ? customDiceValue1 : random.nextInt(6) + 1;
        int dice2 = (customDiceValue2 != 0) ? customDiceValue2 : random.nextInt(6) + 1;
        int total = dice1 + dice2;

        System.out.println("Player " + currentPlayer + " rolled " + dice1 + " and " + dice2 + " (Total: " + total + ")");

        // Feature: Display the doubling cube with ownership
        System.out.println("Doubling Cube: " + (doubleAccepted ? "Player " + currentPlayer : "No Ownership"));

        if (total == 2 * STAKE && currentPlayer == 2 && doubleAccepted) {
            System.out.println("Double was accepted. Player " + currentPlayer + " gets to roll again.");
            doubleAccepted = false;
            rollDice(boardState, currentPlayer, random);
        }

        customDiceValue1 = 0;
        customDiceValue2 = 0;
    }

    boolean offerDouble(int currentPlayer, Scanner scanner) {
        System.out.println("Player " + currentPlayer + " offers a double. Respond with 'accept' or 'refuse':");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("accept")) {
            System.out.println("Double accepted. The stake is now " + 2 * STAKE);
            doubleAccepted = true;
            return true;
        } else if (response.equalsIgnoreCase("refuse")) {
            System.out.println("Double refused. Player " + currentPlayer + " gains the current stake.");
            return false;
        } else {
            System.out.println("Invalid response. Please enter 'accept' or 'refuse'.");
            return offerDouble(currentPlayer, scanner);
        }
    }

    int getWinner(int[] boardState) {
        int player1Checkers = 0;
        int player2Checkers = 0;

        for (int i = 0; i < 24; i++) {
            if (boardState[i] > 0) {
                player1Checkers += boardState[i];
            } else if (boardState[i] < 0) {
                player2Checkers += Math.abs(boardState[i]);
            }
        }

        if (player1Checkers == 0) {
            return 2;
        } else if (player2Checkers == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
