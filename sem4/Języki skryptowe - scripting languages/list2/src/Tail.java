import java.util.Scanner;

public class Tail
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        CircularArray text;

        int n = 0;

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

        //tworzenie tablicy
        if(n > 0)
        {
            text = new CircularArray(n);
            //zbieranie tekstu
            while (scan.hasNext())
                text.add(scan.nextLine());

            //wypisywanie o ile jest go wystarczajaco duzo
            if(text.isFull())
                System.out.println(text.getAll());
            else
            {
                if(e) //sprawdzanie flagi e
                    System.err.println("Zabraklo " + text.freeSpace() + " linii");
                System.exit(2);
            }
        }

        System.exit(0);
    }

    //klasa pomocnicza, dzieki niej nie wysadze komputera ladujac text
    private static class CircularArray
    {
        private int idx = 0;
        private String[] tab;
        boolean full = false;

        public CircularArray(int size) { this.tab = new String[size]; }

        public void add(String s)
        {
            tab[increase()] = s;
            checkFull();
        }
        public String getAll() //metoda zwracajaca String bedacy polaczeniem stringow z tablicy, rozdzielonych newline
        {
            StringBuilder S = new StringBuilder();

            int i = idx;
            S.append(tab[increase()] + "\n");

            while(idx != i)
            {
                S.append(tab[increase()] + "\n");
            }

            return S.toString();
        }
        public boolean isFull() { return full; }

        public int freeSpace() //uzywana tylko, gdy tablica nie jest pelna !!!
        {
            return (tab.length - idx);
        }

        private void checkFull() //metoda wywolywana przy dodawaniu, po inkrementacji indexu
        {
            if(idx == 0)
                full = true;
        }
        private int increase()
        {
            int i = idx++;
            if(idx == tab.length)
                idx = 0;
            return i;
        }

    }
}
