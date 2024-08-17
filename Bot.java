import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;



public class Bot {
    private char symbol;
    int topMove;

    public Bot(char x){
        symbol = x;
    }

    public void bestMove(game newGame){
        int bestScore = Integer.MIN_VALUE;
        int topMove = -1;
        for(int i = 0; i < newGame.spaces; i++){
                if(newGame.board[i] != 'x' && newGame.board[i] != 'o'){
                newGame.board[i] = symbol;
                int curScore = miniMax(newGame, 0, false);
                newGame.board[i] = '-';

                if (curScore > bestScore) {
                    bestScore = curScore;
                    topMove = i;
                }
            }
        }
        newGame.board[topMove] = symbol;
    }
    /*
     * Errors trying to get this to run with availMoves and outside of class, so moved into here and kept very simple
     */
    private int miniMax(game newGame, int depth, boolean isMax){ // breaks rules Alexander Katrompas taught me
        char result = newGame.gameOver();
        if(newGame.gameOver() == 'c' || newGame.gameOver() == 'x' || newGame.gameOver() == 'o'){
            if (result == 'c') {
                return 0; // Draw
            } else if (result == symbol) {
                return 10; // Bot wins
            } else {
                return -10; // Opponent wins
            }
        }
        if(isMax){
            int bestScore = Integer.MIN_VALUE;
            for(int i = 0; i < newGame.spaces; i++){
                if(newGame.board[i] != 'x' && newGame.board[i] != 'o'){
                    newGame.board[i] = symbol;
                    int curScore = miniMax(newGame, depth + 1, false);
                    newGame.board[i] = '-';
                    bestScore = Math.max(curScore, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for(int i = 0; i < newGame.spaces; i++){
                if(newGame.board[i] != 'x' && newGame.board[i] != 'o'){
                    newGame.board[i] = (symbol == 'x') ? 'o' : 'x'; // Opponent's symbol
                    int curScore = miniMax(newGame, depth + 1, true);
                    newGame.board[i] = '-';
                    bestScore = Math.min(curScore, bestScore);
                }
            }
            return bestScore;
        }

    }
    
    // public void pickRandom(game newGame){
    //     Random rnd = new Random();
    //     int randomIndex = rnd.nextInt(newGame.availMoves.size());
    //     int pickedMove = newGame.availMoves.get(randomIndex);
    //     System.out.print(symbol + " picked " + pickedMove + ". ");
    //     newGame.validMoves(pickedMove);
    //     newGame.updateBoard(pickedMove, symbol);
    // }
}


