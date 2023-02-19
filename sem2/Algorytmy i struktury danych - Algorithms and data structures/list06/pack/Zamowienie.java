package pack;

public class Zamowienie
{
	private String nazwa;
	private int ilosc;
	private double cena;
	
	//konstruktory
	public Zamowienie()
	{
		this.nazwa = "nic";
		this.ilosc = 1;
		this.cena = 1;
	}
	public Zamowienie(String s, int i, double d)
	{
		this.nazwa = s;
		this.ilosc = i;
		this.cena = d;
	}
	
	//getery i setery
	public String getNazwa()
	{
		return nazwa;
	}
	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}
	public int getIlosc()
	{
		return ilosc;
	}
	public void setIlosc(int ilosc)
	{
		this.ilosc = ilosc;
	}
	public double getCena()
	{
		return cena;
	}
	public void setCena(double cena)
	{
		this.cena = cena;
	}
	
	//metody
	@Override
	public String toString()
	{
		return "Zamowienie [nazwa: " + nazwa + ", ilosc: " + ilosc + ", cena: " + cena + "]";
	}
	public double kwota()
	{
		System.out.println("\t"+toString()); //<--mozna to odkomentowac zeby zobaczyc zamowienia
		return ilosc*cena;
	}
	
}
