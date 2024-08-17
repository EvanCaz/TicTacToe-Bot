import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;



public class Bot {
    private char symbol;
    Map<Character, Integer> scores = new HashMap<>();
    public static void main(String[] args) {
        // Bot myBot = new Bot();
    }

    public Bot(char x){
        symbol = x;
    }

    public void pickRandom(game newGame){
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(newGame.availMoves.size());
        int pickedMove = newGame.availMoves.get(randomIndex);
        System.out.print(symbol + " picked " + pickedMove + ". ");
        newGame.validMoves(pickedMove);
        newGame.updateBoard(pickedMove, symbol);
    }

    public void pickCenter(game newGame){ // if the center is open, pick it
        if(newGame.availMoves.contains(4)){
            System.out.print(symbol + " picked the center. ");
            newGame.validMoves(4);
            newGame.updateBoard(4, symbol);
            
        } else {
            pickRandom(newGame);
        }
    }

    // public void pickWinCen(game newGame){
    //     boolean moveFound = false;
    //     for(int i = 1; i < newGame.availMoves.size(); i++){ 
    //         int move = newGame.availMoves.get(i);
    //         newGame.updateBoard(move, symbol);
    //         if(newGame.gameOver() == true){ 
    //             moveFound = true;
    //             newGame.validMoves(move);
    //             newGame.updateBoard(move, symbol);
    //             System.out.print(symbol + " picked winning move " + move + ". ");
    //             break;
    //         } else {
    //         newGame.updateBoard(move, '-');
    //         }
    //     }
    //     if(moveFound == false){
    //         pickCenter(newGame);
    //     }
    // }

    public void bestMove(game newGame){
        int bestScore = 0;
        int topMove = -1; // this is the best move itself, not the index in the arraylist availmove. 
        for(int i = 0; i < newGame.availMoves.size(); i++){
            // make each move
            int move = newGame.availMoves.get(i);
            newGame.board[move] = symbol; // make the move
            int curScore = miniMax(newGame, 0, false); // pass the move
            newGame.board[move] = '-'; // undo the move
            if(curScore > bestScore){
                bestScore = curScore;

            }
        }
    }
    private int miniMax(game newGame, int depth, boolean isMax){
        // first check if it is a terminal state 
        int bestScore = -1; // there should always be a tieing move so i shouldnt have to worry about initilizing this to -1
        char result = newGame.gameOver();
        if(result != 'n'){ // if it is a terminal state, such as a winner or a draw, return the score associated
            if(result == 'c'){
                return 0;
            } else if (isMax == true) {
                return 5;
            } else {
                return -5;
            }
        }
        if(isMax) { // if we are the max player
            // check all the possible spots again, but when we recurse, the isMax is false
            for(int i = 0; i < newGame.availMoves.size(); i++){
                int move = newGame.availMoves.get(i);
                newGame.board[move] = symbol;
                int curScore = miniMax(newGame, depth + 1, false);
                newGame.board[move] = '-';
                bestScore = Math.max(curScore, bestScore);
            }  
            return bestScore; 
        } else { // if it is the minimizing players turn, pass the oppsite of x or o
            for(int i = 0; i < newGame.availMoves.size(); i++){
                int move = newGame.availMoves.get(i);
                newGame.board[move] = symbol;
                int curScore = miniMax(newGame, depth + 1, true);
                newGame.board[move] = '-';
                bestScore = Math.min(curScore, bestScore);
            }
            return bestScore;  
        }    
    }

    // public void pickBlock(game newGame){
    //     boolean moveFound = false;
    //     for(int i = 0; i < newGame.availMoves.size(); i++){ 
    //         int move = newGame.availMoves.get(i);
    //         newGame.updateBoard(move, this.symbol);
    //         if(this.symbol == 'x'){
    //             newGame.updateBoard(move, 'o');
    //         }
    //         if(this.symbol == 'o'){
    //             newGame.updateBoard(move, 'x');
    //         }
    //         if(newGame.gameOver() == true){
    //             // moveFound = true;
    //             System.out.println(symbol +"picked could pick blocking move " + move);
    //             break;
    //         } 
    //         newGame.updateBoard(move, '-');
    //     }
    //     if(moveFound == false){
    //         pickRandom(newGame);
    //     }
    // }
}   
    /*
     * If the center has been picked, pick a corner, else pick random. This is tactic used in real tictactoe as if u start second
     * You want to pick the corners if the first player picked a center
     */
    // public void pickCorner(game newGame){ // if center has been picked, pick a corner, else pick random. 
    //     if(newGame.availMoves.contains(4) == false && firstPicked == false){ // if the center has been picked
    //         ArrayList<Integer> availCorners = new ArrayList<>();
    //         firstPicked = true;
    //         if(newGame.availMoves.contains(0)) availCorners.add(0);
    //         if(newGame.availMoves.contains(2)) availCorners.add(2);
    //         if(newGame.availMoves.contains(6)) availCorners.add(6);
    //         if(newGame.availMoves.contains(8)) availCorners.add(8);
    //         // for(int i : availCorners){
    //         //     System.out.print(i + ", ");
    //         // }
    //         if(availCorners.isEmpty() == false){
    //             Random rnd = new Random();
    //             int randomCorner = availCorners.get(rnd.nextInt(availCorners.size()));
    //             // int index = newGame.availMoves.get(randomCorner);
    //             newGame.validMoves(randomCorner);
    //             newGame.updateBoard(randomCorner, symbol);
    //             // System.out.print(symbol + " picked the corner " + randomCorner + ". ");
    //         } 
    //     } else {
    //         pickRandom(newGame);    
    //     }
    // }

    /*
     * for each move in the valid moves list, make them and see if the result in a win, if they do make it, 
     * if none do, revert and make a random move.
     */
    // public void pickWinning(game newGame){
    //     boolean moveFound = false;
    //     for(int i = 1; i < newGame.availMoves.size(); i++){ 
    //         int move = newGame.availMoves.get(i);
    //         newGame.updateBoard(move, symbol);
    //         if(newGame.gameOver() == true){ // incorrectly counts a full board as winning for this bot
    //             moveFound = true;
    //             newGame.validMoves(move);
    //             newGame.updateBoard(move, symbol);
    //             // System.out.print(symbol + " picked winning move " + move + ". ");
    //             break;
    //         } else {
    //         newGame.updateBoard(move, '-');
    //         }
    //     }
    //     if(moveFound == false){
    //         pickRandom(newGame);
    //     }
    // }

        
