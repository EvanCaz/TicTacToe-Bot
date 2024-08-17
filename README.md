Tic Tac Toe bot with random choices and calculated choices. 

What I have noticed is that upon picking a move randomly, the bot that chooses first, 'x', usually wins.
Of 100,000 games, 10,000, 1,000, x consistenly wins over 50% of the games, and very closely to 58% each time, with o winning around 29% and the remaining games are ties. 
The average time for each game is also fairly consistent, and as the game number increases, the precision of the average time is better and becomes seeminlgy lower, althought it really is not. 

Next, created one that picks the center if it is available, if not, picks a random move.
If the bot that plays first has the pick center move, they win around 70% of the time with the other one winnning 20% and the rest ties. 
If these roles are reversed, the first bot wins around 47% of the time, the other one winning 37% and the rest are ties. 
If they both pick the center, the resutls are the same as though the first bot only has center. 
These games all finish aroud 0.0013 miliseconds, and could be improved as each time the center move is tried, it checks the array list even if it has already been picked. 

Another tactic I know of is that if you play second and the center is picked, you should also pick a corner, thus I made a move that does so. 
This changes the win rates. If a pick center bot goes first and up against a pick corner, instaed of the previous 70-20 against a random, it now is a 65-22 with the rest being ties.
The pick corner bot only picks a corner if the center has been picked. If a pick random starts against a pick corner, the rates are 55-32 with the rest being ties. 
If these roles are reversed and the pick corner starts against a random, the spread is 56-30 and the rest are ties. 
These games also take about the same amount of time, running 10,000,000 games, the average time is 0.001113 per game.

Now there is a bot that if a winning move exists, it will pick the move, if not it will pick a random move. 
In 1,000,000 games, a pickWinning bot against a random will win 75% of the time to the random 12% with the rest being ties. 
In 1,000,000 games, a pick center bot against a picking winning bot will win 53% of the time with the winning bot 39%. 
In 1,000,000 games, a pickWinning bot against a pickCenter bot will win 65% of the time and the center 20%.
In 1,000,000 games, a pickRnd bot against a pickWin bot will win 39% of the time and the win 52%. 

Now there is a bot that first picks a winning move if it exists, and then picks the center and then a random. data out of 1,000,000
If a pickWinCen bot starts against a random, it will win 88.3903% of the time, with the random winning 7.0546% and the rest ties. These games take roughly 0.001456 miliseconds.
if a pickWinCen bot starts against a corner, it will win 87.4614% and the corner winning slightly more than 8.2195% and the rest ties.  These games take roughly 0.001437 miliseconds.
If a pickRnd bot starst against a pickWinCen, it will win 33.5069% of the time and the pickWinCen will win 658.2796% and the rest ties. These games take roughly 0.001556 miliseconds.
If pickWinCen is played against it self, it will win 83.7287% and the other bot 12.7606% with the rest ties. These games take roughly 0.001842 miliseconds.


Id like to create a minimax bot with and without alpha beta pruning to show the impact in game time. To do this, I have used the following resources:

https://www.geeksforgeeks.org/finding-optimal-move-in-tic-tac-toe-using-minimax-algorithm-in-game-theory/
https://en.wikipedia.org/wiki/Minimax
https://www.neverstopbuilding.com/blog/minimax

From my understanding, a score needs to be assigned to each outcome of each possible branch of moves. In this example, 5+ for a win, 5- for a loss, and 0 for a tied. These are called terminal states.
The root of the tree is the current state of the board, which is prolly a parameter the method needs to accept.
It is also assumed that the oppising player will play in the most optimal way.
depth also needs to be considered, as the branch of moves that results in a win sooner should be priotized over a branch that results in a win later.
This is an imporant part for alpha beta pruning I think, because this ensures a series of moves and their scores differntiate at different depths. 
For example, if there were a game board such as this:
| o | - | x |
| x | - | - |
| x | o | o |
There are only three possible moves for the next turn, hence three branchs. on branch one, if it results in the following board:
| o | - | x |
| x | x | - |
| x | o | o |
Then we know that on any futher searching, we should not go past a depth of one as there could not possbile be a better move. 
 


All this data goes to show that starting first is crucial to wining. 