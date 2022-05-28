//Student 1 name:Shezaad Jiffry
//Student 2 name:N/A

/**
 * The class <b>TicTacToe</b> is the class that implements the actual Tic Tac
 * Toe game, where it
 * controls the human and computer activity and prints the result of the game at
 * the end. It also
 * asks the player if he/she wants to continue playing once this game is over.
 * 
 * 
 */


public class TicTacToe {
    
    /**
     * <b>main</b> of the application. Creates the instance of GameController
     * and starts the game. If two parameters line and column
     * are passed, they are used.
     * Otherwise, a default value is used. Defaults values are also
     * used if the paramters are too small (less than 2).
     * 
     * @param args
     *             command line parameters
     */

    public static void main(String[] args) {
        StudentInfo.display();
        
        
        //default values used if args are not there:
        int lines = 3;
        int columns = 3;
        int win = 3;

        //change lines, columns and win based on the args values


        //define an array (say p) of two players (use interface playe for the refernce)
        Player[] player = new Player[2];
        HumanPlayer hPlayer = new HumanPlayer();
        ComputerRandomPlayer cPlayer = new ComputerRandomPlayer();

        int first = Utils.generator.nextInt(2);
        int second = 0;
        if (first == 0)
            second = 1;

  

        player[0] = hPlayer;
        player[1] = cPlayer;        
        //choose player randomly (p[0] or p[1]) 
        
        // create a refernce to an object of type TicTacToeGame
        TicTacToeGame game;
        // loop until the input is not 'y' 
    
        do {
            if (first == 1){
                first --;
                second++;
            }
            else if (first == 0){
                first++;
                second--;
            }
                
            game =new TicTacToeGame(lines,columns,win);
            while(game.getGameState() == GameState.PLAYING) {
                
                

                player[first].play(game);

                if(game.getGameState() !=  GameState.PLAYING)
                    break;

                System.out.println("Player 2's turn");
                player[second].play(game);


            }
            //not shown in example to print board after a gamestate is won/loss/tie but it allows the player to see how they won/lost/tied
            System.out.println(game);
            System.out.println("Game Over");
            System.out.println("result: " + game.getGameState());
            System.out.println("play again? (press y)");
            //alternate at end round

            // prints result of game and ask if you want to play again
        }while(Utils.console.readLine().compareToIgnoreCase("y") == 0);


    }
}
