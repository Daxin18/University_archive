package simulation;
import java.util.*;

public class Simulation
{
	//ogolne
	private int bound; //zakres stron [0,bound]
	private int iloscProcesow;
	private int pamiecWirtualna; //ilosc stron
	private int pamiecFizyczna; //ilosc ramek
	private ArrayList<Proces> procesy; //procesy
	private ArrayList<Page> strony; //globalny ciag odwolan do stron
	
	public static int max_rozmiar_strefy;
	public static int min_rozmiar_strefy;
	
	public static int maxBlad; //max ilosc bledow stron
	public static int minBlad; //min ilosc bledow stron
	public static int deltaSCB; //co jaki czas sprawdzamy gledy (ile odwolan)
	
	public static int delta; //delta do MS
	
	//potrzebne do tworzenia procesow
	private int LUB;
	private int LLB;
	private int maxIloscStref;
	private int minIloscStref;
	private ArrayList<Integer> Probability = new ArrayList<>(); //do prawdopodobienstwa wylosowania procesu
	
	//pola pomocnicze
	private Random rand = new Random();
	private ArrayList<Page> past = new ArrayList<>(); //lista do LRU
	private ArrayList<ArrayList<Page>> frames = new ArrayList<>(); //arraylista arraylist ramek odpowiednich procesow
	
	private ArrayList<ArrayList<Page>> zbiorRoboczy = new ArrayList<>(); //arraylista arraylist stron - zbioru roboczego
	private ArrayList<Page> drugaObsluga = new ArrayList<>(); //arraylista trzymajaca odwolania "zbanowanych" procesow
	private ArrayList<Boolean> bany = new ArrayList<>(); //lista "zbanowanych" procesow
	
	private ArrayList<ArrayList<Integer>> PC = new ArrayList<>();	//"Process Counter"
	//roboczo nazwana arrayLista arrayList, ktora dla kazdego procesu przechowuje arrayliste, gdzie
	//.get(0) - counter/licznik ile juz dodano stron z danej strefy w procesie (zerowany przy przekroczeniu wielkosci)
	//.get(1) - wielkosc strefy (losowa, liczona na nowo przy kazdym przekroczeniu wielkosci)
	//.get(2) - strefa, z ktorej obecnie losujemy
	//.get(3) - ilosc ogolnych odwolan wygenerowanych przez proces - "counter odwolan"
	//.get(4) - ilosc przydzielonych ramek
	//.get(5) - ilosc bledow stron danego procesu
	//.get(6) - ilosc odwolan (do SCB)
	//.get(7) - ilosc bledow z deltaSCB poprzednich odwolan
	
	//konstruktor
	public Simulation(int liczbaStron, int liczbaRamek, int zakres, int liczbaProcesow, int LLB, int LUB, int minIloscStref, int maxIloscStref)
	{
		this.pamiecWirtualna = liczbaStron;
		this.pamiecFizyczna = liczbaRamek;
		this.bound = zakres;
		this.iloscProcesow = liczbaProcesow;
		this.LUB = LUB;
		this.LLB = LLB;
		this.maxIloscStref = maxIloscStref;
		this.minIloscStref = minIloscStref;
		this.procesy = generateProcesses();
		this.strony = generatePages();
	}

	public int getBound()
	{
		return bound;
	}
	public void setBound(int bound)
	{
		this.bound = bound;
	}

	public int getIloscProcesow()
	{
		return iloscProcesow;
	}
	public void setIloscProcesow(int iloscProcesow)
	{
		this.iloscProcesow = iloscProcesow;
	}

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

	public ArrayList<Proces> getProcesy()
	{
		return procesy;
	}
	public void setProcesy(ArrayList<Proces> procesy)
	{
		this.procesy = procesy;
	}

	public ArrayList<Page> getStrony()
	{
		return strony;
	}
	public void setStrony(ArrayList<Page> strony)
	{
		this.strony = strony;
	}

	public int getLUB()
	{
		return LUB;
	}
	public void setLUB(int lUB)
	{
		LUB = lUB;
	}

	public int getLLB()
	{
		return LLB;
	}
	public void setLLB(int lLB)
	{
		LLB = lLB;
	}

