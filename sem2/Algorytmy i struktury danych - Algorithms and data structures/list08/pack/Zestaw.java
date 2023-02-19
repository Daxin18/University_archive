package pack;
import java.util.*;

public class Zestaw
{
	private int[] T;
	
	//pola pomocnicze
	private Random rand = new Random();
	private int range;	//zasieg generowania - 1.5*size
	private int SIZE;	//rozmiar tablicy, zapamietany zeby nie pisac "T.length" za kazdym razem...
	
	//konstruktor
	public Zestaw(int size)
	{
		this.SIZE = size;
		this.T = new int[size];
		this.range =(int)Math.round(1.5*size);
	}
	
	//geter, seter
	public int[] getT()
	{
		return T;
	}
	public void setT(int[] t)
	{
		T = t;
	}
	public int getSize()
	{
		return SIZE;
	}
	
	/*
	===========================================
				metody pomocnicze
	===========================================
	*/
	public void generate()
	{
		for(int i=0; i<T.length; i++)
		{
			T[i] = rand.nextInt(range)+1;	//liczba z zakresu <1,range>
		}
	}
	private int[] copy()
	{
		int[] copy = new int[T.length];
		for(int i=0; i<T.length; i++)
		{
			copy[i] = T[i];
		}
		return copy;
	}
	private void swap(int[] T, int indX, int indY)
	{
		if(indX != indY)
		{
			int c = T[indX];
			T[indX] = T[indY];
			T[indY] = c;
		}
	}
	public static void wyswietl(int[] T)
	{
		System.out.print("[");
		for(int t:T)
		{
			System.out.print(t+", ");
		}
		System.out.print("]");
	}
	/*
	===========================================
				ciagi do sortowan
	===========================================
	*/
	//a) h1=1, hi = 3*h(i-1) +1, az do hi takiego, ze hi>=n (n to rozmiar, zatem n=SIZE)
	public ArrayList<Integer> a()
	{
		ArrayList<Integer> A = new ArrayList<>();
		int i=0;
		while(i<SIZE) //petla przestanie sie wykonywac przy i(hi) >= SIZE
		{
			i=3*i+1;
			A.add(i);
		}
		//System.out.println(A);	//<-- do testow
		return A;
	}
	//b) 2^k -1 : 1,3,7,15...
	public ArrayList<Integer> b()
	{
		ArrayList<Integer> B = new ArrayList<>();
		int i=0;
		int k=1;
		while(i<SIZE)
		{
			i=(int)Math.pow(2, k++)-1;
			B.add(i);
		}
		//System.out.println(B);	//<--do testow
		return B;
	}
	//c) 2^k +1 : 1,3,5,9,17...
	public ArrayList<Integer> c()
	{
		ArrayList<Integer> C = new ArrayList<>();
		int i=1;
		C.add(i);
		int k=1;
		while(i<SIZE)
		{
			i=(int)Math.pow(2, k++)+1;
			C.add(i);
		}
		//System.out.println(C);	//<--do testow
		return C;
	}
	//d) liczby Fibonacciego
	public ArrayList<Integer> d()
	{
		ArrayList<Integer> D = new ArrayList<>();
		int i=1;
		int k=1;
		D.add(i);
		D.add(k);
		int temp;
		while(i<SIZE)
		{
			temp = i;
			i+=k;
			k=temp;
			D.add(i);
		}
		//System.out.println(D);	//<--do testow
		return D;
	}
	
	//dodatkowe
	public ArrayList<Integer> dodatkowe()
	{
		ArrayList<Integer> list = new ArrayList<>();
		double x = SIZE/2;
		list.add(0, (int)Math.round(x));
		for(int i=SIZE-1; i>=1; i--)
		{
			x*=0.75;
			list.add(0, (int)Math.round(x));
		}
		return list;
	}
	
	/*
	===========================================
				metody sortowan
	===========================================
	*/
	
