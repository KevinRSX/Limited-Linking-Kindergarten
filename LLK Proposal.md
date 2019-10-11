# LLK Proposal

## Rule

[https://baike.baidu.com/item/%E5%AE%A0%E7%89%A9%E8%BF%9E%E8%BF%9E%E7%9C%8B](https://baike.baidu.com/item/宠物连连看)

Note that Lianliankan is NOT Xiaoxiaole

## Modules

###  Basic Moves (BM)

The player chooses a block, and match it with another block. Design an algorithm to determine whether these two blocks can be cancelled out.

- If these two blocks are not of the same type, fail to cancel (F)
- If these two blocks are of the same type, but one needs to take turns for more than two times, fail to cancel (F)
- Otherwise, succeed to cancel (S)

After each move, system must determine whether a player can continue to perform cancellation on the remaining shape. If there's no possible cancellation, system needs to either shuffle the whole shape or end this game.



### Graphical User Interface (GUI)

Main features:

- After clicking a block A, its color needs to be changed (or shaded)
- If the player chooses a different block B that results in F (fail to cancel), change the color of block B while recover the color of block A
- If the player chooses a different block B that results in S (succeed to cancel), eliminate both block A and B
- Start and end page



### Advanced Features (AF, Optional)

- Time limit
- Different modes, for example, shuffle after each cancellation, switch position towards top, bottom, middle, etc.
- 3D LLK (needs to reconsider the algorithm)
- Animation when cancelling
- Achievement system (may need database)



##Testing 

- BM: use different shape and configuration of smaller size (the game may be 10 * 10, but testing can be other 5 * 5)
- GUI: effect of each click, please do more research on the tools taught in class
- AF: not settled yet





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