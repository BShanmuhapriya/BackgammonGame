package Backgammon;

import java.util.*;
import java.util.Scanner;

public class Message {
    public void diplayMessage () {
        System.out.println("Welcome to Backgammon Game!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter player one name: ");
        String PlayerOneName = scanner.nextLine();

        System.out.println("Enter player two name: ");
        String PlayerTwoName = scanner.nextLine();
    }
}