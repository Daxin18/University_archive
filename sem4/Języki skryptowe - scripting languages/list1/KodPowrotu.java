import java.util.ArrayList;
import java.util.Scanner;

public class KodPowrotu
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in); //wejscie standardowe
        ArrayList<String> text = new ArrayList<>(); //arraylista przechowujaca tekst z pliku
        int[] count = new int[args.length]; //tablica przechowujaca ilosc wystapien kazdego parametru

        for(String a : args) System.out.println(a);

        for(int i : count) i=0; //zerowanie wartosci na wszelki wypadek

        System.out.print("Enter the text: ");
        String input = scan.nextLine();

        String[] words;
        words = input.split("[,.!? ]");
        for (String word : words)
        {
            text.add(word);
            //System.out.println(word); //do testow
        }

        for(int i=0; i< args.length; i++) //zliczanie wystapien parametrow
            for (String word : text)
                if(word.equals(args[i]))
                {
                    count[i]++;
                    //System.out.println(args[i]); //do testow
                }

        int maxI = 0;
        int maxCount = 0;
        for(int i=0 ; i<count.length; i++)
            if (count[i] > maxCount)
            {
                maxCount = count[i];
                maxI = i;
            }

        if(maxCount>0) maxI++; //jesli sa wystapienia to zwiekszamy numer, zeby nie byl indeksem

        if(args.length > 0)
            System.out.println(maxI + " " + args[Math.max(0,maxI-1)] + " " + maxCount);
        System.exit(maxI);
    }
}
