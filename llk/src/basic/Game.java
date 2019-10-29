package basic;

public class Game implements Stoppable {
	private Board board;
	private Timer timer;
	private boolean is_paused;
	private boolean is_stopped;
	
	public Game(int size,int time) throws WrongGameSizeException {
		timer=Timer.getInstance();
		timer.setUp(time, this);
		board=new Board(size);
		is_paused = is_stopped = false;
	}
	
	public void start() throws TimerStartErrorException {
		timer.start();
		board.generateAll();
		board.show();
		timer.showTime();
		is_paused = is_stopped = false;
	}
	
	public void pause() throws TimerPauseErrorException {
		timer.pause();
		is_paused = true;
	}
	
	public void resume() throws TimerRestartErrorException {
		timer.restart();
		is_paused = false;
	}
	
	public void terminate() throws TimerTerminateErrorException {
		timer.terminate();
		is_paused = false;	
		is_stopped = true;
	}
	
	public void cancelGrids(int x1, int y1, int x2, int y2) throws OutOfBoundException, CannotCancelException, TimerTerminateErrorException {
		if(!is_paused) {	
				board.cancel(x1,y1,x2,y2);
			if(board.getExistingNumber()==0) {
					terminate();
					System.out.println("Congratulation!");
			}
		}else {
			System.out.println("Please restart game first!");
		}
	}
	
	public void showBoard() {
		board.show();
	}
	
	public void showTime() {
		timer.showTime();
	}
	
	public boolean isStopped() {
		return is_stopped;
	}
	
	public boolean stop() {
		if(board.getExistingNumber()!=0) {
			System.out.println("You failed! There is no time left!\n"
					+ "Press e to exit game.");
		}
		is_stopped = true;
		return true;
	}
}