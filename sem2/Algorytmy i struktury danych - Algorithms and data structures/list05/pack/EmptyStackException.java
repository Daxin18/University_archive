package pack;

public class EmptyStackException extends Exception
{
	private static final long serialVersionUID = 1234123890;
	
	public EmptyStackException()
	{
		super("Stos jest pusty");
	}
}
