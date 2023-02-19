package pack;
import java.util.*;

public class HuffmanTree
{
	private class Node implements Comparable<Node>
	{
		char symbol;
		int frequency;
		Node left;
		Node right;
		
		public Node(char symbol, int frequency)
		{
			this.symbol = symbol;
			this.frequency = frequency;
		}
		public Node(int frequency, Node l, Node r)
		{
			this.frequency = frequency;
			this.left = l;
			this.right = r;
		}
		public int getFrequency()
		{
			return frequency;
		}
		public Node mergeWith(Node secondOne)
		{
			return new Node(frequency+secondOne.frequency, this, secondOne);
		}
		public int compareTo(Node n)
		{
			return n.frequency-frequency;	//nizsza czestotliwosc = wiekszy priorytet (tylko tam wykorzystuje compareTo)
		}
		public boolean contains(char c)
		{
			if(left==null && right==null)
				return symbol==c;
			else
				return (left.contains(c) || right.contains(c));
		}
	}
	
	private Node root;
	private String tekst;
	
	public HuffmanTree(String s)
	{
		this.tekst = s;
		if(s.length()>0)
		{
			ArrayList<Node> nodes = new ArrayList<>();
			for(int i=0; i<s.length(); i++)	//w tej petli tworzymy ArrayListe pojedynczych wezlow (drzew)
			{
				char x = s.charAt(i);
				boolean contains = false;
				int index=-1;
				for(int j=0; j<nodes.size(); j++)
				{
					if(nodes.get(j).symbol == x)
					{
						contains=true;
						index=j;
						break;
					}
				}
				if(contains)
				{
					nodes.set(index, new Node(x, nodes.get(index).getFrequency()+1));
				}
				else
					nodes.add(new Node(x, 1));
			}
			LinkedPriorityQueue<Node> queue = new LinkedPriorityQueue<>();
			//
			//w tej petli przerzucamy je do kolejki priorytetowej
			//
			//jesli wpiszemy
			//
			//
			//for(int i=0; i<nodes.size(); i++)
			//
			//
			//to drzewo nadal bedzie poprawne, jednak uzyskamy inne kody ze wzgledu na ulozenie 
			//symboli w kolejce priorytetowej, co przelozy sie na ich miejsce w drzewie
			//
			//w "ala i ola." - 'l' zamieni sie ze ' ', natomiast 'i' zamini sie z '.'
			//
			//
			for(int i=nodes.size()-1; i>=0; i--)
			{
				queue.enqueue(nodes.get(i));
			}
			Node node1=null;
			Node node2=null;
			try
			{
				while(!queue.isEmpty())
				{
					node1 = queue.dequeue();
					if(queue.isEmpty())	//jesli wyjdziemy z petli to znaczy, ze node1 byl ostatnim elementem, czyli korzeniem drzewa Huffmana
						break;
					else
					{
						node2 = queue.dequeue();
						queue.enqueue(node1.mergeWith(node2));
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("To nie powinno sie nigdy pojawic");
				e.printStackTrace();
			}
			this.root = node1;
			System.out.println("Drzewo utworzone!");
		}
		else
		{
			this.root = null;
			System.out.println("Podano pusty ciag znakow!");
		}
	}
	
	public String getTekst()
	{
		return tekst;
	}
	
	public String showSymbolsFrequencyAndCode()
	{
		System.out.println("Kolejnosc symboli jak w drzewie od lewej strony");
		return leaves(root);
	}
	private String leaves(Node node)
	{
		StringBuilder S = new StringBuilder();
		if(node.left==null && node.right==null)
			S.append("Symbol-kod:\t\'"+node.symbol+"\' - "+codeOf(node.symbol)+"\t ilosc wystapien: "+ node.frequency+"\n");
		else
		{
			S.append(leaves(node.left));
			S.append(leaves(node.right));
		}
		return S.toString();
	}
	
	public String codeOf(char x)
	{
		return codeOf(root, x);
	}
	private String codeOf(Node node, char x)
	{
		if(!node.contains(x))
			return "Nie ma takiego znaku w tekscie";
		StringBuilder S = new StringBuilder();
		if(node.symbol==x)
			return S.toString();
		if(node.left.contains(x))
		{
			S.append("0");
			S.append(codeOf(node.left, x));
		}
		else
		{
			S.append("1");
			S.append(codeOf(node.right,x));
		}
		return S.toString();
	}
	
	public String cypher()
	{
		StringBuilder S = new StringBuilder();
		for(int i=0; i<tekst.length(); i++)
		{
			S.append(codeOf(tekst.charAt(i)));
		}
		return S.toString();
	}
	public String decypher(String s)
	{
		StringBuilder S = new StringBuilder();
		Node current = root;
		for(int i=0; i<s.length(); i++)	//petla przechodzi po drzewie przez cala dlugosc podanego stringa
		{
			if(current.left==null && current.right==null) //jesli mamy do czynienia z lisciem
			{
				S.append(current.symbol); //dopisujemy jego symbol do StringBuildera
				current = root; //i ustawiamy aktualnego Node'a na korzen
			}
			if(s.charAt(i)=='0')	//jesli char to 0, idziemy w lewo
				current = current.left;
			else if(s.charAt(i)=='1')	//jesli char to 1, idziemy w prawo
				current = current.right;
			else	//jesli mamy innego chara, to znaczy, ze podano bledny zakodowany tekst
				return "Wystapil blad - zakodowany tekst powinien zawierac tylko '0' i '1'";
		}
		if(current.left==null && current.right==null) //sprawdzamy czy na koncu jestesmy na lisciu
		{
			S.append(current.symbol); //jesli tak, to dopisujemy go do StringBuildera
		}
		else //jesli nie to zwracamy komunikat o bledzie
		{
			return "Wystapil blad - nie doszlismy do liscia, podany kod jest zle napisany, brakuje w nim znakow, lub jest ich za duzo";
		}
		return S.toString();
	}
	
}
