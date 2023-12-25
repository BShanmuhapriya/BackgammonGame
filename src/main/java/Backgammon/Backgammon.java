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

        String playerOneName = message.getPlayerName(1, scanner);
        String playerTwoName = message.getPlayerName(2, scanner);
    }

}
