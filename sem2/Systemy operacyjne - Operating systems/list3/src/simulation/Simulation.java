package simulation;
import java.util.*;
import structures.*;
import main.*;

public class Simulation
{
	private int pamiecWirtualna; //strony
	private int pamiecFizyczna; //ramki
	private ArrayList<Strona> strony;
	private int zakres;	//zakres numerow stron
	
	//pomocnicze pola
	private static final Random rand = new Random();
	private int bound;	//=zakres*boundRatio
	private int yBound;	//=bound*yBoundRatio
	
	//private final double yBoundRatio = 2;	// im wiecej tym mniej stron w okolicy x
	//private final double boundRatio = 0.1;	// 0<boundRatio<1 im wiecej tym wiekszy zakres "okolicy x"
	
	//konstruktor
	public Simulation(int liczbaStron, int liczbaRamek, int zakres)
	{
		this.pamiecWirtualna = liczbaStron;
		this.pamiecFizyczna = liczbaRamek;
		this.zakres = zakres;
		this.strony = new ArrayList<>(liczbaStron);
		this.bound = (int) Math.round(zakres*Main.boundRatio);
		this.yBound = (int) Math.round(bound*Main.yBoundRatio);
	}
	
	//getery i setery
	public int getPamiecWirtualna()
	{
		return pamiecWirtualna;
	}
	public void setPamiecWirtualna(int pamiecWirtualna)
	{
		this.pamiecWirtualna = pamiecWirtualna;
	}
	public int getPamiecFizyczna()
	{
		return pamiecFizyczna;
	}
	public void setPamiecFizyczna(int pamiecFizyczna)
	{
		this.pamiecFizyczna = pamiecFizyczna;
	}
	public ArrayList<Strona> getStrony()
	{
		return strony;
	}
	public void setStrony(ArrayList<Strona> strony)
	{
		this.strony = strony;
	}
	public int zakres()
	{
		return zakres;
	}
	public void setZakres(int zakres)
	{
		this.zakres = zakres;
	}
	
	/*
	======================================
					metody
	======================================
	*/
	
	//generowanie stron/ramek itp
	public void generate()
	{
		int x;	//losowy numer ramki
		int y;	//numer ramki w okolicy x (w okolicy +-bound)
		for(int i=0; i<pamiecWirtualna; i++)
		{
			strony.add(new Strona(x = rand.nextInt(zakres)+1));
			System.out.println("dodano x"); // <-- odkomentowac jesli chcemy sprawdzic generowanie
			y = rand.nextInt(yBound) +x - yBound/2;
			while(y<zakres && y>0 && y>x - bound && y<x + bound && i<pamiecWirtualna-1)
			{
				strony.add(new Strona(y));
				i++;
				System.out.println("dodano strone w okolicy x"); //<--odkomentowac jesli chcemy sprawdzic generowanie
				y = rand.nextInt(yBound) +x - yBound/2;
			}
		}
	}
	
	//pomocnicze wyswietlanie
	public void wyswietlStrony()
	{
		System.out.print("[");
		for(Strona s: strony)
		{
			System.out.print(s.toString()+", ");
		}
		System.out.println("]");
	}
	
