import java.util.ArrayList;
import java.util.Scanner;

public class TESTCLASS
{
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
                    e = fa;

        System.exit(0);
    }
}
