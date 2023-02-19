//260413 Kamil Ciaglo
package pack;
import java.util.*;
import java.io.*;

public class Z2cz2
{
	public static void main(String args[])
	{
		String p1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe pliku, z ktorego wczytamy wykaz towarów:");
		//przygotowany plik z cz1 to "test.txt"
		p1 = scan.next();
		ArrayList<String[]> dostepne = new ArrayList<>();
		ArrayList<String[]> wyczerpane = new ArrayList<>();
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(p1)))))
		{
			String temp = sc.nextLine();
			temp = sc.nextLine();
			while(sc.hasNext())
			{
				String x = sc.nextLine();
				String[] y;
				y = x.split(" ");
				if (y[1].equals("0"))
					wyczerpane.add(y);
				else
					dostepne.add(y);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Wykaz wyczerpanych.txt"))))
		{
			bw.write("Wykaz wyczerpanych towarow\nLp. Nazwa Ilosc Cena");
			int lp=1;
			for(String[] S : wyczerpane)
			{
				bw.write("\n"+lp+" ");
				for(String s : S)
				{
					bw.write(s+ " ");
				}
				lp++;
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Wykaz dostepnych.txt"))))
		{
			bw.write("Wykaz dostepnych towarow\nLp. Nazwa Ilosc Cena Wartosc");
			int lp=1;
			for(String[] S : dostepne)
			{
				bw.write("\n"+lp+" ");
				for(String s : S)
				{
					bw.write(s+ " ");
				}
				bw.write(Double.valueOf(Double.parseDouble(S[1])*Double.parseDouble(S[2])).toString());
				lp++;
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
