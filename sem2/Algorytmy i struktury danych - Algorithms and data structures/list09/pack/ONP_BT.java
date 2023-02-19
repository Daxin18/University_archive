package pack;

public class ONP_BT
{
	//klasa wewnetrzna - wezel
	private class Node
	{
		String value;
		Node left;
		Node right;
		Node(String obj)
		{
			this.value = obj;
		}
		boolean isOperator()
		{
			String s = this.value;
			if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")) return true;
			return false;
		}
	}
	
	//pole klasy - korzen
	private Node root;
	
	//konstruktor
	public ONP_BT(String s)
	{
		int i = s.length()-1;
		if(s.charAt(i)==' ') i--;
		boolean goOn = true;
		boolean goOnSpace = false;
		StringBuilder S = new StringBuilder();
		while(i>=0)
		{
			//System.out.println(S.toString());		//<-- do testow wpisywania do drzewa
			if(s.charAt(i)==' ')
			{
				try		//dziwny sposob na sprawdzenie, czy poza operatorami mielismy same liczby
				{
					if(!isOperator(S.toString()))
						Integer.parseInt(S.toString());
				}
				catch(Exception e)
				{
					System.out.println("Niewlasciwy zapis ONP - blad w liczbie");
					goOn = false;
					break;
				}
				if(addNode(root, S.toString()))		//dodawanie sprawdzajac, czy mamy poprawny zapis ONP
					S = new StringBuilder();
				else
				{
					System.out.println("Niewlasciwy zapis ONP - za duzo liczb");
					goOn = false;
					break;
				}
			}
			else		//jesli nie natrafilismy na spacje, to dodajemy (cyfry liczby) na poczatek StringBuildera
			{
				S.insert(0,s.charAt(i));
			}
			i--;
		}
		//powtarzamy to po petli, bo ONP nie zaczyna sie spacja, wiec ostatnie co dodamy powinno byc integerem
		if(goOn)
		{
			//System.out.println(S.toString());		//<-- do testow wpisywyania do drzewa
			try		//dziwny sposob na sprawdzenie, czy poza operatorami mielismy same liczby
			{
				Integer.parseInt(S.toString());
			}
			catch(Exception e)
			{
				if(S.toString().equals(""))
				{
					//przypadek ten nie wystapi ze wzgledu na poprawnosc zamiany ciagu na ONP
					System.out.println("Niewlasciwy zapis ONP (spacja z przodu, drzewo jest zbudowane poprawnie)");
					goOnSpace = true;
				}
				else
					System.out.println("Niewlasciwy zapis ONP");
				goOn=false;
			}
			if(goOn)
			{
				if(addNode(root, S.toString()))		//dodawanie sprawdzajac, czy mamy poprawny zapis ONP (rownania)
					S = new StringBuilder();
				else
				{
					System.out.println("Niewlasciwy zapis ONP - za duzo liczb");
					goOn = false;
				}
			}
		}
		if(isDone(root))
		{
			if(checkForZero(root))
			{
				if(goOnSpace || goOn)				//jesli gdzies wyrzucilo blad w zapisie ONP (poza przypadkiem ze spacja z przodu
					System.out.println("Poprawne drzewo ONP gotowe");	//to usuwamy korzen, tracac dostep do calej struktury zbudowanej w miedzyczasie
				else													//bo drzewo jest i tak bledne
				{
					System.out.println("Usuwam drzewo");
					this.root = null;
				}
			}
			else
			{
				System.out.println("W drzewie wystepuje dzielenie przez 0, usuwam drzewo");
				this.root = null;
			}
		}
		else
		{
			System.out.println("Usuwam drzewo");
			this.root = null;
		}
	}
	
	//sprawdzanie, czy nie dzielimy przez 0, jesli dzielenie wystepuje - zwraca false
	private boolean checkForZero(Node node)
	{
		if(node.value.equals("/") || node.value.equals("%"))
		{
			return wartosc(node.right)!=0;
		}
		else if(node.isOperator())
			return checkForZero(node.left) && checkForZero(node.right);
		else
			return true;

	}
	
	//metody pomocnicze
	public boolean addNode(Node node, String x)
	{
		if(node==null)	//w sumie ta czesc przyda sie tylko jesli dodamy korzen, w zadnym innym przypadku nie powinnismy
						//dodawac do nulla
		{
			node = new Node(x);
			root = node;
			return true;
		}
		if(node.right==null && node.isOperator())	//sprawdzamy czy po prawej mamy wolne miejsce, a node jest operatorem
		{
			node.right=new Node(x);		//jesli tak, to dopisujemy tam Node z "x"
			return true;
		}
		else if(!isDone(node.right))		//jesli nie to sprawdzamy, czy po prawej jest jeszcze miejsce
		{
			addNode(node.right,x);		//jesli tak, to wywolujemy ta metode po prawej stronie
			return true;
		}
		else if(node.left==null && node.isOperator())	//jesli nie to powtarzamy poprzedni zestaw metod dla lewej strony
		{
			node.left=new Node(x);
			return true;
		}
		else if(!isDone(node.left))
		{
			addNode(node.left,x);
			return true;
		}
		else
			return false;		//jesli oba poddrzewa sa pelne, to znaczy, ze mamy blad w zapisie ONP, zwracam false, co obsluze potem
		
	}
	//metoda sprawdzajaca, czy poddrzewo z korzeniem w node jest "pelne" tj. ma liczbe w kazdym lisciu
	private boolean isDone(Node node)
	{
		if(!node.isOperator()) return true;				//jesli node to cyfra - jest lisciem i drzewo jest z tej strony dobre
		else if(node.right!=null && node.left!=null)	//jesli node jest operatorem i ma dwojke dzieci
			return (isDone(node.right) && isDone(node.left));	//sprawdzamy czy ich poddrzewa sa dobre
		else return false;		//jesli nie ma jakiegos dziecka to poddrzewo nie jest skonczone, zwracamy false
	}
	//metoda sprawdzajaca czy String jest operatorem
	private boolean isOperator(String s)
	{
		if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")) return true;
		return false;
	}
	
