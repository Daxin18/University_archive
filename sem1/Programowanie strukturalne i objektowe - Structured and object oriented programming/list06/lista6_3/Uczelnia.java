package lista6_3;
import java.io.*;
import java.util.*;


public class Uczelnia implements Serializable
{
	private static final long serialVersionUID = 1234567890;
	private ArrayList<Osoba> U;
	private ArrayList<Kursy> K;
	
	//komparatory - pola klasy
	private Compare_nazw comp1;
	private Compare_nazw_im comp2;
	private Compare_nazw_wiek comp3;
	private CompareK_ECTS comp4;
	private CompareK_nazw comp5;
	
	//zmienne pomocnicze
	protected int usunTemp=0;
	protected int wczytajTemp=0;
	
	//usuwanie - strategia
	private usuwanie us;
	
	//konstruktory
	public Uczelnia()
	{
		this.U = new ArrayList<Osoba>();
		this.K = new ArrayList<Kursy>();
		createComparators();
		this.us = new usuwanieWszystkich();
	}
	public Uczelnia(ArrayList<Osoba> o, ArrayList<Kursy> k)
	{
		this.U=o;
		this.K=k;
		createComparators();
		this.us = new usuwanieWszystkich();
	}
	
	//tworzenie komparator�w
	private void createComparators()
	{
		this.comp1 = new Compare_nazw();
		this.comp2 = new Compare_nazw_im();
		this.comp3 = new Compare_nazw_wiek();
		this.comp4 = new CompareK_ECTS();
		this.comp5 = new CompareK_nazw();
	}
	
	//getery
	public int getWczytajTemp()
	{
		return wczytajTemp;
	}
	public int getUsunTemp()
	{
		return usunTemp;
	}
	public Osoba getOsoba(int x)
	{
		return U.get(x);
	}
	public Kursy getKurs(int x)
	{
		return K.get(x);
	}
	public ArrayList<Osoba> getOsoby()
	{
		return U;
	}
	public ArrayList<Kursy> getKursy()
	{
		return K;
	}
	
	//getery dla komparator�w (Etap 3)
	public Compare_nazw getComparator1()
	{
		return comp1;
	}
	public Compare_nazw_im getComparator2()
	{
		return comp2;
	}
	public Compare_nazw_wiek getComparator3()
	{
		return comp3;
	}
	public CompareK_ECTS getComparator4()
	{
		return comp4;
	}
	public CompareK_nazw getComparator5()
	{
		return comp5;
	}
	
	//setery
	public void addOsoba(Osoba o)
	{
		U.add(o);
	}
	public void removeOsoba(Osoba o)
	{
		U.remove(o);
	}
	public void addKurs(Kursy k)
	{
		K.add(k);
	}
	public void removeKurs(Kursy k)
	{
		K.remove(k);
	}
	public void setOsoby(ArrayList<Osoba> o)
	{
		this.U = o;
	}
	public void setKursy(ArrayList<Kursy> k)
	{
		this.K=k;
	}
	public void setUsunTemp(int i)
	{
		this.usunTemp = i;
	}
	public void setUsuwanie(usuwanie usu)
	{
		this.us = usu;
	}
	
	/*
	 ------------------------------------------------------------------------------------------------
	 	(poniek�d Etap	1)	szukanie ze Scannerem (wersja bez znajduje si� w mainie listy 5,
	 	kt�ry przepisa�em i zmodyfikowa�em na potrzeby zmiany kodu/dodania funkcjonalno�ci,
	 	metoda ta wypisuje od razu dane wyszukanej osoby/kursu)
	 ------------------------------------------------------------------------------------------------
	*/
	
