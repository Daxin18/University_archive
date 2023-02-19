package structures;
import java.util.*;

public class OneWayLinkedListWithHeadAndTail<E> extends AbstractList<E>
{
	private class Element
	{
		private E value;
		private Element next;
		
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
	}
	
	Element head=null;
	Element tail = null;
	
	//konstruktor listy, pusty, bo glowa i ogon odgornie sa nullami
	public OneWayLinkedListWithHeadAndTail()
	{
		
	}
	
	
	//metody
	public boolean isEmpty()
	{
		return head==null;
	}
	
	@Override
	public void clear()
	{
		head=null;
	}
	
	public int size()
	{
		int pos=0;
		Element actElem=head;
		while(actElem!=null)
		{
			pos++;
			actElem=actElem.getNext();
		}
		return pos;
	}
	
	private Element getElement(int index)
	{
		Element actElem=head;
		while(index>0 && actElem!=null)
		{
			index--;
			actElem=actElem.getNext();
		}
		return actElem;
	}
	
	@Override
	public boolean add(E e)
	{
		Element newElem = new Element(e);
		if(!isEmpty())
		{
			tail.setNext(newElem);
			tail=newElem;
			return true;
		}
		else
		{
			head = newElem;
			tail = newElem;
			return true;
		}
	}
	@Override
	public boolean add(int index, E data)
	{
		if(index<0) return false;
		if(index==size())
		{
			return this.add(data);
		}
		else
		{
			Element newElem=new Element(data);
			if(index==0)
			{
				newElem.setNext(head);
				head=newElem;
				return true;
			}
			Element actElem=getElement(index-1);
			if(actElem==null)
				return false;
			newElem.setNext(actElem.getNext());
			actElem.setNext(newElem);
			return true;
		}
	}
	
	@Override
	public int indexOf(E data)
	{
		int pos=0;
		Element actElem=head;
		while(actElem!=null)
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
		return actElem==null?null:actElem.getValue();
	}	
	@Override
	public E set(int index, E data)
	{
		Element actElem=getElement(index);
		if(actElem==null)
			return null;
		E elemData=actElem.getValue();
		actElem.setValue(data);
		return elemData;
	}
	
	@Override
	public E remove(int index)
	{
		if(head==null)
			return null;
		if(index==size()-1)
		{
			if(size()>=2)
			{
				Element temp = getElement(index-1);
				tail=temp;
				E retValue = temp.getNext().getValue();
				tail.setNext(null);
				return retValue;
			}
			else
			{
				E retValue = tail.getValue();
				tail = null;
				head = null;
				return retValue;
			}
		}
		else
		{
			if(index==0)
			{
				E retValue=head.getValue();
				head=head.getNext();
				return retValue;
			}
			Element actElem=getElement(index-1);
			if(actElem==null || actElem.getNext()==null)
				return null;
			E retValue=actElem.getNext().getValue();
			actElem.setNext(actElem.getNext().getNext());
			return retValue;
		}
	}	
	@Override
	public boolean remove(E value)
	{
		if(head==null)
			return false;
		if(head.getValue().equals(value))
		{
			head=head.getNext();
			return true;
		}
		if(tail.getValue().equals(value))
		{
			Element temp = getElement(size()-2);
			tail=temp;
			tail.setNext(null);
			return true;
		}
		else
		{
			Element actElem=head;
			while(actElem.getNext()!=null && !actElem.getNext().getValue().equals(value))
				actElem=actElem.getNext();
			if(actElem.getNext()==null)
				return false;
			actElem.setNext(actElem.getNext().getNext());
			return true;
		}
	}
		
	private class InnerIterator implements Iterator<E>
	{
		Element actElem;
		
		public InnerIterator()
		{
			actElem=head;
		}
		
		@Override
		public boolean hasNext()
		{
			return actElem!=null;
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