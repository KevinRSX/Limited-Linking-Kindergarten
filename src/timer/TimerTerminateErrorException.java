package timer;

public class TimerTerminateErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimerTerminateErrorException() {
		super("Timer can not terminate correctly.");
	}
}
