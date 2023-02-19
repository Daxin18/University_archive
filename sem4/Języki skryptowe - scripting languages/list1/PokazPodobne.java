import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;

public class PokazPodobne
{
    public static void main(String[] args)
    {
        Map<String, String> env = System.getenv(); //zmienne jako mapa <nazwa, wartosc>
        ArrayList<String> envF = new ArrayList<>(); //zmienne jako stringi w arrayliscie

        for(String par : args) //petla zapisujaca wszystkie pasujace do parametrow zmienne w arrayliscie envF
        {
            boolean found = false;
            for (String envName : env.keySet()) //wypisywanie zmiennych
                if (envName.contains(par))
                {
                    envF.add(envName + " = " + env.get(envName));
                    found = true;
                }
            if(!found)
                System.out.println(par + " = NONE");
        }

        Collections.sort(envF);

        for(String s : envF)
        {
            if(s.contains(";"))
                showComplexVariable(s);
            else
                System.out.println(s);
        }

    }

    public static void showComplexVariable(String s)
    {
        char curr;
        for(int i=0; i<s.length(); i++)
        {
            curr = s.charAt(i);
            if(curr == '=')
            {
                System.out.print(curr + "\n\t");
                i++; //zeby pominac spacje po = i wszystko bylo rowno
            }
            else if(curr == ';')
                System.out.print("\n\t");
            else
                System.out.print(curr);
        }
        System.out.println();
    }

}
