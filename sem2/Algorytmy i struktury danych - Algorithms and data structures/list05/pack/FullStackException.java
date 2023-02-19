package pack;

public class FullStackException extends Exception
{
	private static final long serialVersionUID = 1234123890;
	
	public FullStackException()
	{
		super("Stos jest pelny");
	}
}