	//metody wyswietlania InOrder (z nawiasami)
	public void wyswietlInOrder()
	{
		wyswietlInOrder(root);
		System.out.println(" = "+wartosc());
	}
	private void wyswietlInOrder(Node node)
	{
		if(node!=null)
		{
			if(node.isOperator())
				System.out.print("(");
			wyswietlInOrder(node.left);
			System.out.print(node.value);
			wyswietlInOrder(node.right);
			if(node.isOperator())
				System.out.print(")");
		}
	}
	//metody wyswietlania PostOrder (ONP)
	public void wyswietlPostOrder()
	{
		wyswietlPostOrder(root);
		System.out.println("= "+wartosc());
	}
	private void wyswietlPostOrder(Node node)
	{
		if(node!=null)
		{
			wyswietlPostOrder(node.left);
			wyswietlPostOrder(node.right);
			System.out.print(node.value+" ");
		}
	}
	
	//metody obliczajace wartosc wyrazenia w drzewie
	public int wartosc()
	{
		return wartosc(root);
	}
	private int wartosc(Node node)
	{
		if(!node.isOperator()) return Integer.valueOf(node.value);
		else
		{
		if(node.value.equals("+"))
			return wartosc(node.left) + wartosc(node.right);
		else if(node.value.equals("-"))
			return wartosc(node.left) - wartosc(node.right);
		else if(node.value.equals("*"))
			return wartosc(node.left) * wartosc(node.right);
		//metoda zwraca 0 dla dzielenia przez 0, w przeciwnym wypadku metoda checkForZero (ktora korzysta z wartosci)
		//potrafilaby wykryc tylko "pojedyncze zagniezdzenie" tj. 1/0 zostanie wykryte, ale 1/(1/0) juz nie
		//nie prowadzi to do problemow z wartoscia, bo przy tworzeniu drzewa sprawdzamy dzielenie
		//a jesli zaszlo dzielenie przez 0, to usuwamy drzewo ze stosownym komunikatem
		//zatem nie ma prawa dojsc do dzielenia przez 0 przy faktycznym obliczaniu wartosci
		else if(node.value.equals("/"))
		{
			if(wartosc(node.right)!=0)
				return wartosc(node.left) / wartosc(node.right);
			else
				return 0;
		}
		else if(node.value.equals("%"))
		{
			if(wartosc(node.right)!=0)
				return wartosc(node.left) % wartosc(node.right);
			else
				return 0;
		}
		else return 0;
		}
	}
	
	//metoda liczaca ilosc lisci
	public int liscie()
	{
		return liscie(root);
	}
	private int liscie(Node node)
	{
		if(node.left==null && node.right==null)
			return 1;
		else
			return liscie(node.left)+liscie(node.right);
	}
	
	//metoda liczaca ilosc wezlow
	public int wezly()
	{
		return wezly(root);
	}
	private int wezly(Node node)
	{
		if(node.left==null && node.right==null)
			return 1;
		else
			return wezly(node.left)+wezly(node.right)+1;
	}
	
	//metoda liczaca wysokosc
	public int height()
	{
		return height(root);
	}
	private int height(Node node)
	{
		if(node.left==null && node.right==null)
			return 0;	//zwracamy 0, zeby nie wliczac korzenia do wysokosci, gdybysmy chcieli go uwzglednic nalezy zwrocic 1
						//tak, wiem ,ze w tym miejscu z warunku wynika, ze node jest lisciem, ale w ogolnosci chodzi o odjecie
						//1 od wyniku...
		else
			return Math.max(height(node.left), height(node.right))+1;
	}
	
	//metoda wyswietlajaca drzewo przechodzac po nim wszerz
	public void wyswietlWszerz()
	{
		LinkedQueue<Node> Q = new LinkedQueue<>();
		try
		{
			if(root!=null)
			{
				Q.enqueue(root);
				while(!Q.isEmpty())
				{
					Node node = Q.dequeue();
					System.out.print(node.value+" ");
					if(node.left!=null) Q.enqueue(node.left);
					if(node.right!=null) Q.enqueue(node.right);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Wystapil blad");
		}
	}
	
	
	
}
