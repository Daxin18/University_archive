package pack;
import java.util.*;

public class Main 
{
	//klasa main
	public static void main(String args[])
	{
		menu(new ArrayList<Card>());
	}
	
	// MENU
	public static void menu(ArrayList<Card> A)
	{
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		while(running)
		{
			System.out.println("------------------------------------------------");
			System.out.println("\t\tCo chcesz zrobic?");
			System.out.println("------------------------------------------------");
			System.out.println("1. Wygenerowac pseudolosowa talie (liste kart)\n2. Wyswietlic talie\n"
					+ "3. Wyswietlic liczbe elementow\n4. Wyswietlic karty o danej wartosci\n5. Wyswietlic karty o podanym kolorze\n"
					+ "6. Usunac powtarzajace sie karty\n7. Zakonczyc prace programu");
			System.out.println("------------------------------------------------");
			String input = scan.nextLine();
			try
			{
				int option = Integer.valueOf(input);
				switch(option)
				{
				case 1:
					menu_generowanie(A, scan);
					break;
				case 2:
					wyswietl(A);
					break;
				case 3:
					wyswietlIlosc(A);
					break;
				case 4:
					menu_wartosc(A, scan);
					break;
				case 5:
					menu_kolor(A, scan);
					break;
				case 6:
					usunKopie(A);
					break;
				case 7:
					running = false;
					break;
				default:
					System.out.println("Podano niewlasciwe dane (nie ma opcji odpowiadajacej podanej liczbie)");
					break;
				}
			}
			catch(Exception e)
			{
				System.out.println("Podano niewlasciwe dane (podano String zamiast liczby)");			
			}
			//"stoper" programu dodany dla czytelnosci tak, aby menu nie wyswietlalo sie od razu po np. wypisaniu kart,
			//pozwala wybrac uzytkownikowi, kiedy (jesli w ogole) chce przejsc z powrotem do menu
			if(running)
			{
				System.out.println("Kliknij \"Enter\" aby kontynuowac, lub 7, aby zakonczyc dzialanie programu"
						+ "(wpisanie czegokolwiek innego np \"6\" zadziala jak \"Enter\")");
				if(scan.nextLine().equals("7"))
					running = false;
			}
		}
	}
   	
	//poszczegolne metody menu, jesli byly potrzebne
	public static void menu_generowanie(ArrayList<Card> A, Scanner scan)
	{
		System.out.println("Chcesz wygenerowac talie z trzema asami kier (T), czy wylosowac wszystkie karty (kazda inna odpowiedz)?");
		if(scan.nextLine().equals("T"))
			generujTalie3AsyKier(A);
		else
			generujTalie(A);
	}
	public static void menu_wartosc(ArrayList<Card> A, Scanner scan)
	{
		System.out.println("Wpisz wartosc kart, ktora chcesz wyszukac:");
		boolean szukaj_string = false;
		String input = scan.nextLine();
		try
		{
			int szukana = Integer.valueOf(input);
			if(szukana > 0 && szukana < 14)
				wyswietlWartosc(A, szukana);
		}
		catch (Exception e)
		{
			System.out.println("Wpisana wartosc nie jest cyfra - sprawdzam, czy to nazwa karty...");
			szukaj_string = true;
		}
		if (szukaj_string)
		{
			input = input.toLowerCase();
			if (input.equals("as") || input.equals("ace") )
				wyswietlWartosc(A,1);
			else if (input.equals("walet") || input.equals("jack"))
				wyswietlWartosc(A,11);
			else if (input.equals("dama") || input.equals("queen"))
				wyswietlWartosc(A,12);
			else if (input.equals("krol") || input.equals("king"))
				wyswietlWartosc(A,13);
			else
				System.out.println("Sorry, nie ma takiej wartosci w programie");
		}
	}
	public static void menu_kolor(ArrayList<Card> A, Scanner scan)
	{
		System.out.println("Wpisz kolor kart, ktory chcesz wyszukac:");
		boolean szukaj_string = false;
		String input = scan.nextLine();
		try
		{
			int szukany = Integer.valueOf(input);
			if(szukany >= 0 && szukany < 4)
				wyswietlKolor(A, szukany);
		}
		catch (Exception e)
		{
			System.out.println("Wpisany kolor nie jest cyfra - sprawdzam, czy to nazwa koloru...");
			szukaj_string = true;
		}
		if (szukaj_string)
		{
			input = input.toLowerCase();
			if (input.equals("kier") ||input.equals("hearts"))
				wyswietlKolor(A,0);
			else if (input.equals("karo") || input.equals("diamonds"))
				wyswietlKolor(A,1);
			else if (input.equals("trefl") || input.equals("clubs"))
				wyswietlKolor(A,2);
			else if (input.equals("pik") || input.equals("spades"))
				wyswietlKolor(A,3);
			else
				System.out.println("Sorry, nie ma takiego koloru w programie");
		}
	}
	
