import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Aggregate
{
    /*
    arguments/parameters:

        --function=[String]
            ,where [String] =
            "min" - min value in a set
            "max" - max value in a set
            "sum" - sum of all the elements in a set
            "avg" - average of all the elements in a set
            "count" - number of all elements in a set
            "var" - variance of a set
            "stdev" - standard deviation of a set

    exit codes:

        0 - everything is ok
        1 - no set/empty set given
        2 - unsupported function given

    supported data input (while(scan.hasNext())):

        First option: (number[enter]number[enter]...)
           1
           2
           3
           4
           5
        Second option: (number[space/tab]number[space/tab]...number[enter]...)
           1    2
           3 4  5
    */
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Double> X = new ArrayList<>();

        String type = ""; //typ wykonywanej funkcji

        //definiowanie funkcji
        for(String curr : args) //petla przegladajaca argumenty i ustawiajaca wszystkie parametry
        {
            if(curr.contains("--function="))
            {
                String[] split = curr.split("=");

                if(split.length == 2)
                    type = split[1];
                else
                    System.out.println("\"" + curr + "\" is not a valid parameter");
            }
            else
            {
                System.out.println("\"" + type + "\" is not a supported aggregation function");
                System.exit(2);
            }
        }
        if(type.equals(""))
        {
            System.out.println("\"" + type + "\" is not a supported aggregation function");
            System.exit(2);
        }

        //przepisywanie danych z wejscia do arraylisty
        String line;
        while(scan.hasNext()) //w konsoli w bashu można to zatrzymać ctrl+D
        {
            line = scan.nextLine();

            String[] potentialNumbers = line.split("[ \t]");
            for(String s : potentialNumbers)
                if (isNumber(s))
                    X.add(Double.parseDouble(s));
        }
        if(X.isEmpty())
        {
            //System.out.println("Empty set given");
            System.exit(1);
        }

        //wywolywanie odpowiednich funkcji
        switch(type)
        {
            case "min":
                System.out.println(min(X));
                break;
            case "max":
                System.out.println(max(X));
                break;
            case "sum":
                System.out.println(sum(X));
                break;
            case "avg":
                System.out.println(avg(X));
                break;
            case "count":
                System.out.println(count(X));
                break;
            case "var":
                System.out.println(variance(X));
                break;
            case "stdev":
                System.out.println(standard_deviation(X));
                break;
            default:
                System.out.println("\"" + type + "\" is not a supported aggregation function");
                System.exit(2);
        }

        //zamkniecie programu
        System.exit(0);
    }

    public static boolean isNumber(String s)
    {
        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static double min(ArrayList<Double> X) { return Collections.min(X); }
    public static double max(ArrayList<Double> X) { return Collections.max(X); }
    public static double sum(ArrayList<Double> X)
    {
        int res = 0;
        for(double i : X)
            res+=i;
        return res;
    }
    public static int count(ArrayList<Double> X) { return X.size(); }
    public static double avg(ArrayList<Double> X) { return ( sum(X) / ((double)count(X)) ); }
    public static double variance(ArrayList<Double> X)
    {
        ArrayList<Double> X2 = new ArrayList<>();
        for (double i : X)
            X2.add(i * i);
        double ex = avg(X);
        return (avg(X2) - (ex * ex));
    }
    public static double standard_deviation(ArrayList<Double> X) { return Math.sqrt(variance(X)); }

}
