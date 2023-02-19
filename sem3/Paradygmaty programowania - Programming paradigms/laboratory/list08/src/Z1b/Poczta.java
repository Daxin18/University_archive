package Z1b;

import java.util.ArrayList;

public class Poczta
{
    private double med;
    private double lar;
    private ArrayList<Paczka> small;
    private ArrayList<Paczka> medium;
    private ArrayList<Paczka> large;

    public Poczta(double med, double lar)
    {
        this.med = med;
        this.lar = lar;
        this.small = new ArrayList<>();
        this.medium = new ArrayList<>();
        this.large = new ArrayList<>();
    }

    public String sortPackage(Paczka pack)
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
        for(Paczka p: small)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }
    public String showMediumList()
    {
        StringBuilder S = new StringBuilder();
        S.append("Medium packages:\n");
        for(Paczka p: medium)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }
    public String showLargeList()
    {
        StringBuilder S = new StringBuilder();
        S.append("Large packages:\n");
        for(Paczka p: large)
            S.append(" "+ p.toString()+"\n");
        return S.toString();
    }
}
