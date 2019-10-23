package basic;

public class TerminateErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TerminateErrorException()
	{
		super("Can not terminate");
	}
	public TerminateErrorException(String message)
	{
		super(message);
	}

}
