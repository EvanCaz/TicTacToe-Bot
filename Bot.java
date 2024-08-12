import java.util.Random;

public class Bot {
    private static int turnCnt = 0;
    private static boolean hasCenter = false;
    // private static int[] lastMove = new int[2];
    public static void main(String[] args) {
        // Bot myBot = new Bot();
    }
    /*
     * If they dont take the center, take the center, if they do, take any one of four corners
     */
    public void makeMove(char[][] board){
        if(turnCnt == 0){
            if(board[1][1] == '#'){
                board[1][1] = 'o';
                turnCnt++;
            } else { // choose a four corner, id like it to be a random corner, so the games are not repetitive
                Random rnd = new Random();
                int[] values = {0, 2};
                int rndIndex = rnd.nextInt(values.length);
                int rndCol = values[rndIndex];
                rndIndex = rnd.nextInt(values.length);
                int rndRow = values[rndIndex];
                // lastMove[0] = rndRow;
                // lastMove[1] = rndCol;
                System.out.println("player chose (1,1), choosing: (" + rndRow + "," + rndCol+ ")");    
                board[rndRow][rndCol] = 'o';
                hasCenter = true;
                turnCnt++;
            }
        } else if (turnCnt == 1) { // if he has the center, we are basically playing defense unles he screws up
                int x = game.pastMoves[0];
                int y = game.pastMoves[1];    
                // System.out.println("(" + x + "," + y + ")");
                // System.out.println("Test" + hasCenter);
                if(hasCenter == true){ // if they have the center, no matter where they place it we have to go to the oppsite
                    if(x == 0 && y == 0){
                        board[2][2] = 'o';
                    }
                    if(x == 0 && y == 1){
                        board[2][1] = 'o';
                    }
                    if(x == 0 && y == 2){
                        board[2][0] = 'o';
                    }
                    if(x == 1 && y == 0){
                        board[1][2] = 'o';
                    }
                    if(x == 1 && y == 2){
                        board[1][0] = 'o';
                    }
                    if(x == 2 && y == 0){
                        board[0][2] = 'o';
                    }
                    if(x == 2 && y == 1){
                        board[0][1] = 'o';
                    }
                    if(x == 2 && y == 2){
                        board[0][0] = 'o';
                    }
                } else {

                }
        }   

    }
}