# Class Diagram Design

## Version 1.0

### Function Included

* Functionalities 
  * Start a new game
    * select a mode press start button to start a new game (multithreading)
      * generate board according to different types of game mode
      * start timer according to different types of game mode
    * Achievement:
    * Difficulty:
      * type of grid
      * size of board
      * timer
      * similarity between grids
    * Mode:
      * pass
      * infinite => calculate score
      * Map => blocker, empty, normal, special grid
  * generate new game
    * need to be solvable (each types of grids are even, there is at least one cancelable pairs)
    * number of different types of grid
    * size of the board
    * Timer 
      * how long it is
      * how much to recover after cancelling
    * number of hint
    * Algorithm?
      * random
      * other algorithm
      * how to generate => interface (strategy pattern)
  * During the game
    * Select
    * linked and cancel
      * judge cancel
      * change state
      * recover
      * (add Score )
      * test remaining blocks are solvable, if not, shuffle
        * find all possible pairs initially, decrease count for each succuessful cancel
    * Hint
    * **pause & continue**
    * Visual the game information
    * Trigger achievement
  * Finish
    * Listening to the finish event
      * Success: listening to the board
      * fail: listening to the timer
    * turn off game(board & timer) directly
* Classes:
  - Game (state or hard coding)
  - Board (singleton)
    - Map
  - Grid (state pattern or child class)
  - Timer (singleton)
  - Achievements
  - Scoreboard 
    - infinity mode
    - finite mode => pass
* Some ideas
  * Tools: for example, a bomb to cancel all grids
  * \* background music



