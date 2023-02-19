package pack;
import java.util.*;

public class Procesor
{
	int numer;
	int obciazenie;
	int zapytaniaObciazenia;
	int migracje;
    ArrayList<Proces> procesy;
	
    public Procesor(int x)
    {
    	this.numer = x;
    	this.obciazenie = 0;
    	this.zapytaniaObciazenia = 0;
    	this.migracje = 0;
    	this.procesy = new ArrayList<>();
    }
    
    public void uplywaCzasProcesow()
    {
        if (procesy.size()==0)
            return;

        for (int i=0; i<procesy.size(); i++)
        {
            procesy.get(i).czasWykonania--;
            if (procesy.get(i).czasWykonania==0)
            {
                obciazenie = Math.max(0 , obciazenie - procesy.get(i).rozmiar);
                procesy.remove(i--);
            }
        }
    }
    
    public void przeniesProces(Procesor proc)
    {
    	Proces p = proc.procesy.remove(0);
    	proc.obciazenie -= p.rozmiar;
    	this.procesy.add(p);
    	this.obciazenie += p.rozmiar;
    }
}
