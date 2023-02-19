package Z1a;

import java.util.ArrayList;

public class Post
{
    private double med;
    private double lar;
    private ArrayList<Package> small;
    private ArrayList<Package> medium;
    private ArrayList<Package> large;

    public Post(double med, double lar)
    {
        this.med = med;
        this.lar = lar;
        this.small = new ArrayList<>();
        this.medium = new ArrayList<>();
        this.large = new ArrayList<>();
    }

    public String sortPackage(Package pack)
    {
        if(pack.getVolume() > lar) {
            large.add(pack);
            return "Large";
        }
        else if(pack.getVolume() > med) {
            medium.add(pack);
            return "Medium";
        }
        else {
            small.add(pack);
            return "Small";
        }
    }

    public String showSmallList()
    {
        StringBuilder S = new StringBuilder();
        S.append("Small packages:\n");
        for(Package p: small)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }
    public String showMediumList()
    {
        StringBuilder S = new StringBuilder();
        S.append("Medium packages:\n");
        for(Package p: medium)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }
    public String showLargeList()
    {
        StringBuilder S = new StringBuilder();
        S.append("Large packages:\n");
        for(Package p: large)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }


}
