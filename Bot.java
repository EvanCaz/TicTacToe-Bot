import java.util.Random;

public class Bot {
    private static int turnCnt = 0;
    private static boolean hasCenter = false;
    // private static int[][] lookup;
    private static int[] lastMove = new int[2];
    public static void main(String[] args) {
        // Bot myBot = new Bot();
    }

    public void randomMove(game newGame, char symbol){
        Random rnd = new Random();
        int randomIndex = rnd.nextInt(newGame.validMoves.size());
        int pickedMove = newGame.validMoves.get(randomIndex);
        System.out.println();
        System.out.println(symbol + " picked index: " + pickedMove);
        newGame.validMoves(pickedMove);
        newGame.updateBoard(pickedMove, symbol);
    }
        
}
