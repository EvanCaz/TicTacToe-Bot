import java.util.Random;

public class Bot {
    public static void main(String[] args) {
        // Bot myBot = new Bot();
    }

    public void randomMove(game newGame, char symbol){
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(newGame.validMoves.size());
        int pickedMove = newGame.validMoves.get(randomIndex);
        System.out.print(symbol + " picked " + pickedMove + ". ");
        newGame.validMoves(pickedMove);
        newGame.updateBoard(pickedMove, symbol);
    }
        
}
