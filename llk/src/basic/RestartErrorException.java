package basic;

public class RestartErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RestartErrorException()
	{
		super("Can not restart");
	}
	public RestartErrorException(String message)
	{
		super(message);
	}

	
}
