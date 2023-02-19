package pack;

public class Magazyn
{
	private LinkedQueue<Klient> klienci;
	
	//konstruktor
	public Magazyn()
	{
		this.klienci = new LinkedQueue<>();
	}
	
	//geter i seter
	public LinkedQueue<Klient> getKlienci()
	{
		return klienci;
	}
	public void setKlienci(LinkedQueue<Klient> klienci)
	{
		this.klienci = klienci;
	}
	
	//metody
	public double suma_kwot()
	{
		double x=0;
		try
		{
			while(!klienci.isEmpty())
			{
				x+=klienci.dequeue().kwotaKlienta();
			}
		}
		catch(Exception e)
		{
			System.out.println("Blad przy liczeniu kwoty do zaplaty, zwracam 0");
			x=0;
			e.printStackTrace();
		}
		System.out.println("Suma kwot klientow = "+x);
		return x;
	}
	public void addKlient(Klient k)
	{
		klienci.enqueue(k);
	}
}
