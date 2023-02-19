package pack;
import java.util.*;

public class Disk
{
	private final int min = 1;
	private final int max;
	private int position;
	public static final int DEADLINE_RANGE=1;
	
	//dysk domyslnie startuje z pozycji 1
	public Disk()
	{
		this.max = 150;
		this.resetPosition();
		
	}
	public Disk(int max)
	{
		this.max = max;
		this.resetPosition();
	}
	
	//getery
	public int getMax()
	{
		return max;
	}
	public int getPosition()
	{
		return position;
	}
	//seter
	public void resetPosition()
	{
		this.position = max/2;
	}
	public void getToTheFirst()
	{
		this.position = 1;
	}
	/*
	============================
	 			metody
	============================
	*/
	//metody szukajace
	public int findClosestRight(ArrayList<Request> A)
	{
		int ind=-1;
		int minPos=max;
		for(Request r:A)
		{
			if(r.getPosition()>=position && distance(r)<minPos)
			{
				ind=A.indexOf(r);
				minPos=distance(r);
			}
		}
		return ind;
	}
	public int findClosestRight(ArrayList<Request> A, int deadline)
	{
		int ind=-1;
		int minPos=max;
		for(Request r:A)
		{
			if(r.getPosition()>=position && distance(r)<minPos && r.getDeadline()==deadline)
			{
				ind=A.indexOf(r);
				minPos=distance(r);
			}
		}
		return ind;
	}
	public int findClosestLeft(ArrayList<Request> A)
	{
		int ind=-1;
		int minPos=max;
		for(Request r:A)
		{
			if(r.getPosition()<=position && distance(r)<minPos)
			{
				ind=A.indexOf(r);
				minPos=distance(r);
			}
		}
		return ind;
	}
	public int findClosestLeft(ArrayList<Request> A, int deadline)
	{
		int ind=-1;
		int minPos=max;
		for(Request r:A)
		{
			if(r.getPosition()<=position && distance(r)<minPos && r.getDeadline()==deadline)
			{
				ind=A.indexOf(r);
				minPos=distance(r);
			}
		}
		return ind;
	}
	public Request findClosest(ArrayList<Request> A)
	{
		int ind =-1;
		int minPos=max;
		for(Request r:A)
		{
			if(distance(r)<minPos)
			{
				minPos=distance(r);
				ind=A.indexOf(r);
			}
		}
		if(ind!=-1)
			return A.get(ind);
		else
			return null;
	}

	
	//metoda zwracajaca dystans zadania od glowicy
	public int distance(Request req)
	{
		if(req==null) return 0;
		int x = req.getPosition();
		if(position>x)
			return position-x;
		else
			return x-position;
	}
	
	//metoda handle zwraca odleglosc (w blokach), o jaka zostala przesunieta glowica (?)
	public int handle(Request req)
	{
		if(req==null)
			return 0;
		int x = req.getPosition();
		int pos = position;
		this.position = x;
		if(pos>x)
			return pos-x;
		else
			return x-pos;
	}
	
	//FCFS (zwraca ilosc przesuniec glowicy (sume odleglosci z handle))
	public int FCFS(ArrayList<Request> A)
	{
		int x=0;
		Iterator<Request> iter = A.iterator();
		Queue<Request> q = new Queue<>();
		while (iter.hasNext())
		{
			q.enqueue(iter.next());
		}
		while(!q.isEmpty())
		{
			x+=this.handle(q.dequeue());
		}
		this.resetPosition();
		return x;
	}
	
	//SSTF (zwraca ilosc przesuniec glowicy (sume odleglosci z handle))
	public int SSTF(ArrayList<Request> A)
	{
		int x=0;
		ArrayList<Request> temp = new ArrayList<>();
		for(Request r:A)
		{
			temp.add(r);
		}
		while(!temp.isEmpty())
		{
			Request r = findClosest(temp);
			temp.remove(r);
			x+=handle(r);
		}
		this.resetPosition();
		return x;
	}
	
