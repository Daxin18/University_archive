package pack;
import java.util.*;

public class Graf
{
	//krawedz
	private class Kr implements Comparable<Kr>
	{
		int from; //z ktorego
		int to; //do ktorego
		int dist; //odleglosc
		
		Kr(int from, int to, int dist)
		{
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Kr k)
		{
			return this.dist - k.dist;
		}
		@Override
		public String toString()
		{
			return ((from+1) + " - " + (to+1) + " : "+ dist);
		}
	}
	
	private int COUNTER;
	private int n; //liczba lampionow
	private int m; //liczba polaczen
	private int[] lampiony;	//lista lampionow
	private ArrayList<Kr> krawedzie; //lista krawedzi
	
	public Graf(int n, int m)
	{
		this.COUNTER = 0;
		this.n = n;
		this.m = m;
		this.krawedzie = new ArrayList<>();
		this.lampiony = new int[n];
		//od razu zapisujemy lampiony (indeks 0 = lampion 1, indeks 1 = lampion 2 itd...)
		for(int i=0; i<n; i++)
			lampiony[i] = i+1;
	}
	
	public void addKrawedz(int i, int j, int k)
	{
		Kr kr = new Kr(i-1, j-1, k);
		COUNTER++;
		krawedzie.add(kr);
		
		if(COUNTER>m)
		{
			System.out.println("\n\nPodano wiecej krawedzi, niz przy deklarowaniu rozmiaru ich ilosci w konstruktorze!!!\n\n");
		}
	}
	
	public int Kruskal()
	{
		int x = 0;
		ArrayList<Kr> S = new ArrayList<>(krawedzie); //wszystkie krawedzie
		ArrayList<Kr> T = new ArrayList<>(); //Krawedzie MST
		
		S.sort(null); //posortuje zgodnie z metoda compareTo
		
		while(T.size()<n-1 && !S.isEmpty())
		{
			Kr temp = S.remove(0);
			if(T.isEmpty())
			{
				T.add(temp);
				//System.out.println("Warunek 1, dodano: "+ temp.toString());
			}
			else
			{
				//System.out.println(temp.toString());
				if(checkFor(T, temp.from, temp.to, -1))
				{
					T.add(temp);
					//System.out.println("Warunek 2, dodano: "+ temp.toString());
				}
			}
		}
		
		for(Kr k: T)
		{
			x += k.dist;
		}
		return x;
	}
	
	private boolean checkFor(ArrayList<Kr> T, int t, int to, int prev)
	{
		if(t == to)
			return false;
		boolean result = true;
		for(int i=0; i<T.size(); i++)
		{
			if(T.get(i).from == t && T.get(i).to!=prev)
			{
				result = result && checkFor(T, T.get(i).to, to, t);
			}
			if(T.get(i).to == t && T.get(i).from!=prev)
			{
				result = result && checkFor(T, T.get(i).from, to, t);
			}
		}
		return result;
	}
}
