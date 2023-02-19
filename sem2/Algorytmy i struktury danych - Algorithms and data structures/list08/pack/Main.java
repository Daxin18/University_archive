package pack;

public class Main
{
	public static void main(String args[])
	{
		//dodatkoweTesty();
		//testGenerowania();
		//testSortowania();
		testCzasu();
	}
	
	public static void dodatkoweTesty()
	{
		Zestaw Z = new Zestaw(1); //rozmiar
		Z.generate();
	}
	
	public static void testGenerowania()
	{
		Zestaw Z = new Zestaw(10);
		System.out.println(Z.dodatkowe());
	}
	
	public static void testSortowania()
	{
		Zestaw Z = new Zestaw(20);
		Z.generate();
		System.out.println("Oryginal:");
		Zestaw.wyswietl(Z.getT());
		System.out.println("\nPosortowany:");
		Zestaw.wyswietl(Z.ShellSortV2(Z.dodatkowe()));
	}
	
	public static void testCzasu()
	{
		Zestaw Z1 = new Zestaw(5000);
		Z1.generate();
		Zestaw Z2 = new Zestaw(10000);
		Z2.generate();
		Zestaw Z3 = new Zestaw(50000);
		Z3.generate();
		Zestaw Z4 = new Zestaw(100000);
		Z4.generate();
		System.out.println("===============================================================================");
		System.out.println(" Czasy sortowania ShellSort (w wersji 1) w nanosekundach dla tablic wielkosci:\n"
				+ "===============================================================================");
		System.out.println(Z1.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z1.measureShellSortV1(Z1.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z1.measureShellSortV1(Z1.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z1.measureShellSortV1(Z1.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z1.measureShellSortV1(Z1.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z1.measureShellSortV1(Z1.dodatkowe()));
		System.out.println(Z2.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z2.measureShellSortV1(Z2.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z2.measureShellSortV1(Z2.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z2.measureShellSortV1(Z2.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z2.measureShellSortV1(Z2.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z2.measureShellSortV1(Z2.dodatkowe()));
		System.out.println(Z3.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z3.measureShellSortV1(Z3.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z3.measureShellSortV1(Z3.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z3.measureShellSortV1(Z3.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z3.measureShellSortV1(Z3.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z3.measureShellSortV1(Z3.dodatkowe()));
		System.out.println(Z4.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z4.measureShellSortV1(Z4.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z4.measureShellSortV1(Z4.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z4.measureShellSortV1(Z4.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z4.measureShellSortV1(Z4.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z4.measureShellSortV1(Z4.dodatkowe()));
		System.out.println("===============================================================================");
		System.out.println(" Czasy sortowania ShellSort (w wersji 2) w nanosekundach dla tablic wielkosci:\n"
				+ "===============================================================================");
		System.out.println(Z1.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z1.measureShellSortV2(Z1.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z1.measureShellSortV2(Z1.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z1.measureShellSortV2(Z1.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z1.measureShellSortV2(Z1.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z1.measureShellSortV2(Z1.dodatkowe()));
		System.out.println(Z2.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z2.measureShellSortV2(Z2.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z2.measureShellSortV2(Z2.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z2.measureShellSortV2(Z2.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z2.measureShellSortV2(Z2.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z2.measureShellSortV2(Z2.dodatkowe()));
		System.out.println(Z3.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z3.measureShellSortV2(Z3.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z3.measureShellSortV2(Z3.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z3.measureShellSortV2(Z3.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z3.measureShellSortV2(Z3.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z3.measureShellSortV2(Z3.dodatkowe()));
		System.out.println(Z4.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z4.measureShellSortV2(Z4.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z4.measureShellSortV2(Z4.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z4.measureShellSortV2(Z4.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z4.measureShellSortV2(Z4.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z4.measureShellSortV2(Z4.dodatkowe()));
		System.out.println("===============================================================================");
		System.out.println(" Czasy sortowania ShellSort (w wersji 3) w nanosekundach dla tablic wielkosci:\n"
				+ "===============================================================================");
		System.out.println(Z1.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z1.measureShellSortV3(Z1.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z1.measureShellSortV3(Z1.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z1.measureShellSortV3(Z1.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z1.measureShellSortV3(Z1.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z1.measureShellSortV3(Z1.dodatkowe()));
		System.out.println(Z2.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z2.measureShellSortV3(Z2.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z2.measureShellSortV3(Z2.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z2.measureShellSortV3(Z2.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z2.measureShellSortV3(Z2.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z2.measureShellSortV3(Z2.dodatkowe()));
		System.out.println(Z3.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z3.measureShellSortV3(Z3.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z3.measureShellSortV3(Z3.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z3.measureShellSortV3(Z3.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z3.measureShellSortV3(Z3.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z3.measureShellSortV3(Z3.dodatkowe()));
		System.out.println(Z4.getSize()+" elementow: ");
			System.out.printf("\t ciag a)\t %11d \n", Z4.measureShellSortV3(Z4.a()));
			System.out.printf("\t ciag b)\t %11d \n", Z4.measureShellSortV3(Z4.b()));
			System.out.printf("\t ciag c)\t %11d \n", Z4.measureShellSortV3(Z4.c()));
			System.out.printf("\t ciag d)\t %11d \n", Z4.measureShellSortV3(Z4.d()));
			System.out.printf("\t ciag dod)\t %11d \n", Z4.measureShellSortV3(Z4.dodatkowe()));
	}
}
