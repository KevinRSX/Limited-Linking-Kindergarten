package basic;

public class OutOfBoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public OutOfBoundException()
	{
		super("Row or column is out of bound");
	}
	public OutOfBoundException(String message)
	{
		super(message);
	}
}
