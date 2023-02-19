package pack;

public class Main
{
	public static void main(String args[])
	{
		test();
	}
	
	public static final int ILOSC_PROCESOW = 20000;
	
	
	public static void test()
	{
		Symulacja.coIleLiczycSrednia = 100;	//co ile liczymy srednie obciazenie procesorow
		Symulacja.penalty = 2;	// czas * penalty jesli obciazenie przekroczy 100
		
		Proces.SIZE = 20;	//przedzial losowania
		Proces.MIN = 20;	//min wartosc
		//tak samo jak wyzej, ale dla czasu wykonania
		Proces.T_MIN = 200;
		Proces.T_SIZE = 400;
		
		int N = 100;	//ilosc procesorow
		int p = 70;	//prog gorny
		int r = 20;	//prog dolny	(strat3)
		int z = 10;	//ilosc zapytan (strat1)
		
		Symulacja sim = new Symulacja(N, p, r, z);
		
		sim.Strat1();
		sim.Strat2();
		sim.Strat3();
	}
}
