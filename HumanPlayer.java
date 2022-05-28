//Student 1 name: Shezaad Jiffry
//Student 2 name: N/A
/**
//Student 1 name:Shezaad Jiffry
//Student 2 name:N/A

 * The class <b>HumanPlayer</b> is the class that controls the human's plays.
 * 
 * 
 */

public class HumanPlayer implements Player {

	// game.play(position): if the level was advanced after the call, then finish, otherwise repeat and get another position
    public void play(TicTacToeGame game){
        int currentTurn = game.getLevel();

        if(game.getGameState() != GameState.PLAYING){
            System.out.println("error, not playable, someone already won moving on");
            return;
        }

        while(game.getGameState() == GameState.PLAYING) {
            //print the game and the turn first (player 1 is always player 1)
            System.out.println("Player 1's turn"); 
            System.out.println(game);
            //save the result of the readline for future use in printing to console
            int playAt = Integer.parseInt(Utils.console.readLine()) - 1;
            //since we print the turn player after the board is updated we must store nextcellvalue before hand
            CellValue playing = game.nextCellValue();
            game.play(playAt);//to make it more playable ie index 1 is the beginning instead of 0
            System.out.println(playing + " to play: " + (playAt + 1));
            //means we went ahead one turn so the input was successfull 
            if(game.getLevel() != currentTurn)
                break;
        }
        
    }
}