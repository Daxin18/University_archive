package pack;
import java.util.*;

public class Main
{
	public static void main(String args[])
	{
		testNawiasow();
		testPalindromow();
	}
	/*
	 	wyrazenia podane w poleceniu:
	 	
	 	(w*[x+y]/z-[p/{r-q}]) - zrownowazone
	 	
	 	(w*[x+y)/z-[p/{r-q}]) - nie zrownowazone
	*/
	public static void testNawiasow()
	{
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		while(running)
		{
			System.out.println("Wpisz wyrazenie, ktorego zrownowazonosc nawiasow mam sprawdzic");
			if(Nawiasy.nawiasyZrownowazone(scan.nextLine()))
				System.out.println("Nawiasy sa poprawnie zrownowazone");
			else
				System.out.println("Nawiasy NIE sa poprawnie zrownowazone");
			System.out.println("Chesz kontynuowac (T)");
			if(!scan.nextLine().equals("T"))
				running = false;
		}
	}
	
	public static void testPalindromow()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Wpisz wyrazenie, ktore moze byc potencjalnym palindromem");
		String wyrazenie = scan.nextLine();
		wyrazenie = wyrazenie.toLowerCase();	//"Kajak" jest palindromem, ale dla programu K i k to rozne litery,
												// zatem wszystko sprowadzamy do malych liter
		ArrayStack<Character> stack = new ArrayStack<>(wyrazenie.length());
		try
		{
			for(int i=0; i<wyrazenie.length(); i++)
			{
				stack.push(Character.valueOf(wyrazenie.charAt(i)));
			}
			ArrayStack<Character> odwrocony = stack.odwroconyStos();
			boolean palindrom = true;
			for(int i=0; i<stack.size()/2; i++)
			{
				if(!(stack.pop().equals(odwrocony.pop())))
				{
					System.out.println("To nie palindrom");
					palindrom = false;
					break;
				}
			}
			if(palindrom)
				System.out.println("To palindrom");			
		}
		catch(Exception e)
		{
			System.out.println("Zaczynam zalowac, ze dopisalem te wyjatki,"
					+ " bo nie maja prawa sie zdazyc w tym kodzie, a trzeba je obsluzyc...");
		}
	}
}
