package pack;
import java.util.*;
import java.io.*;

public class Main
{
	//metoda statyczna main - "do testow"
	public static void main(String args[])
	{
		operacje();
	}
	
	//metoda "operacje" zawierajaca opisy danych metod wzgledem polecenia (ktorym punktem/kropka byly w poleceniu)
	public static void operacje()
	{
		/*
		 ---------------------------------
			deklaracja tablicy (pkt 1)
		 ---------------------------------
		*/
		Pracownik[] T = new Pracownik[6];
		
		/*
		 ----------------------------------------------
		  wypelnianie tablicy (pkt 2, rozne sposoby)
		 ----------------------------------------------
		*/

		wypelnijPrzykladami(T);
		wypelnijScanner(T);		
		
		/*
		 --------------------------------------------------
		 	wyswietlanie tablicy w formie tabeli (pkt 3)
		 --------------------------------------------------
		*/
		wyswietl(T);
		
		/*
		 --------------------------------------------------
		 	zapis do pliku/odczyt z pliku (pkt 4)
		 --------------------------------------------------
		*/
		
		//test zapisu
		//zapis_odczyt(T)
		zapisz(T,"testZapis.txt");
		
		//test odczytu
		Pracownik[] X = new Pracownik[6];		zapis_odczyt(X);		
		
		/*
		 --------------------------------------------
		 	wyswietlanie iteratorem (pkt 5)
		 --------------------------------------------
		*/
		wyswietlIter(X);
		
	}
	
	/*
	=================================================
						metody
	=================================================
	*/
	
	//wypelnianie tablicy
	public static void wypelnijPrzykladami(Pracownik[] T)
	{
		//PracownikGodzinowy(String nazw, String im, long pesel, String stan, int staz, double stawka, int godziny)
		//PracownikEtatowy(String nazw, String im, long pesel, String stan, int staz, double etat, double stawka)
		
		PracownikGodzinowy p1 = new PracownikGodzinowy();
		PracownikEtatowy p2 = new PracownikEtatowy("Nowak","Piotr",12345678901L,"Ksiegowy", 3, 1, 1800);
		PracownikEtatowy p3 = new PracownikEtatowy("Gajek","Filip",24864567123L,"Kierowca", 5, 0.75, 1700);
		PracownikGodzinowy p4 = new PracownikGodzinowy("Hernas", "Dawid", 54231095671L, "Nauczyciel", 15, 12, 140);
		PracownikGodzinowy p5 = new PracownikGodzinowy("Kowalczyk", "Bartosz", 53476512398L, "Scenazysta", 1, 10, 150);
		PracownikEtatowy p6 = new PracownikEtatowy("Joszko","Andrzej",14789428901L,"Programista", 3, 1.5, 2100);
		
		T[0] = p1;
		T[1] = p2;
		T[2] = p3;
		T[3] = p4;
		T[4] = p5;
		T[5] = p6;
	}
	public static void wypelnijScanner(Pracownik[] T)
	{
		Scanner scan = new Scanner(System.in);
		String nazw;
		String im;
		long pesel; 
		String stan;
		int staz; 
		double stawka; 
		int godziny; 
		double etat;
		try
		{
		for(int i=0; i<T.length; i++)
		{
			System.out.println("Podaj nazwisko pracownika nr."+Integer.toString(i+1)+" (String)");
			nazw = scan.nextLine();
			System.out.println("Podaj imie pracownika nr."+Integer.toString(i+1)+" (String)");
			im = scan.nextLine();
			System.out.println("Podaj PESEL pracownika nr."+Integer.toString(i+1)+" (11 cyfr)");
			pesel = Long.parseLong(scan.nextLine());
			System.out.println("Podaj stanowisko pracownika nr."+Integer.toString(i+1)+" (String)");
			stan = scan.nextLine();
			System.out.println("Podaj staz pracownika nr."+Integer.toString(i+1)+" (int)");
			staz = Integer.parseInt(scan.nextLine());
			System.out.println("Wpisz 1, jesli pracownik ma byc pracownikiem etatowym, lub cokolwiek innego, jesli ma byc pracownikiem godzinowym");
			if(scan.nextLine().equals("1"))
			{
				System.out.println("Podaj etat pracownika nr."+Integer.toString(i+1)+" (double)");
				etat = Double.parseDouble(scan.nextLine());
				System.out.println("Podaj stawke pracownika nr."+Integer.toString(i+1)+" (double)");
				stawka = Double.parseDouble(scan.nextLine());
				T[i] = new PracownikEtatowy(nazw,im,pesel,stan,staz,etat,stawka);
			}
			else
			{
				System.out.println("Podaj stawke pracownika nr."+Integer.toString(i+1)+" (double)");
				stawka = Double.parseDouble(scan.nextLine());
				System.out.println("Podaj ilosc godzin pracownika nr."+Integer.toString(i+1)+" (int)");
				godziny = Integer.parseInt(scan.nextLine());
				T[i] = new PracownikEtatowy(nazw,im,pesel,stan,staz,stawka,godziny);
			}
			System.out.println("Czy chcesz kontynuowac i stworzyc pracownika nr."+Integer.toString(i+2)+"? T - tak, inna odpowiedz - nie");
			if (!(scan.nextLine().equals("T"))) break;
		}
		}
		catch(Exception e)
		{
			System.out.println("Wystapil blad, najpewniej podano niewlasciwe dane");	
		}
	}

