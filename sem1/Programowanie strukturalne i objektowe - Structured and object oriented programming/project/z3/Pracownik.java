package z3;

public class Pracownik
{
	private String nazwisko;
	private String imie;
	private Wynagrodzenie wynagrodzenie;
	
	//konstruktory
	public Pracownik()
	{
		this.imie = "Jan";
		this.nazwisko = "Kowalski";
		this.wynagrodzenie = new Etatowy();
	}
	public Pracownik(String imie, String nazwisko, Wynagrodzenie w)
	{
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wynagrodzenie = w;
	}
	//seter na wynagrodzenie
	public void setWynagrodzenie(Wynagrodzenie w)
	{
		this.wynagrodzenie = w;
	}
	public void wynik(double x, double y)
	{
		System.out.println("Wynagrodzenie tego pracownika to: " + wynagrodzenie.Oblicz(x,y) + "zl");
	}
	
	public static void main(String args[])
	{
		Pracownik p = new Pracownik();
		p.wynik(1000, 10);
		p.setWynagrodzenie(new UmowaZlecenie());
		p.wynik(1000, 10);
	}
}
