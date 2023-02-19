package pack;
import java.io.*;
import java.util.*;

//program zaklada, ze pracownicy rozpoznawani sa w calosci przez pesel
//niesie to za soba fakt, ze z zalozenia pesele sa rozne
public class BazaDanych implements Serializable
{
	private static final long serialVersionUID = 1234567890;
	private TwoWayCycledListWithSentinel<Pracownik> T;
	private FiniteListStack<Pracownik> parking;
	
	//konstruktor domyslny i przeciazony
	public BazaDanych()
	{
		this.T = new TwoWayCycledListWithSentinel<>();
		this.parking = new FiniteListStack<Pracownik>();
	}
	public BazaDanych(TwoWayCycledListWithSentinel<Pracownik> X)
	{
		this.T = new TwoWayCycledListWithSentinel<Pracownik>();
		this.parking = new FiniteListStack<Pracownik>();
		this.wypelnijZListy(X);
	}

	//geter i seter
	public TwoWayCycledListWithSentinel<Pracownik> getLista()
	{
		return T;
	}
	public void setLista(TwoWayCycledListWithSentinel<Pracownik> t)
	{
		T = t;
	}
	
	/*
	 ---------------------------------------------------------
	 	metody (komentowane nad nimi punktami do realizacji)
	 ---------------------------------------------------------
	*/
	//1.
	public void wypelnijZListy(TwoWayCycledListWithSentinel<Pracownik> X)
	{
		Iterator<Pracownik> iter = X.iterator();
		while(iter.hasNext())
		{
			this.add(iter.next());
		}
	}
	
	//2.
	@SuppressWarnings("unchecked")
	public void odczyt(String s)
	{
		try (ObjectInputStream si = new ObjectInputStream(new FileInputStream(s)))
		{
			Object x=si.readObject();
			if (x instanceof TwoWayCycledListWithSentinel<?>)
			{
				this.wypelnijZListy((TwoWayCycledListWithSentinel<Pracownik>)x);
			}
		}
		catch (Exception e)
		{
			System.out.println("Blad przy odczytywaniu");
			e.printStackTrace();
		}
	}
	
	//3. (zbior metod potrzebnych w roznych przypadkach)
	public void wyswietlPodstawoweDanePracownikow()
	{
		System.out.println("Lp. Dane pracownika");
		Iterator<Pracownik> iter = T.iterator();
		int i = 1;
		while(iter.hasNext())
		{
			System.out.println(i++ + ". "+iter.next().podstawoweDanePracownika());
		}
	}
	public void wyswietlPelneDanePracownikow()
	{
		System.out.println("Lp. Dane pracownika");
		Iterator<Pracownik> iter = T.iterator();
		int i = 1;
		while(iter.hasNext())
		{
			System.out.println(i++ + ". " +iter.next().pelneDanePracownika());
		}
	}
	public void wyswietlDanePracownikow()
	{
		System.out.println("Lp. Dane pracownika");
		Iterator<Pracownik> iter = T.iterator();
		int i = 1;
		while(iter.hasNext())
		{
			System.out.println(i++ + ". " +iter.next().danePracownika());
		}
	}
	
	//4.
	public void wyswietlPelneDanePracownika(Pracownik p)
	{
		Iterator<Pracownik> iter = T.iterator();
		while(iter.hasNext())
		{
			Pracownik x = iter.next();
			if(x.comparePesel(p)==0)
			{
				System.out.println(x.pelneDanePracownika());
				break;
			}
		}
	}
	public void wyswietlPelneDanePracownika(int lp)
	{
		System.out.println(T.get(lp-1).pelneDanePracownika());
	}
	
	//5.
	public void add(Pracownik p)
	{
		Iterator<Pracownik> iter = T.iterator();
		int i=0;
		while(iter.hasNext())
		{
			Pracownik x = iter.next();
			if(p.comparePesel(x)>=0)
				i++;
			else
				break;
		}
		T.add(i,p);
		przyjechal(p);
	}
	
	//6.
	public void usunPracownika(int lp)
	{
		wyjezdza(T.get(lp-1));
		T.remove(lp-1);
	}
	