	// 1. generowanie pseudolosowej talii (listy kart) 
	public static void generujTalie(ArrayList<Card> A)
	{
		A.clear();
		Random rand = new Random();
		int wartosc_temp = rand.nextInt(13) + 1;
		int kolor_temp = rand.nextInt(4);
		while(wartosc_temp!=0)
		{
			add(A, new Card(wartosc_temp, kolor_temp));
			wartosc_temp = rand.nextInt(14);
			kolor_temp = rand.nextInt(4);
		}
		System.out.println("Talia wygenerowana!");
	}
	public static void generujTalie3AsyKier(ArrayList<Card> A)
	{
		A.clear();
		A.add(new Card());
		A.add(new Card());
		A.add(new Card());
		Random rand = new Random();
		int wartosc_temp = rand.nextInt(13) + 1;
		int kolor_temp = rand.nextInt(4);
		while(wartosc_temp!=0)
		{
			add(A, new Card(wartosc_temp, kolor_temp));
			wartosc_temp = rand.nextInt(14);
			kolor_temp = rand.nextInt(4);
		}
		System.out.println("Talia wygenerowana!");
	}
	
	//dodawanie pojedynczej karty do ArrayListy na jej miejsce
	public static void add(ArrayList<Card> A, Card card)
	{
		/*
		sprawdzam, czy na liscie jest juz taka sama karta (A.contains(card) nie zadziala, bo mamy do
		czynienia z dwoma roznymi obiektami, zatem porownuje ich pola z pomoca Card.compare(Card card)
		
		Jesli znajde taka sama karte, zapamietuje jej indeks w zmiennej temp_ind, w przeciwnym wypadku jej
		wartosc zostaje rowna -1, co powinno wyrzucic blad jesli program zadziala zle
		*/
		boolean contain = false;
		int temp_ind = -1;
		for(Card x : A)
		{
			if(card.compare(x) == 0)
			{
				temp_ind = A.indexOf(x);
				contain = true;
			}
		}
		//wstawiam karte w konkretne miejsce, jesli jest juz w talii, to na indeks, na ktorym znajduje sie ta sama karta
		//jesli nie, to powinnismy przejsc dalej, inaczej program wyrzuci blad (contain == false <==> temp_ind == -1)
		if(contain)
			try
			{
				A.add(temp_ind, card);
			}
			catch(Exception e)
			{
				System.out.println("To nie powinno sie nigdy pojawic!");
			}
		//dla rozmiaru 1 i wiecej mamy 3 przypadki
		else if(A.size()>=1)
		{
			//przypadek 1 - karta jest mniejsza niz najmniejsza z kart -> musi wleciec na "spod talii"/poczatek listy
			if(card.compare(A.get(0))<0)
				A.add(0, card);
			//przypadek 2 - karta jest wieksza niz najwieksza z kart -> musi wleciec na "gore talii"/koniec listy
			else if(card.compare(A.get(A.size()-1))>0)
				A.add(A.size(),card);
			//przypadek 3 - karta powinna wyladowac gdzies w srodku -> wtedy szukamy gdzie
			else
			{
				for(int i=0; i<A.size()-1; i++)
					if(card.compare(A.get(i))>0 && card.compare(A.get(i+1))<0)
					{
						A.add(i+1, card);
						break;
					}
			}
		}
		//dla rozmiaru 0 po prostu wrzucamy karte do listy
		else
			A.add(card);
	}
	
	// 2. wyswietlanie ArrayListy (petla for)
	public static void wyswietl(ArrayList<Card> A)
	{
		System.out.println("Kolejne karty w talii(elementy z listy): ");
		for(int i=0; i<A.size(); i++)
		{
			System.out.println(A.get(i).toString());
		}
	}

	// 3. wyswietlanie liczby elementow
	public static void wyswietlIlosc(ArrayList<Card> A)
	{
		System.out.println("Ilosc kart(elementow): " + A.size());
	}
	
	// 4. wyswietlanie kart o podanej wartosci (iteratorem)
	public static void wyswietlWartosc(ArrayList<Card> A, int wartosc)
	{
		if(wartosc>0 && wartosc<14)
		{
			System.out.println("Karty o wartosci " + wartosc + " (" + Card.wartoscToString(wartosc) + "):");
			ArrayIterator<Card> iter = new ArrayIterator<Card>(A);
			boolean nie_ma = true;
			Card x;
			while(iter.hasNext())
			{
				x = iter.next();
				if(x.getWartosc() == wartosc)
				{
					System.out.println(x.toString());
					nie_ma = false;
				}
			}
			if(nie_ma)
				System.out.println("--- brak takich kart ---");
		}
		else
			System.out.println("!!!Szukana wartosc nie istnieje!!!");
	}
	
	// 5. wyswietlanie kart o podanym kolorze (petla foreach)
	public static void wyswietlKolor(ArrayList<Card> A, int kolor)
	{
		if(kolor>=0 && kolor<4)
		{
			System.out.println("Karty o kolorze " + kolor + " (" + Card.kolorToString(kolor) + "):");
			boolean nie_ma = true;
			for(Card x: A)
			{
				if(x.getKolor() == kolor)
				{
					System.out.println(x.toString());
					nie_ma = false;
				}
			}
			if(nie_ma)
				System.out.println("--- brak takich kart ---");
		}
		else
			System.out.println("!!!Szukany kolor nie istnieje!!!");
	}

	// 6. usuwanie powtarzajacych sie kart (petla for)
	public static void usunKopie(ArrayList<Card> A)
	{
		System.out.println("\t\t!!!\nUsuwam ewentualne kopie kart z talii/listy\n\t\t!!!");
		Card temp = A.get(0);
		for(int i=1; i<A.size(); i++)
		{
			if(A.get(i).compare(temp) == 0)
			{
				A.remove(A.get(i));
				i--;
			}
			temp = A.get(i);	
		}
	}
}
