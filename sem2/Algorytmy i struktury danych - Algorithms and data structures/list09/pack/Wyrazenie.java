package pack;

public class Wyrazenie
{
	private String oryginal;
	private String ONP;
	private ONP_BT onp;
	
	//pola pomocnicze - do zamiany na ONP
	private int TEMP = 0;
	private int ONPi = 0;
	private StringBuilder S = new StringBuilder();
	
	//konstruktor
	public Wyrazenie(String s)
	{
		this.ONPi=0;
		this.oryginal = s;
		this.ONP = toONP(s);
		this.onp = new ONP_BT(ONP);
	}
	//geter do testow
	public String getONP()
	{
		return ONP;
	}
	
	//metody
	public String toONP(String s)
	{
		//dopoki ONPi ("i dla ONP") jest mniejsze niz dlugosc stringa to sprawdzamy czym jest char na danym miejscu
		//i wykonujemy odpowiednie metody, ktore zwracaja indeks na ktorym skonczyly (bo one same buduja StringBuildera)
		//zwrocona przez metode wartosc przypisujemy zmiennej ONPi, aby nie sprawdzac drugi raz przerobionych char'ow
		while(ONPi<s.length())
		{
			if(isNumber(s.charAt(ONPi))) ONPi=Number(ONPi, S, s);
			if(isOperator(s.charAt(ONPi))) ONPi=Operator(ONPi, S, s);
			if(isBracket(s.charAt(ONPi))) ONPi=Bracket(ONPi, S, s);
			ONPi++;
		}
		return S.toString();
	}
	//metoda Number obsluguje numery (cyfry)
	private int Number(int i, StringBuilder S, String s)
	{
		//petla for sprawdza, czy kolejny char jest tez cyfra
		for(int j=i; j<s.length(); j++)
		{
			i=j;
			if(isNumber(s.charAt(j)))
			{
				S.append(s.charAt(j));	//jesli jest, to zapisuje go do StringBuildera
			}
			else
			{
				break;	//jesli nie, przerywa petle
			}
		}
		S.append(' ');	//po dopisaniu cyfry, dopisujemy "spacje"
		if(i<s.length()-1)	//warunek jest potrzebny do przypadku gdy mamy np "2+12", bo w metodzie operatora moze sie wywalic
			return --i;	//zwracamy wartosc o 1 mniejsza niz ostatni znak, ktory nie byl cyfra, aby inne metody nie 
						//zwrocily potem true, a wykonaly sie przy kolejnej iteracji po petli while w toONP albo inBracketString
						//dalej opisuje ja jako petla M
		return i;	//jesli i jest ostatnim indeksem stringa, to zwracamy czyste i, co zakonczy petle M bez dopisania nadmiarowych cyfr
	}
	//metoda obslugujaca operatory
	private int Operator(int i, StringBuilder S, String s)
	{
		char x = s.charAt(i);	//char x jest naszym "wykrytym" operatorem z petli M 
		for(int j=i+1; j<s.length(); j++)	//petla sprawdza nastepne char'y w poszukiwaniu najblizszego operatora
											//w obrebie tego samego nawiasu
		{
			i=j;
			char y = s.charAt(j);	//char y jest nastepnym sprawdzanym charem
			if(isNumber(y))
			{
				j=Number(i,S,s);	//jesli to numer, to dodajemy go do StringBuildera i przypisujemy j wartosc ostatniej cyfry numeru
									//(lub konca stringa jesli mamy taki przypadek)
									//nie przerywamy petli, bo szukamy nastepnego operatora...
			}
			if(isOperator(y))	//jesli to operator to trzeba sie nameczyc...
			{
				if(priority(x)==1 && priority(y)==2)	//sprawdzamy priorytet (opisany nizej)
														//jesli trafi nam sie np x='+',y='*' to musimy znowu wyszukac kolejnego operatora 
				{
					j = Operator(i,S,s)+1;	//wywolujemy metode dla operatora y i wartosc zwrocona zwiekszamy o 1
											//(aby uniknac bledow z podwojnym zapisem)
					i=j;		//wartosc i ustawiamy na j, aby zwrocic dobra wartosc
					break;		//wychodzimy z petli
				}
				else
					break;	//jesli mamy jakakolwiek inna sytuacje (np x='+' i y='-') to wychodzimy z petli
			}
			if(isBracket(y))	//jesli trafimy na nawias otwierajacy to obslugujemy go i przypisujemy j wartosc zwrocana
			{					//(patrz - index na ktorym konczy sie nawias)
				if(y=='(')
				{
					j=Bracket(i,S,s);
				}
				else
					break;	//jesli mamy jakikolwiek inny nawias - ignorujemy go i wychodzimy z petli (w sumie to nie pamietam czemu,
							//ale pewnie cos sie wywalalo bez tego, chociaz jesli bedziemy mieli zly zapis ONP, to drzewo sie nie utworzy)
			}
			i=j;	//przypisujemy znowu i wartosc j dla metod Number oraz Bracket (jesli np. petla mialaby sie skonczyc tutaj)
		}
		S.append(x);	//po obsluzeniu wszystkiego po drodze dopisujemy naszego operatora
		S.append(' ');	//ze spacja po nim
		if(i==s.length()-1) return i;	//zwracamy i jesli doszlismy do konca (ostatni index)
		return --i;	//oraz i-1 jesli nie
	}
	//metoda obslugujaca nawiasy - wyrazenie zawarte w nawiasie
	private int Bracket(int i, StringBuilder S, String s)
	{
		StringBuilder temp = new StringBuilder();	//tworzymy tymczasowego StringBuildera (dalej nazywany temp)
		int counter=1;	//zmienna counter liczy nam nawiasy, zaczynamy od wykrytego otwierajacego, wiec
						//zaczynamy z wartoscia 1
		for(int j=i+1; j<s.length(); j++)	//petla iteruje az do konca stringa zapisujac wszystko po drodze
											//w temp
		{
			i=j;	//ustawiamy i na j aby zapamietac gdzie konczymy
			char x = s.charAt(j);	//char x - sprawdzany aktualnie char
			
			//dwie operacje ponizej zapewniaja, ze jesli mamy nawiasy w nawiasie, to nic sie nie zepsuje
			if(x=='(') counter++;			//jesli x jest nawiasem otwierajacym - zwiekszamy counter
			else if(x==')') counter--;		//jesli jset zamykajacym - zmniejszamy
			
			if(counter>0) temp.append(x);	//jesli counter nie doszedl do 0, to dopisujemy x do temp
			else break;						//jesli tak, to przerywamy dopisywanie (w temp mamy cale wyrazenie z nawiasu)
		}
		inBracketString(S,temp.toString());	//wywolujemy metode obslugujaca wyrazenie w nawiasie (String temp.toString())
											//przekazujemy jej tez glownego StringBuildera, aby dopisywala do niego swoje
											//elementy
		return i;	//zwracamy i - index nawiasu zamykajacego, lub ostatni index stringa
					//(jesli brakowalo nawiasow zamykajacych zakladamy, ze mialy one byc na koncu)
	}
	//metoda obslugujaca Stringa w nawiasie
	//jest to w zasadzie kopia metody toONP, ale pracujaca na wlasnych, tymczasowych zmiennych
	//mimo to dopisuje juz do wlasciwego StringBuildera
	private void inBracketString(StringBuilder S, String s)
	{
		TEMP=0;
		while(TEMP<s.length())
		{
			if(isNumber(s.charAt(TEMP))) TEMP=Number(TEMP, S, s);
			if(isOperator(s.charAt(TEMP))) TEMP=Operator(TEMP, S, s);
			if(isBracket(s.charAt(TEMP))) TEMP=Bracket(TEMP, S, s);
			TEMP++;
		}
	}
	
