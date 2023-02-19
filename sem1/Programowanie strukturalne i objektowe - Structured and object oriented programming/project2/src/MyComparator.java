import java.util.Comparator;

public class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {
        int result = x.waga - y.waga;
        if(result==0){
            result = ZbiorDrzew.znajdzWysokosc(x)-ZbiorDrzew.znajdzWysokosc(y);
        }
        return result;
    }
}