	//FIFO
	//ramki sa kolejka stron - latwiejsza implementacja
	public int FIFO()
	{
		int error=0;
		LinkedQueue<Strona> frames = new LinkedQueue<>();
		LinkedQueue<Strona> pages = new LinkedQueue<>(strony);
		try
		{
			while(!pages.isEmpty())
			{
				Strona page = pages.dequeue();
				if(!frames.contains(page))
				{
					if(frames.size()<pamiecFizyczna) //nie ma ramki i mamy wolne miejsce
					{
						frames.enqueue(page);
						error++;
					}
					else	//nie ma ramki ani miejsca - trzeba zastapic
					{
						frames.dequeue();
						frames.enqueue(page);
						error++;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return error;
	}
	
	//OPT
	//ramki jako Arraylista stron
	public int OPT()
	{
		int error=0;
		ArrayList<Strona> frames = new ArrayList<>();
		ArrayList<Strona> pages = new ArrayList<>(strony);
		ArrayList<Integer> future = new ArrayList<>();	//ArrayLista pomocnicza, przechowuje moment w przyszlosci, w ktorym bedziemy potrzebowac strony
		for(int i=0; i<pamiecFizyczna; i++)
		{
			future.add(0);
		}
		while(!pages.isEmpty())
		{
			Strona page = pages.remove(0);
			if(!frames.contains(page))
			{
				if(frames.size()<pamiecFizyczna)
				{
					frames.add(page);
					error++;
				}
				else
				{
					for(int i=0; i<pamiecFizyczna; i++)
					{
						future.set(i, 0);
					}
					for(int j=0; j<pamiecFizyczna; j++)
					{
						Strona p = frames.get(j);
						int x=pages.size();
						for(int i=0; i<pages.size(); i++)
						{
							if(pages.get(i).compareTo(p)==0)
							{
								x=i;
								break;
							}
						}
						future.set(j, x);
					}
					
					int max = -1;
					int idxMax = -1;
					for(int i=0; i<future.size(); i++)
					{
						if(future.get(i)>max)
						{
							max=future.get(i);
							idxMax=i;
						}
					}
					
					frames.set(idxMax, page);
					error++;
				}
			}
		}
		return error;
	}
	
	//LRU
	//ramki jako Arraylista stron
	public int LRU()
	{
		int error=0;
		ArrayList<Strona> frames = new ArrayList<>();
		ArrayList<Strona> pages = new ArrayList<>(strony);
		ArrayList<Strona> past = new ArrayList<>();	//arraylista pamietajaca uzyte strony
		while(!pages.isEmpty())
		{
			Strona page = pages.remove(0);
			if(!frames.contains(page))
			{
				if(frames.size()<pamiecFizyczna)
				{
					frames.add(page);
					error++;
				}
				else
				{
					int max = -1;
					int idxMax = -1;
					for(int j=0; j<pamiecFizyczna; j++)
					{
						Strona p = frames.get(j);
						for(int i=past.size()-1; i>=0; i--)
						{
							if(past.get(i).equals(p))
							{
								if(past.size()-i>max)
								{
									max=past.size()-i;
									idxMax=j;
								}
								break;
							}
						}
					}
					frames.set(idxMax, page);
					error++;
				}
			}
			past.add(page);
		}
		return error;
	}
	
	//aLRU
	//ramki jako ArrayLista stron
	public int aLRU()
	{
		int error=0;
		ArrayList<Strona> frames = new ArrayList<>();
		ArrayList<Strona> pages = new ArrayList<>(strony);
		ArrayList<Integer> secondChance = new ArrayList<>();
		while(!pages.isEmpty())
		{
			Strona page = pages.remove(0);
			if(!frames.contains(page))
			{
				if(frames.size()<pamiecFizyczna)
				{
					frames.add(page);
					secondChance.add(1);
					error++;
				}
				else
				{
					for(int i=0; i<pamiecFizyczna; i++)
					{
						if(secondChance.get(i)==1)
							secondChance.set(i, 0);
						else
						{
							frames.set(i, page);
							error++;
							secondChance.set(i, 1);
							break;
						}
					}
				}
			}
			else	//jesli strona jest w pamieci musimy jej "odnowic" druga szanse
			{
				secondChance.set(frames.indexOf(page), 1);
			}
		}
		return error;
	}
	
	//RAND
	//ramki jako Arraylista stron
	public int RAND()
	{
		int error=0;
		ArrayList<Strona> frames = new ArrayList<>();
		ArrayList<Strona> pages = new ArrayList<>(strony);
		while(!pages.isEmpty())
		{
			Strona page = pages.remove(0);
			if(!frames.contains(page))
			{
				if(frames.size()<pamiecFizyczna)
				{
					frames.add(page);
					error++;
				}
				else
				{
					frames.set(rand.nextInt(pamiecFizyczna), page);
					error++;
				}
			}
		}
		return error;
	}
	
}
