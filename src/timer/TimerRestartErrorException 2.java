package timer;

public class TimerRestartErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public TimerRestartErrorException()	{
		super("Timer can not restart correctly.");
	}
	
	public TimerRestartErrorException(String message) {
		super(message);
	}
}
