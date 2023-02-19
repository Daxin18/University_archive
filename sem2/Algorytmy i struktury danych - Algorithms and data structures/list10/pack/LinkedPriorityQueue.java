package pack;
import java.util.*;

public class LinkedPriorityQueue<E extends Comparable<E>> implements IQueue<E>
{
	private OneWayLinkedListWithHeadAndTail<E> T;
	
	public LinkedPriorityQueue()
	{
		this.T = new OneWayLinkedListWithHeadAndTail<>();
	}
	
	public boolean isEmpty()
	{
		return T.isEmpty();
	}
	public boolean isFull()
	{
		return false;
	}
	public E dequeue() throws EmptyQueueException
	{
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return T.remove(getIndexOfHighestPriorityElement());
	}
	public void enqueue(E elem)
	{
		T.add(elem);
	}
	public int size()
	{
		return T.size();
	}
	public E first() throws EmptyQueueException
	{
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return T.get(0);
	}
	public int getIndexOfHighestPriorityElement()
	{
		if(T.isEmpty())
			return -1;
		Iterator<E> iter = T.iterator();
		int counter=0, maxPos=0;
		E value = iter.next();
		E elem = null;
		while(iter.hasNext())
		{
			counter++;
			elem = iter.next();
			if(elem.compareTo(value)>0)
			{
				maxPos=counter;
				value=elem;
			}
		}
		return maxPos;
	}
	
}
