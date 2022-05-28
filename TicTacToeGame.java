//Student 1 name:Shezaad Jiffry
//Student 2 name:N/A

/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

   /**
    * The board of the game, stored as a single array.
    */
   private CellValue[] board;

   /**
    * level records the number of rounds that have been
    * played so far. Starts at 0.
    */
   private int level;

   /**
    * gameState records the current state of the game.
    */
   private GameState gameState;

   /**
    * lines is the number of lines in the grid
    */
   private final int lines;

   /**
    * columns is the number of columns in the grid
    */
   private final int columns;

   /**
    * sizeWin is the number of cell of the same type
    * that must be aligned to win the game. 
    * For simplicity, it will be always 3 in this assignment.
    */
   private final int sizeWin;
   /**
    * default constructor, for a game of 3x3, which must
    * align 3 cells
    */
   public TicTacToeGame() {
      this.level = 0;
      this.gameState = GameState.PLAYING;
      this.lines = 3;
      this.columns = 3;
      this.sizeWin = 3;
      this.board = new CellValue[columns * lines];
      for (int i = 0; i < this.board.length; i++)
         this.board[i] = CellValue.EMPTY;
   }

   /**
    * constructor allowing to specify the number of lines
    * and the number of columns for the game. 3 cells must
    * be aligned.
    * @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    */
   public TicTacToeGame(int lines, int columns) {
      this.lines = lines;
      this.columns = columns;

      this.level = 0;
      this.gameState = GameState.PLAYING;
      this.sizeWin = 3;
      this.board = new CellValue[columns * lines];
      for (int i = 0; i < this.board.length; i++)
         this.board[i] = CellValue.EMPTY;
   }

   /**
    * constructor allowing to specify the number of lines
    * and the number of columns for the game, as well as
    * the number of cells that must be aligned to win.
    * @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
    */
   public TicTacToeGame(int lines, int columns, int sizeWin) {
      this.lines = lines;
      this.columns = columns;
      this.sizeWin = sizeWin;

      this.level = 0;
      this.gameState = GameState.PLAYING;
      this.board = new CellValue[columns * lines];
      for (int i = 0; i < this.board.length; i++)
         this.board[i] = CellValue.EMPTY;

   }

   /**
    * getter for the variable lines
    * @return
    * 	the value of lines
    */
   public int getLines() {
      return this.lines;
   }

   /**
    * getter for the variable columns
    * @return
    * 	the value of columns
    */
   public int getColumns() {
      return this.columns;
   }

   /**
    * getter for the variable level
    * @return
    * 	the value of level
    */
   public int getLevel() {
      return this.level;
   }

   /**
    * getter for the variable gameState
    * @return
    * 	the value of gameState
    */
   public GameState getGameState() {
      return this.gameState;
   }

   /**
    * getter for the variable sizeWin
    * @return
    * 	the value of sizeWin
    */
   public int getSizeWin() {
      return this.sizeWin;
   }

   /**
    * returns the cellValue that is expected next,
    * in other word, which played (X or O) should
    * play next.
    * This method does not modify the state of the
    * game.
    * @return
    *  the value of the enum CellValue corresponding
    * to the next expected value.
    * we start at X
    */
   public CellValue nextCellValue() {
      if (this.getLevel() == 0 || this.getLevel() % 2 == 0) //first value or any even turn afterwards
         return CellValue.X;
      return CellValue.O;

   }

   /**
    * returns the value  of the cell at
    * index i.
    * If the index is invalid, an error message is
    * printed out. The behaviour is then unspecified
    * @param i
    *  the index of the cell in the array board
    * @return
    *  the value at index i in the variable board.
    */
   public CellValue valueAt(int i) {
      if (i >= this.board.length || i < 0) {

         return null;
      }
      return this.board[i];
   }

   /**
    * This method is called by the next player to play
    * at the cell  at index i.
    * If the index is invalid, an error message is
    * printed out. The behaviour is then unspecified
    * If the chosen cell is not empty, an error message is
    * printed out. The behaviour is then unspecified
    * If the move is valide, the board is updated, as well
    * as the state of the game.
    * To faciliate testing, it is acceptable to keep playing
    * after a game is already won. If that is the case, the
    * a message should be printed out and the move recorded.
    * the  winner of the game is the player who won first
    * @param i
    *  the index of the cell in the array board that has been
    * selected by the next player
    */
   public void play(int i) {
      //if conditions to handle invalid choices (precondition checkers)
      if (this.valueAt(i) == CellValue.X || this.valueAt(i) == CellValue.O)
         System.out.println("This cell has already been played");
      else if (this.valueAt(i) == null) {
         System.out.println("The value should be between 1 and " + (this.board.length));
      }

      //update game state depending on whos turn and increment level to change player turn
      else {
         this.board[i] = this.nextCellValue();

         //we do not want to update the gamestate if a user has already won 
         if (gameState == GameState.PLAYING)
            setGameState(i);
         this.level++;

      }
   }

   private boolean validate(int win, int index) {
      if (win >= this.sizeWin) {
         if (valueAt(index) == CellValue.X) {
            gameState = GameState.XWIN;
            return true;
         } else if (valueAt(index) == CellValue.O) {
            gameState = GameState.OWIN;
            return true;
         }
      }
      return false;
   }

   /**
    * A helper method which updates the gameState variable
    * correctly after the cell at index i was just set.
    * The method assumes that prior to setting the cell
    * at index i, the gameState variable was correctly set.
    * it also assumes that it is only called if the game was
    * not already finished when the cell at index i was played
    * (the the game was playing). Therefore, it only needs to
    * check if playing at index i has concluded the game
    * So check if 3 cells are formed to win.
    * @param index
    *  the index of the cell in the array board that has just
    * been set
    */

   private void setGameState(int index) {
      /*we will make a 2d array from our 1d array by 
       *copying the values from board to board2d going by column
       *for the first loop then to row, we increment an integer k
       *to keep track of the one dimensional board*/
      int linePos = 0;
      int columnPos = 0;
      int win = 0;
      boolean reverse = false; 
      CellValue[][] board2d = new CellValue[this.lines][this.columns];
      for (int i = 0; i < this.lines; i++) {
         for (int j = 0; j < this.columns; j++) {
            //if we hit the index then save that value for future use
            if (win == index) {
               linePos = i;
               columnPos = j;
            }
            board2d[i][j] = board[win];
            win++;

         }
      }
      win = 1;

      for (int l = columnPos + 1; l < this.columns; l++) {
         if (board2d[linePos][columnPos] == board2d[linePos][l])
            win++;
         else
            break;
         }
         //horizontal case opposite direction now (-1)
      for (int l = columnPos - 1; l > -1; l--) {
         if (board2d[linePos][columnPos] == board2d[linePos][l])
            win++;
         else
            break;
         }
      //check for win after every case then reset if there is no win, if there is then reset
      if (validate(win, index))
         return;

      else
         win = 1;

      //vertical cases, check column ahead (+1)
      for (int l = linePos + 1; l < this.lines; l++) {
         if (board2d[linePos][columnPos] == board2d[l][columnPos])
            win++;
         else
            break;
      }
      //vertical cases, check column behind (-1)
      for (int l = linePos - 1; l > -1; l--) {
         if (board2d[linePos][columnPos] == board2d[l][columnPos])
            win++;
         else
            break;
      }
      //check for win after every case then reset if there is no win, if there is then reset
      if (validate(win, index))
         return;
      else
         win = 1;

      int n = columnPos + 1;
      //diagonal cases check one line ahead and one column ahead
      outer: for (int l = linePos + 1; l < this.lines; l++) {
         while (n < this.columns) {
            //break out of first loop

            if (board2d[linePos][columnPos] == board2d[l][n]) {
               win++;
               n++;
               break;
            }
            //break out of the outer loop otherwise
            else
               break outer;
         }
      }
      n = columnPos - 1;
      //diagonal case check one line behind and one column behind
      outer: for (int l = linePos - 1; l > -1; l--) {
         while (n > -1) {
            if (board2d[linePos][columnPos] == board2d[l][n]) {
               win++;
               n--;
               break;
            }
            //break out of the outer loop otherwise
            else
               break outer;
         }
      }
      //check for win after every case then reset if there is no win, if there is then reset
      if (validate(win, index))
         return;

      else
         win = 1;

      n = columnPos + 1;
      //inverse diagonal case check one line behind, one column forward
      outer: for (int l = linePos - 1; l > -1; l--) {
         while (n < this.columns) {
            //break out of first loop
            if (board2d[linePos][columnPos] == board2d[l][n]) {
               win++;
               n++;
               break;
            }
            //break out of the outer loop otherwise
            else
               break outer;
         }
      }
      n = columnPos - 1;
      //inverse diagonal case check one line ahead one column behind
      outer: for (int l = linePos + 1; l < this.lines; l++) {
         while (n > -1) {
            //break out of first loop
            if (board2d[linePos][columnPos] == board2d[l][n]) {

               win++;
               n--;
               break;
            }
            //break out of the outer loop otherwise
            else

               break outer;
         }
      }
      //check for win after every case then reset if there is no win, if there is then reset
      if (validate(win, index))
         return;

      else
         win = 1;

   /*lastly check for a tie ie if there is not an
    * EMPTY cellValue then it is a tie (we reuse
    * the int k one last time to keep track of board)*/
   win = 0;
   while (valueAt(win) != CellValue.EMPTY && gameState == GameState.PLAYING) {
      //if we make it to the end of the board with no empty spaces then its a draw
      if (win == board.length - 1)
         gameState = GameState.DRAW;
      win++;
   }
}


