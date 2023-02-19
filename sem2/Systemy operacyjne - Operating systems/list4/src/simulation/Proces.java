package simulation;
import java.util.*;

public class Proces
{
	private int bound; //zakres numerow stron [0,bound]
	private int localityUpperBound; //zakres gorny (maksymalna odleglosc od jakiejs poczatkowej wartosci) w ktorym generowany jest zakres strefy
	private int localityLowerBound; //zakres dolny (minimalna odleglosc od jakiejs poczatkowej wartosci) w ktorym generowany jest zakres strefy
	private int iloscStref; //ilosc stref w danym procesie
	private ArrayList<ArrayList<Integer>> zakresyStref; //.get(0) - minimalny numer, .get(1) - maksymalny numer (w liscie wewnetrznej)
	
	private int rozmiar=0; 	//rozumiany jako ilosc wszystkich stron, do ktorych proces moze sie odwolac
							//(suma rozmiarow wszystkich stref)
	
	//pola pomocnicze
	private Random rand = new Random();
	
	public Proces(int bound, int LUB, int LLB, int iloscStref)
	{
		this.bound = bound;
		this.localityUpperBound = LUB;
		this.localityLowerBound = LLB;
		this.iloscStref = iloscStref;
		this.zakresyStref = new ArrayList<>();
		for(int i=0; i<this.iloscStref; i++)
		{
			generateStrefa(i);
		}
	}
	
	public int getIloscStref()
	{
		return iloscStref;
	}
	public ArrayList<Integer> getStrefa(int i)
	{
		return zakresyStref.get(i);
	}
	public int getRozmiar()
	{
		return rozmiar;
	}
	public void setRozmiar(int i)
	{
		this.rozmiar = i;
	}
	
	//metoda generujaca zakres strefy w obrebie ktorej beda generowane odwolania do stron
	private void generateStrefa(int i)
	{
		ArrayList<Integer> temp = new ArrayList<>(); //lista z zakresami
		int tempBound = rand.nextInt(localityUpperBound-localityLowerBound+1)+localityLowerBound; //generujemy zakres (wzgledem srodka zakresu)
		
		int x = rand.nextInt(bound+1); //generujemy numer srodka zakresu
		temp.add(Math.max(0, x-tempBound)); //dodajemy minimalny numer strony do zakresow
		temp.add(Math.min(bound, x+tempBound)); //dodajemy maksymalny numer strony do zakresow
		zakresyStref.add(temp); //zapisujemy zakresy w liscie procesu
		rozmiar += (2*tempBound) + 1; //Math.max(rozmiar,(2*tempBound) + 1);
	}
	
	//toString for fun
	@Override
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		for(int i=0; i<zakresyStref.size(); i++)
		{
			S.append("Strefa numer: "+i+", zakres: "+zakresyStref.get(i).get(0)+" - "+zakresyStref.get(i).get(1)+"\n");
		}
		return S.toString();
	}
	
}