	/*
	=============================
				metody
	=============================
	*/
	//generowanie procesow
	private ArrayList<Proces> generateProcesses()
	{
		ArrayList<Proces> A = new ArrayList<>();
		int iloscStref;
		for(int i=0; i<iloscProcesow; i++)
		{
			iloscStref = rand.nextInt(maxIloscStref-minIloscStref) + minIloscStref;
			A.add(new Proces(bound, LUB, LLB, iloscStref));
			PC.add(new ArrayList<Integer>());
			PC.get(i).add(0);
			PC.get(i).add(rand.nextInt(max_rozmiar_strefy - min_rozmiar_strefy) + min_rozmiar_strefy);
			PC.get(i).add(-1);
			PC.get(i).add(0);
			PC.get(i).add(0);
			PC.get(i).add(0);
			PC.get(i).add(0);
			PC.get(i).add(0);
			zbiorRoboczy.add(new ArrayList<>());
			bany.add(false);
			if(i==0)
				Probability.add(A.get(0).getRozmiar());
			else
				Probability.add(A.get(i).getRozmiar() + Probability.get(i-1));
		}
		return A;
	}
	
	//generowanie stron (ciagu odwolan)
	private ArrayList<Page> generatePages()
	{
		ArrayList<Page> A = new ArrayList<>();
		int counter=0;
		int x, y, min, max;
		while(counter<pamiecWirtualna) //losujemy tyle stron, ile musimy obsluzyc
		{
			x = pickProcess(); //rand.nextInt(iloscProcesow); //wybieramy proces, ktory wygeneruje odwolanie
			if(PC.get(x).get(0)==0) //jesli counter dla tego procesu jest rowny 0
			{
				y = rand.nextInt(procesy.get(x).getIloscStref()); //losujemy numer strefy, z ktorej wybierzemy odwolania
				PC.get(x).set(2, y); //zapamietujemy wybor w PC
			}
			PC.get(x).set(0,  PC.get(x).get(0)+1); //zwiekszamy counter dla wybranego procesu
			PC.get(x).set(3,  PC.get(x).get(3)+1); //zwiekszamy counter odwolan dla wybranego procesu
			
			min = procesy.get(x).getStrefa(PC.get(x).get(2)).get(0); //wartosc minimalna w aktualnie opracowywanej strefie
			max = procesy.get(x).getStrefa(PC.get(x).get(2)).get(1); //wartosc maksymalna w aktualnie opracowywanej strefie
			
			A.add(new Page(rand.nextInt(max-min)+min, procesy.get(x))); //dodajemy wylosowana strone
			
			if(PC.get(x).get(0)==PC.get(x).get(1)) //sprawdzamy, czy doszlismy do maksymalnej wielkosci strefy, jesli tak
			{
				PC.get(x).set(0, 0);	//zerujemy ProcessCounter
				PC.get(x).set(1, rand.nextInt(max_rozmiar_strefy - min_rozmiar_strefy) + min_rozmiar_strefy); //ustawiamy nowa wielkosc strefy
				
				//System.out.println("Zmiana strefy dla: P"+(x+1)); //<--do testow
				
			}
			
			//System.out.println("P"+(x+1)+", S"+ PC.get(x).get(2)); //<--do testow
			
			counter++; //zwiekszamy counter (dla stron) o 1
		}
		return A;
	}
	
	//metody pomocnicze/do wizualizacji
	public void showProcesses()
	{
		System.out.println();
		for(int i=0; i<iloscProcesow; i++)
		{
			System.out.println("Proces "+ (i+1)+" (rozmiar "+procesy.get(i).getRozmiar()+"):");
			System.out.println(procesy.get(i).toString());
		}
	}
	public String pagesToString()
	{
		StringBuilder S = new StringBuilder();
		S.append("[");
		for(int i=0; i<strony.size(); i++)
		{
			S.append(strony.get(i)+", ");
		}
		S.append("]");
		return S.toString();
	}
	public void showRequestCount()
	{
		System.out.println("\nOdwolania poszczegolnych procesow w globalnym ciagu odwolan: ");
		for(int i=0; i<iloscProcesow; i++)
		{
			System.out.print("Proces "+ (i+1)+": ");
			System.out.println(PC.get(i).get(3));
		}
	}
	
	public void showPageFaults()
	{
		System.out.println("\nBledy stron kazdego procesu:");
		for(int i=0; i<iloscProcesow; i++)
		{
			System.out.print("Proces "+ (i+1)+": ");
			System.out.println(PC.get(i).get(5)+ ", przy "+PC.get(i).get(4)+" ramkach");
		}
	}
	
