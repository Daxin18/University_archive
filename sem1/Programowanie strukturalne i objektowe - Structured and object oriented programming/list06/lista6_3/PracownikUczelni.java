package lista6_3;

public abstract class PracownikUczelni extends Osoba
{
	private String stanowisko;
	private int staz;
	private int pensja;
	
	//konstruktory
	public PracownikUczelni()
	{
		super();
		this.stanowisko="nikt";
		this.staz=1;
		this.pensja=1000;
	}
	public PracownikUczelni(String imie, String nazwisko, String PESEL, int wiek, String plec, String stanowisko, int staz, int pensja)
	{
		super(imie, nazwisko, PESEL, wiek, plec);
		this.stanowisko=stanowisko;
		this.staz=staz;
		this.pensja=pensja;
	}
	
	//getery
	public String getStanowisko()
	{
		return stanowisko;
	}
	public int getStaz()
	{
		return staz;
	}
	public int getPensja()
	{
		return pensja;
	}
	
	//setery
	public void setStanowisko(String s)
	{
		this.stanowisko=s;
	}
	public void setStaz(int s)
	{
		this.staz=s;
	}
	public void setPensja(int s)
	{
		this.pensja=s;
	}
	
	//metody
	public String podstawoweDaneUczelnia()
	{
		return ("Pracownik, " + podstawoweDaneOsobowe() + ", " + stanowisko);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof PracownikUczelni)
		{
			PracownikUczelni p = (PracownikUczelni) o;
			if(this.getPESEL().equals(p.getPESEL()))
				return true;
		}
		return false;
	}
	
	public int hashCode()
	{
		final int prime=31;
		int result = 1;
		result = prime * result + ((this.getPESEL() == null) ? 0 : this.getPESEL().hashCode());
		return result;
	}
}