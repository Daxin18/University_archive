package main;
import simulation.*;
import java.util.*;

public class Main
{
	public static void main(String args[])
	{
		test();
	}
	
	//Simulation(int liczbaStron, int liczbaRamek, int zakres, int liczbaProcesow, int LLB, int LUB, int minIloscStref, int maxIloscStref)
	public static void test1()
	{
		
	}
	
	public static void test()
	{
		Simulation.max_rozmiar_strefy = 21;		//gorny limit generowania wielkosci strefy
		Simulation.min_rozmiar_strefy = 10;		//dolny limit generowania wielkosci strefy
		
		Simulation.maxBlad = 6; //max ilosc bledow stron
		Simulation.minBlad = 3; //min ilosc bledow stron
		Simulation.deltaSCB = 25; //co jaki czas sprawdzamy bledy (ile odwolan)
		
		Simulation.delta = 25; //delta do modelu strefowego
		
		int liczbaStron = 1000; //ilosc odwolan
		int liczbaRamek = 100; //ilosc ramek
		int zakres = 150; //zakres stron
		int liczbaProcesow = 10; //liczba procesow
		int LLB = 2; //dolny zakres generowania lokalnosci (w procesie)
		int LUB = 5; //gorny zakres generowania lokalnosci (w procesie)
		int minIloscStref = 3; //minimalna ilosc stref w procesie
		int maxIloscStref = 7; //maksymalna ilosc stref w procesie
		
		
		Simulation sim = new Simulation(liczbaStron,liczbaRamek,zakres,liczbaProcesow,LLB,LUB,minIloscStref,maxIloscStref);
		sim.showProcesses();
		System.out.println("\n"+sim.pagesToString()+"\n");
		sim.showRequestCount();
		System.out.println("\nBledy stron dla przydzialu rownego: "+ sim.rowny());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla przydzialu proporcjonalnego: "+ sim.proporcjonalny());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla SCB: "+ sim.SCB());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla MS: "+ sim.MS());
		//sim.showPageFaults();
		
		System.out.println("==================================================\n"
				+ "\t\tDruga proba\n"
				+ "==================================================");
		
		sim.setPamiecFizyczna(99);
		//sim.showProcesses();
		//System.out.println("\n"+sim.pagesToString()+"\n");
		//sim.showRequestCount();
		System.out.println("\nBledy stron dla przydzialu rownego: "+ sim.rowny());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla przydzialu proporcjonalnego: "+ sim.proporcjonalny());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla SCB: "+ sim.SCB());
		//sim.showPageFaults();
		System.out.println("\nBledy stron dla MS: "+ sim.MS());
		//sim.showPageFaults();
	}
	
	public static void mainTest()
	{
		int bound = 0; 				//zakres stron [0,bound]
		int iloscProcesow = 0;		//ilosc procesow w symulacji
		int pamiecWirtualna = 0; 	//ilosc stron w symulacji (dlugosc ciagu odwolan)
		int pamiecFizyczna = 0; 	//ilosc ramek w symulacji
		
		//potrzebne do tworzenia procesow
		int LUB = 0;				//gorny limit generowania zakresu generowania strefy
		int LLB = 0;				//dolny limit generowania zakresu generowania strefy
		int iloscStref = 0;			//ilosc stref na proces
		
		Simulation.max_rozmiar_strefy = 8;		//gorny limit generowania wielkosci strefy
		Simulation.min_rozmiar_strefy = 4;		//dolny limit generowania wielkosci strefy
	}
}
