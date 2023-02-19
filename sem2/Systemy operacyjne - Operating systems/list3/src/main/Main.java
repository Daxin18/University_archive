package main;
import simulation.*;

public class Main
{
	public static void main(String args[])
	{
		//testGeneracji();
		test();
	}
	
	//parametry do symulacji
	private static int iloscStron=1000;
	private static int zakres=50;
	private static int iloscRamek=5;
	
	public final static double yBoundRatio = 2;	// im wiecej tym mniej stron w okolicy x (wartosci <2 oznaczaja brak nowych procesow)
	public final static double boundRatio = 0.1;	// 0<boundRatio<1 im wiecej tym wiekszy zakres "okolicy x"
	
	//metody
	public static void testGeneracji()
	{
		System.out.println("=====================================================================");
		System.out.println("Ilosc bledow stron dla "+iloscStron+" stron z przedzialu 1-"+zakres+", oraz "+iloscRamek+" ramek:\n"
				+ "=====================================================================");
		Simulation sim = new Simulation(iloscStron, iloscRamek, zakres);
		sim.generate();
		sim.wyswietlStrony();
		System.out.println(sim.getPamiecWirtualna());
	}
	
	public static void test()
	{
		System.out.println("=====================================================================");
		System.out.println("Ilosc bledow stron dla "+iloscStron+" stron z przedzialu 1-"+zakres+", oraz "+iloscRamek+" ramek:\n"
				+ "=====================================================================");
		Simulation sim = new Simulation(iloscStron, iloscRamek, zakres);
		sim.generate();
		System.out.println("FIFO: "+sim.FIFO());
		System.out.println("OPT: "+sim.OPT());
		System.out.println("LRU: "+sim.LRU());
		System.out.println("aLRU (algorytm drugiej szansy): "+sim.aLRU());
		System.out.println("RAND: "+sim.RAND());
	}
}
