import java.io.*;
public class GameBoardDetail {
  String boardWidth = null;
  String boardHeight = null;
  
  public GameBoardDetail() {
    getInfo();
  }

  public void getInfo() {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /* Collect user information */
    System.out.println("Welcome to the Game Of Life");

    System.out.println("How wide will the board be?");
    boardWidth = getData(br);

    System.out.println("How tall will the board be?");
    boardHeight = getData(br);

    System.out.println("Successfully intered game data.");
  }

  public String getWidth() {
    return boardWidth;
  }

  public String getHeight() {
    return boardHeight;
  }

  private static String getData(BufferedReader br) {
    String userInput = "";
    try {
      userInput = br.readLine();
    } catch (IOException ioe) {
      System.out.println("Error in data input");
      System.exit(1);
    }
    return userInput;
  }
}
