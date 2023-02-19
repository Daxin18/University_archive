package z3;

public class Etatowy implements Wynagrodzenie
{
	public double Oblicz(double pensja, double premia)
	{
		return (pensja+((premia*pensja)/100));
	}
}
