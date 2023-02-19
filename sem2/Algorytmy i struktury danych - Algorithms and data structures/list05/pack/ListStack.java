package pack;
import java.util.*;

public class ListStack<E>
{
	private OneWayLinkedListWithHead<E> T;
	private int index;
	
	public ListStack()
	{
		this.T = new OneWayLinkedListWithHead<E>();
		index=0;
	}
	
	public boolean isEmpty()
	{
		if(index>0)
			return false;
		return true;
	}
	public void push(E elem)
	{
		T.add(index++,elem);
	}
	public E pop() throws EmptyStackException
	{
		if(!isEmpty())
			return T.get(--index);
		else
			throw new EmptyStackException();	
	}
	public int size()
	{
		return index;
	}
	public E top() throws EmptyStackException
	{
		if(!isEmpty())
			return T.get(index-1);
		else
			throw new EmptyStackException();	
	}
	
	public void showStack()
	{
		Iterator<E> iter = T.iterator();
		while(iter.hasNext())
		{
			System.out.println("- "+iter.next().toString());
		}
	}
	
}
