package basic;

public class GameHasEndedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public GameHasEndedException() {
		super("Game has ended! Program exited.");
	}
	
	public GameHasEndedException(String message) {
		super(message);
	}
}