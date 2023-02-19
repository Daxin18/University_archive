package pack;
import java.io.*;

public abstract class Pracownik implements Serializable
{
	static final long serialVersionUID = 1234353557;
	//pola klasy
	protected String nazwisko;
	protected String imie;
	protected long pesel;
	protected String stanowisko;
	protected int staz;
	
	//konstruktory
	public Pracownik()
	{
		this.nazwisko = "Kowalski";
		this.imie = "Jan";
		this.pesel = 11234567890L;
		this.stanowisko = "Pracownik_domyslny";
		this.staz = 1;	
	}
	public Pracownik(String nazw, String im, long pesel, String stan, int staz)
	{
		this.nazwisko = nazw;
		this.imie = im;
		this.pesel = pesel;
		this.stanowisko = stan;
		this.staz = staz;	
	}
	
	//getery i setery
	public String getNazwisko()
	{
		return nazwisko;
	}
	public String getImie()
	{
		return imie;
	}
	public long getPesel()
	{
		return pesel;
	}
	public String getStanowisko()
	{
		return stanowisko;
	}
	public int getStaz()
	{
		return staz;
	}
	
	public void setNazwisko(String s)
	{
		this.nazwisko = s;
	}
	public void setImie(String s)
	{
		this.imie = s;
	}
	public void setPesel(long l)
	{
		this.pesel = l;
	}
	public void setStanowisko(String s)
	{
		this.stanowisko = s;
	}
	public void setStaz(int i)
	{
		this.staz = i;
	}
	
	//metody
	public void wyswietl()
	{
		System.out.printf("| %-12s | %-12s | %011d | %-20s | %5d | %6.2f |%n", nazwisko, imie, pesel, stanowisko, staz, pensja());
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s, %011d, %s, %d, %3.2f", nazwisko, imie, pesel, stanowisko, staz, pensja());
	}
	public String wzorToString()
	{
		return "nazwisko, imie, pesel, stanowisko, staz, pensja";
	}
	//metody abstrakcyjne
	abstract double pensja();
	
}
