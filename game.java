import java.util.Scanner;

public class game {
    private static final int rows = 3;
    private static final int cols = 3;
    public static char board[][] = new char[rows][cols];
    public static int[] pastMoves = new int[2];

    public static void main (String[] args) {
        game newGame = new game();
        Bot newBot = new Bot();
        while(newGame.gameOver() != true){
            newGame.printBoard();
            newGame.getInput();
            newBot.makeMove(board);
        }
        // prolly while loop until a winner is found, passing the board to a bot class for other move
    }

    public game () {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                board[i][j] = '#';
            }
        }
        // System.out.println("test");
    }
    
    private void printBoard() { // testing and shi
        System.out.println("    0   1   2");
        for(int i = 0; i < rows; i++){
            System.out.print(i + " | ");
            for(int j = 0; j < cols; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        
        
    }
    /*
     * No input validation simply gets one after the other, this project is simply to demostrate the creation of a bot to play against
     */
    private void getInput() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter x coordinate: ");
        int xCord = scnr.nextInt();
        pastMoves[0] = xCord;
        System.out.print("Enter y coordinate: ");
        int yCord = scnr.nextInt();
        pastMoves[1] = yCord;
        // System.out.println(xCord + " : " + yCord);
        updateBoard(xCord, yCord, 'x');

    }

    public void updateBoard(int xCord, int yCord, char symbol){
        board[xCord][yCord] = symbol;
    }

    /*
     * Checks all row and if one is equal, game over
     * checks all cols and if one is equal, game over
     * checks both diagonals and if one is equal, game is over, should probably check first for efficieny
     * check if board is full, which would iterate over the whole thing and should be done last
     * breaks the rule of more than one return statmenet as this is more efficnet  
     */
    private boolean gameOver() { 
        if (board[0][0] != '#' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            System.out.println("Player " + board[0][0] + " Wins");
            return true;
        }
        if (board[0][2] != '#' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            System.out.println("Player " + board[0][2] + " Wins");
            return true;
        }
        for(int i = 0; i < rows; i++){ // check all rows if they are equal to each other
            if(board[i][0] != '#' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                System.out.println("Player " + board[i][0] + " Wins");
                return true;
            }
            if(board[0][i] != '#' && board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                System.out.println("Player " + board[0][i] + " Wins");
                return true;
            }
        }
        return false;
    }

}