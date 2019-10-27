package basic;

public class TimerPauseErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public TimerPauseErrorException() {
		super("Timer can not pause correctly.");
	}
	
	public TimerPauseErrorException(String message)	{
		super(message);
	}
}