	private int pickProcess()
	{
		int r = rand.nextInt(Probability.get(Probability.size()-1))+1;
		int x = 0;
		for(int i=0; i<Probability.size()-1; i++)
		{
			if(i==0 && r<Probability.get(i))
				break;
			if(Probability.get(i)<r && Probability.get(i+1)>r)
			{
				x = i;
				break;
			}
			else
			{
				x = i+1;
			}
		}
		return x;
	}

	//dodaje strone do zbioru roboczego, zwraca true jesli zbior roboczy ma pelen rozmiar
	private boolean addPage(Page p)
	{
		int x = procesy.indexOf(p.getProces());
		if(zbiorRoboczy.get(x).size()==delta)
		{
			zbiorRoboczy.get(x).remove(0);
			zbiorRoboczy.get(x).add(p);
			return true;
		}
		else
		{
			zbiorRoboczy.get(x).add(p);
			if(zbiorRoboczy.get(x).size()<delta)
				return false;
			else
				return true;
		}
	}
	private int roboczyGetSize(int x)
	{
		ArrayList<Page> p = new ArrayList<>();
		for(int i=0; i<delta; i++)
		{
			if(!p.contains(zbiorRoboczy.get(x).get(i)))
				p.add(zbiorRoboczy.get(x).get(i));
		}
		return p.size();
	}
	private int uzywaneRamki()
	{
		int x=0;
		for(int i=0; i<procesy.size(); i++)
		{
			x+=PC.get(i).get(4);
		}
		return x;
	}
	
