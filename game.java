// import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class game {
    public final int spaces = 9;
    public char board[] = new char[spaces];
    public List<Integer> availMoves = new ArrayList<>(); // for random chose bot
    static private int ties = 0;
    static private int xWins = 0;
    static private int oWins = 0;
    

    public static void main (String[] args) {
        int numGames = 10;
        long totalTime = 0;
        System.out.println("Loading...");
        long bigTime = System.currentTimeMillis();
        for(int i = 0; i < numGames; i++) {
            game newGame =  new game();
            Bot bot1 = new Bot('x');
            Bot bot2 = new Bot('o');
            long startTime = System.currentTimeMillis();
            while(newGame.gameOver() == 'n'){
                try {
                    bot1.bestMove(newGame);
                } catch (Exception e){
                    ties++;
                }
                try {
                    bot2.pickRandom(newGame);
                } catch (Exception e){
                    ties++;
                }
            }
            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            totalTime += elapsed;
            if(newGame.gameOver() == 'x'){
                xWins++;
            } else if(newGame.gameOver() == 'o'){
                oWins++;
            }
        }
        long bigTime2 = System.currentTimeMillis();

        double avgTime = (double) totalTime / numGames; // num games is first number
        double totalSeconds = (double) (bigTime2 - bigTime) / 1000;
        System.out.println();
        System.out.println("There were " + xWins + " number of x wins and there were " + oWins + " o wins and " + ties + " ties.");
        System.out.println("Average time of " + avgTime + " miliseconds with total time of " + totalSeconds + " Total seconds");
        

    }

    public game () {
        for(int i = 0; i < spaces; i++){  
                board[i] = '-';
        }
        for(int i = 0; i < spaces; i++){  
            availMoves.add(i); // all moves are valid at the start
        }
    }
    
    public void printBoard() { // testing and shi
        System.out.println();
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
        System.out.println();
    }

    public void printAvailMoves(){
        for(int i : availMoves){
            System.out.print(availMoves.get(i) + ", ");
        }
        System.out.println();
    }

    public void validMoves(int move){
        availMoves.remove(Integer.valueOf(move));
    }
     
    public void updateBoard(int cord, char symbol){
        board[cord] = symbol;
    }

    
    public char gameOver() { 
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
                return board[combination[0]];
            }
        }
        for (char cell : board) {
            if (cell == '-') {
                return 'n'; // Empty cell found, game is not over
            } 
        }
        // System.out.println("TIED");
        return 'c';
    } 
    // private void getInput() {
    //     Scanner scnr = new Scanner(System.in);
    //     int cord = -1;
    //     boolean success = false;
    //     System.out.println();
    //     while(!success) {
    //         System.out.print("Enter coordinate: ");
    //         try {
    //             cord = scnr.nextInt();
    //             if((cord >= 0 && cord <= 8) && (board[cord] == '-')) {
    //                 success = true;
    //             } else {
    //                 System.out.println("Enter a number within range and is not already used.");
    //             }
    //         } catch (Exception e) {
    //             System.out.println("Enter a valid number.");
    //             scnr.nextLine(); // Clear the invalid input
    //         }
    //     }
    //     board[cord] = 'x';
    //     validMoves(cord);
    // }
}