package z3;

public class UmowaZlecenie implements Wynagrodzenie
{
	public double Oblicz(double godziny, double stawka)
	{
		return (godziny*stawka);
	}
}
