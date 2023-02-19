import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> text = new ArrayList<>();

        int ifN = 0; //ignore first (bool zbedny przy wartosci domyslnej 0)
        int ilN = 0; //ignore last (bool zbedny przy wartosci domyslnej 0)

        String str = ";"; //symbol po ktorym dokonamy separacji
        String sep = "\t"; //symbol ktorym go zastapimy
        String sel = ""; // selektor

        StringBuilder S = new StringBuilder();
        String output;

        ArrayList<Integer> toProject = new ArrayList<>();

        for(int i=0; i< args.length; i++) //petla przegladajaca argumenty i ustawiajaca wszystkie parametry
        {
            String curr = args[i];

            if(curr.length() >= 2 && curr.charAt(0) == '-' && curr.charAt(1) == '-')
            {
                String[] split = curr.split("=");
                if(curr.contains("ignorefirst") && split.length == 2 && isNumber(split[1]))
                    ifN = Integer.parseInt(split[1]);
                else if(curr.contains("ignorelast") && split.length == 2 && isNumber(split[1]))
                    ilN = Integer.parseInt(split[1]);
                else if(curr.contains("delimiter") && split.length == 2)
                    str = split[1];
                else if(curr.contains("separator") && split.length == 2)
                    sep = split[1];
                else if(curr.contains("select") && split.length == 2)
                    sel = split[1];
                else if(curr.contains("project") && split.length == 2)
                {
                    String[] numbers = split[1].split(",");
                    for(String n : numbers)
                        if(isNumber(n))
                            toProject.add(Integer.parseInt(n));
                        else
                        {
                            System.out.println("\"" + n + "\" is not a valid value for parameter \"project\"");
                            System.out.println("Ignoring \"" + curr + "\"");
                            toProject = new ArrayList<>();
                            break;
                        }
                }
                else
                    System.out.println("\"" + curr + "\" is not a valid parameter");

            }
            else
                System.out.println("\"" + curr + "\" is not a valid parameter");
        }

        //przerzucanie tekstu z wejscia standardowego
        while(scan.hasNext())
        {
            text.add(scan.nextLine());
        }
        if(text.isEmpty()) System.exit(2);

        //wszystkie operacje na liniach
        for(String curr : text)
        {
            //ignorowanie
            if(ifN + ilN < curr.length())
                curr = curr.substring(ifN, (curr.length() - ilN));
            else
                curr = "";
            //separacja
            curr = curr.replace(str, sep);

            //select
            if(curr.contains(sel))
            {
                //project
                if(!toProject.isEmpty())
                {
                    String[] columns = curr.split(sep);
                    for (int i = 0; i < columns.length; i++)
                    {
                        if (toProject.contains(i + 1))
                        {
                            S.append(columns[i]);
                            if (i < (columns.length - 1))
                                S.append(sep);
                        }
                    }
                    S.append("\n");
                }
                else
                    S.append(curr + "\n");
            }
        }

        //sprawdzanie, czy sa dane do wypisania
        output = S.toString();

        if(output.equals(""))
            System.exit(1);
        else
            System.out.println(output);

        System.exit(0);
    }

    public static boolean isNumber(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

}
