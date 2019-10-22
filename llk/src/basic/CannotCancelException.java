package basic;

public class CannotCancelException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CannotCancelException()
	{
		super("Blocks can not be cancelled");
	}
	public CannotCancelException(String message)
	{
		super(message);
	}
}
