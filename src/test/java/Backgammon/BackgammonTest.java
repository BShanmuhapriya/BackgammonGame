package Backgammon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackgammonTest {

    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        // Creating a mock Scanner for testing input
        String testInput = "10\nPlayer One\nPlayer Two\nyes";
        InputStream in = new ByteArrayInputStream(testInput.getBytes());
        mockScanner = new Scanner(in);
    }

    @Test
    void testGetMatchLength() {
        int expectedMatchLength = 10;
        int actualMatchLength = Backgammon.getMatchLength(mockScanner);
        assertEquals(expectedMatchLength, actualMatchLength);
    }

    @Test
    void testAnnounceMatchWinner_PlayerOneWins() {
        int[] matchScore = {5, 3}; // Player One wins
        String playerOneName = "Alice";
        String playerTwoName = "Bob";

        String expectedOutput = "Alice wins the match!";
        String actualOutput = getWinnerAnnouncement(matchScore, playerOneName, playerTwoName);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAnnounceMatchWinner_PlayerTwoWins() {
        int[] matchScore = {2, 4}; // Player Two wins
        String playerOneName = "Alice";
        String playerTwoName = "Bob";

        String expectedOutput = "Bob wins the match!";
        String actualOutput = getWinnerAnnouncement(matchScore, playerOneName, playerTwoName);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testAnnounceMatchWinner_Draw() {
        int[] matchScore = {3, 3}; // Draw
        String playerOneName = "Alice";
        String playerTwoName = "Bob";

        String expectedOutput = "The match ends in a draw!";
        String actualOutput = getWinnerAnnouncement(matchScore, playerOneName, playerTwoName);

        assertEquals(expectedOutput, actualOutput);
    }

    private String getWinnerAnnouncement(int[] matchScore, String playerOneName, String playerTwoName) {
        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Perform the announcement
        Backgammon.announceMatchWinner(matchScore, playerOneName, playerTwoName);

        // Reset System.out
        System.out.flush();
        System.setOut(originalOut);

        return outputStream.toString().trim(); // Trim any extra spaces or line breaks
    }
}