package pack;

public class Karta
{
	private int wartosc;
	private int kolor;
	private boolean znacznik;
	
	//konstruktory (domyslny - as kier, i przeciazony)
	public Karta()
	{
		this.wartosc = 1;
		this.kolor = 0;
		this.znacznik = true;
	}
	public Karta(int wart, int kol) throws IllegalArgumentException
	{
		//dla wartosci 14 - karty zakrytej mozemy dac dowolna wartosc i kolor, bo upewnilem sie, ze
		//metody porownujace, szukajace itp najpierw patrza na znacznik, a potem na kolor/wartosc
		if(wart==14)
		{
			this.wartosc = 1;
			this.kolor = 1;
			this.znacznik = false;
		}
		else if(wart>0 && wart<14)
		{
			this.wartosc = wart;
			this.znacznik = true;
			if(kol>=0 && kol<4)
				this.kolor = kol;
			else
				throw new IllegalArgumentException();
		}
		else
			throw new IllegalArgumentException();
	}
	
	//getery (seterow nie pisze, gdyz zmieniajac np wartosc mielibysmy inna karte)
	public int getWartosc()
	{
		return wartosc;
	}
	public int getKolor()
	{
		return kolor;
	}
	public boolean getZnacznik()
	{
		return znacznik;
	}

	//metody (String na wyswietlanie, compare do wstawiania kart na swoje miejsca)
	@Override
	public String toString()
	{
		if(znacznik)
		{
			StringBuilder S = new StringBuilder();
			switch(wartosc)
			{
			case 1:
				S.append("As ");
				break;
			case 11:
				S.append("Walet ");
				break;
			case 12:
				S.append("Dama ");
				break;
			case 13:
				S.append("Krol ");
				break;
			default:
				S.append(Integer.valueOf(wartosc).toString() + " ");
				break;
			}
			
			switch(kolor)
			{
			case 0:
				S.append("kier");
				break;
			case 1:
				S.append("karo");
				break;
			case 2:
				S.append("trefl");
				break;
			case 3:
				S.append("pik");
				break;
			default:
				S.append("Jesli to pojawi sie kiedykolwiek na konsoli, to cos mocno zepsulem");
				break;
			}
			return S.toString();
		}
		else
			return "()";
	}
	public int compare(Karta card)
	{
		if(znacznik && card.getZnacznik())
		{
			if(wartosc == card.getWartosc())
				return kolor-card.getKolor();
			else
				return wartosc-card.getWartosc();
		}
		else if(!card.getZnacznik() && !znacznik)
			return 0;
		else if(!card.getZnacznik())
			return -1;
		else
			return 1;

	}
	
	//metody statyczne uzywane przy wyswietlaniu
	public static String wartoscToString(int i)
	{
		if(i>0 && i<14)
		{
			switch(i)
			{
			case 1:
				return "As";
			case 11:
				return "Walet";
			case 12:
				return "Dama";
			case 13:
				return "Krol";
			default:
				return Integer.valueOf(i).toString();
			}
		}
		else
			return "wartosc nie istnieje albo zepsulem cos ze znacznikiem";
			
	}
	public static String kolorToString(int i)
	{
		switch(i)
		{
		case 0:
			return "kier";
		case 1:
			return "karo";
		case 2:
			return "trefl";
		case 3:
			return "pik";
		default:
			return "kolor nie istnieje albo zepsulem cos ze znacznikiem";
		}
	}
}
