package basic;

public class WrongGameSizeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public WrongGameSizeException()
	{
		super("Game size should be even and larger than zero");
	}
	public WrongGameSizeException(String message)
	{
		super(message);
	}
}
