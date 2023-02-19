package pack;

public class Testy
{
	public static void main(String args[])
	{
		//testy(new Zadanie3<String>(10));
		System.out.println("Wynik to: " + liczenieONP("57*29*+"));
	}
	
	public static void testy(IStack<String> Stack)
	{
		try
		{
			Stack.push("element 1");
			Stack.push("element 2");
			Stack.push("element 3");
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			Stack.push("element 4");
			Stack.push("element 5");
			Stack.push("element 6");
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			Stack.push("element 7");
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
			System.out.println(Stack.pop() + ", rozmiar stosu: "+ Stack.stackSize());
		}
		catch(FullStackException e)
		{
			e.printStackTrace();
			System.out.println("Aktualny stan stosu:\nIlosc elementow: "+Stack.elements()+"\nElementy(W kolejnosci w jakiej beda opuszczac stos):");
			Stack.showStack();
		}
		catch(EmptyStackException e)
		{
			e.printStackTrace();
		}
	}
	
	public static double liczenieONP(String s)
	{
		double a;
		double b;
		String temp;
		Zadanie5<String> Stack = new Zadanie5<String>(5); 
		try {
		for(int i=0; i<s.length(); i++)
		{
			temp = Character.toString(s.charAt(i));
			switch(temp)
			{
			case "+":
			{
				b = Double.parseDouble(Stack.pop());
				a = Double.parseDouble(Stack.pop());
				Stack.push(Double.valueOf(a+b).toString());
				break;
			}
			case "-":
			{
				b = Double.parseDouble(Stack.pop());
				a = Double.parseDouble(Stack.pop());
				Stack.push(Double.valueOf(a-b).toString());
				break;
			}
			case "/":
			{
				b = Double.parseDouble(Stack.pop());
				a = Double.parseDouble(Stack.pop());
				Stack.push(Double.valueOf(a/b).toString());
				break;
			}
			case "*":
			{
				b = Double.parseDouble(Stack.pop());
				a = Double.parseDouble(Stack.pop());
				Stack.push(Double.valueOf(a*b).toString());
				break;
			}
			default:
				Stack.push(temp);
				break;
			}
			
		}
		return Double.parseDouble(Stack.pop());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
