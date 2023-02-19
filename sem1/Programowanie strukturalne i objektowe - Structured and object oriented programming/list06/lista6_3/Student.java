package lista6_3;

public class Student extends Osoba
{
	private String indeks;
	private Kursy[] K;
	private boolean ERASMUS;
	private boolean stopien1;
	private boolean stopien2;
	private boolean stacjonarne;
	private boolean niestacjonarne;
	
	//konstruktory
	public Student()
	{
		super();
		this.indeks="000000";
		this.K= null;
		this.ERASMUS=false;
		this.stopien1=false;
		this.stopien2=false;
		this.stacjonarne=false;
		this.niestacjonarne=false;
	}
	public Student(String imie, String nazwisko, String PESEL, int wiek, String plec, int indeks, int iloscKursow, boolean ERASMUS, boolean stopien1, boolean stopien2, boolean stacjonarne, boolean niestacjonarne)
	{
		super(imie, nazwisko, PESEL, wiek, plec);
		this.indeks=Integer.toString(indeks);
		this.K= new Kursy[iloscKursow];
		this.ERASMUS=ERASMUS;
		this.stopien1=stopien1;
		this.stopien2=stopien2;
		this.stacjonarne=stacjonarne;
		this.niestacjonarne=niestacjonarne;
	}
	
	//getery
	public String getIndeks()
	{
		return indeks;
	}
	public Kursy[] getKursy()
	{
		return K;
	}
	public boolean czyERASMUS()
	{
		return ERASMUS;
	}
	public boolean czyStopnia1()
	{
		return stopien1;
	}
	public boolean czyStopnia2()
	{
		return stopien2;
	}
	public boolean czyStacjonarne()
	{
		return stacjonarne;
	}
	public boolean czyNiestacjonarne()
	{
		return niestacjonarne;
	}
	public Kursy getKurs(int x)
	{
		return K[x-1];
	}
	public int ileKursow()
	{
		return K.length;
	}
	
	//setery
	public void setIndeks(int nowy)
	{
		this.indeks = Integer.toString(nowy);
	}
	public void setKurs(int x, Kursy k)
	{
		this.K[x-1]=k;
	}
	public void setERASMUS(boolean t)
	{
		this.ERASMUS=t;
	}
	public void setStopien1(boolean t)
	{
		this.stopien1=t;
	}
	public void setStopien2(boolean t)
	{
		this.stopien2=t;
	}
	public void setStancjonarne(boolean t)
	{
		this.stacjonarne = t;
	}
	public void setNiestancjonarne(boolean t)
	{
		this.niestacjonarne = t;
	}
	
	//metody
	public String wszystkieDane()
	{
		StringBuilder s = new StringBuilder();
		s.append("Student: " + this.daneOsobowe() + ", numer indeksu: " + indeks+"\n");
		s.append("Student jest: \n");
		if (ERASMUS)
			s.append("-uczestnikiem programu ERASMUS\n");
		if (stopien1)
			s.append("-studentem I-stopnia studiów\n");
		if (stopien2)
			s.append("-studentem II-stopnia studiów\n");
		if (stacjonarne)
			s.append("-studentem studiów stacjonarnych\n");
		if (niestacjonarne)
			s.append("-studentem studiów niestacjonarnych\n");
		s.append("\nKursy, na które jest zapisany: \n");
		for(int i=0; i<K.length; i++)
			if(K[i]!=null)
				s.append(K[i].toString()+"\n");
		return s.toString();
	}
	
	public String podstawoweDaneUczelnia()
	{
		return ("Student, " + podstawoweDaneOsobowe());
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Student)
		{
			Student s = (Student) o;
			if (this.indeks.equals(s.getIndeks()))
				return true;
		}
		return false;
	}
	
	public int hashCode()
	{
		final int prime=31;
		int result = 1;
		result = prime * result + ((this.indeks == null) ? 0 : this.indeks.hashCode());
		return result;
	}
}