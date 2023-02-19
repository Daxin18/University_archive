package pack;

public class Zadanie3<T> implements IStack<T>
{
	private static final int def_size = 8;
	private T[] tab;
	private int index;	
	
	//konstruktory
	@SuppressWarnings("unchecked")
	public Zadanie3()
	{
		this.tab = (T[]) (new Object[def_size]);
		this.index = 0;
	}
	@SuppressWarnings("unchecked")
	public Zadanie3(int size)
	{
		this.tab = (T[]) (new Object[size]);
		this.index = 0;
	}
	
	//metody
	public boolean isFull()
	{
		return (index == tab.length); 
	}
	public boolean isEmpty()
	{
		return (index == 0);
	}
	public T pop() throws EmptyStackException
	{
		if(isEmpty())
			throw new EmptyStackException();
		else
			return tab[--index];
	}
	public void push(T el)
	{
		if(isFull())
		{
			for(int i=0; i<tab.length-1; i++)
			{
				tab[i] = tab[i+1];
			}
			tab[index-1] = el;
		}
		else
			tab[index++] = el;
	}
	public int elements()
	{
		return index;
	}
	public T top() throws EmptyStackException
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
			return tab[index-1];
	}
	public void showStack()
	{
		for(int i=index-1; i>=0; i--)
			System.out.println(tab[i]);
	}
	public int stackSize()
	{
		return tab.length;
	}
}