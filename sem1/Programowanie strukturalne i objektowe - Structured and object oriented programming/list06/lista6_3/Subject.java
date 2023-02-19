package lista6_3;

public interface Subject
{
	public void addObserver(Observer1 o);
	public void removeObserver(Observer1 o);
	public void notifyObservers();
}
