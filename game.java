import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class game {
    private static final int spaces = 9;
    public static char board[] = new char[spaces];
    public static List<Integer> validMoves = new ArrayList<>(); // for random chose bot
    static private int ties = 0;
    static private boolean tied = false;
    static private int xWins = 0;
    static private int oWins = 0;

    public static void main (String[] args) {
        Bot bot1 = new Bot();
        Bot bot2 = new Bot();
        boolean game;
        for(int i = 0; i < 1000; i++){
            game newGame = new game();
            game = false;
            System.out.println();
            while(game == false){
                bot1.randomMove(newGame, 'x');
                game = newGame.gameOver();
                if(game == true){
                    xWins++;
                    break;
                }
                bot2.randomMove(newGame, 'o');
                game = newGame.gameOver();
                if(game == true){
                    oWins++;
                    break;
                }
            }
            System.out.println();
            newGame.printBoard();
        }
        System.out.println();
        System.out.println("There were " + xWins + " number of x wins and there were " + oWins + " and " + ties + " ties.");
        // prolly while loop until a winner is found, passing the board to a bot class for other move
    }

    public game () {
        for(int i = 0; i < spaces; i++){  
                board[i] = '-';
        }
        for(int i = 0; i < spaces; i++){  
            validMoves.add(i); // all moves are valid at the start
    }
        // System.out.println("test");
    }
    
    private void printBoard() { // testing and shi
        int k = 0;
        for(int i = 0; i < spaces; i++){
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

    public void validMoves(int move){
        validMoves.remove(Integer.valueOf(move));
        // for (int validMove : validMoves) {
        //     System.out.print(validMove + " ");
        // }
        // System.out.println();
    }

    private void getInput() {
        Scanner scnr = new Scanner(System.in);
        int cord = -1;
        boolean success = false;
        System.out.println();
        while(!success) {
            System.out.print("Enter coordinate: ");
            try {
                cord = scnr.nextInt();
                if((cord >= 0 && cord <= 8) && (validMoves.contains(cord) == true)) {
                    success = true;
                } else {
                    System.out.println("Enter a number within range and is not already used.");
                }
            } catch (Exception e) {
                System.out.println("Enter a valid number.");
                scnr.nextLine(); // Clear the invalid input
            }
        }
        updateBoard(cord, 'X');
        validMoves(cord);
    }


    public void updateBoard(int cord, char symbol){
        board[cord] = symbol;
    }

    private boolean gameOver() { 
        int[][] winningCombinations = {
            {0, 1, 2}, // Row 1
            {3, 4, 5}, // Row 2
            {6, 7, 8}, // Row 3
            {0, 3, 6}, // Column 1
            {1, 4, 7}, // Column 2
            {2, 5, 8}, // Column 3
            {0, 4, 8}, // Diagonal from top-left to bottom-right
            {2, 4, 6}  // Diagonal from top-right to bottom-left
        };
        
        for (int[] combination : winningCombinations) {
            if (board[combination[0]] != '-' && board[combination[0]] == board[combination[1]] && board[combination[1]] == board[combination[2]]) {
                return true;
            }
        }
        for (char cell : board) {
            if (cell == '-') {
                return false; // Empty cell found, game is not over
            } 
        }
        // System.out.println("TIED");
        ties++;
        xWins--;
        return true;
    } 
}