import java.util.ArrayList;
import java.util.Random;

public class Bot {
    private char symbol;
    private boolean firstPicked = false;

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

    /*
     * can be optimized as it checks everytime for center move, instaed of checking until it has been picked
     */
    public void pickCenter(game newGame){ // if the center is open, pick it
        if(newGame.availMoves.contains(4)){
            System.out.print(symbol + " picked the center. ");
            newGame.validMoves(4);
            newGame.updateBoard(4, symbol);
            
        } else {
            pickRandom(newGame);
        }
    }
    /*
     * If the center has been picked, pick a corner, else pick random. This is tactic used in real tictactoe as if u start second
     * You want to pick the corners if the first player picked a center
     */
    public void pickCorner(game newGame){ // if center has been picked, pick a corner, else pick random. 
        if(newGame.availMoves.contains(4) == false && firstPicked == false){ // if the center has been picked
            ArrayList<Integer> availCorners = new ArrayList<>();
            firstPicked = true;
            if(newGame.availMoves.contains(0)) availCorners.add(0);
            if(newGame.availMoves.contains(2)) availCorners.add(2);
            if(newGame.availMoves.contains(6)) availCorners.add(6);
            if(newGame.availMoves.contains(8)) availCorners.add(8);
            // for(int i : availCorners){
            //     System.out.print(i + ", ");
            // }
            if(availCorners.isEmpty() == false){
                Random rnd = new Random();
                int randomCorner = availCorners.get(rnd.nextInt(availCorners.size()));
                // int index = newGame.availMoves.get(randomCorner);
                newGame.validMoves(randomCorner);
                newGame.updateBoard(randomCorner, symbol);
                // System.out.print(symbol + " picked the corner " + randomCorner + ". ");
            } 
        } else {
            pickRandom(newGame);    
        }
    }

    /*
     * for each move in the valid moves list, make them and see if the result in a win, if they do make it, 
     * if none do, revert and make a random move.
     */
    public void pickWinning(game newGame){
        boolean moveFound = false;
        for(int i = 1; i < newGame.availMoves.size(); i++){ 
            int move = newGame.availMoves.get(i);
            newGame.updateBoard(move, symbol);
            if(newGame.gameOver() == true){ // incorrectly counts a full board as winning for this bot
                moveFound = true;
                newGame.validMoves(move);
                newGame.updateBoard(move, symbol);
                System.out.print(symbol + " picked winning move " + move + ". ");
                break;
            } else {
            newGame.updateBoard(move, '-');
            }
        }
        if(moveFound == false){
            pickRandom(newGame);
        }
    }

    public void pickWinCen(game newGame){
        boolean moveFound = false;
        for(int i = 1; i < newGame.availMoves.size(); i++){ 
            int move = newGame.availMoves.get(i);
            newGame.updateBoard(move, symbol);
            if(newGame.gameOver() == true){ 
                moveFound = true;
                newGame.validMoves(move);
                newGame.updateBoard(move, symbol);
                System.out.print(symbol + " picked winning move " + move + ". ");
                break;
            } else {
            newGame.updateBoard(move, '-');
            }
        }
        if(moveFound == false){
            pickCenter(newGame);
        }
    }

    public void pickBlock(game newGame){
        boolean moveFound = false;
        for(int i = 0; i < newGame.availMoves.size(); i++){ 
            int move = newGame.availMoves.get(i);
            if(symbol == 'x'){
                newGame.updateBoard(move, 'o');
            } else {
                newGame.updateBoard(move, 'x');
            }
            if(newGame.gameOver() == true){
                moveFound = true;
                newGame.validMoves(move);
                newGame.updateBoard(move, symbol);
                System.out.print(symbol + " picked blocking move " + move + ". ");
                break;
            } else {
            newGame.updateBoard(move, '-');
            }
        }
        if(moveFound == false){
            pickRandom(newGame);
        }
    }
}   
        
