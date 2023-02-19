package pack;
import java.io.*;

public class Pracownik implements Serializable
{
	private static final long serialVersionUID = 1234567120;
	
	private long pesel;
	private String nazwisko;
	private String imie;
	private Data data;
	private String stanowisko;
	private double pensja;
	private int staz;
	private double premia;
	
	//konstruktory
	public Pracownik()
	{
		this.pesel = 1;
		this.nazwisko = "Kowalski";
		this.imie = "Jan";
		this.data = new Data(1,1,1);
		this.stanowisko = "domyslny";
		this.pensja = 2000;
		this.staz = 10;
		this.premia = premiaDla(pensja,staz);
	}
	public Pracownik(long pesel)
	{
		this.pesel = pesel;
		this.nazwisko = "Kowalski";
		this.imie = "Jan";
		this.data = new Data(1,1,1);
		this.stanowisko = "domyslny";
		this.pensja = 2000;
		this.staz = 10;
		this.premia = premiaDla(pensja,staz);
	}
	public Pracownik(long pes, String nazw, String im, int d, int m, int y, String stan, double pen, int staz)
	{
		this.pesel = pes;
		this.nazwisko = nazw;
		this.imie = im;
		this.data = new Data(d,m,y);
		this.stanowisko = stan;
		this.pensja = pen;
		this.staz = staz;
		this.premia = premiaDla(pen, staz);
	}
	
	//metoda pomocnicza - premia dla pensji i stazu
	private double premiaDla(double pen, int staz)
	{
		if(staz>=20)
			return pen*0.2;
		else if(staz>=10)
			return pen*0.1;
		else 
			return 0;
	}
	
	//getery i setery
	public long getPesel()
	{
		return pesel;
	}
	public void setPesel(long pesel)
	{
		this.pesel = pesel;
	}
	
	public String getNazwisko()
	{
		return nazwisko;
	}
	public void setNazwisko(String nazwisko)
	{
		this.nazwisko = nazwisko;
	}
	
	public String getImie()
	{
		return imie;
	}
	public void setImie(String imie)
	{
		this.imie = imie;
	}
	
	public String getData()
	{
		return data.toString();
	}
	public void setData(int d, int m, int y)
	{
		this.data = new Data(d,m,y);
	}
	
	public String getStanowisko()
	{
		return stanowisko;
	}
	public void setStanowisko(String stanowisko)
	{
		this.stanowisko = stanowisko;
	}
	
	public double getPensja()
	{
		return pensja;
	}
	public void setPensja(double pensja)
	{
		this.pensja = pensja;
		this.premia = premiaDla(this.pensja, this.staz);
	}
	
	public int getStaz()
	{
		return staz;
	}
	public void setStaz(int staz)
	{
		this.staz = staz;
		this.premia = premiaDla(this.pensja, this.staz);
	}
	
	public double getPremia()
	{
		return premia;
	}
	
	//metody
	public double placa()
	{
		return pensja+premia;
	}
	
	public long comparePesel(Pracownik p)
	{
		return pesel-p.getPesel();
	}
	
	//metody przydatne do wyswietlania
	public String daneOsobowe()
	{
		return String.format("%011d", pesel) +"\t"+imie +" "+ nazwisko + "\t"+ data.toString();
	}
	public String podstawoweDaneOsobowe()
	{
		return imie +" "+ nazwisko;
	}
	public String pelneDanePracownika()
	{
		return daneOsobowe()+", \t"+stanowisko+", \tpensja: "+pensja+", \tstaz: "+staz+", \tpremia: "+premia+", \tplaca: "+placa();
	}
	public String danePracownika()
	{
		return daneOsobowe()+", \t"+stanowisko+", \tplaca:"+placa();
	}
	public String podstawoweDanePracownika()
	{
		return podstawoweDaneOsobowe()+", "+stanowisko;
	}
	@Override
	public String toString()
	{
		return this.podstawoweDaneOsobowe();
	}
	@Override
	public boolean equals(Object p)
	{
		if (p.equals(null))
			return false;
		Pracownik temp;
		if(p instanceof Pracownik)
		{
			temp = (Pracownik) p;
			return this.comparePesel(temp)==0;
		}
		else
			return false;
			
		
	}
	
	
}
