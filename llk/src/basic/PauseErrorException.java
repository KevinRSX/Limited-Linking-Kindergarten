package basic;

public class PauseErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PauseErrorException()
	{
		super("Can not pause");
	}
	public PauseErrorException(String message)
	{
		super(message);
	}

}
