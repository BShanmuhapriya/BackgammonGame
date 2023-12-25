package Backgammon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {

    private Message message;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        message = new Message();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testDisplayMessage() {
        final String playerOneName = "Alice";
        final String playerTwoName = "Bob";
        final int matchLength = 5;
        final String expectedOutput = "Welcome to Backgammon Game!\nAlice vs Bob\nMatch Length: 5\n";

        String input = playerOneName + "\n" + playerTwoName + "\n" + matchLength + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        message.displayMessage(playerOneName, playerTwoName, matchLength);

        assertEquals(expectedOutput, outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    public void testGetPlayerName() {
        final String playerName = "Alice";
        final int playerNumber = 1;

        String input = playerName + "\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        String result = message.getPlayerName(playerNumber, scanner);
        assertEquals(playerName, result);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}
