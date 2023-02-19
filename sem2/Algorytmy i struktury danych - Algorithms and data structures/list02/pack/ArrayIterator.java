package pack;
import java.util.*;

public class ArrayIterator<T> implements Iterator<T>
{
		private ArrayList<T> A;
		private int position = 0;
		
		//konstruktor
		public ArrayIterator(ArrayList<T> A1)
		{
			this.A = A1;
		}
		
		//metody
		public boolean hasNext()
		{
			return position<A.size();
		}
		
		public T next() throws NoSuchElementException
		{
			if(hasNext())
				return A.get(position++);
			else
				throw new NoSuchElementException();
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
}