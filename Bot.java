import java.util.Random;

public class Bot {
    private static int turnCnt = 0;
    private static boolean hasCenter = false;
    // private static int[][] lookup;
    private static int[] lastMove = new int[2];
    public static void main(String[] args) {
        // Bot myBot = new Bot();
    }

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
                lastMove[0] = rndRow;
                lastMove[1] = rndCol;
                System.out.println("player chose (1,1), choosing: (" + rndRow + "," + rndCol+ ")");    
                board[rndRow][rndCol] = 'o';
                hasCenter = true;
                turnCnt++;
            }
        } else if (turnCnt == 1) { 
                int x = game.pastMoves[0];
                int y = game.pastMoves[1];    
                if(hasCenter == true){ // if they have the center, no matter where they place it we have to go to the oppsite, single case we dont have to check in our last move is there
                    int[][] lookup = {
                        {2, 2, 2, 1, 2, 0}, // for x = 0, y = 0 maps to board[2][2], y = 1 maps to board[2][1], y = 2 maps to board[2][0]
                        {1, 2, 1, 0, 1, 2}, // for x = 1, y = 0 maps to board[1][2], y = 1 maps to board[1][0], y = 2 maps to board[1][2]
                        {0, 2, 0, 1, 0, 0}  // for x = 2, y = 0 maps to board[0][2], y = 1 maps to board[0][1], y = 2 maps to board[0][0]
                    };
                    if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                        int row = lookup[x][y];
                        int col = 2 - y;  //  inversion 
                        System.out.println(board[row][col]); // before a spot is chosen
                        if(row == 2 && col == 1){ 
                            board[0][1] = 'o';
                        } else {
                            if(board[row][col] != 'o'){
                                board[row][col] = 'o';
                            } else { // if we have already blocked it, pick whatever
                                if(lastMove[0] == 0){
                                    board[0][1] = 'o';
                                } else {
                                    board[2][1] = 'o';
                                }
                            }
                        }
                    }
                    turnCnt++;
                } else { // if bot has center
                    /*
                     *  if his second move allows him to win, block it. This case occurs when there are to 'x's in the same row or column, as we have the center piece
                     *  otherwise we can win
                     */
                    // if()
                    
                }
        } else if (turnCnt > 1){
            // if it is greater than one and no winners, i think we cant win and must just play defense
            System.out.println("Test reach"); 
        }

    }
}