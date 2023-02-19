package pack;

public class ArrayStack<T> implements IStack<T>
{
	private T[] tab;
	private int index;
	
	//konstruktory
	@SuppressWarnings("unchecked")
	public ArrayStack()
	{
		this.tab = (T[]) (new Object[10]);
		this.index = 0;
	}
	@SuppressWarnings("unchecked")
	public ArrayStack(int size)
	{
		this.tab = (T[]) (new Object[size]);
		this.index = 0;
	}

	//metody
	public boolean isEmpty()
	{
		return index==0?true:false;
	}
	public boolean isFull()
	{
		return index==tab.length?true:false;
	}
	public void push(T element) throws FullStackException
	{
		if(!(isFull()))
				tab[index++]=element;
		else
			throw new FullStackException();
	}
	public T pop() throws EmptyStackException
	{
		if(!(isEmpty()))
			return tab[--index];
		else
			throw new EmptyStackException();
	}
	public int size()
	{
		return index;
	}
	public T top() throws EmptyStackException
	{
		if(!(isEmpty()))
			return tab[index-1];
		else
			throw new EmptyStackException();
	}

	//odwracanie stosu
	public ArrayStack<T> odwroconyStos()
	{
		ArrayStack<T> odwrocony = new ArrayStack<>(this.tab.length);
		try
		{
			for(int i=tab.length-1; i>=0; i--)
			{
				odwrocony.push(tab[i]);
			}
		}
		catch(Exception e)
		{
			System.out.println("Gdzies sie wywalilo, ale nie mam pojecia gdzie");
		}
		return odwrocony;
	}
}