	//SCAN (zwraca ilosc przesuniec glowicy (sume odleglosci z handle))
	public int SCAN(ArrayList<Request> A)
	{
		int x=0;
		ArrayList<Request> temp = new ArrayList<>();
		for(Request r:A)
		{
			temp.add(r);
		}
		boolean right = true;
		int r;
		while(!temp.isEmpty())
		{
			if(right)
			{
				r = findClosestRight(temp);
				if(r==-1)
				{
					x+=handle(new Request(max));
					right = false;
				}
				else
				{
					x+=handle(temp.get(r));
					temp.remove(r);
				}
			}
			else
			{
				r = findClosestLeft(temp);
				if(r==-1)
				{
					x+=handle(new Request(min));
					right = true;
				}
				else
				{
					x+=handle(temp.get(r));
					temp.remove(r);
				}
			}
		}
		this.resetPosition();
		return x;
	}

	//C-SCAN (zwraca ilosc przesuniec glowicy (sume odleglosci z handle + ewentualnych powrotów na start))
	public int CSCAN(ArrayList<Request> A)
	{
		int x=0;
		ArrayList<Request> temp = new ArrayList<>();
		for(Request r:A)
		{
			temp.add(r);
		}
		int r;
		while(!temp.isEmpty())
		{
			r = findClosestRight(temp);
			if(r==-1)
			{
				x+=handle(new Request(max));
				this.getToTheFirst();
				x+=max-1;
			}
			else
			{
				x+=handle(temp.get(r));
				temp.remove(r);
			}
		}
		this.resetPosition();
		return x;
	}
	
	//EDF (zwraca ilosc przesuniec glowicy (sume odleglosci z handle))
	public int EDF(ArrayList<Request> A)
	{
		int x=0;
		Queue<Request> normal = new Queue<>();
		Queue<Request> deadline = new Queue<>();
		
		for(Request r:A)
		{
			if(r.getDeadline()==0)	normal.enqueue(r);
		}
		
		for(int i=1; i<DEADLINE_RANGE;i++)
		{
			for(Request r:A)
			{
				if(r.getDeadline()==i) deadline.enqueue(r);
			}
		}
		
		while(!deadline.isEmpty())
			x+=handle(deadline.dequeue());
		
		while(!normal.isEmpty())
			x+=handle(normal.dequeue());
		
		this.resetPosition();
		return x;
	}
	
	//FDSCAN (zwraca ilosc przesuniec glowicy (sume odleglosci z handle))
	public int FDSCAN(ArrayList<Request> A)
	{
		int x=0;
		ArrayList<Request> norm_temp = new ArrayList<>();
		ArrayList<Request> dead_temp = new ArrayList<>();
		for(Request r:A)
		{
			if(r.getDeadline()==0) norm_temp.add(r);
		}
		
		for(int i=1; i<DEADLINE_RANGE;i++)
		{
			for(Request r:A)
			{
				if(r.getDeadline()==i) dead_temp.add(r);
			}
		}
		boolean right = true;
		ArrayList<Request> dead_temp_2 = new ArrayList<>();
		int r;
		for(int i=1; i<DEADLINE_RANGE; i++)
		{
			for(int j=0; j<dead_temp.size(); j++)
			{
				if(dead_temp.get(j).getDeadline()<=i)
				{
					if(dead_temp.get(j).getDeadline()==i)
						dead_temp_2.add(dead_temp.get(j));
				}
				else
					break;
			}
			while(!dead_temp_2.isEmpty())
			{
				if(right)
				{
					r = findClosestRight(dead_temp_2,i);
					if(r==-1)
					{
						x+=handle(new Request(max));
						right = false;
					}
					else
					{
						x+=handle(dead_temp_2.get(r));
						dead_temp_2.remove(r);
					}
				}
				else
				{
					r = findClosestLeft(dead_temp_2,i);
					if(r==-1)
					{
						x+=handle(new Request(min));
						right = true;
					}
					else
					{
						x+=handle(dead_temp_2.get(r));
						dead_temp_2.remove(r);
					}
				}
			}
		}
		while(!norm_temp.isEmpty())
		{
			if(right)
			{
				r = findClosestRight(norm_temp);
				if(r==-1)
				{
					x+=handle(new Request(max));
					right = false;
				}
				else
				{
					x+=handle(norm_temp.get(r));
					norm_temp.remove(r);
				}
			}
			else
			{
				r = findClosestLeft(norm_temp);
				if(r==-1)
				{
					x+=handle(new Request(min));
					right = true;
				}
				else
				{
					x+=handle(norm_temp.get(r));
					norm_temp.remove(r);
				}
			}
		}
		//x/=DEADLINE_RANGE;
		//x+=800;
		
		
		this.resetPosition();
		return x;
	}
	
}
