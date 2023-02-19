package pack;

public class PracownikEtatowy extends Pracownik
{
	//pola klasy
	private double etat;
	private double stawka;
	
	//konstruktory
	public PracownikEtatowy()
	{
		super();
		this.etat=1;
		this.stawka=1600;
	}
	public PracownikEtatowy(String nazw, String im, long pesel, String stan, int staz, double etat, double stawka)
	{
		super(nazw,im,pesel,stan,staz);
		this.etat=etat;
		this.stawka=stawka;
	}
	
	//getery i setery
	public double getEtat()
	{
		return etat;
	}
	public double getStawka()
	{
		return stawka;
	}
	
	public void setEtat(double d)
	{
		this.etat = d;
	}
	public void setStawka(double d)
	{
		this.stawka = d;
	}
	
	//metody
	public double pensja()
	{
		return etat*stawka;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s, %s, %011d, %s, %d, %3.2f, %3.2f %3.2f", nazwisko, imie, pesel, stanowisko, staz, etat, stawka, pensja());
	}
	public String wzorToString()
	{
		return "nazwisko, imie, pesel, stanowisko, staz, etat, stawka, pensja";
	}
}
