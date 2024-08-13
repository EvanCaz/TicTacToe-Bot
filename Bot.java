import java.util.Random;

import javax.sound.midi.SysexMessage;

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
                if(hasCenter == true){   // if they have the center, no matter where they place it we have to go to the oppsite, single case we dont have to check in our last move is there
                    int[][] lookup = {
                        {2, 2, 2, 1, 2, 0}, // for x = 0, y = 0 maps to board[2][2], y = 1 maps to board[2][1], y = 2 maps to board[2][0]
                        {1, 2, 1, 0, 1, 2}, // for x = 1, y = 0 maps to board[1][2], y = 1 maps to board[1][0], y = 2 maps to board[1][2]
                        {0, 2, 0, 1, 0, 0}  // for x = 2, y = 0 maps to board[0][2], y = 1 maps to board[0][1], y = 2 maps to board[0][0]
                    };
                    if (x >= 0 && x < 3 && y >= 0 && y < 3) {
                        int row = lookup[x][y];
                        int col = 2 - y;  //  inversion 
                        System.out.println(board[row][col]); // before a spot is chosen
                        if(row == 2 && col == 1){  // weird case
                            board[0][1] = 'o';
                        } else {
                            if(board[row][col] != 'o'){
                                board[row][col] = 'o';
                            } else { // if we have already blocked it, pick remaining corner, this could also be randomized for non repetitive games
                                System.out.println("Player last move: (" + x + "," + y + ")");
                                System.out.println("Computer last move: (" + lastMove[0] + "," + lastMove[1] + ")");
                                if((x == 2 && y == 0) && (lastMove[0] == 0 && lastMove[1] == 2) || ((x == 0 && y == 2) && (lastMove[0] == 2 && lastMove[1] == 0))){ // if both bottom left and top right corners taken, choose top left
                                    board[0][0] = 'o';
                                } else if ((x == 0 && y == 0) && (lastMove[0] == 2 && lastMove[1] == 2) || ((x == 2 && y == 2) && (lastMove[0] == 2 && lastMove[1] == 2))){ // oppsite of above
                                    board[0][2] = 'o';
                                }
                            }
                        }
                    }
                } else { // if bot has center
                    /*
                    *  if his second move allows him to win, block it. This case occurs when there are to 'x's in the same row or column, as we have the center piece
                    *  otherwise we can win
                    */
                    
                    // System.out.println("Test center reached"); 
                    int j = 0; int k = 0;
                    int tmpX = 0, tmpY = 0;
                    for(int i = 0; i < 3; i++){
                        if(board[0][i] == 'x'){
                            System.out.println("test");
                            if(board[0][i] == '#'){
                                System.out.println("test");
                                tmpY = i;
                                
                            }
                            j++;
                        }  
                        if (board[2][i] == 'x'){
                            if(board[i][0] == '#'){
                                System.out.println(i);
                                tmpY = i;
                            }
                            j++;
                        }
                        // if(board[i][0] == 'x' || board[i][2] == 'x'){
                        //     if(board[0][i] == '#'){
                        //         tmpY = i;
                        //     } else {
                        //         tmpX = 2;
                        //     }
                        //     k++;
                        // }
                    }
                    if(j > 1){
                        // board[0][temp] = 'o';
                        System.out.println("in the same row detected 2");
                        System.out.println(tmpY);
                    } else if (k > 1) {
                        // board[tmpX][tmpY] = 'o';
                        System.out.println("in the same col detected 2");
                        System.out.println(tmpX);
                    }
                }
                turnCnt++;
        } else if (turnCnt > 1){
            // if it is greater than one and no winners, i think we cant win and must just play defense
            System.out.println("Test reach"); 
        }

    }
}