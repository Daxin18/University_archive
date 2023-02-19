package pack;
import java.util.*;

public class Main
{
	private static int zakryte = 0;
	
	//klasa main
	public static void main(String args[])
	{
		menu(new OneWayLinkedListWithHead<Karta>());
	}
	
	//MENU
	public static void menu(OneWayLinkedListWithHead<Karta> T)
	{
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		while(running)
		{
			System.out.println("------------------------------------------------");
			System.out.println("\t\tCo chcesz zrobic?");
			System.out.println("------------------------------------------------");
			System.out.println("1. Wygenerowac pseudolosowa talie (liste kart)\n2. Wyswietlic talie\n"
					+ "3. Wyswietlic liczbe kart (w tym zakrytych i odkrytych)\n4. Wyswietlic karty o danej wartosci\n5. Wyswietlic karty o podanym kolorze\n"
					+ "6. Usunac zakryte karty\n7. Zakonczyc prace programu");
			System.out.println("------------------------------------------------");
			String input = scan.nextLine();
			try
			{
				int option = Integer.valueOf(input);
				switch(option)
				{
				case 1:
					generowanie(T);
					break;
				case 2:
					wyswietl(T);
					break;
				case 3:
					wyswietlIlosc(T);
					break;
				case 4:
					menu_wartosc(T, scan);
					break;
				case 5:
					menu_kolor(T, scan);
					break;
				case 6:
					usunZakryte(T);
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
				e.printStackTrace();
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
	
	//metody pomagajace pracowac ze "spisem" kart w talii
	public static void addCard(Karta card, boolean[] spis)
	{
		if(card.getWartosc()<14 && card.getWartosc()>0)
		spis[card.getWartosc()-1+(card.getKolor()*13)] = true;
	}
	public static boolean check(Karta card, boolean[] spis)
	{
		if(card.getWartosc()<14 && card.getWartosc()>0)
			if(spis[card.getWartosc()-1+(card.getKolor()*13)] == true)
				return false;
		return true;
	}
	
	//punkt 1. generowanie talii/utworzenie listy
	public static void add(OneWayLinkedListWithHead<Karta> T, Karta card, boolean[] spis)
	{
		int size = T.size();
		//dodanie kart zakrytych na koniec
		if (!card.getZnacznik())
		{
			T.add(card);
			zakryte++;
		}
		//jesli karty nie ma w spisie, tj nie wystapila jeszcze - to mozemy ja dodac do talii
		else if (check(card, spis))
		{
			Iterator<Karta> iter = T.iterator();
			Karta temp;
			int i=0;		//zmienna i oznacza indeks, na ktory wstawimy karte
			try
			{
				temp = iter.next();
				//dla rozmiaru>1 iterujemy po talii do momentu znalezienia karty wiekszej niz "nasza karta"
				//inkrementujemy i przy kazdym porownaniu, w ktorym karta wstawiana ("nasza karta") jest wieksza.
				if(size >1)
				{
					while(iter.hasNext())
					{
						if(card.compare(temp)>0)
						{
							i++;
							temp = iter.next();
						}
						else
							break;
					}
					if(card.compare(temp)>0)
						i++;
				}
				//dla rozmiaru==1 robimy w sumie to, co wyzej, ale tylko z jednym porownaniem
				else if (size==1)
					if(card.compare(temp)>0)
						i++;
			} catch(Exception e)
			{
				//metoda nie powinna wyrzucac wyjatku, ale wole sie zabezbieczyc na wypadek, gdyby cos sie zepsulo...
			}
			//na koniec dodajemy karte na odpowiednie miejsce (w liscie i w spisie)
			T.add(i, card);
			addCard(card, spis);
		}
	}
	public static void generowanie(OneWayLinkedListWithHead<Karta> T)
	{
		zakryte = 0;
		T.clear();
		Random rand = new Random();
		int wartosc_temp = rand.nextInt(14) + 1;
		int kolor_temp = rand.nextInt(4);
		boolean[] spis = new boolean[52];	//tablica automatycznie zapelnia sie wartosciami false (lub w zaleznosci od typu danych tym, co odpowiada wartosci 0)
		while(wartosc_temp!=0)
		{
			System.out.println("WYLOSOWANO: wartosc: "+wartosc_temp +" kolor: "+kolor_temp);
			add(T, new Karta(wartosc_temp, kolor_temp), spis);
			wartosc_temp = rand.nextInt(15);
			kolor_temp = rand.nextInt(4);
		}
		System.out.println("Talia wygenerowana!");
	}
	
	//punkt 2. wyswietlanie listy
	public static void wyswietl(OneWayLinkedListWithHead<Karta> T)
	{
		System.out.println("Kolejne karty w talii(elementy z listy): ");
		Iterator<Karta> iter = T.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().toString());
		}
	}
	
	//punkt 3. wyswietlanie liczby elementow + zakryte/odkryte
	public static void wyswietlIlosc(OneWayLinkedListWithHead<Karta> T)
	{
		System.out.println("Ilosc kart(elementow): " + T.size());
		System.out.println("W tym kart zakrytych: " + zakryte);
		System.out.println("A odkrytych: " + (T.size()-zakryte));
	}
	
	//punkt 4. wyswietlanie po wartosci
	public static void menu_wartosc(OneWayLinkedListWithHead<Karta> T, Scanner scan)
	{
		System.out.println("Wpisz wartosc kart, ktora chcesz wyszukac:");
		boolean szukaj_string = false;
		String input = scan.nextLine();
		try
		{
			int szukana = Integer.valueOf(input);
			if(szukana > 0 && szukana < 14)
				wyswietlWartosc(T, szukana);
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
				wyswietlWartosc(T,1);
			else if (input.equals("walet") || input.equals("jack"))
				wyswietlWartosc(T,11);
			else if (input.equals("dama") || input.equals("queen"))
				wyswietlWartosc(T,12);
			else if (input.equals("krol") || input.equals("king"))
				wyswietlWartosc(T,13);
			else if (input.equals("zakryte") || input.equals("karty zakryte") || input.equals("karta zakryta") || input.equals("zakryta"))
				wyswietlZakryte(T);
			else
				System.out.println("Sorry, nie ma takiej wartosci w programie");
		}
	}
	public static void wyswietlZakryte(OneWayLinkedListWithHead<Karta> T)
	{
		System.out.println("Karty zakryte:");
		Iterator<Karta> iter = T.iterator();
		boolean nie_ma = true;
		Karta x;
		while(iter.hasNext())
		{
			x = iter.next();
			if(!x.getZnacznik())
			{
				System.out.println(x.toString());
				nie_ma = false;
			}
		}
		if(nie_ma)
			System.out.println("--- brak takich kart ---");
	}
	public static void wyswietlWartosc(OneWayLinkedListWithHead<Karta> T, int wartosc)
	{
		if(wartosc>0 && wartosc<14)
		{
			System.out.println("Karty o wartosci " + wartosc + " (" + Karta.wartoscToString(wartosc) + "):");
			Iterator<Karta> iter = T.iterator();
			boolean nie_ma = true;
			Karta x;
			while(iter.hasNext())
			{
				x = iter.next();
				if(x.getZnacznik() && x.getWartosc() == wartosc)
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
	
	//punkt 5. wyswietlanie po kolorze
	public static void menu_kolor(OneWayLinkedListWithHead<Karta> T, Scanner scan)
	{
		System.out.println("Wpisz kolor kart, ktory chcesz wyszukac:");
		boolean szukaj_string = false;
		String input = scan.nextLine();
		try
		{
			int szukany = Integer.valueOf(input);
			if(szukany >= 0 && szukany < 4)
				wyswietlKolor(T, szukany);
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
				wyswietlKolor(T,0);
			else if (input.equals("karo") || input.equals("diamonds"))
				wyswietlKolor(T,1);
			else if (input.equals("trefl") || input.equals("clubs"))
				wyswietlKolor(T,2);
			else if (input.equals("pik") || input.equals("spades"))
				wyswietlKolor(T,3);
			else
				System.out.println("Sorry, nie ma takiego koloru w programie");
		}
	}
	public static void wyswietlKolor(OneWayLinkedListWithHead<Karta> T, int kolor)
	{
		if(kolor>=0 && kolor<4)
		{
			System.out.println("Karty o kolorze " + kolor + " (" + Karta.kolorToString(kolor) + "):");
			Iterator<Karta> iter = T.iterator();
			boolean nie_ma = true;
			Karta x;
			while(iter.hasNext())
			{
				x = iter.next();
				if(x.getZnacznik() && x.getKolor() == kolor)
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
	
	//punkt 6. usuwanie kart zakrytych
	public static void usunZakryte(OneWayLinkedListWithHead<Karta> T)
	{
		Iterator<Karta> iter = T.iterator();
		while(iter.hasNext())
		{
			Karta card_temp = iter.next();
			if(!(card_temp.getZnacznik()))
				T.remove(card_temp);
		}
		zakryte = 0;
	}
	
}
