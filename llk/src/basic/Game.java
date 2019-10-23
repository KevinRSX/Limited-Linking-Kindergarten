package basic;

public class Game {
	private Board board;
	private Timer timer;
	private boolean is_paused;
	public Game(int size,int time) throws WrongGameSizeException {
		timer=Timer.getInstance();
		timer.setUp(time);
		board=new Board(size);
		is_paused=false;
	}
	public void start() throws StartErrorException {
		timer.start();
		board.generateAll();
		board.show();
		is_paused=false;
	}
	public void pause() throws PauseErrorException {
		timer.pause();
		is_paused=true;
	}
	public void restart() throws RestartErrorException {
		
		timer.restart();
		is_paused=false;
	}
	public void terminate() throws TerminateErrorException {
		timer.terminate();
		is_paused=false;	
	}
	public void cancelGrids(int x1, int y1, int x2, int y2) throws OutOfBoundException, CannotCancelException, TerminateErrorException {
		if(!is_paused) {	
				board.cancel(x1,y2,x2,y2);
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
		timer.show();
	}

}
