package basic;
import java.util.Scanner;


public class Game {
	private Board board;
	private Timer timer;
	private boolean is_paused;
	private static Game game=new Game();
	public static Game getInstance() {
		return game;
	}
	private Game() {
		board=new Board();
		timer=Timer.getInstance();
	}
	public boolean start(int size, int time) throws Exception {
		timer.setUp(time);
		timer.start();
		board.setSize(size);
		board.generateGrids();
		return true;
	}
	public void pauseTimer() {
		timer.pause();
	}
	public void endGame() {
		timer.terminate();
		
	}
	public void executeCancel(int x1, int y1, int x2, int y2) {
		if(board.isSolvable(x1, y1, x2, y2)) {
			board.cancel(x1,x2,x3,x4);					
			if(!board.isSolvable()) {
				board.shuffle();
				System.out.println("There is no grid can be solved. Board shuffled!")
			}
		}
		else {
			System.out.println("The two grids can not be linked together")
		}
	}
}
