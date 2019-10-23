package basic;

public class StartErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StartErrorException()
	{
		super("Can not start");
	}
	public StartErrorException(String message)
	{
		super(message);
	}

}
