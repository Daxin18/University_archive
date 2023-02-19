package pack;

public interface IStack<T>
{
	boolean isEmpty();
	boolean isFull();
	T pop() throws EmptyStackException;	//zwroci i usunie element z gory
	void push(T el) throws FullStackException;	//nowy element
	int elements();	//ilosc elementow na stosie
	T top() throws EmptyStackException;	//element z gory, bez usuwania
	
	//dodatkowo na potrzeby prezentacji kodu
	void showStack();
	int stackSize();
}
