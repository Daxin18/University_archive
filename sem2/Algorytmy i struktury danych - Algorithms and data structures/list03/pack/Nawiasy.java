package pack;

public class Nawiasy
{
	public static boolean nawiasyZrownowazone(String wyrazenie)
	{
		ArrayStack<Character> stack = new ArrayStack<>(wyrazenie.length());
		try
		{
			for(int i=0; i<wyrazenie.length(); i++)
			{
				char x = wyrazenie.charAt(i);
				if(nawiasOtwierajacy(x))
					stack.push(Character.valueOf(x));
				else if(nawiasZamykajacy(x))
				{
					if(dobryNawias(stack.top().charValue(), x))
						stack.pop();
					else
						return false;
				}
			}
		}
		catch(EmptyStackException e)
		{
			return false; //stos jest wielkosci wyrazenia, zatem jedynym sposobem na wywolanie
			//bledu bedzie proba porownania nawiasu zamykajacego ze szczytem pustego stosu, co oznacza,
			//ze nawiasy nie sa zrownowazone
		}
		catch(Exception e)
		{
			System.out.println("To sie nie powinno nigdy pojawic na konsoli");
		}
		return stack.isEmpty();
	}
	public static boolean nawiasOtwierajacy(char ch)
	{
		return (ch=='(' || ch=='[' || ch=='{');
	}
	public static boolean nawiasZamykajacy(char ch)
	{
		return (ch==')' || ch==']' || ch=='}');
	}
	
	//metoda pomocnicza - sprawdza dopasowanie nawiasow
	public static boolean dobryNawias(char otwierajacy, char zamykajacy )
	{
		return ((otwierajacy == '(' && zamykajacy == ')') || (otwierajacy == '[' && zamykajacy == ']') || (otwierajacy == '{' && zamykajacy == '}'));
	}
}
