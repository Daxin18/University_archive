import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class KodPowrotuPlik
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in); //wejscie standardowe
        ArrayList<String> text = new ArrayList<>(); //arraylista przechowujaca tekst z pliku
        int[] count = new int[args.length]; //tablica przechowujaca ilosc wystapien kazdego parametru

        for(String a : args) System.out.println(a);

        for(int i : count) i=0; //zerowanie wartosci na wszelki wypadek

        System.out.print("Enter filename: ");
        String filename = scan.next();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)));) //otwarcie pliku
        {
            String[] words;
            while (br.ready()) //czytanie pliku i rozdzielanie go na slowa
            {
                words = br.readLine().split("[,.!? ]");
                for (String word : words) {
                    text.add(word);
                    //System.out.println(word); //do testow
                }
            }

            for(int i=0; i< args.length; i++) //zliczanie wystapien parametrow
                for (String word : text)
                    if(word.equals(args[i])) {
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

            if(maxCount>0) maxI++; //jesli sa wystapienia to zwiekszamy numer

            System.out.println(maxI + " " + args[maxI-1] + " " + maxCount);
            System.exit(maxI);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.printf("File %s does not exist!\n", filename);
        }
    }
}