private void checkCases(CellValue[][] board2d, int linePos, int columnPos, String caseCheck){
   int win = 1;
   for (int l = columnPos + 1; l < this.columns; l++) {
      if (board2d[linePos][columnPos] == board2d[linePos][l])
         win++;
      else
         break;
      }
      //horizontal case opposite direction now (-1)
   for (int l = columnPos - 1; l > -1; l--) {
      if (board2d[linePos][columnPos] == board2d[linePos][l])
         win++;
      else
         break;
      }
   if (win >= this.sizeWin) {
      if (board2d[linePos][columnPos] == CellValue.X) {
         gameState = GameState.XWIN;
      } else if (board2d[linePos][columnPos] == CellValue.O) {
         gameState = GameState.OWIN;

      }
   }


}


/**
 * Returns a String representation of the game matching
 * the example provided in the assignment's description
 *
 * @return
 *  String representation of the game
 */
public String toString() {
   String grid = "";
   //counter used to determine whether or not you should add the '|'' in order to avoid '|'' going at the end of the grid
   int counter = 0;
   for (int i = 0; i < this.board.length; i++) {
      //multiples of columns means a new row should be added 
      if (i % this.getColumns() == 0 && i != 0) {
         grid += Utils.NEW_LINE;
         for (int j = 0; j < this.getColumns(); j++)
            grid += "--";
         grid += Utils.NEW_LINE;
      }

      if (valueAt(i) == CellValue.EMPTY)
         grid += " ";
      else if (valueAt(i) == CellValue.X)
         grid += "X";
      else
         grid += "O";
      //if the counter hits the column number -1 ie the end of a row then do not add '|'
      if (counter != this.getColumns() - 1) {
         grid += "|";
         counter++;
      }
      //resets counter at the end of a row to repeat the process
      else
         counter = 0;
   }

   return (grid);
}

}