package simulation;
import java.util.*;

public class Page implements Comparable
{
	private int number;
	private Proces proces;
	
	//konstruktor
	public Page(int n, Proces proces)
	{
		this.number = n;
		this.proces = proces;
	}
	
	//geter i seter
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int n)
	{
		this.number = n;
	}
	public Proces getProces()
	{
		return proces;
	}
	
	//metody
	public int compareTo(Object o)
	{
		if(!(o instanceof Page))
			throw new InputMismatchException();
		else
		{
			Page p = (Page) o;
			return number-p.getNumber();
		}
	}
	@Override
	public boolean equals(Object o)
	{
		return this.compareTo(o)==0;
	}
	@Override
	public String toString()
	{
		return Integer.toString(number);
	}
}
