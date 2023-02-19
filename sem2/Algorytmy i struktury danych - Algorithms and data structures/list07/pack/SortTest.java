package pack;
import java.util.*;

public class SortTest
{
	private int[] T;
	public static final int SIZE = 10000; 
	private final Random random = new Random();
	
	//konstruktor
	public SortTest()
	{
		this.T = new int[SIZE];
	}
	
	//getery do testow
	public int getIndex(int ind)
	{
		return T[ind];
	}
	public int[] getT()
	{
		return T;
	}
	
	//generowanie tablicy T
	public void generateRand()
	{
		for(int i=0; i<SIZE; i++)
		{
			T[i] = random.nextInt(SIZE) +1;
		}
	}
	public void generateRosnaco()
	{
		for(int i=0; i<SIZE; i++)
		{
			T[i] = i;
		}
	}
	public void generateMalejaco()
	{
		for(int i=0; i<SIZE; i++)
		{
			T[i] = SIZE-i;
		}
	}
	
	//metoda przepisujaca tablice aby kazdy algorytm dostal kopie oryginalnej tablicy
	public int[] copy()
	{
		int[] temp = new int[SIZE];
		for(int i=0; i<SIZE; i++)
		{
			temp[i] = T[i];
		}
		return temp;
	}
	
	//metody pomocnicze
	private void swap(int[] T, int indX, int indY)
	{
		if(indX != indY)
		{
			int c = T[indX];
			T[indX] = T[indY];
			T[indY] = c;
		}
	}
	
	/*
	==================================
			metody sortowania
	==================================
	*/
	public int[] InsertSort()
	{
		int[] temp = this.copy();
		for(int i=1; i<SIZE; i++)
		{
			int x = temp[i];
			int j;
			for(j=i; j>0 && temp[j-1]>x ; j--)
			{
				temp[j] = temp[j-1];
			}
			temp[j] = x;
		}
		return temp;
	}
	
	//BubbleSort z usprawnieniem w postaci przerywania algorytmu, jesli tabela jest uporzadkowana
	public int[] BubbleSort()
	{
		int[] temp = this.copy();
		boolean goOn;
		for(int i=1; i<SIZE; ++i)
		{
			goOn=false;
			for(int j=0; j<(SIZE-i); ++j)
			{
				if(temp[j] > temp[j+1])
				{
					swap(temp, j, j+1);
					goOn = true;
				}
			}
			if(!goOn) break;
		}
		return temp;
	}
	
	public int[] SelectSort()
	{
		int[] temp = this.copy();
		int min;
		int ind;
		for(int i=0; i<SIZE-1; i++)
		{
			min = temp[i];
			ind = i;
			for(int j=i+1; j<SIZE; j++)
			{
				if(temp[j]<min)
				{
					min = temp[j];
					ind = j;
				}
			}
			swap(temp, ind, i);
		}
		return temp;
	}
	
	public int[] QuickSort()
	{
		int[] temp = this.copy();
		quickSort(temp, 0, SIZE);
		return temp;
	}
	//metody pomocnicze do QuickSort
	private void quickSort(int[] T, int startIndex, int endIndex)
	{
		if(endIndex - startIndex > 1)
		{
			int partition = partition(T, startIndex, endIndex);
			quickSort(T, startIndex, partition );
			quickSort(T, partition + 1, endIndex);
		}
	}
	private int partition(int[] T, int nFrom, int nTo)
	{
		int rand = nFrom+random.nextInt(nTo-nFrom);	
		swap(T, nFrom, rand);
		int x = T[nFrom];
		int indBigger = nFrom+1, indLower = nTo-1;
		do
		{
			while(indBigger<=indLower && T[indBigger]<=x)
				indBigger++;
			while(T[indLower]>x)
				indLower--;
			if(indBigger<indLower)
				swap(T,indBigger,indLower);
		}while(indBigger<indLower);
		swap(T, indLower, nFrom);
		return indLower;
	}
	
