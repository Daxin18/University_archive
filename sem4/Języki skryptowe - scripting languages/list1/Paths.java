import java.io.File;
import java.util.Arrays;

public class Paths
{
    public static void main(String[] args)
    {
        File current = new File(System.getProperty("user.dir"));
        File[] paths = current.listFiles();
        boolean R = false;
        boolean d = false;
        boolean s = false;
        boolean sort = false;
        String howSort = "";

        for(int i=0; i< args.length; i++) //petla przegladajaca argumenty i ustawiajaca wszystkie boole
        {
            if(args[i].charAt(0) == '-' && args[i].charAt(1) == '-')
            {
                if (args[i].contains("sort"))
                {
                    sort = true;
                    if (i < args.length - 1)
                        howSort = args[i+1];
                    else
                        sort = false;
                }
            }
            else if(args[i].charAt(0) == '-')
            {
                if (args[i].contains("R")) R = true;
                if (args[i].contains("d")) d = true;
                if (args[i].contains("s")) s = true;
            }
        }

        if (sort) //opcjonalne sortowanie
        {
            if(howSort.equals("alpha")) //alfabetycznie
                Arrays.sort(paths);
            if(howSort.equals("date")) //po dacie modyfikacji (tak... bubble sort...)
            {
                File temp;
                for (int i = 0; i < paths.length - 1; i++)
                    for (int j = i + 1; j < paths.length; j++)
                    {
                        if (paths[i].lastModified() > paths[j].lastModified())
                        {
                            temp = paths[j];
                            paths[j] = paths[i];
                            paths[i] = temp;
                        }
                    }
            }
        }

        for (File path:paths) //przejscie po plikach, aby je wyswietlic
        {
            if( !d || (d && path.isDirectory())) //przelacznik -d wyswietli tylko foldery
            {
                System.out.print(path);
                if(s) System.out.print("\tSize: " + path.length() + " B"); //przelacznik -s doda rozmiar
                System.out.println();
            }
            if(R && path.isDirectory()) Recursive(path, 1, s); //przelacznik R wyswietli tez wszystkie pliki w folderach
        }
    }

    public static void Recursive(File curr, int i, boolean s)
    {
        File[] paths = curr.listFiles();

        for (File path:paths)
        {
            for(int j=0; j<i; j++)
                System.out.print("\t");
            System.out.print(path);
            if(s) System.out.print("\tSize: " + path.length() + " B"); //przelacznik -s doda rozmiar
            System.out.println();
            if(path.isDirectory())
                Recursive(path, i+1, s);
        }
    }
}
