# Workload

## Basic Functionalities

Command line only

1. Definition of solvable:
   - At least one pair can be cancelled
2. Generate a game board:
   - numbers to represent types
   - the initial configuration is solvable + the number of each type is even
3. User operation:
   - Input two `(row, column)` that indicates the position of the blocks to be eliminated
   - Test if these two blocks can be cancelled
     - if yes, cancel the blocks and output the remaining configuration
     - if no, output the error message
4. Successful cancellation:
   - Test if the remaining configuration is solvable
   - If no, shuffle (step 2)
5. Timer:
   - Use a timer that counts the remaining time
   - Upon time up, end the game and tell user "Game Over"
   - Output time at the time of each configuration
6. Procedural `main` class:
   - Multithreading or other concurrent techniques to control the board and timer
   - User interface with an arrangement of input and output



Notes on [class diagram ](basic_class.png)([source code](basic_class.wsd)):

- Board is singleton

- Need more consideration on the elimination of grids

- Investigate existing timer classes

- In testing, test stub of the Grid class can be used

- 3 groups:

  - set up a template first

  - one on Grid, challenge: `isSolvable()`
  - one on Board, challenge: `cancellable()`
  - one on Timer and main, challenge: concurrent programming

- Internal due on the first release: Oct 20 (next Sunday)



## Integration of Advanced Features (Next)

- Hints
- Difficulties
- Achievements
- Scoreboard