package timer;

public class TimerStartErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimerStartErrorException() {
		super("Timer can not start correctly.");
	}
}