	//szukanie pracownika
	public int szukajPracownikImie(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikUczelni)
			{
				PracownikUczelni P = (PracownikUczelni) U.get(i);
				if (P.getImie().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajPracownikNazwisko(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikUczelni)
			{
				PracownikUczelni P = (PracownikUczelni) U.get(i);
				if (P.getNazwisko().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajPracownikStaz(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikUczelni)
			{
				PracownikUczelni P = (PracownikUczelni) U.get(i);
				if (P.getStaz()==Integer.parseInt(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajPracownikStanowisko(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikUczelni)
			{
				PracownikUczelni P = (PracownikUczelni) U.get(i);
				if (P.getStanowisko().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajPracownikNadgodziny(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikAdministracyjny)
			{
				PracownikAdministracyjny P = (PracownikAdministracyjny) U.get(i);
				if (P.getNadgodziny() == Integer.parseInt(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajPracownikPensja(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof PracownikUczelni)
			{
				PracownikUczelni P = (PracownikUczelni) U.get(i);
				if (P.getPensja()==Integer.parseInt(s))
					return i;
			}
		}
		return -1;
	}
	
	//szukanie studenta
	public int szukajStudentImie(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof Student)
			{
				Student P = (Student) U.get(i);
				if (P.getImie().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajStudentNazwisko(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof Student)
			{
				Student P = (Student) U.get(i);
				if (P.getNazwisko().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajStudentPESEL(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof Student)
			{
				Student P = (Student) U.get(i);
				if (P.getPESEL().equals(s))
					return i;
			}
		}
		return -1;
	}
	public int szukajStudentIndeks(String s)
	{
		for(int i=0; i<U.size(); i++)
		{
			if (U.get(i) instanceof Student)
			{
				Student P = (Student) U.get(i);
				if (P.getIndeks().equals(s))
					return i;
			}
		}
		return -1;
	}
	
	//szukanie kursu
	public int szukajKursNazwa(String s)
	{
		for(int i=0; i<K.size(); i++)
		{
			if (K.get(i).getNazwa().equals(s))
					return i;
		}
		return -1;
	}
	public int szukajKursImie(String s)
	{
		for(int i=0; i<K.size(); i++)
		{
			if (K.get(i).getProwadzacy().getImie().equals(s))
					return i;
		}
		return -1;
	}
	public int szukajKursNazwisko(String s)
	{
		for(int i=0; i<K.size(); i++)
		{
			if (K.get(i).getProwadzacy().getNazwisko().equals(s))
					return i;
		}
		return -1;
	}
	public int szukajKursECTS(String s)
	{		
		for(int i=0; i<K.size(); i++)
		{
			if (K.get(i).getECTS() == Integer.parseInt(s))
				return i;
		}
		return -1;
	}
	
	/*
	 ---------------------------------------
	 		wypisywanie os�b/kurs�w
	 ---------------------------------------
	*/
	//wypisanie ca�ej uczelni (podstawowe dane uczelnia)
	public void wypiszOsoby()
	{
		System.out.println("\n-------------------------------------\nPodstawowe dane wszystkich os�b w uczelni\n-------------------------------------");
		for (int i=0; i<U.size(); i++)
			System.out.println(U.get(i).podstawoweDaneUczelnia());
	}
	//wypisanie uczelni zaczynaj�c od pracownik�w (podstawowe dane uczelnia)
	public void wypiszOsobyPoKolei()
	{
		System.out.println("\n-------------------------------------\nPodstawowe dane wszystkich os�b w uczelni, poczynaj�c od pracownik�w\n-------------------------------------");
		for (int i=0; i<U.size(); i++)
			if(U.get(i) instanceof PracownikUczelni)
				System.out.println(U.get(i).podstawoweDaneUczelnia());
		for (int i=0; i<U.size(); i++)
			if(U.get(i) instanceof Student)
				System.out.println(U.get(i).podstawoweDaneUczelnia());
	}
	//wypisanie kurs�w
	public void wypiszKursy()
	{
		System.out.println("\n-------------------------------------\nWszystkie dost�pne na uczelni kursy\n-------------------------------------");
		for (int i=0; i<K.size(); i++)
			System.out.println(K.get(i).toString());
	}
	
	/*
	 ---------------------------------------------
	 		Dodawanie osoby/kursu Scannerem (Etap 1)
	 ---------------------------------------------
	*/
	public void addBaza()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\n-------------------------------------\nDodawanie czego� do uczelni\n-------------------------------------");
		System.out.println("UWAGA!!! Ta metoda sprawdza jedynie poprawno�� typu wprowadzonych danych!\n"
				+ "Na ka�de zapytanie o dane wprowadz je bez spacji, to co wpiszesz zale�y od Ciebie wi�c za dziwne imiona, czy PESELe nie jest odpowiedzialny program!");
		System.out.println("Mo�esz doda� dowoln� osob�/kurs do uczelni, wybierz, co chcesz doda�:\n1. Pracownik\n2. Student\n3. Kurs");
		try
		{
			int f = scan.nextInt();
			switch(f)
			{
			case 1:
				newPracownik(scan);
				break;
			case 2:
				newStudent(scan);
				break;
			case 3:
				newKurs(scan);
				break;
			default:
				System.out.println("Podano niew�a�ciwe dane");
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("Podano niew�a�ciwe dane");
		}
		catch(Exception e)
		{
			System.out.println("Wyst�pi� b��d");
		}
		finally
		{
			System.out.println("\n-----Koniec metody dodawania czego� do uczelni-----");
		}
	}
	
	private void newPracownik(Scanner scan)
	{
		try
		{
			System.out.print("UWAGA! Je�li co� p�jdzie nie tak (np. wprowadzone zostan� niepoprawne dane), program poinformuje Ci� o tym, zako�czy prac� i nie doda pracownika do uczelni!\nWprowadzaj po kolei dane pracownika:");
			System.out.print("\nImi�: "); String imie = scan.next();
			System.out.print("\nNazwisko: "); String nazw = scan.next();
			System.out.print("\nPESEL: "); String PES = scan.next();
			System.out.print("\nWiek: "); int wiek = scan.nextInt();
			System.out.print("\nP�e�: "); String plec = scan.next();
			System.out.print("\nStanowisko: "); String stan = scan.next();
			System.out.print("\nSta�: "); int staz = scan.nextInt();
			System.out.print("\nPensja: "); int pensja = scan.nextInt();
			System.out.print("\n\nTeraz wybierz, jaki ma to by� pracownik:\n1. Administracyjny\n2. Badawczo-Dydaktyczny"); int ktory = scan.nextInt();
			switch(ktory)
			{
			case 1:
				System.out.print("\nWpisz ilo�� nadgodzin: "); int nad = scan.nextInt();
				U.add(new PracownikAdministracyjny(imie,nazw,PES,wiek,plec,stan,staz,pensja,nad));
				System.out.print("\nPracownik pomy�lnie dodany do uczelni");
				break;
			case 2:
				System.out.print("\nWpisz punktacj� z dorobku naukowego: "); int pkt = scan.nextInt();
				U.add(new PracownikBadawczoDydaktyczny(imie,nazw,PES,wiek,plec,stan,staz,pensja,pkt));
				System.out.print("\nPracownik pomy�lnie dodany do uczelni");
				break;
			default:
				System.out.print("Co� posz�o nie tak...");
			}
		}
		catch (Exception e)
		{
			System.out.println("Co� posz�o nie tak...");
		}
	}
	private void newStudent(Scanner scan)
	{
		try
		{
			System.out.print("UWAGA! Je�li co� p�jdzie nie tak (np. wprowadzone zostan� niepoprawne dane), program poinformuje Ci� o tym, zako�czy prac� i nie doda studenta do uczelni!\nWprowadzaj po kolei dane studenta:");
			System.out.print("\nImi�: "); String imie = scan.next();
			System.out.print("\nNazwisko: "); String nazw = scan.next();
			System.out.print("\nPESEL: "); String PES = scan.next();
			System.out.print("\nWiek: "); int wiek = scan.nextInt();
			System.out.print("\nP�e�: "); String plec = scan.next();
			System.out.print("\nIndeks: "); int ind = scan.nextInt();
			System.out.print("\nIlo�� kurs�w, na kt�re jest zapisany (kursy trzeba wprowadzi� p�zniej r�cznie): "); int kursy = scan.nextInt();
			System.out.print("\nTeraz przechodzimy do pyta� typu \"tak lub nie\", je�li Twoj� odpowiedzi� jest \"tak\", wpisz T, w ka�dym innym wypadku program uzna, �e odpowiedzi� jest \"nie\"");
			System.out.print("\nCzy student jest uczestnikime programu ERASMUS? "); String ER = scan.next();
			System.out.print("\nCzy student jest studentem I-stopnia studi�w? "); String st1 = scan.next();
			System.out.print("\nCzy student jest studentem II-stopnia studi�w? "); String st2 = scan.next();
			System.out.print("\nCzy student jest studentem studi�w stacjonarnych? "); String sta = scan.next();
			System.out.print("\nCzy student jest studentem studi�w niestacjonarnych? "); String nsta = scan.next();
			U.add(new Student(imie,nazw,PES,wiek,plec,ind,kursy,ER.equals("T")?true:false,st1.equals("T")?true:false,st2.equals("T")?true:false,sta.equals("T")?true:false,nsta.equals("T")?true:false));
			System.out.print("\nStudent pomy�lnie dodany do uczelni, pami�taj o wprowadzeniu jego kurs�w!");
		}
		catch (Exception e)
		{
			System.out.println("Co� posz�o nie tak...");
		}
	}
	private void newKurs(Scanner scan)
	{
		try
		{
			System.out.println("UWAGA! Je�li co� p�jdzie nie tak (np. wprowadzone zostan� niepoprawne dane), program poinformuje Ci� o tym, zako�czy prac� i nie doda Kursu do uczelni!\nChcesz doda� nowego pracownika, kt�ry b�dzie prowadzi� kurs (je�li tak, to wpisz T), czy wolisz stworzy� sam kurs nie posiadaj�cy prowadz�cego, co mo�e prowadzi� p�zniej do b��d�w?");
			String czy = scan.next();
			if(czy.equals("T"))
			{
				System.out.print("\nImi�: "); String imie = scan.next();
				System.out.print("\nNazwisko: "); String nazw = scan.next();
				System.out.print("\nPESEL: "); String PES = scan.next();
				System.out.print("\nWiek: "); int wiek = scan.nextInt();
				System.out.print("\nP�e�: "); String plec = scan.next();
				System.out.print("\nStanowisko: "); String stan = scan.next();
				System.out.print("\nSta�: "); int staz = scan.nextInt();
				System.out.print("\nPensja: "); int pensja = scan.nextInt();
				System.out.print("\nWpisz punktacj� z dorobku naukowego: "); int pkt = scan.nextInt();
				PracownikBadawczoDydaktyczny x = new PracownikBadawczoDydaktyczny(imie,nazw,PES,wiek,plec,stan,staz,pensja,pkt);
				U.add(x);
				System.out.print("\nProwadz�cy kurs zosta� dodany do uczelni, przejdzmy teraz do tworzenia kursu!");
				System.out.print("\nPodaj nazw� kursu: "); String nazwa = scan.next();
				System.out.print("\nPodaj ilo�� punkt�w ECTS za kurs: "); int ECTS = scan.nextInt();
				K.add(new Kursy(nazwa,x,ECTS));
				System.out.print("\nKurs pomy�lnie dodany do uczelni!");
			}
			else
			{
				System.out.print("\nPodaj nazw� kursu: "); String nazwa = scan.next();
				System.out.print("\nPodaj ilo�� punkt�w ECTS za kurs: "); int ECTS = scan.nextInt();
				Kursy x1 = new Kursy();
				x1.setNazwa(nazwa);
				x1.setECTS(ECTS);
				K.add(x1);
				System.out.println("\nKurs pomy�lnie dodany do uczelni!\n!!!Pami�taj o dodaniu do niego prowadz�cego!!!");
			}
		}
		catch (Exception e)
		{
			System.out.println("Co� posz�o nie tak...");
		}
	}
	
	/*
	 ------------------------------------------------
	 		Zapis do(lub odczyt z) pliku (Etap 2)
	 ------------------------------------------------
	*/
	//zapisz
	public void zapiszOsoby(String s)
	{
		try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(s)))
		{
			so.writeObject(U);
		}
		catch (Exception e)
		{
			System.out.println("B��d przy zapisywaniu");
			e.printStackTrace();
		}		
	}
	public void zapiszKursy(String s)
	{
		try (ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(s)))
		{
			so.writeObject(K);
		}
		catch (Exception e)
		{
			System.out.println("B��d przy zapisywaniu");
			e.printStackTrace();
		}		
	}
	//wczytaj
	public void wczytajOsoby(String s)
	{
		wczytajTemp=0;
		try (ObjectInputStream si = new ObjectInputStream(new FileInputStream(s)))
		{
			Object x=si.readObject();
			if (x instanceof ArrayList<?>)
			{
				this.U=(ArrayList<Osoba>)x;
				wczytajTemp++;
			}
		}
		catch (Exception e)
		{
			wczytajTemp=0;
			System.out.println("B��d przy odczytywaniu");
			e.printStackTrace();
		}	
	}
	public void wczytajKursy(String s)
	{
		wczytajTemp=0;
		try (ObjectInputStream si = new ObjectInputStream(new FileInputStream(s)))
		{
			Object x=si.readObject();
			if (x instanceof ArrayList<?>)
			{
				this.K=(ArrayList<Kursy>)x;
				wczytajTemp++;
			}
		}
		catch (Exception e)
		{
			wczytajTemp=0;
			System.out.println("B��d przy odczytywaniu");
			e.printStackTrace();
		}
	
	}
	
	/*
	 ----------------------------------
	 		Komparatory (Etap 3)
	 ----------------------------------
	*/
	//klasy wewn�trzne
	class Compare_nazw implements Comparator<Osoba>
	{
		public int compare(Osoba o1, Osoba o2)
		{
			return o1.getNazwisko().compareTo(o2.getNazwisko());
		}
	}
	class Compare_nazw_im implements Comparator<Osoba>
	{
		public int compare(Osoba o1, Osoba o2)
		{
			if(o1.getNazwisko().equals(o2.getNazwisko()))
				return o1.getImie().compareTo(o2.getImie());
			else
				return 0;
		}
	}
	class Compare_nazw_wiek implements Comparator<Osoba>
	{
		public int compare(Osoba o1, Osoba o2)
		{
			if(o1.getNazwisko().equals(o2.getNazwisko()) && o1.getWiek()<o2.getWiek()) return -1;
			if(o1.getNazwisko().equals(o2.getNazwisko()) && o1.getWiek()>o2.getWiek()) return 1;
			return 0;			
		}
	}
	class CompareK_ECTS implements Comparator<Kursy>
	{
		public int compare(Kursy k1, Kursy k2)
		{
			if(k1.getECTS()<k2.getECTS()) return -1;
			if(k1.getECTS()>k2.getECTS()) return 1;
			return 0;			
		}
	}
	class CompareK_nazw implements Comparator<Kursy>
	{
		public int compare(Kursy k1, Kursy k2)
		{
			if (k1!=null && k2!=null)
				return k1.getProwadzacy().getNazwisko().compareTo(k2.getProwadzacy().getNazwisko());
			return 0;
		}
	}
	
	//metody sortuj�ce
	public void sortNazw()
	{
		Collections.sort(U, comp1);
	}
	public void sortNazwIm()
	{
		Collections.sort(U, comp1);
		Collections.sort(U, comp2);
	}
	public void sortNazwWiek()
	{
		Collections.sort(U, comp1);
		Collections.sort(U, comp3);
	}
	public void sortKects()
	{
		Collections.sort(K, comp4);
	}
	public void sortKnazw()
	{
		Collections.sort(K, comp5);
	}
	
	/*
	 ----------------------------------
	 		Usuwanie (Etap 4)
	 ----------------------------------
	*/
	//usuwanie pracownika
	public void usunPracownikImie(String s)
	{
		us.usunPracownikImie(s, this);
	}
	public void usunPracownikNazwisko(String s)
	{
		us.usunPracownikNazwisko(s, this);
	}
	public void usunPracownikStaz(String s)
	{
		us.usunPracownikStaz(s, this);
	}
	public void usunPracownikStanowisko(String s)
	{
		us.usunPracownikStanowisko(s, this);
	}
	
	//usuwanie studenta
	public void usunStudentImie(String s)
	{
		us.usunStudentImie(s, this);
	}
	public void usunStudentNazwisko(String s)
	{
		us.usunStudentNazwisko(s, this);
	}
	public void usunStudentIndeks(String s)
	{
		us.usunStudentIndeks(s, this);
	}
	
	//usuwanie kursu
	public void usunKursECTS(String s)
	{
		us.usunKursECTS(s, this);
	}
	public void usunKursNazwisko(String s)
	{
		us.usunKursNazwisko(s, this);
	}
	
	/*
	-----------------------------------
			Metody do etapu 5
	-----------------------------------
	*/
	@Override
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("------------------------------------------------\n\tOsoby:\n------------------------------------------------\n");
		for (int i=0; i<U.size(); i++)
			S.append(U.get(i).podstawoweDaneUczelnia()+"\n");
		S.append("------------------------------------------------\n\tKursy\n------------------------------------------------\n");
		for (int i=0; i<K.size(); i++)
			S.append(K.get(i).toString()+"\n");
		return S.toString();
	}
	
	/*
	----------------------	
			Lista 7
	----------------------
	*/
	
	public void usunHash()
	{
		HashSet<Osoba> temp = new HashSet<>();
		for(Osoba o : U)
			temp.add(o);
		U = new ArrayList<Osoba>();
		for(Osoba o : temp)
		{
			U.add(o);
		}
	}
}