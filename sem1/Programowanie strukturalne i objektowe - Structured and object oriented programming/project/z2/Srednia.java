package z2;
import java.util.*;
import java.io.*;

public class Srednia
{
	public static void main(String args[])
	{
		String p1;
		String p2;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe pliku, z ktorego wczytamy liste uczniow:");
		p1 = scan.next();
		System.out.println("Podaj nazwe pliku, do ktorego zapiszemy liste:");
		p2 = scan.next();
		
		try(Scanner sc = new Scanner(new BufferedReader(new FileReader(new File(p1)))))
		{
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(p2))))
			{
				bw.write(sc.nextLine()+"\n");
				bw.write(sc.nextLine()+" Srednia\n");
				while(sc.hasNext())
				{
					String x = sc.nextLine();
					String[] y;
					y = x.split(" ");
					for(String s : y)
					{
						bw.write(s+" ");
					}
					double sred;
					sred=(Double.parseDouble(y[3])+Double.parseDouble(y[4])+Double.parseDouble(y[5]))/3;
					bw.write(sred+"\n");
				}
					
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
