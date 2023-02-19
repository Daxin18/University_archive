package lista6_3;
import java.io.*;

public abstract class Osoba implements Serializable
{
	private static final long serialVersionUID = 234567890;
	private String imie;
	private String nazwisko;
	private String PESEL;
	private int wiek;
	private String plec;
	
	//konstruktory
	public Osoba()
	{
		this.imie="Jan";
		this.nazwisko="Kowalski";
		this.PESEL="12345678901";
		this.wiek=32;
		this.plec="M";
	}
	public Osoba(String imie, String nazwisko, String PESEL, int wiek, String plec)
	{
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.PESEL=PESEL;
		this.wiek=wiek;
		this.plec=plec;
	}
	
	//getery
	public String getImie()
	{
		return imie;
	}
	public String getNazwisko()
	{
		return nazwisko;
	}
	public String getPESEL()
	{
		return PESEL;
	}
	public String getPlec()
	{
		return plec;
	}
	public int getWiek()
	{
		return wiek;
	}
	
	//setery
	public void setImie(String s)
	{
		this.imie=s;
	}
	public void setNazwisko(String s)
	{
		this.nazwisko=s;
	}
	public void setPESEL(String s)
	{
		this.PESEL=s;
	}
	public void setPlec(String s)
	{
		this.plec=s;
	}
	public void setWiek(int s)
	{
		this.wiek=s;
	}
	
	//metody publiczne
	public String podstawoweDaneOsobowe()
	{
		return (nazwisko + " " + imie);
	}
	public String daneOsobowe()
	{
		StringBuilder S = new StringBuilder();
		S.append(imie + " " + nazwisko + ", PESEL: " + PESEL + ", wiek: ");
		S.append(Integer.toString(wiek));
		S.append(", plec: " + plec);
		return S.toString();
	}
	
	//metody abstrakcyjne
	abstract String wszystkieDane();
	abstract String podstawoweDaneUczelnia();
}