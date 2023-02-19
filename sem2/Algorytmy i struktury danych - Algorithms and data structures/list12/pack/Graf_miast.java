package pack;
import java.util.*;

public class Graf_miast
{
	//klasy wewnetrzne
	private class Droga
	{
		private int to;	//miasto do ktorego prowadzi
		private int dist;	//odleglosc
		
		Droga(int to, int dist)
		{
			this.to = to;
			this.dist = dist;
		}
	}
	
	//pola
	private int liczbaMiast;
	private ArrayList<String> miasta; //lista miast/wierzcholkow (V)
	private ArrayList<LinkedList<Droga>> LS;	//Lista_Sasiedztwa
	//pola pomocnicze
	private final int WHITE=0, GREY=1, BLACK=2;
	
	
	
	//konstruktor
	public Graf_miast(int liczbaMiast)
	{
		this.liczbaMiast = liczbaMiast;
		this.miasta = new ArrayList<>();
		this.LS = new ArrayList<>();
		for(int i=0; i<liczbaMiast; i++)
		{
			LS.add(new LinkedList<>());
			miasta.add(null);
		}
	}

	
	//metody
	public boolean addMiasto(int number, String s)
	{
		if(number<0 || number>=liczbaMiast)
		{
			System.out.println("Podano zly numer miasta");
			return false;
		}
		miasta.set(number, s);
		return true;
	}
	public boolean addDroga(int from, int to, int dist)
	{
		if(from<0 || from>=liczbaMiast || to<0 || to>=liczbaMiast)
		{
			System.out.println("Podano zly numer miasta");
			return false;
		}
		Droga d = new Droga(to, dist);
		LS.get(from).add(d);
		return true;
	}
	
	public String LS_numerami()
	{
		StringBuilder S = new StringBuilder();
		S.append("Lista Sąsiedztwa (numerami miast):");
		for(int i=0; i<liczbaMiast; i++)
		{
			S.append("\n" + i + ":");
			for(int j=0; j<LS.get(i).size(); j++)
			{
				if(j!=0)
					S.append(",");
				S.append(" -->");
				S.append(LS.get(i).get(j).to);
			}
		}
		return S.toString();
	}
	public String LS_nazwami()
	{
		StringBuilder S = new StringBuilder();
		S.append("Lista Sąsiedztwa (nazwami miast):");
		for(int i=0; i<liczbaMiast; i++)
		{
			S.append("\n" + miasta.get(i) + ":");
			for(int j=0; j<LS.get(i).size(); j++)
			{
				S.append(" -->");
				S.append(miasta.get(LS.get(i).get(j).to));
			}
		}
		return S.toString();
	}
	public String LS_nazwami_odleglosciami()
	{
		StringBuilder S = new StringBuilder();
		S.append("Lista Sąsiedztwa (nazwami miast z odległościami):");
		for(int i=0; i<liczbaMiast; i++)
		{
			S.append("\n" + miasta.get(i) + ":");
			for(int j=0; j<LS.get(i).size(); j++)
			{
				S.append("   --"+LS.get(i).get(j).dist+"km-->");
				S.append(miasta.get(LS.get(i).get(j).to));
			}
		}
		return S.toString();
	}
	
	public void showMiasta()
	{
		System.out.println("Miasta (numer - miasto):");
		for(int i=0; i<liczbaMiast; i++)
		{
			System.out.println(i + " - " + miasta.get(i));
		}
		System.out.println();
	}
	
	public void Dijkstry(int idx)
	{
		ArrayList<String> Q = new ArrayList<>(miasta);
		ArrayList<Integer> S = new ArrayList<>();
		int[] d = new int[liczbaMiast];	//odleglosc danego wierzcholka od wierzcholka idx
		int[] p = new int[liczbaMiast];	//poprzednik danego wierzcholka 
		//szykowanie tablic
		for(int i=0; i<liczbaMiast; i++)
		{
			if(i!=idx)
				d[i] = Integer.MAX_VALUE;
			else
				d[i] = 0;
			
			p[i] = -1;
		}
		//algorytm
		while(!Q.isEmpty())
		{
			int u = Integer.MAX_VALUE;
			String str = null;
			int ind = -1;
			for(int i=0; i<Q.size(); i++)
			{
				if(d[miasta.indexOf(Q.get(i))]<u)
				{
					u = d[miasta.indexOf(Q.get(i))];
					str = Q.get(i);
					ind = i;
				}
			}
			if(ind == -1)
				break;
			Q.remove(ind);
			u = miasta.indexOf(str);
			S.add(u);
			for(Droga dr : LS.get(u))
			{
				int w = dr.to;
				if(d[w] > d[u] + dr.dist)
				{
					d[w] = d[u] + dr.dist;
					p[w] = u;
				}
			}
		}
		//cokolwiek tylko chcemy robić z tablicami, u mnie - wypisanie wszystkiego na mainie
		System.out.println("Z miasta " + miasta.get(idx) + "(" + idx + ")" + " można dostać się do:");
		for(int i=0; i<d.length; i++)
		{
			if(d[i] != 0 && d[i] != Integer.MAX_VALUE)
			{
				System.out.print("("+i+")"+miasta.get(i)+", odległość: "+d[i]+"km");
				//System.out.print(", poprzednik: "+p[i]); //<-- dopisac jesl chcemy zobaczyc trase
				System.out.println();
			}
		}
		
		
		//      do testow
		//System.out.println("\n");
		//System.out.println("Z miasta " + miasta.get(idx) + "(" + idx + ")" + " można dostać się do:");
		//for(int i=0; i<d.length; i++)
		//{
		//	System.out.println("("+i+")"+miasta.get(i)+", odległość: "+d[i]+", poprzednik: "+p[i]);
		//}
	}
	
	public void DFS_print()
	{
		System.out.println("Miasta w kolejności odwiedzania w DFS:");
		
		int[] t = new int[liczbaMiast]; //tablica czasu wejścia
		int[] f = new int[liczbaMiast]; //tablica czasu wyjścia
		int[] color = new int[liczbaMiast]; //tablica kolorow
		int[] p = new int[liczbaMiast]; //tablica poprzednikow
		
		for(int i=0; i<liczbaMiast; i++)
		{
			color[i] = WHITE;
			p[i] = -1;
		}
		int time = 0;
		
		for(int i=0; i<liczbaMiast; i++)
		{
			if(color[i] == WHITE)
				DFS_visit_print(i, color, p, t, f, time);
		}
	}
	private void DFS_visit_print(int u, int[] color, int[] p, int[] t, int[] f, int time)
	{
		System.out.println("("+u+")"+miasta.get(u));
		color[u] = GREY;
		time+=1;
		t[u] = time;
		for(int i=0; i<LS.get(u).size(); i++)
		{
			int v = LS.get(u).get(i).to;
			if(color[v]==WHITE)
			{
				p[v] = u;
				DFS_visit_print(v, color, p, t, f, time);
			}
		}
		color[u] = BLACK;
		f[u] = time+=1;
	}
}
