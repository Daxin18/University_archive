//260413 Kamil Ciaglo
package pack;
import java.util.*;

//pomimmo tlumaczen niezbyt zrozumialem to jak jedne obiekty maja zawierac drugie, wiec kieruje sie poleceniem i intuicja (?)
public class Grupa
{
	private ArrayList<Zaliczenia> G;
	
	public Grupa()
	{
		this.G = new ArrayList<Zaliczenia>();
	}
	public Grupa(ArrayList<Zaliczenia> z)
	{
		this.G = z;
	}
	public void addZaliczenia(Zaliczenia z)
	{
		this.G.add(z);
	}
	//pierwszy podpunkt
	public void addStudent(Student s)
	{
		this.G.add(new Zaliczenia(s));
	}
	//drugi podpunkt
	public void addStudentZaliczenie(Student s1, Zaliczenie z1)
	{
		for(Zaliczenia z : G)
			if(z.getStudent().equals(s1))
				z.addZaliczenie(z1);
	}
	//trzeci podpunkt
	public void studentAlf()
	{
		TreeSet<Student> temp = new TreeSet<>();
		for(Zaliczenia z : G)
			temp.add(z.getStudent());
		for(Student s : temp)
			System.out.println(s.toString());
	}
	
	public static void main(String args[])
	{
		Student s1 = new Student("Ciaglo", "Kamil");
		Student s3 = new Student("Kowalski", "Jan");
		Student s2 = new Student("Nowak", "Adam");
		
		Zaliczenie z1 = new Zaliczenie("PSiO", 3);
		Zaliczenie z2 = new Zaliczenie("PSiO", 4);
		Zaliczenie z3 = new Zaliczenie("PSiO", 5);
		Zaliczenie z4 = new Zaliczenie("Fizyka", 3);
		Zaliczenie z5 = new Zaliczenie("Fizyka", 4);
		Zaliczenie z6 = new Zaliczenie("Fizyka", 5);
		
		//Zaliczenia zal1 = new Zaliczenia(s1);
		Zaliczenia zal2 = new Zaliczenia(s2);
		Zaliczenia zal3 = new Zaliczenia(s3);
		//zal1.addZaliczenie(z3);
		zal3.addZaliczenie(z2);
		zal2.addZaliczenie(z1);
		zal3.addZaliczenie(z4);
		zal2.addZaliczenie(z5);
		
		Grupa G = new Grupa();
		//G.addZaliczenia(zal1);
		//pkt1
		G.addStudent(s1);
		G.addZaliczenia(zal2);
		G.addZaliczenia(zal3);
		
		//pkt2
		G.addStudentZaliczenie(s1, z3);
		G.addStudentZaliczenie(s1, z6);
		
		//pkt3
		G.studentAlf();
		
		
	}
}