	//priorytet (im wyzszy, tym wazniejszy znak/operator)
	//nawiasy w sumie porzucilem w trakcie pisania programu, ale niech ten ich wyzszy priorytet zostanie
	//IllegalArgumentException nie powinien nigdy wystapic w programie wyzej, ale jest, zeby nie musiec zwracac np.0 dla
	//ewentualnych anomalii
	private int priority(char s) throws IllegalArgumentException
	{
		if(s=='+' || s=='-') return 1;
		if(s=='*' || s=='/' || s=='%') return 2;
		if(s=='(' || s==')') return 3;
		throw new IllegalArgumentException();
	}
	//zwraca true dla operatora wspieranego przez program
	public static boolean isOperator(char s)
	{
		if(s=='+' || s=='-' || s=='*' || s=='/' || s=='%') return true;
		return false;
	}
	//zwraca true dla nawiasu OTWIERAJACEGO
	//wczesniej byl tu tez zamykajacy, ale chyba cos sie z nim psulo (?) nie pamietam juz, opisuje to
	//wszystko dopiero jak program zaczal dzialac dla wiekszosci przypadkow (wszystkich sprawdzanych)
	public static boolean isBracket(char s)
	{
		if(s=='(') return true;
		return false;
	}
	//zwraca true, jesli char jest cyfra
	public static boolean isNumber(char s)
	{
		if(s=='0' || s=='1' || s=='2' || s=='3' || s=='4' || s=='5' || s=='6' || s=='7' || s=='8' || s=='9') return true;
		return false;
	}
	
	//nazwa mowi sama za siebie - wypisuje co mialo byc w zadaniu
	//try-catch jest dla przypadku, gdy ONP bylo bledne
	//psuje sie wtedy drzewo, wiec dla bezpieczenstwa przy blednym drzewie ONP
	//ustawiam korzen na null (usuwam drzewo), moze to wyrzucic blad przy innych metodach, wiec
	//zostawiam tam tylko komunikat, zeby nic sie nie zepsulo
	public void wyswietlWszystkoCoMialoByc()
	{
		try
		{
			System.out.println("\nOryginalnie wpisane wyrazenie: "+ this.oryginal);
			System.out.println("\nInOrder i wynik: "); onp.wyswietlInOrder();
			System.out.println("\nPostOrder  wynik: "); onp.wyswietlPostOrder();
			System.out.println("\nIlosc lisci: "+ onp.liscie());
			System.out.println("Ilosc wezlow: "+ onp.wezly());
			System.out.println("Wysokosc drzewa: "+ onp.height());
			System.out.println("\nDrzewo wszerz: "); onp.wyswietlWszerz();
		}
		catch(Exception e)
		{
			System.out.println("Wystapil blad, drzewo nie zostalo utworzone poprawnie");
		}
	}
}
