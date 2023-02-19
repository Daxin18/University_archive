package lista6_3;
import java.io.*;

public class Kursy implements Serializable
{
	private static final long serialVersionUID = 3456789;
	private String nazwa;
	private PracownikUczelni prowadzacy;
	private int ECTS;
	
	//konstruktory
	public Kursy()
	{
		this.nazwa="Nie tak tworzy siê kurs";
		this.prowadzacy=null;
		this.ECTS=0;
	}
	public Kursy(String nazwa, PracownikUczelni prowadzacy, int ECTS)
	{
		this.nazwa=nazwa;
		this.prowadzacy=prowadzacy;
		this.ECTS=ECTS;
	}
	
	//getery
	public String getNazwa()
	{
		return nazwa;
	}
	public PracownikUczelni getProwadzacy()
	{
		return prowadzacy;
	}
	public int getECTS()
	{
		return ECTS;
	}
	
	//setery
	public void setNazwa(String s)
	{
		this.nazwa=s;
	}
	public void setProwadzacy(PracownikUczelni p)
	{
		this.prowadzacy=p;
	}
	public void setECTS(int x)
	{
		this.ECTS=x;
	}
	
	//metody
	@Override
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append(nazwa);
		if(prowadzacy!=null) S.append(", prowadzony przez: " + prowadzacy.podstawoweDaneOsobowe());
		S.append(", za " + Integer.toString(ECTS) + " pkt ECTS");
		return S.toString();
	}
	
	public String wszystkieDane()
	{
		return this.toString();
	}
	
	
}
