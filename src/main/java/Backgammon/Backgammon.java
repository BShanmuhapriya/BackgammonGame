package Backgammon;

public class Backgammon {
    public static void main(String[] args) {
        Message message = new Message();
        message.diplayMessage();

        int[] boardState =new int[24];
        int currentPlayer = 1;
        Board board = new Board();
        board.displayBoard(boardState, currentPlayer);

        Command cmd = new Command();
        cmd.cmdInput();
    }
}
