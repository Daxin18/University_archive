package z1;

public class Book implements Comparable<Book>
{
	private Autor autor;
	private String title;
	private int numer; //ewidencyjny
	//konstruktory
	public Book()
	{
		this.autor = new Autor();
		this.title = "Book";
		this.numer = 1;
	}
	public Book(Autor a, String t, int i)
	{
		this.autor = a;
		this.title = t;
		this.numer = i;
	}
	//getery
	public Autor getAutor()
	{
		return autor;
	}
	public String getTitle()
	{
		return title;
	}
	public int getNumer()
	{
		return numer;
	}
	//setery
	public void setAutor(Autor a)
	{
		this.autor = a;
	}
	public void setTitle(String s)
	{
		this.title = s;
	}
	public void setNumer(int i)
	{
		this.numer = i;
	}
	
	//metody
	@Override
	public String toString()
	{
		return (autor.toString() + ", " + title); 
	}
	@Override
	public int compareTo(Book b)
	{
		return this.title.compareTo(b.getTitle());
	}
}
