import java.util.Scanner;

public class game {
    private static final int spaces = 9;
    public static char board[] = new char[spaces];

    public static void main (String[] args) {
        game newGame = new game();
        // Bot newBot = new Bot();
        newGame.printBoard();
        newGame.getInput();
        newGame.printBoard();
        // while(newGame.gameOver() != true){
        //     newGame.printBoard();
        //     newGame.getInput();
        //     newBot.makeMove(board);
        // }
        // prolly while loop until a winner is found, passing the board to a bot class for other move
    }

    public game () {
        for(int i = 0; i < spaces; i++){  
                board[i] = '-';
        }
        // System.out.println("test");
    }
    
    private void printBoard() { // testing and shi
        System.out.println("     0   1   2");
        int k = 0;
        for(int i = 0; i < spaces; i++){
            if(i % 3 == 0) { 
                System.out.print(i / 3 + " "); // row number
            }
            System.out.print(" | " + board[i]);
            if((i + 1) % 3 == 0 && i != spaces - 1){
                System.out.print(" |  ");
                for(int j = 0; j < 3; j++){
                    System.out.print(" | " + k);
                    k++;
                }
                System.out.print(" |");
                System.out.println();
            }
        } 
        System.out.print(" |  ");
        for(int j = 0; j < 3; j++){
            System.out.print(" | " + k);
            k++;
        }
        System.out.print(" |");
    }
    /*
     * No input validation simply gets one after the other, this project is simply to demostrate the creation of a bot to play against
     */
    private void getInput() {
        Scanner scnr = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter x coordinate: ");
        int cord = scnr.nextInt();
        updateBoard(cord, 'X');
        
        
    }

    public void updateBoard(int cord, char symbol){
        board[cord] = symbol;
    }

    /*
     * Checks all row and if one is equal, game over
     * checks all cols and if one is equal, game over
     * checks both diagonals and if one is equal, game is over, should probably check first for efficieny
     * check if board is full, which would iterate over the whole thing and should be done last
     * breaks the rule of more than one return statmenet as this is more efficnet  
     */
    // private boolean gameOver() { 
    //     if (board[0][0] != '#' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
    //         System.out.println("Player " + board[0][0] + " Wins");
    //         return true;
    //     }
    //     if (board[0][2] != '#' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
    //         System.out.println("Player " + board[0][2] + " Wins");
    //         return true;
    //     }
    //     for(int i = 0; i < rows; i++){ // check all rows if they are equal to each other
    //         if(board[i][0] != '#' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
    //             System.out.println("Player " + board[i][0] + " Wins");
    //             return true;
    //         }
    //         if(board[0][i] != '#' && board[0][i] == board[1][i] && board[1][i] == board[2][i]){
    //             System.out.println("Player " + board[0][i] + " Wins");
    //             return true;
    //         }
    //     }
    //     return false;
    // }

}