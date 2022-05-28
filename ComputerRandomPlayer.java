//Student 1 name:Shezaad Jiffry
//Student 2 name:N/A

/**
 * The class <b>ComputerRandomPlayer</b> is the class that controls the computer's plays.
 * 
 * 
 */

import java.util.*;

public class ComputerRandomPlayer implements Player {
    Utils util = new Utils();
	//generate random position at an empty cell!!
	//call game.play(position)
    public void play(TicTacToeGame game){
        int currentTurn = game.getLevel();
        if(game.getGameState() != GameState.PLAYING){
            System.out.println("error, not playable, game state is not playing");
            return;
        }

        //randomly generate the next move cell choice
        int choice = util.generator.nextInt(game.getColumns() * game.getLines());


         while(game.getGameState() == GameState.PLAYING) {
            if(game.valueAt(choice) == CellValue.EMPTY){
                game.play(choice);
                break;

            }

            choice = util.generator.nextInt(game.getColumns() * game.getLines());

        }
        


    }


}