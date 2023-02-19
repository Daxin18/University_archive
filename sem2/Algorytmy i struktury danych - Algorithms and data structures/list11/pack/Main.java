package pack;
import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String args[])
	{
		test();
	}
	
	public static void test()
	{
		int w=1;
		RBTree<String> RBT = new RBTree<>();
		try(Scanner scan = new Scanner(new FileReader(new File("aforyzm.txt"))))
		{
			while(scan.hasNext())
			{
				String s = scan.nextLine();
				String[] S = s.split("[,. ?!'\"-]");
				//if(i==1)
				//	RBT = new RBTree<>(S[0], i);
				for(int j=0; j<S.length; j++)
				{
					RBT.add(S[j], w);
					//System.out.println(S[j]); //<-- do testow
				}
				w++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(RBT.inOrder());
		System.out.println("\nWyswietlanie wszerz:"+RBT.wszerz());
	}
	
	//metoda w ktorej dodaje limit wyrazow dla tworzenia drzewa - pomocna jesli chcialem zobaczyc jak po kolei tworzy sie drzewo
	public static void test2()
	{
		int LIMIT = 5;
		
		int w=1;
		int in=1;
		RBTree<String> RBT = new RBTree<>();
		try(Scanner scan = new Scanner(new FileReader(new File("przyklad.txt"))))
		{
			while(scan.hasNext())
			{
				String s = scan.nextLine();
				String[] S = s.split("[,. ?!'\"-]");
				//if(i==1)
				//	RBT = new RBTree<>(S[0], i);
				for(int j=0; j<S.length; j++)
				{
					if(in<=LIMIT)
						RBT.add(S[j], w);
					//System.out.println(S[j]); //<-- do testow
					in++;
				}
				w++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(RBT.inOrder());
		System.out.println("\nWyswietlanie wszerz:"+RBT.wszerz());
	}
}
