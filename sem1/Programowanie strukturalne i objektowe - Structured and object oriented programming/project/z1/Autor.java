package z1;

public class Autor implements Comparable<Autor>
{
	private String imie;
	private String nazwisko;
	
	//konstruktory
	public Autor()
	{
		this.imie = "Jan";
		this.nazwisko = "Kowalski";
	}
	public Autor(String i, String n)
	{
		this.imie = i;
		this.nazwisko = n;
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
	//setery
	public void setImie(String s)
	{
		this.imie = s;
	}
	public void setNazwisko(String s)
	{
		this.nazwisko = s;
	}
	
	//metody
	@Override
	public String toString()
	{
		return (imie +" "+ nazwisko);
	}
	@Override
	public int compareTo(Autor a1)
	{
		return this.toString().compareTo(a1.toString());
	}
}
