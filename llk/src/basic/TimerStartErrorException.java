package basic;

public class TimerStartErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimerStartErrorException() {
		super("Timer can not start correctly.");
	}
	
	public TimerStartErrorException(String message) {
		super(message);
	}
}
