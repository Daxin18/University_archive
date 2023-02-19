package pack;
import java.util.*;

public class Symulacja
{
	int N;
	int p;
	int r;
	int z;
	ArrayList<Procesor> baseProcesory;
	ArrayList<Proces> baseProcesy;
	
	int czas;
	
	//pola pomocnicze
	Random rand = new Random();
	static int coIleLiczycSrednia;
	static int penalty;
	
	
	public Symulacja(int N, int p, int r, int z)
	{
		this.N = N;
		this.p = p;
		this.r = r;
		this.z = z;
		this.baseProcesory = new ArrayList<>();
		this.baseProcesy = new ArrayList<>();
		generujProcesory();
		generujProcesy();
	}
	
	private void generujProcesy()
	{
		for(int i=0; i<Main.ILOSC_PROCESOW; i++)
		{
			baseProcesy.add(new Proces(i));
		}
	}
	private void generujProcesory()
	{
		for(int i=0; i<N; i++)
		{
			baseProcesory.add(new Procesor(i));
		}
	}
	
	private void wyslijProces(Proces p, Procesor proc)
	{
		if(proc.obciazenie + p.rozmiar > 100)
			p.czasWykonania *= penalty;
		
		proc.obciazenie = Math.min(100, proc.obciazenie + p.rozmiar);
		proc.procesy.add(p);
	}
	private void symulujCzas(ArrayList<Procesor> procesory, ArrayList<Double> srednie)
	{
		czas++;
		for(Procesor p: procesory)
				p.uplywaCzasProcesow();
		//liczenie srednich
		Double x = Double.valueOf(0);
		if(czas%coIleLiczycSrednia==0)
		{
			for(Procesor p : procesory)
				x += Double.valueOf(p.obciazenie);
			x /= Double.valueOf(procesory.size());
			srednie.add(x);
		}
	}
	
	//===============================
	//			Strategie
	//===============================
	
	public void Strat1()
	{
		czas = 0;
		ArrayList<Proces> procesy = new ArrayList<>();
		ArrayList<Procesor> procesory = new ArrayList<>();
		ArrayList<Double> srednie = new ArrayList<>();
		
		for(Proces p: baseProcesy)
			procesy.add(new Proces(p.numer));
		for(Procesor p: baseProcesory)
			procesory.add(new Procesor(p.numer));
		
		while(!procesy.isEmpty())
		{
			Proces P = procesy.remove(0);	//bierzemy proces
			int x = rand.nextInt(N);	//losujemy procesor, na ktorym go wykonamy
			
			Procesor proc = procesory.get(x);
			
			for(int i=0; i<z; i++)	//sprawdzamy reszte procesorow
			{
				
				symulujCzas(procesory, srednie);	//czas uplywa przy kazdym zapytaniu
				
				int y = rand.nextInt(N);
				Procesor py = procesory.get(y);
				py.zapytaniaObciazenia++;
				if(py.obciazenie < p)
				{
					proc = py;
					proc.migracje++;
					break;
				}
			}
			wyslijProces(P, proc);
			
			symulujCzas(procesory, srednie);	//i na koncu gdy pojawia sie nowy proces		
		}
		
		double srednie_obciazenie = 0;
		for(Double d : srednie)
		{
			//System.out.println(d);
			srednie_obciazenie += d;
		}
		srednie_obciazenie /= Double.valueOf(srednie.size());
		
		
		double srednie_odchylenie = 0;
		for(Double d : srednie)
		{
			srednie_odchylenie += Math.abs(d - srednie_obciazenie);
		}
		srednie_odchylenie /= Double.valueOf(srednie.size());
		
		int zapytania = 0;
		int migracje = 0;
		for(Procesor p : procesory)
		{
			migracje += p.migracje;
			zapytania += p.zapytaniaObciazenia;
		}
		System.out.println("===============================================");
		System.out.println("\t\tStretegia 1:");
		System.out.println("===============================================");
		System.out.println("Srednie obciazenie (%): " + srednie_obciazenie);
		System.out.println("Srednie odchylenie (%): " + srednie_odchylenie);
		System.out.println("Zapytania o obciazenie: " + zapytania);
		System.out.println("Migracje: " + migracje);
		System.out.println("\nDODATKOWO --- Czas: " + czas);
	}
		
