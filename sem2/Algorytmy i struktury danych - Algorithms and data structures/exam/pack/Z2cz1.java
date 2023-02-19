//260413 Kamil Ciaglo
package pack;
import java.util.*;
import java.io.*;

public class Z2cz1
{
	public static void main(String args[])
	{
		String p1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe pliku, do ktorego zapiszemy liste:");
		p1 = scan.next();
		//zapisalem to w pliku test.txt
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(p1))))
		{
			int k=0;
			bw.write("Wykaz towarow nr 12/2020\nNazwa_Towaru Ilosc_sztuk Cena\n");
			while (k==0)
			{
				System.out.println("Podaj nazwe towaru:");
				bw.write(scan.next()+ " ");
				System.out.println("Podaj ilosc sztuk:");
				bw.write(scan.next()+ " ");
				System.out.println("Podaj cene za sztuke:");
				bw.write(scan.next()+ "\n");
				System.out.println("Jesli chcesz przerwac wpisz \"T\", a zostanie utworzony plik, wpisanie czegos innego to chec dodania nowego towaru\n");
				if (scan.next().equals("T"))
					k=1;
			}
			System.out.println("Zapisano do pliku: "+ p1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
