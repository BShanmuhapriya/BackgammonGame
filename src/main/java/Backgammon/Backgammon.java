package Backgammon;

import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Backgammon {
    private static final int MATCH_LENGTH_DEFAULT = 5;
    private static final int STAKE = 1;
    private static final int NUM_DICE_SIDES = 6;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Message message = new Message();

        int matchLength = getMatchLength(scanner);
        String playerOneName = message.getPlayerName(1, scanner);
        String playerTwoName = message.getPlayerName(2, scanner);

        boolean playAnotherGame = true;

        while (playAnotherGame) {
            int[] matchScore = new int[2];
            int die1 = random.nextInt(NUM_DICE_SIDES) + 1;
            int die2 = random.nextInt(NUM_DICE_SIDES) + 1;

            System.out.println("Rolling the dice...");
            System.out.println(playerOneName + " rolled " + die1);
            System.out.println(playerTwoName + " rolled " + die2);

            int currentPlayer = (die1 > die2) ? 1 : 2;
            System.out.println(message.getPlayerName(currentPlayer, scanner) + " goes first!");

            while (matchScore[0] < matchLength && matchScore[1] < matchLength) {

                message.displayMessage(playerOneName, playerTwoName, matchLength);

                int[] boardState = new int[24];
                Board board = new Board();

                // Feature: Display match score and match length on the board
                board.displayBoard(boardState, currentPlayer, matchScore, matchLength, boardState);
                Command cmd = new Command();
                if (args.length > 0 && args[0].startsWith("test")) {
                    String fileName = args[0].substring(4); // Extracting filename from "test<filename>"
                    cmd.processCommandsFromFile(fileName, boardState, currentPlayer, matchScore, matchLength, random, scanner);
                } else {
                    cmd.cmdInput(boardState, currentPlayer, matchScore, matchLength, random, scanner);
                }
                cmd.cmdInput(boardState, currentPlayer, matchScore, matchLength, random, scanner);

                // Feature: Display the updated board after each move
                board.displayBoard(boardState, currentPlayer, matchScore, matchLength, boardState);

                // Switch to the other player after the end of a move
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }

            // Feature: Announce the winner of the match
            announceMatchWinner(matchScore, playerOneName, playerTwoName);
            System.out.println("Do you want to play another game? (yes/no)");
            String playAgainInput = scanner.nextLine().toLowerCase();
            playAnotherGame = playAgainInput.equals("yes") || playAgainInput.equals("y");
        }
        System.out.println("Thanks for playing Backgammon!");
    }

}
