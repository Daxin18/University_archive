package pack;

public class IntIterator
{
	private int[] T;
	private int index=0;
	
	public IntIterator(int[] T)
	{
		this.T = T;
	}
	
	public boolean hasNext()
	{
		return index<T.length;
	}
	public int next()
	{
		return T[index++];
	}
}
