package pack;

public interface IStack<T>
{
	public boolean isEmpty();
	public boolean isFull();
	public void push(T t) throws FullStackException;
	public T pop() throws EmptyStackException;
	public int size();
	public T top() throws EmptyStackException;
}
