# Jobs

## Basic

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



## Integrated (Not Decided)

- Hints
- Difficulties
- 