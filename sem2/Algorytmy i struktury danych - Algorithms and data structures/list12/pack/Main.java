package pack;

public class Main
{
	public static void main(String args[])
	{
		test();
	}
	
	public static void test()
	{
		//tworzymy graf z 5 miastami
		Graf_miast g = new Graf_miast(5);
		//dodajemy miasta
		g.addMiasto(0,  "Wrocław");
		g.addMiasto(1,  "Oława");
		g.addMiasto(2,  "Brzeg");
		g.addMiasto(3,  "Nysa");
		g.addMiasto(4,  "Opole");
		//dodajemy krawedzie (drogi)
		g.addDroga(0, 1, 10);
		g.addDroga(0, 3, 30);
		g.addDroga(0, 4, 100);
		g.addDroga(1, 2, 50);
		g.addDroga(2, 4, 10);
		g.addDroga(3, 2, 20);
		g.addDroga(3, 4, 60);
		
		g.showMiasta();
		
		System.out.println(g.LS_numerami()+"\n");
		System.out.println(g.LS_nazwami()+"\n");
		System.out.println(g.LS_nazwami_odleglosciami()+"\n");
		
		g.Dijkstry(0);
		
		System.out.println();
		
		g.DFS_print();
	}
}
