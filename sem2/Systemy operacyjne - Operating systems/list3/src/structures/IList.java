package structures;
import java.util.*;

public interface IList<E> extends Iterable<E>
{
	boolean add(E e); // dodanie elementu na koniec listy
	boolean add(int index, E element); // dodanie elementu na podanej pozycji
	void clear(); // skasowanie wszystkich elementw
	boolean contains(E element); // czy lista zawiera podany element (equals())
	E get(int index); // pobranie elementu z podanej pozycji
	E set(int index, E element); // ustawienie nowej wartosci na pozycji
	int indexOf(E element); // pozycja szukanego elementu (equals())
	boolean isEmpty(); // czy lista jest pusta
	Iterator<E> iterator(); // zwraca iterator przed pierwsza pozycja
	ListIterator<E> listIterator(); // j.w. dla ListIterator
	E remove(int index); // usuwa element z podanej pozycji
	boolean remove(E element); // usuwa element (equals())
	int size(); // rozmiar listy
}