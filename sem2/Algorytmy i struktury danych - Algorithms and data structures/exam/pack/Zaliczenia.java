//260413 Kamil Ciaglo
package pack;
import java.util.*;

public class Zaliczenia
{
	private Student student;
	private ArrayList<Zaliczenie> zaliczenie;
	
	public Zaliczenia(Student s)
	{
		this.student = s;
		this.zaliczenie = new ArrayList<Zaliczenie>();
	}
	public Zaliczenia(Student s, ArrayList<Zaliczenie> z)
	{
		this.student = s;
		this.zaliczenie = z;
	}
	public Student getStudent()
	{
		return student;
	}
	public void addZaliczenie(Zaliczenie z)
	{
		this.zaliczenie.add(z);
	}
}