	public int[] HeapSort()
	{
		int[] temp = this.copy();
		heapAdjustment(temp, SIZE);
		for(int i=SIZE-1; i>0; i--)
		{
			swap(temp, i, 0);
			sink(temp, 0, i);
		}
		return temp;
	}
	//metody pomocnicze do heapSort
	private void sink(int[] heap, int idx, int n)
	{
		int idxOfBigger = 2*idx+1;
		if(idxOfBigger<n)
		{
			if(idxOfBigger+1<n && heap[idxOfBigger] < heap[idxOfBigger+1])
				idxOfBigger++;
			if( heap[idx] < heap[idxOfBigger])
			{
				swap(heap, idx, idxOfBigger);
				sink(heap, idxOfBigger, n);
			}
		}
	}
	private void heapAdjustment(int[] heap, int n)
	{
		for(int i=(n-1)/2; i>=0; i--)
			sink(heap, i, n);
	}

	public int[] MergeSort()
	{
		int[] temp = this.copy();
		return mergeSort(temp, 0, SIZE-1);
	}
	//metody pomocnicze do MergeSort()
	private int[] mergeSort(int[] T, int startIdx, int endIdx)
	{
		if(startIdx == endIdx)
		{
			int[] result = new int[1];
			result[0] = T[startIdx];
			return result;
		}
		int splitIdx = startIdx + (endIdx - startIdx)/2;
		return merge(mergeSort(T,startIdx,splitIdx), mergeSort(T, splitIdx+1, endIdx));
	}
	private int[] merge(int[] left, int[] right)
	{
		int[] result = new int[left.length + right.length];
		int resIndex=0;
		IntIterator l = new IntIterator(left);
		IntIterator r = new IntIterator(right);
		int elemL=0, elemR=0;
		boolean contL, contR;
		if(contL = l.hasNext()) elemL = l.next();
		if(contR = r.hasNext()) elemR = r.next();
		while(contL && contR)
		{
			if(elemL<=elemR)
			{
				result[resIndex++] = elemL;
				if(contL = l.hasNext()) elemL = l.next();
				else result[resIndex++] = elemR;
			}
			else
			{
				result[resIndex++] = elemR;
				if(contR = r.hasNext()) elemR = r.next();
				else result[resIndex++] = elemL;
			}
		}
		while(l.hasNext()) result[resIndex++] = l.next();
		while(r.hasNext()) result[resIndex++] = r.next();
		return result;
	}

	//metody wyswietlania calosci do testow
	public void wyswietl()
	{
		System.out.print("[");
		for(int i=0; i<SIZE; i++)
		{
			System.out.print(T[i] + ", ");
		}
		System.out.print("]\n");
	}
	public static void wyswietl(int[] T)
	{
		System.out.print("[");
		for(int i=0; i<T.length; i++)
		{
			System.out.print(T[i] + ", ");
		}
		System.out.print("]\n");
	}

	/*
	======================================
			metody pomiaru czasu
	======================================
	*/
	public long measureCopyCreation()
	{
		long beggining = System.currentTimeMillis();
		this.copy();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureInsertSort()
	{
		long beggining = System.currentTimeMillis();
		this.InsertSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureBubbleSort()
	{
		long beggining = System.currentTimeMillis();
		this.BubbleSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureSelectSort()
	{
		long beggining = System.currentTimeMillis();
		this.SelectSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureQuickSort()
	{
		long beggining = System.currentTimeMillis();
		this.QuickSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureHeapSort()
	{
		long beggining = System.currentTimeMillis();
		this.HeapSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	public long measureMergeSort()
	{
		long beggining = System.currentTimeMillis();
		this.MergeSort();
		long end = System.currentTimeMillis();
		return end-beggining;
	}
	
	//czasy w nanosekundach
	public long measureCopyCreationNano()
	{
		long beggining = System.nanoTime();
		this.copy();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureInsertSortNano()
	{
		long beggining = System.nanoTime();
		this.InsertSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureBubbleSortNano()
	{
		long beggining = System.nanoTime();
		this.BubbleSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureSelectSortNano()
	{
		long beggining = System.nanoTime();
		this.SelectSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureQuickSortNano()
	{
		long beggining = System.nanoTime();
		this.QuickSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureHeapSortNano()
	{
		long beggining = System.nanoTime();
		this.HeapSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	public long measureMergeSortNano()
	{
		long beggining = System.nanoTime();
		this.MergeSort();
		long end = System.nanoTime();
		return end-beggining;
	}
	
	
}