	public void Strat2()
	{
		czas = 0;
		ArrayList<Proces> procesy = new ArrayList<>();
		ArrayList<Procesor> procesory = new ArrayList<>();
		ArrayList<Double> srednie = new ArrayList<>();
		
		for(Proces p: baseProcesy)
			procesy.add(new Proces(p.numer));
		for(Procesor p: baseProcesory)
			procesory.add(new Procesor(p.numer));
		
		while(!procesy.isEmpty())
		{
			Proces P = procesy.remove(0);	//bierzemy proces
			int x = rand.nextInt(N);	//losujemy procesor, na ktorym go wykonamy
			
			Procesor proc = procesory.get(x);
			
			if(proc.obciazenie > p)
			{
				for(int i=0; i<10000; i++)	//sprawdzamy reszte procesorow
				{
					
					symulujCzas(procesory, srednie);	//czas uplywa przy kazdym zapytaniu
					
					int y = rand.nextInt(N);
					Procesor py = procesory.get(y);
					py.zapytaniaObciazenia++;
					if(py.obciazenie < p)
					{
						proc = py;
						proc.migracje++;
						break;
					}
				}
			}
			wyslijProces(P, proc);
			
			symulujCzas(procesory, srednie);	//i na koncu gdy pojawia sie nowy proces		
		}
		
		double srednie_obciazenie = 0;
		for(Double d : srednie)
		{
			//System.out.println(d);
			srednie_obciazenie += d;
		}
		srednie_obciazenie /= Double.valueOf(srednie.size());
		
		
		double srednie_odchylenie = 0;
		for(Double d : srednie)
		{
			srednie_odchylenie += Math.abs(d - srednie_obciazenie);
		}
		srednie_odchylenie /= Double.valueOf(srednie.size());
		
		int zapytania = 0;
		int migracje = 0;
		for(Procesor p : procesory)
		{
			migracje += p.migracje;
			zapytania += p.zapytaniaObciazenia;
		}
		System.out.println("===============================================");
		System.out.println("\t\tStretegia 2:");
		System.out.println("===============================================");
		System.out.println("Srednie obciazenie (%): " + srednie_obciazenie);
		System.out.println("Srednie odchylenie (%): " + srednie_odchylenie);
		System.out.println("Zapytania o obciazenie: " + zapytania);
		System.out.println("Migracje: " + migracje);
		System.out.println("\nDODATKOWO --- Czas: " + czas);
	}

	public void Strat3()
	{
		czas = 0;
		ArrayList<Proces> procesy = new ArrayList<>();
		ArrayList<Procesor> procesory = new ArrayList<>();
		ArrayList<Double> srednie = new ArrayList<>();
		
		for(Proces p: baseProcesy)
			procesy.add(new Proces(p.numer));
		for(Procesor p: baseProcesory)
			procesory.add(new Procesor(p.numer));
		
		while(!procesy.isEmpty())
		{
			Proces P = procesy.remove(0);	//bierzemy proces
			int x = rand.nextInt(N);	//losujemy procesor, na ktorym go wykonamy
			
			Procesor proc = procesory.get(x);
			
			if(proc.obciazenie > p)
			{
				for(int i=0; i<10000; i++)	//sprawdzamy reszte procesorow
				{
					
					symulujCzas(procesory, srednie);	//czas uplywa przy kazdym zapytaniu
					
					int y = rand.nextInt(N);
					Procesor py = procesory.get(y);
					py.zapytaniaObciazenia++;
					if(py.obciazenie < p)
					{
						proc = py;
						proc.migracje++;
						break;
					}
				}
			}
			wyslijProces(P, proc);
			
			
		// ============= kod dodatkowy w strategii 3 =============	
			proc = procesory.get(x);
			
			if(proc.obciazenie < r)
			{
				for(int i=0; i<10000; i++)	//sprawdzamy reszte procesorow
				{
					
					symulujCzas(procesory, srednie);	//czas uplywa przy kazdym zapytaniu
					
					int y = rand.nextInt(N);
					Procesor py = procesory.get(y);
					py.zapytaniaObciazenia++;
					if(py.obciazenie > p)
					{
						proc.przeniesProces(py);
						proc.migracje++;
						break;
					}
				}
			}
		// ============= koniec kodu dodatkowego w strategii 3 =============
			
			symulujCzas(procesory, srednie);	//i na koncu gdy pojawia sie nowy proces		
		}
		
		double srednie_obciazenie = 0;
		for(Double d : srednie)
		{
			//System.out.println(d);
			srednie_obciazenie += d;
		}
		srednie_obciazenie /= Double.valueOf(srednie.size());
		
		
		double srednie_odchylenie = 0;
		for(Double d : srednie)
		{
			srednie_odchylenie += Math.abs(d - srednie_obciazenie);
		}
		srednie_odchylenie /= Double.valueOf(srednie.size());
		
		int zapytania = 0;
		int migracje = 0;
		for(Procesor p : procesory)
		{
			migracje += p.migracje;
			zapytania += p.zapytaniaObciazenia;
		}
		System.out.println("===============================================");
		System.out.println("\t\tStretegia 3:");
		System.out.println("===============================================");
		System.out.println("Srednie obciazenie (%): " + srednie_obciazenie);
		System.out.println("Srednie odchylenie (%): " + srednie_odchylenie);
		System.out.println("Zapytania o obciazenie: " + zapytania);
		System.out.println("Migracje: " + migracje);
		System.out.println("\nDODATKOWO --- Czas: " + czas);
	}
}
