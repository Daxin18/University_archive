import java.util.ArrayList;
import java.util.Scanner;

public class Head
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> text = new ArrayList<>();

        int n = 0; //ilosc linii do wypisania

        boolean e = true;

        //ustawianie parametrow
        for(int i=0; i < args.length; i++)
        {
            String curr = args[i];

            if(curr.length() >= 2 && curr.charAt(0) == '-')
            {
                if(curr.charAt(1) == '-' && curr.contains("lines"))
                {
                    String[] split = curr.split("=");
                    if(split.length == 2 && Process.isNumber(split[1]))
                        n = Integer.parseInt(split[1]);
                    else
                        System.out.println("\"" + split[1] + "\" is not a valid value for parameter \"lines\"");
                }
                else if (curr.charAt(1) == 'e')
                    e = false;
                else
                    System.out.println("\"" + curr + "\" is not a valid parameter");
            }
            else
                System.out.println("\"" + curr + "\" is not a valid parameter");
        }

        //zbieranie tekstu
        while(scan.hasNext() && text.size() <= n)
        {
            text.add(scan.nextLine());
        }

        //sprawdzanie ilosci linii
        if(text.size() < n)
        {
            if(e) //sprawdzenie flagi e
                System.err.println("Zabraklo " + (n - text.size()) + " linii");
            System.exit(2);
        }
        else //wypisywanie
            for(int i=0; i < n; i++)
                System.out.println(text.get(i));

        System.exit(0);
    }
}
