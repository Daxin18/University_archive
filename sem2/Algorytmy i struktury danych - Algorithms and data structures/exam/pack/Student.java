//260413 Kamil Ciaglo
package pack;

public class Student implements Comparable<Student>
{
	private String nazwisko;
	private String imie;
	
	public Student(String nazwisko, String imie)
	{
		this.nazwisko = nazwisko;
		this.imie = imie;
	}
	public String getNazwisko()
	{
		return nazwisko;
	}
	public String getImie()
	{
		return imie;
	}
	public boolean equals(Student s1)
	{
		if(this.nazwisko.equals(s1.getNazwisko()) && this.imie.equals(s1.getImie()))
			return true;
		return false;
	}
	public String toString()
	{
		return (nazwisko + " "+ imie);
	}
	public int compareTo(Student s)
	{
		return this.toString().compareTo(s.toString());
	}
}
