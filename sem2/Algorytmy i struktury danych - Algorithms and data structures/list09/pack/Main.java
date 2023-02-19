package pack;

public class Main
{
	public static void main(String args[])
	{	
		//wyrazenie 1: "12+(3*2+6)"
		//wyrazenie 2: "((4+3)-(2+1)*2+3)/2"
		//wyrazenie "0": "1/(2-2)"
		//wyrazenie "bardziej zagniezdzone 0": "1/(1/(2-2))"
		//wyrazenie "z pomijalnym k": "k12+(3*2+6)"
		//wyrazenie "z blednym k": "1k2+(3*2+6)"
		
		System.out.println("UWAGA! Program zignoruje wszystkie litery i nieobslugiwane znaki w zapisie,"
				+ "\n\to ile nie beda one w srodku liczby np. 1k2\n"
				+ "\t(dostaniemy wtedy komunikat - za duzo liczb)\n"
				+ "============================================================================");
		
		Wyrazenie x = new Wyrazenie("2/(2-2)");
		x.wyswietlWszystkoCoMialoByc();
	}

}
