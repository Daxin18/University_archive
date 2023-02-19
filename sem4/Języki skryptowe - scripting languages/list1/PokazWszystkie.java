import java.util.Map;

public class PokazWszystkie
{
    public static void main(String[] args)
    {
        Map<String, String> env = System.getenv(); //zmienne jako mapa <nazwa, wartosc>

        for(String envName : env.keySet()) //wypisywanie zmiennych
            System.out.format("%s=%s ", envName, env.get(envName));

        System.out.println();

        for( String S : args) //wypisywanie parametrow
            System.out.print(S + " ");
    }
}
