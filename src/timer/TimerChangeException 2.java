package timer;

public class TimerChangeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimerChangeException() {
		super("Timer time can not be changed correctly.");
	}
	
	public TimerChangeException(String message) {
		super(message);
	}
}
