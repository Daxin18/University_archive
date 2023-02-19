package pack;
import java.util.*;

public class Proces
{	
	int numer;
	int rozmiar; //ile procent procesora potrzebuje proces
	int czasWykonania;
	
	//pola pomocnicze
	Random rand = new Random();
	
	public static int SIZE = 20;
	public static int MIN = 20;
	public static int T_SIZE = 150;
	public static int T_MIN = 200;
	
	public Proces(int x)
	{
		this.numer = x;
		this.rozmiar = losujRozmiar();
		this.czasWykonania = losujCzas();
	}
	
	
	private int losujRozmiar()
	{
		int x = 0;
		x+= rand.nextInt(SIZE) + MIN;
		return x;
	}
	private int losujCzas()
	{
		int x = 0;
		x+= rand.nextInt(T_SIZE) + T_MIN;
		return x;
	}
}
