package z1;
import java.util.*;

public class Biblioteka
{
	private ArrayList<Book> B;
	
	private compAutor comp1;
	private compTitle comp2;
	//konstruktory
	public Biblioteka()
	{
		this.B = new ArrayList<Book>();
	}
	public Biblioteka(ArrayList<Book> x)
	{
		this.B = x;
	}
	//getery i setery
	public ArrayList<Book> getList()
	{
		return B;
	}
	public Book getBook(int i)
	{
		return B.get(i);
	}
	public void addBook(Book b)
	{
		B.add(b);
	}
	public void removeBook(Book b)
	{
		B.remove(b);
	}
	
	//inne metody
	public void printAll()
	{
		int lp=1;
		for(Book b : B)
		{
			System.out.println(lp + ". " + b.toString());
			lp++;
		}
	}
	public void printAutor()
	{
		Collections.sort(B, comp1);
		ArrayList<Autor> temp = new ArrayList<>();
		for(Book b : B)
		{
			if (!(temp.contains(b.getAutor())))
					temp.add(b.getAutor());
		}
		for(Autor a : temp)
			System.out.println(a.toString());
	}
	
	public void sortAutor()
	{
		Collections.sort(B,comp1);
	}
	public void sortTitle()
	{
		Collections.sort(B, comp2);
	}
	
 	public void create()
	{
		comp1 = new compAutor();
		comp2 = new compTitle();
	}
	class compAutor implements Comparator<Book>
	{
		public int compare(Book b1, Book b2)
		{
			return b1.getAutor().toString().compareTo(b2.getAutor().toString());
		}
	}
	class compTitle implements Comparator<Book>
	{
		public int compare(Book b1, Book b2)
		{
			return b1.getTitle().toString().compareTo(b2.getTitle().toString());
		}
	}

	public void odAutora(Autor a)
	{
		ArrayList<Book> temp = new ArrayList<>();
		for(Book b :B)
		{
			if (b.getAutor().toString().equals(a.toString()) && !(temp.contains(b)))
			{
				temp.add(b);
			}
		}
		Collections.sort(temp,comp2);
		for(Book b: temp)
		{
			System.out.println(b.getTitle());
		}
	}
	
	//main
	public static void main(String args[])
	{
		Autor a1 = new Autor();
		Autor a2 = new Autor("Adam", "Nowak");
		Autor a3 = new Autor("Adam", "Bak");
		
		Book b1 = new Book();
		Book b2 = new Book(a2, "Ksiazka2",2);
		Book b3 = new Book(a2, "Ksiazka",3);
		Book b4 = new Book(a3, "Aaa",4);
		
		ArrayList<Book> x = new ArrayList<>();
		x.add(b1);
		x.add(b2);
		x.add(b3);
		
		Biblioteka y = new Biblioteka(x);
		y.printAll();
		System.out.println("\nPo dodaniu b4:");
		y.addBook(b4);
		y.addBook(b2);
		y.printAll();
		y.create();
		System.out.println("\nPo sortowaniu autorami:");
		y.sortAutor();
		y.printAll();
		System.out.println("\nPo sortowaniu tytulami:");
		y.sortTitle();
		y.printAll();
		System.out.println("\nPosortowane tytuly autora a2:");
		y.odAutora(a2);
		System.out.println("\nPosortowani autorzy:");
		y.sortAutor();
		y.printAutor();			
	}
}
