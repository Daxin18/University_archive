package pack;
import java.util.*;

public class Request
{
	private int position;
	private int deadline;
	
	//konstruktory
	public Request()
	{
		this.position = 1;
		this.deadline = 0;
	}
	public Request(int i)
	{
		this.position = i;
		this.deadline = 0;
	}
	public Request(int i, int dead)
	{
		this.position = i;
		this.deadline = dead;
	}
	
	//geter/seter
	public int getPosition()
	{
		return this.position;
	}
	public void setPosition(int i)
	{
		this.position = i;
	}
	public int getDeadline()
	{
		return this.deadline;
	}
	public void setDeadline(int i)
	{
		this.deadline = i;
	}
	
	//metody
	
	
	public static void generate(ArrayList<Request> A, int max)
	{
		Random rand = new Random();
		int x = rand.nextInt(max)+1;
		int d = rand.nextInt(Disk.DEADLINE_RANGE);
		while (x!=0)
		{
			A.add(new Request(x,d));
			x = rand.nextInt(max+1);
			d = rand.nextInt(Disk.DEADLINE_RANGE);
		}
	}
	public static void generate(ArrayList<Request> A, int max, int number)
	{
		Random rand = new Random();
		for(int i=0; i<number; i++)
		{
			A.add(new Request(rand.nextInt(max)+1,rand.nextInt(Disk.DEADLINE_RANGE)));
		}
	}
	@Override
	public String toString()
	{
		return String.valueOf(position);
	}
	
}
