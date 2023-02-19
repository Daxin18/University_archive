package pack;
import java.io.*;
import java.util.*;

@SuppressWarnings("resource")	//nie chce masy powiadomien o tym, ze nie zamykam Scannera, a nie robie tego, zeby
								//przy okazji nie zamknac tez System.in, bez ktorego nastepna proba deklaracji
								//Scanner scan = new Scanner(System.in) wywali program
public class Main
{
	
	public static void main(String args[])
	{
		//test1();
		test();
	}
	
	public static void test1()
	{
		HuffmanTree tree = new HuffmanTree("ala i ola.");
		System.out.println(tree.showSymbolsFrequencyAndCode());
		System.out.println("a\t- "+tree.codeOf('a'));
		System.out.println("l\t- "+tree.codeOf('l'));
		System.out.println("spacja\t- "+tree.codeOf(' '));
		System.out.println("i\t- "+tree.codeOf('i'));
		System.out.println("o\t- "+tree.codeOf('o'));
		System.out.println(".\t- "+tree.codeOf('.'));
	}
	public static void test()
	{
		boolean goOn=true;
		while(goOn)
		{
			String one = getFile();
			HuffmanTree treeOne = new HuffmanTree(one);
			System.out.println("\nTekst: "+treeOne.getTekst());
			String kod = treeOne.cypher();
			System.out.println("Zakodowany tekst: "+kod+"\n");
			System.out.println(treeOne.showSymbolsFrequencyAndCode());
			System.out.println("Odkodowany tekst: "+ treeOne.decypher(kod));
			saveFile(kod);
			//String two = getFile(); //<-- jakby trzeba bylo odkodowac z pliku
			//System.out.println("Odkodowany tekst z pliku: "+ treeOne.decypher(two)+"\n"); //<-- jakby trzeba bylo odkodowac z pliku
			goOn = continueQuestion();
		}
	}
	
	public static String getFile()
	{
		String x = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Podaj nazwe/sciezke dostepu do pliku z tekstem");
		String file = scan.next();
		try(Scanner input = new Scanner(new FileReader(new File(file))))
		{
			StringBuilder S = new StringBuilder();
			while(input.hasNext())
			{
				S.append(input.nextLine());
			}
			x = S.toString();
		}
		catch(Exception e)
		{
			System.out.println("Cos poszlo nie tak");
			x = getFile();
		}
		return x;
	}
	public static void saveFile(String s)
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("\nPodaj nazwe/sciezke dostepu do pliku, w ktorym chcesz zapisac zakodowany tekst");
		String file = scan.next();
		try(BufferedWriter output = new BufferedWriter(new FileWriter(new File(file))))
		{
			output.write(s);
			System.out.println("Zapisano w pliku!");
		}
		catch(Exception e)
		{
			System.out.println("Cos poszlo nie tak");
			e.printStackTrace();
		}
	}
	public static boolean continueQuestion()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Wpisz \"T\" jesli chcesz kontynuowac");
		String choice = scan.next();
		return choice.equals("T");
	}
}
