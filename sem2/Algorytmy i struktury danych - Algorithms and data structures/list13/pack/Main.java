package pack;
import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[])
	{
		//test();	//<-- nazwy plikow wprowadzane z klawiatury, sprawdzanie zakresow
		test2();	//<-- wersja wpisana "na twardo" do kodu, wypisuje wszystkie pliki i nie sprawdza zakresow
	}
	
	public static void test()
	{
		boolean goOn = true;
		while(goOn)
		{
			boolean skip = true;
			Scanner scan = new Scanner(System.in);
			System.out.println("\n\nPodaj nazwe pliku, z ktorego odczytac dane");
			String s = scan.nextLine();
			try(Scanner in = new Scanner(new BufferedReader(new FileReader(new File(s)))))
			{
				String[] temp = in.nextLine().split(" ");
				int n = Integer.parseInt(temp[0]);
				int m = Integer.parseInt(temp[1]);
				Graf g = null;
				if(n>=1 && n<=20 && m>=0 && m<=190)
				{
					g = new Graf(n, m);
				}
				else
				{
					System.out.println("Podano zle wartosci n lub m");
					throw new IllegalArgumentException();
				}
				while(in.hasNext())
				{
					temp = in.nextLine().split(" ");
					int i = Integer.parseInt(temp[0]);
					int j = Integer.parseInt(temp[1]);
					int k = Integer.parseInt(temp[2]);
					if(i>=1 && j>=1 && i<=n && j<=n && k>=1 && k<=500)
						g.addKrawedz(i, j, k);
					else
						System.out.println("Nie dodano krawedzi: \""+i+" - "+j+" : "+k+"\" z powodu przekroczenia zakresu");
				}
				//tutaj mamy juz gotowy graf
				
				System.out.println(g.Kruskal());
				
			}
			catch(Exception e)
			{
				skip = false;
				e.printStackTrace();
				System.out.println("Wystapil blad, wracam do poczatku");
			}
			if(skip)
			{
				System.out.println("Czy kontynuowac z inna nazwa pliku? (\"N\" oznacza, ze nie, cokolwiek innego - tak)");
				String choice = scan.nextLine();
				if(choice.equals("N"))
					goOn = false;
			}
		}
	}

	//wersja bez sprawdzania wszystkiego i wypisujaca wszystkie dane
	public static void test2()
	{
		String[] T = new String[]{"da1.txt","da2.txt","da3.txt","da4.txt","da5.txt","da6.txt","da7.txt"};
		int x=0;
		while(x<T.length)
		{
			try(Scanner in = new Scanner(new BufferedReader(new FileReader(new File(T[x])))))
			{
				String[] temp = in.nextLine().split(" ");
				int n = Integer.parseInt(temp[0]);
				int m = Integer.parseInt(temp[1]);
				Graf g = new Graf(n, m);
				while(in.hasNext())
				{
					temp = in.nextLine().split(" ");
					int i = Integer.parseInt(temp[0]);
					int j = Integer.parseInt(temp[1]);
					int k = Integer.parseInt(temp[2]);
					g.addKrawedz(i, j, k);
				}
				//tutaj mamy juz gotowy graf
				
				System.out.println(T[x++] + ": "+g.Kruskal()+"cm\n");	
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Wystapil blad, wracam do poczatku");
			}
		}
	}
}
