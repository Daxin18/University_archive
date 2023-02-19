package pack;

public interface IQueue<E>
{
	 boolean isEmpty();
	 boolean isFull();
	 E dequeue() throws EmptyQueueException;
	 void enqueue(E elem);
	 int size(); //liczba elementow
	 E first() throws EmptyQueueException; //pierwszy element, bez usuwania
	 
	 
	 public class EmptyQueueException extends Exception
	 {
		 public static final long serialVersionUID = 12413513;
		 
		 public EmptyQueueException()
		 {
			 super("kolejka jest pusta");
		 }
	 }

}
