package Backgammon;

public class Board {
    private static final int NUM_POINTS = 24;
    private static final int STAKE = 1;
    private static final int MATCH_LENGTH_DEFAULT = 5;

    private static final int[] INITIAL_BOARD = new int[]{2, 0, 0, 1, 0, 0, -5, 1, 0, 0, 0, 0, -3, 0, 0, 0, 1, 0, 2, 0, 2, 5, 0, 0, 0};
    private static final String[] MOVE_CODES = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final String[] HINT_CODES = new String[]{"roll", "quit"};

    public void displayBoard(int[] board, int currentPlayer, int[] matchScore, int matchLength, int[] boardState) {
        board = INITIAL_BOARD.clone();
        int[] pips = new int[NUM_POINTS];
        for (int i = 1; i <= NUM_POINTS; i++) {
            pips[i - 1] = getPipNumber(board, i, currentPlayer);
        }

        System.out.println(" 13 14 15 16 17 18 | 19 20 21 22 23 24 ");
        System.out.println("+--+--+--+--+--+ BAR +--+--+--+--+--+");
        System.out.println("|  |" + pipString(pips[12]) + "|" + pipString(pips[13]) + "|" + pipString(pips[14]) + "|" + pipString(pips[15]) + "|" + pipString(pips[16]) + "| | |" + pipString(pips[18]) + "|" + pipString(pips[19]) + "|" + pipString(pips[20]) + "|" + pipString(pips[21]) + "|" + pipString(pips[22]) + "|" + pipString(pips[23]) + "|");
        System.out.println("|" + pipString(pips[11]) + "+--+--+--+--+--+" + pipString(pips[17]) + "| | |" + pipString(pips[23]) + "+--+--+--+--+--+" + pipString(pips[10]) + "|");
        System.out.println("|  |" + pipString(pips[10]) + "|" + pipString(pips[9]) + "|" + pipString(pips[8]) + "|" + pipString(pips[7]) + "|" + pipString(pips[6]) + "| | |" + pipString(pips[5]) + "|" + pipString(pips[4]) + "|" + pipString(pips[3]) + "|" + pipString(pips[2]) + "|" + pipString(pips[1]) + "|" + pipString(pips[0]) + "|");
        System.out.println("+--+--+--+--+--+ BAR +--+--+--+--+--+");
        System.out.println(" 12 11 10  9  8  7 |  6  5  4  3  2  1 ");
        System.out.println();

        // Feature: Display match score and match length on the board
        System.out.println("Match Score: " + matchScore[0] + " - " + matchScore[1] + " | Match Length: " + matchLength);
        System.out.println();

        System.out.println("Pip Numbers:");
    }

    public static String pipString(int pip) {
        if (pip == 0) {
            return "  ";
        } else if (pip > 0) {
            return String.format("%2d", pip);
        } else {
            return String.format("%2d", -pip) + "*";
        }
    }

    public int getPipNumber(int[] boardState, int point, int currentPlayer) {
        if (boardState[point - 1] == currentPlayer) {
            return point;
        } else if (boardState[point - 1] == -currentPlayer) {
            return -point;
        } else {
            return 0;
        }
    }

}

