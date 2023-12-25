package Backgammon;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testPipString() {
        Board board = new Board();
        assertEquals("  ", board.pipString(0));
        assertEquals(" 3", board.pipString(3));
        assertEquals(" 5*", board.pipString(-5));
    }

    @Test
    public void testGetPipNumber() {
        Board board = new Board();
        int[] boardState = new int[]{0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertEquals(2, board.getPipNumber(boardState, 2, 1));
        assertEquals(-4, board.getPipNumber(boardState, 4, 1));
        assertEquals(0, board.getPipNumber(boardState, 7, 1));
    }

    // Add more tests for the displayBoard method based on specific expected output or behavior
    // Due to the display nature of the method, full testing might require complex assertions on output strings.
}