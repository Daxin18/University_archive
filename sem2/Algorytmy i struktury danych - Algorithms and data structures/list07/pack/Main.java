package pack;

public class Main
{
	public static void main(String args[])
	{
		//testSortowan();  //<-- przed odkomentowaniem ustawic SortTest.SIZE na 10, czy 20, zeby dalo sie to przeczytac, ale wartosci typu 100, czy 1000 tez zadzialaja
		//testCzasowMilli();
		testCzasowNano();
	}
	
	public static void testSortowan()
	{
		SortTest S = new SortTest();
		S.generateRand();
		System.out.println("Oryginal:");
		S.wyswietl();
		System.out.println();
		System.out.println("InsertSort:");
		SortTest.wyswietl(S.InsertSort());
		System.out.println("BubbleSort:");
		SortTest.wyswietl(S.BubbleSort());
		System.out.println("SelectSort:");
		SortTest.wyswietl(S.SelectSort());
		System.out.println("QuickSort:");
		SortTest.wyswietl(S.QuickSort());
		System.out.println("HeapSort:");
		SortTest.wyswietl(S.HeapSort());
		System.out.println("MergeSort:");
		SortTest.wyswietl(S.MergeSort());
	}
	
	public static void testCzasowMilli()
	{
		SortTest S = new SortTest();
		S.generateRand();
		System.out.println("=================================================="
				+ "\n\t\t Losowe dane\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w milisekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%7d\n", S.measureInsertSort());
		System.out.printf("BubbleSort: \t%7d\n", S.measureBubbleSort());
		System.out.printf("SelectSort: \t%7d\n", S.measureSelectSort());
		System.out.printf("QuickSort: \t%7d\n", S.measureQuickSort());
		System.out.printf("HeapSort: \t%7d\n", S.measureHeapSort());
		System.out.printf("MergeSort: \t%7d\n", S.measureMergeSort());
		
		S.generateRosnaco();
		System.out.println("=================================================="
				+ "\n\t Dane uporzadkowane rosnaco\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w milisekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%7d\n", S.measureInsertSort());
		System.out.printf("BubbleSort: \t%7d\n", S.measureBubbleSort());
		System.out.printf("SelectSort: \t%7d\n", S.measureSelectSort());
		System.out.printf("QuickSort: \t%7d\n", S.measureQuickSort());
		System.out.printf("HeapSort: \t%7d\n", S.measureHeapSort());
		System.out.printf("MergeSort: \t%7d\n", S.measureMergeSort());
		
		S.generateMalejaco();
		System.out.println("=================================================="
				+ "\n\t Dane uporzadkowane malejaco\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w milisekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%7d\n", S.measureInsertSort());
		System.out.printf("BubbleSort: \t%7d\n", S.measureBubbleSort());
		System.out.printf("SelectSort: \t%7d\n", S.measureSelectSort());
		System.out.printf("QuickSort: \t%7d\n", S.measureQuickSort());
		System.out.printf("HeapSort: \t%7d\n", S.measureHeapSort());
		System.out.printf("MergeSort: \t%7d\n", S.measureMergeSort());
	}
	public static void testCzasowNano()
	{
		SortTest S = new SortTest();
		S.generateRand();
		System.out.println("=================================================="
				+ "\n\t\t Losowe dane\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w nanosekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%13d\n", S.measureInsertSortNano());
		System.out.printf("BubbleSort: \t%13d\n", S.measureBubbleSortNano());
		System.out.printf("SelectSort: \t%13d\n", S.measureSelectSortNano());
		System.out.printf("QuickSort: \t%13d\n", S.measureQuickSortNano());
		System.out.printf("HeapSort: \t%13d\n", S.measureHeapSortNano());
		System.out.printf("MergeSort: \t%13d\n", S.measureMergeSortNano());
		
		S.generateRosnaco();
		System.out.println("=================================================="
				+ "\n\t Dane uporzadkowane rosnaco\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w nanosekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%13d\n", S.measureInsertSortNano());
		System.out.printf("BubbleSort: \t%13d\n", S.measureBubbleSortNano());
		System.out.printf("SelectSort: \t%13d\n", S.measureSelectSortNano());
		System.out.printf("QuickSort: \t%13d\n", S.measureQuickSortNano());
		System.out.printf("HeapSort: \t%13d\n", S.measureHeapSortNano());
		System.out.printf("MergeSort: \t%13d\n", S.measureMergeSortNano());
		
		S.generateMalejaco();
		System.out.println("=================================================="
				+ "\n\t Dane uporzadkowane malejaco\n"
				+ "==================================================");
		System.out.println("Czasy wykonania algorytmow w nanosekundach przy tablicy wielkosci "+SortTest.SIZE+":");
		System.out.printf("InsertSort: \t%13d\n", S.measureInsertSortNano());
		System.out.printf("BubbleSort: \t%13d\n", S.measureBubbleSortNano());
		System.out.printf("SelectSort: \t%13d\n", S.measureSelectSortNano());
		System.out.printf("QuickSort: \t%13d\n", S.measureQuickSortNano());
		System.out.printf("HeapSort: \t%13d\n", S.measureHeapSortNano());
		System.out.printf("MergeSort: \t%13d\n", S.measureMergeSortNano());
		
	}
}