	//wersja 1 - InsertSort we wszystkich sortowaniach "co h" i "co 1"
	public int[] ShellSortV1(ArrayList<Integer> h)
	{
		int[] temp = this.copy();
		int hIndex=h.size()-1;
		int hi;
		while (hIndex>=0) //petla dla indeksow hi
		{
			hi = h.get(hIndex--);
			for(int i=0; i<hi; i++)	//petla dla pierwszych wyrazow "podciagow" - elementow co hi
			{
				for(int j=i; j<SIZE; j+=hi)	//insertSort
				{
					int x = temp[j];
					int z;
					for(z=j; z>0 && z-hi>=0 && temp[z-hi]>x ; z-=hi)
					{
						temp[z] = temp[z-hi];
					}
					temp[z] = x;
				}
			}
		}
		return temp;
	}
	//wersja 2 - InsertSort "co h" i BubbleSort "co 1"
	public int[] ShellSortV2(ArrayList<Integer> h)
	{
		int[] temp = this.copy();
		int hIndex=h.size()-1;
		int hi;
		while (hIndex>=0)	//petla dla indeksow hi
			{
			hi = h.get(hIndex--);
			if(hi!=1)	//warunek sprawdzajacy czy nie doszlismy juz do hi=1
			{
				for(int i=0; i<hi; i++)	//petla dla pierwszych wyrazow "podciagow" - elementow co hi
				{
					for(int j=i; j<SIZE; j+=hi)	//insertSort
					{
						int x = temp[j];
						int z;
						for(z=j; z>0 && z-hi>=0 && temp[z-hi]>x ; z-=hi)
						{
							temp[z] = temp[z-hi];
						}
						temp[z] = x;
					}
				}
			}
			else	//jesli doszlismy do hi=1 to sortujemy BubbleSortem
			{
				//System.out.println("Algorytm doszedl do hi=1, korzystam z BubbleSorta..."); //<--do testow
				boolean goOn;
				for(int i=1; i<SIZE; ++i)
				{
					goOn=false;
					for(int j=0; j<(SIZE-i); ++j)
					{
						if(temp[j] > temp[j+1])
						{
							swap(temp, j, j+1);
							goOn = true;
						}
					}
					if(!goOn) break;
				}
				hIndex=-1;	//zmieniamy index na -1, zeby nie sortowac posortowanej tablicy
			}
		}
		return temp;
	}
	//wersja 3 - BubbleSort "co h" i InsertSort "co 1"
	public int[] ShellSortV3(ArrayList<Integer> h)
	{
		int[] temp = this.copy();
		int hIndex=h.size()-1;
		int hi;
		while (hIndex>=0)	//petla dla indeksow hi
			{
			hi = h.get(hIndex--);
			if(hi!=1)	//warunek sprawdzajacy czy nie doszlismy do hi=1
			{
				boolean goOn;
				for(int i=0; i<hi && i<SIZE; i++) //petla dla pierwszych wyrazow "podciagow" - elementow co hi
				{
					for(int j=i; j<SIZE; ++j)	//BubbleSort
					{
						goOn=false;
						for(int z=i; z<(SIZE-j) && z+hi<(SIZE-j); z+=hi)
						{
							if(temp[z] > temp[z+hi])
							{
								swap(temp, z, z+hi);
								goOn = true;
							}
						}
						if(!goOn) break;
					}
				}
			}
			else	//jesli hi=1, to sortujemy InsertSortem
			{
				//System.out.println("Algorytm doszedl do hi=1, korzystam z InsertSorta"); //<--do testow
				for(int i=1; i<SIZE; i++)
				{
					int x = temp[i];
					int j;
					for(j=i; j>0 && temp[j-1]>x ; j--)
					{
						temp[j] = temp[j-1];
					}
					temp[j] = x;
				}
				hIndex=-1;	//ustawiamy indeks na -1, zeby wyjsc z petli i nie sortowac posortowanej tablicy
			}
		}
		return temp;
	}
	
	/*
	============================================
				metody zliczania czasu
	============================================
	*/
	public long measureShellSortV1(ArrayList<Integer> h)
	{
		long beggining = System.nanoTime();
		this.ShellSortV1(h);
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureShellSortV2(ArrayList<Integer> h)
	{
		long beggining = System.nanoTime();
		this.ShellSortV2(h);
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureShellSortV3(ArrayList<Integer> h)
	{
		long beggining = System.nanoTime();
		this.ShellSortV3(h);
		long end = System.nanoTime();
		return end-beggining;
	}
	
}
