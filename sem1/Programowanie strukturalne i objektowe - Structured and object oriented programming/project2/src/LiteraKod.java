import java.util.TreeSet;

public class LiteraKod implements Comparable {
    static TreeSet<LiteraKod> treeSet = new TreeSet<>();
    String litera;
    String kod;
    public LiteraKod(char litera, String kod){
        this.litera=String.valueOf(litera);
        this.kod=kod;
        treeSet.add(this);
    }


    public static String szukanie(char x){
        String cha =String.valueOf(x);
        String z = "";
        for(LiteraKod lk: treeSet){
            if(lk.litera.equals(cha)){
                z=lk.kod;
            }
        }
        return z;
    }

    public static String szukanie2(String x){
        String z = "";
        for(LiteraKod lk: treeSet){
            if(lk.kod.equals(x)){
                z=lk.litera;
            }
        }
        return z;
    }

    @Override
    public int compareTo(Object o) {
        return this.litera.compareTo(((LiteraKod)o).litera);
    }
}
