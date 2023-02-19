//260413 Kamil Ciaglo
package pack;

public class Zaliczenie
{
	private String przedmiot;
	private double ocena;
	
	public Zaliczenie(String p, double o)
	{
		this.przedmiot = p;
		this.ocena = o;
	}
	public String getPrzedmiot()
	{
		return przedmiot;
	}
	public double getOcena()
	{
		return ocena;
	}
}