	//wyswietlanie tablicy
	public static void wyswietl(Pracownik[] T)
	{
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.printf("| %-12s | %-12s | %-11s | %-20s | %-5s | %-7s |%n", "Nazwisko", "Imie", "PESEL", "Stanowisko", "Staz", "Pensja");
		System.out.println("--------------------------------------------------------------------------------------");
		for(int i=0; i<T.length; i++)
		{
			if(T[i] != null) T[i].wyswietl();
		}
		System.out.println("--------------------------------------------------------------------------------------");
	}
	
	//operacje na plikach
	public static void zapis_odczyt(Pracownik[] T)
	{
		Scanner scan = new Scanner(System.in);
		String co;
		String plik;
		System.out.println("Chcesz zapisac (Z) do pliku, czy odczytac (O) z pliku tabele pracownikow?");
		co = scan.nextLine();
		if (co.equals("Z") || co.equals("O"))
		{
			System.out.println("Podaj nazwe pliku");
			plik = scan.nextLine();
			if(co.equals("Z"))
			{
				zapisz(T,plik);
			} else {
				odczytaj(T, plik);
			}
		}
		else
		{
			System.out.println("Zle dane");
		}
	}
	
	public static void zapisz(Pracownik[] T, String s)
	{
		try(ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(new File(s))))
		{
			out.writeObject(T.length + "\n");
			for(int i=0; i<T.length; i++)
			{
				out.writeObject(T[i]);
			}
			System.out.println("Pomyslnie zapisano dane do pliku \""+s+"\"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void odczytaj(Pracownik[] T, String s)
	{
		try(ObjectInputStream in =new ObjectInputStream(new FileInputStream(new File(s))))
		{
			in.readObject();
			for(int i=0; i<T.length; i++)
			{
				T[i] = (Pracownik) in.readObject();
			}
			System.out.println("Odczytano pomyslnie dane z pliku \"" + s + "\"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//wyswietlanie iteratorem
	public static void wyswietlIter(Pracownik[] T)
	{
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("\t\t\t\tPracownicy");
		System.out.println("---------------------------------------------------------------------------");
		Iterator1<Pracownik> iter = new Iterator1<Pracownik>(T); 
		while(iter.hasNext())
		{
			System.out.println(iter.next().toString());
		}
		System.out.println("---------------------------------------------------------------------------");
	}
}
