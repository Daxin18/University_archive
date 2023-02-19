package pack;
import java.util.*;

public class RBTree<E extends Comparable<E>>
{
	private final boolean RED = true;
	private final boolean BLACK = false;
	
	private class Node
	{
		E key;
		ArrayList<Integer> wiersze;
		Node left;
		Node right;
		boolean color;
		Node parent;
		
		Node(E key, boolean color)
		{
			this.key = key;
			this.wiersze = new ArrayList<>();
			this.color = color;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		
		void addA(int x)
		{
			wiersze.add(x);
		}
		
		boolean isRed()
		{
			return color;
		}
		boolean isBlack()
		{
			return !color;
		}
		@Override
		public String toString()
		{
			StringBuilder S = new StringBuilder();
			S.append(String.format("%-15s", key)+"- [");
			for(int i=0; i<wiersze.size(); i++)
			{
				S.append(wiersze.get(i));
				if(i<wiersze.size()-1)
					S.append(", ");
			}
			S.append("], kolor: ");
			if(color)
				S.append("RED");
			else
				S.append("BLACK");
			return S.toString();
		}
	}
	
	private Node root;
	
	//konstruktory
	public RBTree()
	{
		this.root = null;
	}
	public RBTree(E s, int x)
	{
	//	this.root = new Node(s, new ArrayList<>(), BLACK);
	//	root.addA(x);
	}
	
	
	//metoda sprawdzajaca, czy dany klucz jest w drzewie
	private boolean contains(E key)
	{
		return contains(key, root);
	}
	private boolean contains(E key, Node node)
	{
		if(node==null) return false;
		if(key.compareTo(node.key)>0) return contains(key, node.right);
		if(key.compareTo(node.key)<0) return contains(key, node.left);
		return true;
	}
	
	public void add(E key, int wiersz)
	{
		if(!contains(key))
		{
			Node newNode = new Node(key, RED);
			newNode.addA(wiersz);
			Node y = null;
			Node x = root;
			while(x!=null)
			{
				y = x;
				if(key.compareTo(x.key)<0)
					x = x.left;
				else
					x = x.right;
			}
			newNode.parent = y;
			if(y == null)
				root = newNode;
			else if (key.compareTo(y.key)<0)
				y.left = newNode;
			else
				y.right = newNode;
			fix(newNode);	
		}
		else
		{
			Node x = root;
			while(!x.key.equals(key))
			{
				if(key.compareTo(x.key)<0)
					x = x.left;
				else
					x = x.right;
			}
			x.addA(wiersz);
		}
	}
	
	//naprawianie drzewa
	private boolean fix(Node node)
	{
		node.color = RED;
		while(node!=root && node.parent.isRed())
		{
			if(node.parent==null)
			{
				break;
			}
			if(node.parent.parent==null)
			{
				break;
			}
			if(node.parent == node.parent.parent.left)
			{
				Node y = node.parent.parent.right;
				if(y!=null && y.isRed())
				{
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				}
				else
				{
					if(node == node.parent.right)
					{
						node = node.parent;
						leftRotate(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					rightRotate(node.parent.parent);
				}
			}
			else
			{
				Node y = node.parent.parent.left;
				if(y!=null && y.isRed())
				{
					node.parent.color = BLACK;
					y.color = BLACK;
					node.parent.parent.color = RED;
					node = node.parent.parent;
				}
				else
				{
					if(node == node.parent.left)
					{
						node = node.parent;
						rightRotate(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					leftRotate(node.parent.parent);
				}
			}
		}
		root.color = BLACK;
		return true;
	}
	
	//obie rotacje
	private boolean leftRotate(Node node)
	{
		if(node.right!=null)
		{
			Node b = node.right;
			Node c = b.left;				//zakladamy, ze b!=null, w przeciwnym wypadku rotacja jest niemozliwa
			if(node.parent!=null)
			{
				if(node.equals(node.parent.left))
					node.parent.left = b;
				else
					node.parent.right = b;
			}
			
			b.parent = node.parent;
			b.left = node;
			node.parent = b;
			node.right = c;
			if(c!= null)
				c.parent = node;
			if(node == root)
				root=b;
			return true;
		}
		return false;
	}
	private boolean rightRotate(Node node)
	{
		if(node.left!=null)
		{
			Node b = node.left;
			Node c = b.right;				//zakladamy, ze b!=null, w przeciwnym wypadku rotacja jest niemozliwa
			if(node.parent!=null)
			{
				if(node.equals(node.parent.left))
					node.parent.left = b;
				else
					node.parent.right = b;
			}
			
			b.parent = node.parent;
			b.right = node;
			node.parent = b;
			node.left = c;
			if(c!= null)
				c.parent = node;
			if(node == root)
				root=b;
			return true;
		}
		return false;
	}
	
	//przejscie inOrder
	public String inOrder()
	{
		return inOrder(root);
	}
	private String inOrder(Node node)
	{
		StringBuilder S = new StringBuilder();
		if(node.left!=null)
			S.append(inOrder(node.left));
		S.append("\n");
		S.append(node.toString());
		S.append("\n");
		if(node.right!=null)
			S.append(inOrder(node.right));
		return S.toString();
	}
	public String wszerz()
	{
		return poziomy(root);
	} 
	private String poziomy(Node node)
	{
		StringBuilder S = new StringBuilder();
		ArrayList<Node> A = new ArrayList<>(); //ArrayLista robi za kolejke
		Node n;
		A.add(node);
		S.append("\n\n");
		while(!A.isEmpty())
		{
			n = A.remove(0); //"dequeue"
			S.append(n.key+"\n");
			if(n.left!=null)
				A.add(n.left); //"enqueue"
			//else
			//	System.out.println(n.key+"\t nie ma lewego dziecka!"); //<-- do testow
			if(n.right!=null)
				A.add(n.right); //"enqueue"A
			//else
			//	System.out.println(n.key+"\t nie ma prawego dziecka!"); //<--do testow
		}
		return S.toString();
	}
}
