package basic;

public class TimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TimeException()
	{
		super("time is out of bound");
	}
	public TimeException(String message)
	{
		super(message);
	}

}