	//7.
	public void aktualizuj(Pracownik p, Scanner scan)
	{
		Iterator<Pracownik> iter = T.iterator();
		Pracownik aktualizowany = null;
		boolean wystapil = false;
		//szukamy pracownika o takim samym peselu
		while(iter.hasNext())
		{
			Pracownik x = iter.next();
			if(x.comparePesel(p)==0)
			{
				aktualizowany = x;
				wystapil = true;
				break;
			}
		}
		//wybieramy, co zmieniamy
		if(wystapil)
		{
			System.out.println("Co chcesz aktualizowac?\n1. Imie\n2. Nazwisko\n3. Stanowisko\n4. Pensje\n5. Staz");
			try
			{
				int x = scan.nextInt();
				switch(x)
				{
				case 1:
					aktualizujImie(aktualizowany, scan);
					break;
				case 2:
					aktualizujNazwisko(aktualizowany, scan);
					break;
				case 3:
					aktualizujStanowisko(aktualizowany, scan);
					break;
				case 4:
					aktualizujPensje(aktualizowany, scan);
					break;
				case 5:
					aktualizujStaz(aktualizowany, scan);
					break;
				default:
					System.out.println("Podano zle dane, wracam do menu");
				}
			}
			catch(Exception e)
			{
				System.out.println("Podano zle dane, wracam do menu");
			}
		}
		else
		{
			System.out.println("Nie ma takiego pracownika");
		}
	}
	public void aktualizuj(int lp, Scanner scan)
	{
		int index = lp-1;
		Pracownik aktualizowany = T.get(index);
		//wybieramy, co zmieniamy
		System.out.println("Co chcesz aktualizowac?\n1. Imie\n2. Nazwisko\n3. Stanowisko\n4. Pensje\n5. Staz");
		try
		{
			int x = scan.nextInt();
			switch(x)
			{
			case 1:
				aktualizujImie(aktualizowany, scan);
				break;
			case 2:
				aktualizujNazwisko(aktualizowany, scan);
				break;
			case 3:
				aktualizujStanowisko(aktualizowany, scan);
				break;
			case 4:
				aktualizujPensje(aktualizowany, scan);
				break;
			case 5:
				aktualizujStaz(aktualizowany, scan);
				break;
			default:
				System.out.println("Podano zle dane, wracam do menu");
			}
		}
		catch(Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}

	//
	public void aktualizujImie(Pracownik p, Scanner scan)
	{
		System.out.println("Wpisz imie pracownika:");
		String s = scan.next();
		p.setImie(s);
	}
	public void aktualizujNazwisko(Pracownik p, Scanner scan)
	{
		System.out.println("Wpisz nazwisko pracownika:");
		String s = scan.next();
		p.setNazwisko(s);
	}
	public void aktualizujStanowisko(Pracownik p, Scanner scan)
	{
		System.out.println("Wpisz stanowisko pracownika:");
		String s = scan.next();
		p.setStanowisko(s);
	}
	public void aktualizujPensje(Pracownik p, Scanner scan)
	{
		System.out.println("Wpisz pensje pracownika:");
		try
		{
			double x = scan.nextDouble();
			p.setPensja(x);
		}
		catch(Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}

	}
	public void aktualizujStaz(Pracownik p, Scanner scan)
	{
		System.out.println("Wpisz pensje pracownika:");
		try
		{
			int x = scan.nextInt();
			p.setPensja(x);
		}
		catch(Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}

	}
	
	
	//8.
	public double sredniaPensja()
	{
		double x = 0;
		int i=0;
		Iterator<Pracownik> iter = T.iterator();
		while(iter.hasNext())
		{
			x+=iter.next().getPensja();
			i++;
		}
		return i!=0?x/i:0; //zwracam 0 jesli nie ma pracownikow na liscie
	}
	public double sredniaPlaca()
	{
		double x = 0;
		int i=0;
		Iterator<Pracownik> iter = T.iterator();
		while(iter.hasNext())
		{
			x+=iter.next().placa();
			i++;
		}
		return i!=0?x/i:0; //zwracam 0 jesli nie ma pracownikow na liscie
	}
	
	//9.
	public int iluPonizejSredniejPensji()
	{
		int x=0;
		double srednia = sredniaPensja();
		Iterator<Pracownik> iter = T.iterator();
		while(iter.hasNext())
		{
			if(iter.next().getPensja()<srednia)
				x++;
		}
		return x;
	}
	public int iluPonizejSredniejPlacy()
	{
		int x=0;
		double srednia = sredniaPlaca();
		Iterator<Pracownik> iter = T.iterator();
		while(iter.hasNext())
		{
			if(iter.next().placa()<srednia)
				x++;
		}
		return x;
	}
	
	//10.
	public void zapis(String s)
	{
		try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(s)))
		{
			so.writeObject(T);
		}
		catch (Exception e)
		{
			System.out.println("Blad przy zapisywaniu");
			e.printStackTrace();
		}
	}
	/*
	--------------------------------------------
			11.	Parking (i jego metody)
	--------------------------------------------
	*/
	public void przyjechal(Pracownik p)
	{
		try
		{
			parking.push(p);
			System.out.println("Przyjechal(a): " + p.podstawoweDaneOsobowe());
		}
		catch(FullStackException e)
		{
			System.out.println("Nie ma juz miejsca na parkingu, "+ p.podstawoweDaneOsobowe() +" zaparkowal(a) obok");
		}
	}
	public void wyjezdza(Pracownik p)
	{
		ListStack<Pracownik> temp = new ListStack<>();
		try
		{		
			System.out.println("Aby "+p.toString() + " mogl(a) wyjechac: \n");
			boolean jest=false;
			Pracownik x = parking.top();
			if(x.comparePesel(p)==0)
				jest = true;
			while(!jest)
			{
				temp.push(parking.pop());
				System.out.println(x.toString()+" musi przestawic auto");
				x = parking.top();
				if(x.comparePesel(p)==0)
					jest=true;
			}
			if(jest)
			{
				System.out.println("\n"+p.toString() + " wyjechal(a)\n");
				parking.pop();
			}
			else
				System.out.println("\n"+p.toString() + " nie ma dzis w pracy, nikt nie musi wyjezdzac\n");
		}
		catch(Exception e)
		{
			System.out.println("\nSzukanego pracownika nie ma dzis w pracy\n");
		}
		try
		{
			while(!temp.isEmpty())
			{
				parking.push(temp.pop());
			}
		}
		catch(Exception e)
		{
			System.out.println("Tego nie powinienem widziec");
		}
	}
	public void showParking()
	{
		System.out.println("Imiona i nazwiska pracownikow, ktorych auta sa na parkingu w kolejnosci przyjazdu");
		parking.showStack();
	}
	public void parkingFind(Pracownik p)
	{
		Iterator<Pracownik> iter = T.iterator();
		int i=0;
		while(iter.hasNext())
		{
			i++;
			Pracownik x = iter.next();
			if(x.comparePesel(p)==0)
			{
				usunPracownika(i);
				break;
			}
		}
		
	}
}
