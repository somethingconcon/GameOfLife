import java.io.*;

public class GameOfLife {
  public static void main(String[] args) {
    
    /* generate game board details from user input */
    GameBoardDetail Detail = new GameBoardDetail();
    String height = Detail.getHeight();
    String width = Detail.getWidth();

    /* Initialize GameOfLife board */
    GameOfLifeBoard Board = new GameOfLifeBoard(height, width);
    Board.startGame();
  }
}
