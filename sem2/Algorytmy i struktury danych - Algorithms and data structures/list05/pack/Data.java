package pack;
import java.io.*;

public class Data implements Serializable
{
	private static final long serialVersionUID = 1211567890;
	
	private int day;
	private int month;
	private int year;
	
	//konstruktor
	public Data(int dd, int mm, int yyyy)
	{
		this.day = dd;
		this.month = mm;
		this.year = yyyy;
	}
	
	//getery
	public int getDay()
	{
		return day;
	}
	public int getMonth()
	{
		return month;
	}
	public int getYear()
	{
		return year;
	}
	
	//setery
	public void setDay(int x)
	{
		this.day = x;
	}
	public void setMonth(int x)
	{
		this.month= x;
	}
	public void setYear(int x)
	{
		this.year = x;
	}
	
	//metody
	@Override
	public String toString()
	{
		return String.format("%02d.%02d.%04d", day, month, year);
	}
	
	
	
}
