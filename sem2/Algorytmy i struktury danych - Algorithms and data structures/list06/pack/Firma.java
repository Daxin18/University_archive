package pack;

public class Firma
{
	private Magazyn[] magazyny;
	
	//konstruktory
	public Firma()
	{
		this.magazyny = new Magazyn[3];
	}
	public Firma(int n)
	{
		this.magazyny = new Magazyn[n];
	}
	
	//geter i seter
	public Magazyn[] getMagazyny()
	{
		return magazyny;
	}

	public void setMagazyny(Magazyn[] magazyny)
	{
		this.magazyny = magazyny;
	}
	
	//metody
	public void addMagazyn(Magazyn m)
	{
		for(int i=0; i<magazyny.length; i++)
		{
			if(magazyny[i]==null)
			{
				magazyny[i]=m;
				break;
			}
		}
	}
	public double zlicz_przychody()
	{
		double x=0;
		System.out.println("\nRealizuje zamowienia z poszczegolnych magazynow!\n");
		int i=1;
		for(Magazyn m: magazyny)
		{
			System.out.println("\nMagazyn numer "+i+":\n");
			x+=m.suma_kwot();
			i++;
		}
		System.out.println("\n\nLaczne przychody firmy: "+x+""
				+ "\n==================================================");
		return x;
	}
	
}
