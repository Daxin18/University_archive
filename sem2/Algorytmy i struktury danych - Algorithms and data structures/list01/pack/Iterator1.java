package pack;
import java.util.*;

public class Iterator1<T> implements Iterator<T>
{
		private T[] tab;
		private int position = 0;
		
		//konstruktor
		public Iterator1(T[] tab)
		{
		this.tab = tab;
		}
		
		//metody
		public boolean hasNext()
		{
			return position<tab.length;
		}
		
		public T next() throws NoSuchElementException
		{
			if(hasNext())
				return tab[position++];
			else
				throw new NoSuchElementException();
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
}
