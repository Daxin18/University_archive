package pack;

public class PracownikGodzinowy extends Pracownik
{
	//pola klasy
	private double stawka;
	private int liczba_godz;
	
	//konstruktory
	public PracownikGodzinowy()
	{
		super();
		this.stawka = 10;
		this.liczba_godz = 160;
	}
	public PracownikGodzinowy(String nazw, String im, long pesel, String stan, int staz, double stawka, int godziny)
	{
		super(nazw,im,pesel,stan,staz);
		this.stawka=stawka;
		this.liczba_godz=godziny;
	}
	
	//getery i setery
	public int getLiczba_godz()
	{
		return liczba_godz;
	}
	public double getStawka()
	{
		return stawka;
	}
	
	public void setLiczba_godz(int i)
	{
		this.liczba_godz = i;
	}
	public void setStawka(double d)
	{
		this.stawka = d;
	}
	
	
	//metody
	public double pensja()
	{
		return stawka*liczba_godz;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s, %011d, %s, %d, %3.2f, %d, %3.2f", nazwisko, imie, pesel, stanowisko, staz, stawka, liczba_godz, pensja());
	}
	public String wzorToString()
	{
		return "nazwisko, imie, pesel, stanowisko, staz, stawka, liczba_godzin, pensja";
	}
}
