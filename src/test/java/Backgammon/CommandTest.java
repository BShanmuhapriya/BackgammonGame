package Backgammon;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testSetDiceValues() {
        Command command = new Command();
        command.setDiceValues(3, 4);
        assertEquals(3, command.customDiceValue1);
        assertEquals(4, command.customDiceValue2);
    }

    @Test
    public void testRollDice() {
        Command command = new Command();
        int[] boardState = new int[24];
        Random random = new Random();
        command.rollDice(boardState, 1, random);
        // It's challenging to test more about the rollDice method without further context or expected behavior.
        // You may want to check if dice values are generated correctly or if the method changes specific flags/state.
        // Add assertions based on the specific behavior you want to test.
    }

    @Test
    public void testOfferDouble() {
        Command command = new Command();

        // Redirect System.out to capture printed messages
        InputStream originalIn = System.in;
        String input = "accept"; // Simulating 'accept' as the user input
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Assuming currentPlayer = 1
        assertTrue(command.offerDouble(1, new Scanner(System.in)));

        // Reset System.in
        System.setIn(originalIn);

        // It's recommended to test both 'accept' and 'refuse' paths in offerDouble method.
        // This example tests 'accept' only. Add a similar test for 'refuse'.
    }

    @Test
    public void testCheckEndOfMatch() {
        Command command = new Command();
        int[] boardState = new int[24];
        assertFalse(command.checkEndOfMatch(boardState));
        // You may want to set up the boardState to test various scenarios, like when all pieces are in the home board.
    }

    @Test
    public void testGetWinner() {
        Command command = new Command();
        int[] boardState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // Empty board
        assertEquals(0, command.getWinner(boardState));
        // You might create boardState scenarios for both players to win or check other specific cases.
    }

    @Test
    public void testCheckGammon() {
        Command command = new Command();
        int[] boardState = new int[24]; // Set up a scenario where a gammon is possible
        // Add assertions based on the expected behavior of checkGammon method with specific boardState scenarios.
    }

    @Test
    public void testCheckBackgammon() {
        Command command = new Command();
        int[] boardState = new int[24]; // Set up a scenario where a backgammon is possible
        // Add assertions based on the expected behavior of checkBackgammon method with specific boardState scenarios.
    }
}
