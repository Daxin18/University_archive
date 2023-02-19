package lista6_3;

public class PracownikBadawczoDydaktyczny extends PracownikUczelni
{
	private int punktacja;
	
	//konstruktory
	public PracownikBadawczoDydaktyczny()
	{
		super();
		this.punktacja = 0;
	}
	public PracownikBadawczoDydaktyczny(String imie, String nazwisko, String PESEL, int wiek, String plec, String stanowisko, int staz, int pensja, int punktacja)
	{
		super(imie, nazwisko, PESEL, wiek, plec, stanowisko, staz, pensja);
		this.punktacja = punktacja;
	}
	
	//geter
	public int getPunktacja()
	{
		return punktacja;
	}
	//seter
	public void setPunktacja(int x)
	{
		this.punktacja = x;
	}
	
	//metody
	public String wszystkieDane()
	{
		String s = "Pracownik Badawczo-Dydaktyczny: " + this.daneOsobowe() + "\nPracuje na stanowisku: " + getStanowisko() +
		", ze stazem " + Integer.toString(getStaz()) + " lat, zarabia:" + Integer.toString(getPensja()) + ", punktacja z dorobku naukowego: " + Integer.toString(punktacja);		
		return s;
	}
	
}
