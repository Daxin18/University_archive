package pack;
import java.util.*;
import java.io.*;

public class TwoWayCycledListWithSentinel<E> extends AbstractList<E> implements Serializable
{
	private static final long serialVersionUID = 1234517890;
	
	private class Element implements Serializable
	{
		private static final long serialVersionUID = 1234567891;
		
		private E value;
		private Element next;
		private Element prev;
		
		//konstruktor elementu
		public Element(E data)
		{
			this.value=data;
		}
		
		//getery i setery
		public E getValue()
		{
			return value;
		}
		public void setValue(E value)
		{
			this.value = value;
		}
		public Element getNext()
		{
			return next;
		}
		public void setNext(Element next)
		{
			this.next = next;
		}
		public Element getPrev()
		{
			return prev;
		}
		public void setPrev(Element prev)
		{
			this.prev = prev;
		}
		
		//metody wstawiania i usuwania
		public void insertAfter(Element e)
		{
			e.setPrev(this);
			e.setNext(this.next);
			this.next.setPrev(e);
			this.setNext(e);
		}
		public void insertBefore(Element e)
		{
			e.setPrev(this.prev);
			e.setNext(this);
			this.prev.setNext(e);
			this.setPrev(e);
		}
		public void remove()
		{
			this.next.setPrev(this.prev);
			this.prev.setNext(this.next);
		}
	}
	
	Element sentinel=null;
	
	public TwoWayCycledListWithSentinel()
	{
		sentinel = new Element(null);
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
	}
	
	//metody
	public boolean isEmpty()
	{
		return sentinel.getNext()==sentinel;
	}
	
	@Override
	public void clear()
	{
		sentinel.setNext(sentinel);
		sentinel.setPrev(sentinel);
	}
	
	public int size()
	{
		int pos=0;
		Element actElem=sentinel.getNext();
		while(actElem!=sentinel)
		{
			pos++;
			actElem=actElem.getNext();
		}
		return pos;
	}
	
	private Element getElement(int index)
	{
		Element actElem=sentinel.getNext();
		while(index>0 && actElem!=sentinel)
		{
			index--;
			actElem=actElem.getNext();
		}
		if (actElem==sentinel)
			throw new IndexOutOfBoundsException();
		return actElem;
	}
	private Element getElement(E value)
	{
		Element actElem=sentinel.getNext();
		while(actElem!=sentinel && !value.equals(actElem.getValue()))
		{
			actElem=actElem.getNext();
		}
		if (actElem==sentinel)
			return null;
		return actElem;
	}
	
	@Override
	public boolean add(E e)
	{
		sentinel.insertBefore(new Element(e));
		return true;
	}
	@Override
	public boolean add(int index, E data)
	{
		if(index<0) return false;
		Element newElem=new Element(data);
		if(index==0)
		{
			sentinel.insertAfter(newElem);
			return true;
		}
		Element actElem=getElement(index-1);
		actElem.insertAfter(newElem);
		return true;
	}
	
	@Override
	public int indexOf(E data)
	{
		int pos=0;
		Element actElem=sentinel.getNext();
		while(actElem!=sentinel)
		{
			if(actElem.getValue().equals(data))
				return pos;
			pos++;
			actElem=actElem.getNext();
		}
		return -1;
	}
	
	@Override
	public boolean contains(E data)
	{
		return indexOf(data)>=0;
	}
	
	@Override
	public E get(int index)
	{
		Element actElem=getElement(index);
		return actElem.getValue();
	}	
	@Override
	public E set(int index, E data)
	{
		Element actElem=getElement(index);
		E elemData=actElem.getValue();
		actElem.setValue(data);
		return elemData;
	}
	
	@Override
	public E remove(int index)
	{
		Element actElem=getElement(index);
		actElem.remove();
		return actElem.getValue();
	}	
	@Override
	public boolean remove(E value)
	{
		Element actElem = getElement(value);
		if(actElem==null)
			return false;
		actElem.remove();
		return true;
	}
		
	private class InnerIterator implements Iterator<E>
	{
		Element actElem;
		
		public InnerIterator()
		{
			actElem=sentinel.getNext();
		}
		
		@Override
		public boolean hasNext()
		{
			return actElem!=sentinel;
		}
		
		@Override
		public E next()
		{
			E value=actElem.getValue();
			actElem=actElem.getNext();
			return value;
		}
	}
	@Override
	public Iterator<E> iterator()
	{
		return new InnerIterator();
	}
	
	@Override
	public ListIterator<E> listIterator() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
	
}