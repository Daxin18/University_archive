package pack;

import java.util.Iterator;

public class FiniteListStack<E>
{
	private OneWayLinkedListWithHead<E> T;
	private int index;
	private final int MAXsize = 10;
	
	public FiniteListStack()
	{
		this.T = new OneWayLinkedListWithHead<E>();
		index=0;
	}
	
	public boolean isFull()
	{
		if(index>=MAXsize)
			return true;
		return false;
	}
	public boolean isEmpty()
	{
		if(index>0)
			return false;
		return true;
	}
	public void push(E elem) throws FullStackException
	{
		if(!isFull())
			T.add(index++, elem);
		else
			throw new FullStackException();
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
		int temp=0;
		while(iter.hasNext() && temp<index)
		{
			System.out.println("- "+iter.next().toString());
			temp++;
		}
	}
	
}