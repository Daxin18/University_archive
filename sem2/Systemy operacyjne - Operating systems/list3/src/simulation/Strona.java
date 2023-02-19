package simulation;
import java.util.*;

public class Strona implements Comparable
{
	private int numer;
	
	//konstruktor
	public Strona(int nr)
	{
		this.numer = nr;
	}
	//getery i setery
	public int getNumer()
	{
		return numer;
	}
	public void setNumer(int numer)
	{
		this.numer = numer;
	}
	
	//metody
	@Override
	public String toString()
	{
		return Integer.toString(numer);
	}
	@Override
	public int compareTo(Object o)
	{
		if(!(o instanceof Strona))
			throw new InputMismatchException();
		else
		{
			Strona s = (Strona) o;
			return this.numer-s.getNumer();
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.compareTo(o)==0;
	}
}
