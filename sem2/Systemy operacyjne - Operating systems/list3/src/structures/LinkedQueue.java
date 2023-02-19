package structures;
import java.util.*;

public class LinkedQueue<E> implements IQueue<E>
{
	private OneWayLinkedListWithHeadAndTail<E> T;
	
	public LinkedQueue()
	{
		this.T = new OneWayLinkedListWithHeadAndTail<>();
	}
	public LinkedQueue(ArrayList<E> A)
	{
		this.T = new OneWayLinkedListWithHeadAndTail<>();
		for(E a:A)
			enqueue(a);
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
			return T.remove(0);
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
	public boolean contains(E elem)
	{
		return T.contains(elem);
	}
	
}
