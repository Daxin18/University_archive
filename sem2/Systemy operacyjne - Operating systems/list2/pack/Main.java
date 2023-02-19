package pack;
import java.util.*;

public class Main
{
	public static void main(String args[])
	{
		operacje();
	}
	
	public static void operacje()
	{
		Disk disk = new Disk(1000);
		ArrayList<Request> A = new ArrayList<>();
		Request.generate(A,disk.getMax(),50);
		System.out.println("Ilosc zgloszen: "+A.size()+", ilosc blokow na dysku: "+disk.getMax());
		System.out.println("FCFS: "+disk.FCFS(A));
		System.out.println("SSTF: "+disk.SSTF(A));
		System.out.println("SCAN: "+disk.SCAN(A));
		System.out.println("CSCAN: "+disk.CSCAN(A));
		System.out.println("EDF: "+disk.EDF(A));
		System.out.println("FDSCAN: "+disk.FDSCAN(A));
	}
}
