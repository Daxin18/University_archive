package pack;

import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		//public Pracownik(long pes, String nazw, String im, int d, int m, int y, String stan, double pen, int staz)
		Pracownik p0 = new Pracownik();
		Pracownik p1 = new Pracownik(Long.valueOf("12345678901").longValue(),"Nowak","Piotr",12,4,1979,"dyrektor",3500,21);
		Pracownik p2 = new Pracownik(Long.valueOf("11345678901").longValue(),"Kalinowski","Adam",21,8,1989,"ksiegowy",2500,12);
		Pracownik p3 = new Pracownik(Long.valueOf("12345609871").longValue(),"Sikorska", "Roza",8,2,1991,"sekretarka",2100,4);
		Pracownik p4 = new Pracownik(Long.valueOf("01245609871").longValue(),"Piotrowski", "Jan",14,7,1996,"goniec",2200,5);
		Pracownik p5 = new Pracownik(Long.valueOf("01255609871").longValue(),"Jasinski", "Alan",27,11,1995,"kierowca",2300,7);
		TwoWayCycledListWithSentinel<Pracownik> przyklad = new TwoWayCycledListWithSentinel<>();
		przyklad.add(p0);
		przyklad.add(p1);
		przyklad.add(p2);
		przyklad.add(p3);
		przyklad.add(p4);
		przyklad.add(p5);
		BazaDanych baza = new BazaDanych(przyklad);
		menu(baza);
	}
	
	public static void menu(BazaDanych baza)
	{
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		while(running)
		{
			System.out.println("------------------------------------------------");
			System.out.println("\t\tCo chcesz zrobic?");
			System.out.println("------------------------------------------------");
			System.out.println("1. Utworzyc nowa, pusta baze danych\n2. Odczytac baze z pliku\n"
					+ "3. Wyswietlic wszystkich pracownikow\n4. Wyswietlic dane jednego pracownika\n5. Dopisac nowego pracownika\n"
					+ "6. Usunac pracownika z bazy\n7. Zaktualizowac dane pracownika\n8. Obliczyc i wyswietlic srednia pensje w firmie"
					+ "\n9. Obliczyc ilu pracownikow zarabia ponizej sredniej\n10. Zapisac baze do pliku\n11. Parking"
					+ "\n12. Zakonczyc prace programu");
			System.out.println("------------------------------------------------");
			String input = scan.next();
			try
			{
				int option = Integer.valueOf(input);
				switch(option)
				{
				case 1:
					baza = new BazaDanych();
					break;
				case 2:
					odczytaj(baza, scan);
					break;
				case 3:
					wyswietlWszystkich(baza, scan);
					break;
				case 4:
					wyswietlJednego(baza, scan);
					break;
				case 5:
					dopiszNowego(baza, scan);
					break;
				case 6:
					usunPracownika(baza, scan);
					break;
				case 7:
					aktualizuj(baza, scan);
					break;
				case 8:
					System.out.println("Srednia Pensja: "+baza.sredniaPensja());
					System.out.println("Srednia Placa(pensja+premia): "+baza.sredniaPlaca());
					break;
				case 9:
					System.out.println("Ilosc pracownikow z:");
					System.out.println("- pensja mniejsza niz srednia: "+baza.iluPonizejSredniejPensji());
					System.out.println("- placa mniejsza niz srednia: "+baza.iluPonizejSredniejPlacy());
					break;
				case 10:
					zapisz(baza, scan);
					break;
				case 11:
					parkingMenu(baza, scan);
					break;
				case 12:
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
				System.out.println("Wpisz \"12\", aby zakonczyc dzialanie programu"
						+ "(wpisanie czegokolwiek innego np \"6\" sprawi, ze wrocisz do menu)");
				if(scan.next().equals("12"))
					running = false;
			}
		}
	}
	
	public static void odczytaj(BazaDanych T, Scanner scan)
	{
		System.out.println("Podaj nazwe pliku:");
		T.odczyt(scan.next());
	}
	public static void wyswietlWszystkich(BazaDanych T, Scanner scan)
	{
		System.out.println("Jakie informacje chcesz otrzymac?\n1. Podstawowe(imie, nazwisko, stanowisko)\n2. "
				+ "Standardowe(jak wyzej, do tego pesel, data urodzenia i placa, tj. pensja + premia)\n3. Pelne\n"
				+ "(Informacje kazdego pracownika poprzedzone sa jego liczba porzadkowa w bazie)");
		try
		{
			int x = scan.nextInt();
			switch(x)
			{
			case 1:
				T.wyswietlPodstawoweDanePracownikow();
				break;
			case 2:
				T.wyswietlDanePracownikow();
				break;
			case 3:
				T.wyswietlPelneDanePracownikow();
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
	public static void wyswietlJednego(BazaDanych T, Scanner scan)
	{
		System.out.println("Chcesz wyszukac pracownika po peselu(1), czy po liczbie porzadkowej w bazie(2)?");
		try
		{
			int jak = scan.nextInt();
			if(jak==1)
			{
				System.out.println("Podaj pesel:");
				String s = scan.next();
				T.wyswietlPelneDanePracownika(new Pracownik(Long.valueOf(s).longValue()));
			}
			else if(jak==2)
			{
				System.out.println("Podaj liczbe porzadkowa:");
				T.wyswietlPelneDanePracownika(scan.nextInt());
			}
			else
				System.out.println("cos sie zepsulo");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
	public static void dopiszNowego(BazaDanych T, Scanner scan)
	{
		try
		{
			System.out.println("Stworzymy nowego pracownika, podawaj dane, o ktore poprosi program");
			System.out.println("Podaj pesel:");
			long pes = Long.valueOf(scan.next()).longValue();
			System.out.println("Podaj nazwisko:");
			String nazw = scan.next();
			System.out.println("Podaj imie:");
			String im = scan.next();		
			System.out.println("Podaj dzien urodzenia(int):");
			int dd = scan.nextInt();
			System.out.println("Podaj miesiac urodzenia(int):");
			int mm = scan.nextInt();
			System.out.println("Podaj rok urodzenia(int):");
			int yyyy = scan.nextInt();
			System.out.println("Podaj stanowisko:");
			String stan = scan.next();
			System.out.println("Podaj pensje(double):");
			double pen = scan.nextDouble();
			System.out.println("Podaj staz(int):");
			int staz = scan.nextInt();
			T.add(new Pracownik(pes,nazw,im,dd,mm,yyyy,stan,pen,staz));
		}
		catch(Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
	public static void usunPracownika(BazaDanych T, Scanner scan)
	{
		try
		{
			System.out.println("Podaj liczbe porzadkowa pracownika w liscie:");
			T.usunPracownika(scan.nextInt());
		}
		catch (Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
	public static void aktualizuj(BazaDanych T, Scanner scan)
	{
		System.out.println("Chcesz aktualizowac dane pracownika po peselu(1), czy po liczbie porzadkowej w bazie(2)?");
		try
		{
			int jak = scan.nextInt();
			if(jak==1)
			{
				System.out.println("Podaj pesel:");
				T.aktualizuj(new Pracownik(Long.valueOf(scan.next()).longValue()), scan);
			}
			else if(jak==2)
			{
				System.out.println("Podaj liczbe porzadkowa:");
				T.aktualizuj(scan.nextInt(), scan);
			}
			else
				System.out.println("Podano zle dane, wracam do menu");
		}
		catch (Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
	public static void zapisz(BazaDanych T, Scanner scan)
	{
		System.out.println("Podaj nazwe pliku:");
		T.zapis(scan.next());
	}
	public static void parkingMenu(BazaDanych T, Scanner scan)
	{
		System.out.println("Chcesz:\n1. Zobaczyc parking\n2. Dodac samochod pracownika do parkingu(a tym samym pracownika do bazy danych)"
				+ "\n3. Aby pracownik wyjechal");
		try
		{
			int jak = scan.nextInt();
			switch (jak)
			{
			case 1:
				T.showParking();
				break;
			case 2:
				dopiszNowego(T, scan);
				break;
			case 3:
				removeParking(T, scan);
				break;
			}
		}
		catch (Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
	public static void removeParking(BazaDanych T, Scanner scan)
	{
		try
		{
			System.out.println("Podaj pesel pracownika, ktory chce odjechac:");
			T.parkingFind(new Pracownik(Long.valueOf(scan.next()).longValue()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Cos zepsulem");
		}
	}
	public static void addParking(BazaDanych T, Scanner scan)
	{
		try
		{
			System.out.println("Stworzymy nowego pracownika, podawaj dane, o ktore poprosi program");
			System.out.println("Podaj pesel:");
			long pes = Long.valueOf(scan.next()).longValue();
			System.out.println("Podaj nazwisko:");
			String nazw = scan.next();
			System.out.println("Podaj imie:");
			String im = scan.next();		
			System.out.println("Podaj dzien urodzenia(int):");
			int dd = scan.nextInt();
			System.out.println("Podaj miesiac urodzenia(int):");
			int mm = scan.nextInt();
			System.out.println("Podaj rok urodzenia(int):");
			int yyyy = scan.nextInt();
			System.out.println("Podaj stanowisko:");
			String stan = scan.next();
			System.out.println("Podaj pensje(double):");
			double pen = scan.nextDouble();
			System.out.println("Podaj staz(int):");
			int staz = scan.nextInt();
			T.add(new Pracownik(pes,nazw,im,dd,mm,yyyy,stan,pen,staz));
		}
		catch(Exception e)
		{
			System.out.println("Podano zle dane, wracam do menu");
		}
	}
}
