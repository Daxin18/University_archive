package lista6_3;

public class PracownikAdministracyjny extends PracownikUczelni
{
	private int nadgodziny;
	
	//konstruktory
	public PracownikAdministracyjny()
	{
		super();
		this.nadgodziny=0;
	}
	public PracownikAdministracyjny(String imie, String nazwisko, String PESEL, int wiek, String plec, String stanowisko, int staz, int pensja, int nadgodziny)
	{
		super(imie, nazwisko, PESEL, wiek, plec, stanowisko, staz, pensja);
		this.nadgodziny=nadgodziny;
	}
	
	//geter
	public int getNadgodziny()
	{
		return nadgodziny;
	}
	
	//seter
	public void setNadgodziny(int x)
	{
		this.nadgodziny=x;
	}
	
	//metody
	public String wszystkieDane()
	{
		String s = "Pracownik Administracyjny: " + this.daneOsobowe() + ", pracuje na stanowisku: " + getStanowisko() +
		", ze stazem " + Integer.toString(getStaz()) + " lat, zarabia:" + Integer.toString(getPensja()) + ", nadgodziny: " + Integer.toString(nadgodziny);		
		return s;
	}
	
}
