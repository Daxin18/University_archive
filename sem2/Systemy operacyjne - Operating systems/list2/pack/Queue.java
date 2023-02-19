package pack;
import java.util.*;

public class Queue<E>
{
	private ArrayList<E> A;
	private int index=0;
	
	public Queue()
	{
		this.A = new ArrayList<E>();
	}
	public Queue(ArrayList<E> a1)
	{
		this.A = new ArrayList<E>();
	}
		
	public boolean isEmpty()
	{
		return index==A.size();
	}
	public boolean isFull()
	{
		return false;
	}
	public void enqueue(E elem)
	{
		A.add(elem);
	}
	public E dequeue()
	{
		if(!isEmpty())
			return A.get(index++);
		else
			return null;
	}
	public int size()
	{
		int x=0;
		for(int i=index; A.get(i)!=null; i++)
		{
			x++;
		}
		return x;
	}
	public E first() throws EmptyQueueException
	{
		if(!isEmpty())
			return A.get(index);
		else
			return null;
	}
}
