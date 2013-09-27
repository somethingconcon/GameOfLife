 import java.util.Arrays;

 public class GameOfLifeBoard {
  
  int height;
  int width;
  int currentHeight;
  int currentColumn;
  String currentCell;
  String[][] grid;

  public GameOfLifeBoard(String height_in, String width_in) {

  	height = Integer.parseInt(height_in);
    width = Integer.parseInt(width_in);
    
    grid = new String[height][width];
  }

  public void startGame() {
  	setDefaultBoard();

  	while(true) {
		  printGrid();
		  next();

		  try {
	    	Thread.sleep(1000);
		  } catch(InterruptedException ex) {
	    	Thread.currentThread().interrupt();
		  }
  	}
  }

  private int countCharacter(String surroundingString, char character) {
    int count = 0;
    for(int i = 0; i < surroundingString.length(); i++) {
      char c = surroundingString.charAt(i);
      if ( c == character ) {
	      count++;
      }
    }    
    return count;
  }

  private void next() {

  	String[] neighbors;

  	for(int height_index = 0; height_index < height; height_index++ ) {
      for(int width_index = 0; width_index < width; width_index++){
        currentCell = grid[height_index][width_index];
        neighbors = getNeighbors(height_index, width_index);
        grid[height_index][width_index] = evaluateNeighbors(neighbors);
      }
  	}
  }

  private String evaluateNeighbors(String[] surroundingCells) {

  	char alive = '*';
  	char dead = '/';

		StringBuilder surroundingStringBuilder = new StringBuilder(100);
  	String aliveOrDead = "";

  	for(int i = 0; i < surroundingCells.length; i++) {
  	  surroundingStringBuilder.append(surroundingCells[i]);
  	}

  	String surroundingString = surroundingStringBuilder.toString();

  	int aliveCellsCount = countCharacter(surroundingString, alive);
  	int deadCellsCount = countCharacter(surroundingString, dead);

  	if(currentCell.charAt(0) == alive) {
      if(aliveCellsCount < 2) {
        aliveOrDead = "/";
      } else if (aliveCellsCount == 2 || aliveCellsCount == 3) {
      	aliveOrDead = "*";
      } else if (aliveCellsCount > 3) {
      	aliveOrDead = "/";
      } else {
      	aliveOrDead = "*";
      }
  	} else {
  		if(aliveCellsCount == 3) {
  			aliveOrDead = "*";
  		} else {
  			aliveOrDead = "/";
  		}
  	}
  	return aliveOrDead;
  }

  private String[] getNeighbors(int height_index, int width_index) {
  	
  	currentHeight = height_index;
  	currentColumn = width_index;
    currentCell = grid[height_index][width_index];
  	
  	String top_left = getCellValue(-1, -1);
  	String top_top = getCellValue(-1, 1);
  	String top_right = getCellValue(-1, +1);
  	String left = getCellValue(0, -1);
  	String right = getCellValue(0, +1);
  	String bottom_left = getCellValue(+1, -1);
  	String bottom_bottom = getCellValue(+1, 0);
  	String bottom_right = getCellValue(+1, +1);

  	String[] evaluated_neighbors = { top_left, top_top, top_right, left, right, bottom_left, bottom_bottom, bottom_right };

  	return evaluated_neighbors;
  }

  private String getCellValue(int row_adjuster, int column_adjuster) {
  	String cell;

  	try {
      cell = grid[currentHeight + row_adjuster][currentColumn + column_adjuster];
    } catch (IndexOutOfBoundsException e) {
      cell = "-";
    }
    return cell;
  }

  private String getRandomCharacter(){
  	boolean random = Math.random() < 0.5;
  	if(random) {
  	  return "*";
  	} else {
  	  return "/";
  	}
  }

  private void printGrid() {
  	System.out.println(Arrays.deepToString(grid));
  }

  private void setDefaultBoard() {
  	for(int height_index = 0; height_index < height; height_index++ ) {
      for(int width_index = 0; width_index < width; width_index++) {
        grid[height_index][width_index] = getRandomCharacter();
      }
  	}
  }

}