	/*
	==========================================
			algorytmy przydzialu ramek
	==========================================
	*/
	//rowny
	public int rowny()
	{
		int error = 0;
		frames = new ArrayList<>();
		ArrayList<Page> pages = new ArrayList<>(strony);
		past = new ArrayList<>();
		int x = pamiecFizyczna/iloscProcesow; //najpierw x pelni funkcje ilosci ramek
		//System.out.println(x);	//<-- do testow
		//int noerror = 0;	//<-- wszystko z tym jest do testow
		for(int i=0; i<iloscProcesow; i++)	//w tej petli przydzielamy procesom ramki
		{
			PC.get(i).set(4, x);
			PC.get(i).set(5, 0);
			frames.add(new ArrayList<>()); //oraz tworzymy ich ArrayListe ramek
		}
		//LRU
		while(!pages.isEmpty())
		{
			Page page = pages.remove(0);
			x = procesy.indexOf(page.getProces());
			if(!frames.get(x).contains(page))
			{
				if(frames.get(x).size()<PC.get(x).get(4))
				{
					frames.get(x).add(page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					error++;
				}
				else
				{
					int max = -1;
					int idxMax = -1;
					for(int j=0; j<PC.get(x).get(4); j++)
					{
						Page p = frames.get(x).get(j);
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
					frames.get(x).set(idxMax, page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					error++;
				}
			}
			past.add(page);
		}
		
		return error;
	}
	
	//proporcjonalny
	public int proporcjonalny()
	{
		int error = 0;
		frames = new ArrayList<>();
		ArrayList<Page> pages = new ArrayList<>(strony);
		past = new ArrayList<>();
		int x; //najpierw x pelni funkcje ilosci ramek
		double a, b=0, c, y;
		for(int i=0; i<iloscProcesow; i++)	//w tej petli zliczamy rozmiar wszystkich procesow
		{
			b+=procesy.get(i).getRozmiar();
		}
		for(int i=0; i<iloscProcesow; i++)	//w tej petli przydzielamy procesom ramki
		{
			a = PC.get(i).get(3);//procesy.get(i).getRozmiar();
			b = pamiecWirtualna;
			c = pamiecFizyczna;
			y = (a/b)*c;
			x = (int) y;
			//System.out.println(y+" "+x); //<-- do testow
			PC.get(i).set(4, x);
			PC.get(i).set(5, 0);
			frames.add(new ArrayList<>()); //oraz tworzymy ich ArrayListe ramek
		}
		//LRU
		while(!pages.isEmpty())
		{
			Page page = pages.remove(0);
			x = procesy.indexOf(page.getProces());
			if(!frames.get(x).contains(page))
			{
				if(frames.get(x).size()<PC.get(x).get(4))
				{
					frames.get(x).add(page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					error++;
				}
				else
				{
					int max = -1;
					int idxMax = -1;
					for(int j=0; j<PC.get(x).get(4); j++)
					{
						Page p = frames.get(x).get(j);
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
					frames.get(x).set(idxMax, page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					error++;
				}
			}
			past.add(page);
		}
		
		return error;
	}
	
	//algorytmy "zaawansowane" startuja z podzialu proporcjonalnego
	//sterowanie czestoscia bledow
	public int SCB()
	{
		int error = 0;
		frames = new ArrayList<>();
		ArrayList<Page> pages = new ArrayList<>(strony);
		past = new ArrayList<>();
		int x; //najpierw x pelni funkcje ilosci ramek
		double a, b=0, c, y;
		for(int i=0; i<iloscProcesow; i++)	//w tej petli zliczamy rozmiar wszystkich procesow
		{
			b+=procesy.get(i).getRozmiar();
		}
		for(int i=0; i<iloscProcesow; i++)	//w tej petli przydzielamy procesom ramki
		{
			a = PC.get(i).get(3);//procesy.get(i).getRozmiar();
			b = pamiecWirtualna;
			c = pamiecFizyczna;
			y = (a/b)*c;
			x = (int) y;
			PC.get(i).set(4, x);
			PC.get(i).set(5, 0);
			PC.get(i).set(6, 0);
			frames.add(new ArrayList<>()); //oraz tworzymy ich ArrayListe ramek
		}
		//LRU
		while(!pages.isEmpty())
		{
			Page page = pages.remove(0);
			x = procesy.indexOf(page.getProces());
			if(PC.get(x).get(6)==deltaSCB)
			{
				if(PC.get(x).get(7)>maxBlad)
					PC.get(x).set(4, PC.get(x).get(4)+1);
				if(PC.get(x).get(7)<minBlad && PC.get(x).get(4)>1)
					PC.get(x).set(4, PC.get(x).get(4)-1);
				
				PC.get(x).set(6, 0);
				PC.get(x).set(7, 0);
			}
			PC.get(x).set(6, PC.get(x).get(6)+1);
			if(!frames.get(x).contains(page))
			{
				if(frames.get(x).size()<PC.get(x).get(4))
				{
					frames.get(x).add(page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					PC.get(x).set(7, PC.get(x).get(7)+1);
					error++;
				}
				else
				{
					int max = -1;
					int idxMax = -1;
					for(int j=0; j<PC.get(x).get(4); j++)
					{
						Page p = frames.get(x).get(j);
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
					frames.get(x).set(idxMax, page);
					PC.get(x).set(5, PC.get(x).get(5)+1);
					PC.get(x).set(7, PC.get(x).get(7)+1);
					error++;
				}
			}
			past.add(page);
		}
		
		return error;
	}
	
	//model strefowy
	public int MS()
	{
		int error = 0;
		frames = new ArrayList<>();
		ArrayList<Page> pages = new ArrayList<>(strony);
		past = new ArrayList<>();
		int x; //najpierw x pelni funkcje ilosci ramek
		double a, b=0, c, y;
		for(int i=0; i<iloscProcesow; i++)	//w tej petli zliczamy rozmiar wszystkich procesow
		{
			b+=procesy.get(i).getRozmiar();
		}
		for(int i=0; i<iloscProcesow; i++)	//w tej petli przydzielamy procesom ramki
		{
			a = PC.get(i).get(3);//procesy.get(i).getRozmiar();
			b = pamiecWirtualna;
			c = pamiecFizyczna;
			y = (a/b)*c;
			x = (int) y;
			PC.get(i).set(4, x);
			PC.get(i).set(5, 0);
			frames.add(new ArrayList<>()); //oraz tworzymy ich ArrayListe ramek
		}
		//LRU
		while(!pages.isEmpty())
		{
			Page page = pages.remove(0);
			x = procesy.indexOf(page.getProces());
			if(addPage(page))
			{
				if(PC.get(x).get(4)!=roboczyGetSize(x))
				{
					PC.get(x).set(4, Math.min(roboczyGetSize(x),PC.get(x).get(4)+(pamiecFizyczna-uzywaneRamki())));
				}
			}
			
			if(bany.get(x))
			{
				drugaObsluga.add(page);
			}
			else
			{
				if(!frames.get(x).contains(page))
				{
					if(frames.get(x).size()<PC.get(x).get(4))
					{
						frames.get(x).add(page);
						PC.get(x).set(5, PC.get(x).get(5)+1);
						error++;
					}
					else
					{
						int max = -1;
						int idxMax = -1;
						for(int j=0; j<PC.get(x).get(4); j++)
						{
							Page p = frames.get(x).get(j);
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
						frames.get(x).set(idxMax, page);
						PC.get(x).set(5, PC.get(x).get(5)+1);
						error++;
					}
				}
				past.add(page);
			}
		}
		
		return error;
	}
}
