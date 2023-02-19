package pack;

public class Klient
{
	private String nazwa;
	private LinkedQueue<Zamowienie> zamowienia;
	
	//konstruktory
	public Klient()
	{
		this.nazwa = "Jan Kowalski";
		this.zamowienia = new LinkedQueue<>();
	}
	public Klient(String s)
	{
		this.nazwa = s;
		this.zamowienia = new LinkedQueue<>();
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
	public LinkedQueue<Zamowienie> getZamowienia()
	{
		return zamowienia;
	}
	public void setZamowienia(LinkedQueue<Zamowienie> zamowienia)
	{
		this.zamowienia = zamowienia;
	}
	
	//metody
	public void addZamowienie(Zamowienie z)
	{
		zamowienia.enqueue(z);
	}
	
	public double kwotaKlienta()
	{
		double x=0;
		try
		{
			System.out.println("Realizuje zlecenie: "+nazwa+", ktore zawiera:"); //<--mozna to odkomentowac przy zamowieniach
			while(!zamowienia.isEmpty())
			{
				x+=zamowienia.dequeue().kwota();
			}
		}
		catch(Exception e)
		{
			System.out.println("Blad przy liczeniu kwoty do zaplaty, zwracam 0");
			x=0;
			e.printStackTrace();
		}
		System.out.println("Zlecenie zrealizowane: " + nazwa+", kwota do zaplaty: "+x);
		System.out.println(); //<--to tez
		return x;
	}
}
