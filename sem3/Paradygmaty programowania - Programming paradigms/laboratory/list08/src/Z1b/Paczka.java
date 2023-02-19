package Z1b;

public class Paczka
{
    private Typy.typ type;
    private double[] dim;

    public Paczka()
    {
        this.type = Typy.typ.prostopad;
        this.dim = new double[3];
    }
    public Paczka(Typy.typ t, double[] dim)
    {
        this.type = t;
        this.dim = dim;
    }

    public double getVolume()
    {
        double vol;
        if(type == Typy.typ.prostopad)
            vol = dim[0]*dim[1]*dim[2];
        else if(type == Typy.typ.walec)
            vol = (Math.pow(dim[0],2)*3.1415)*dim[1];
        else if(type == Typy.typ.szesciakat)
            vol = (((Math.pow(dim[0],2)*Math.pow(3,1./2.))/4.)*6)*dim[1];
        else //type == typ.kula
            vol = (4./3.) * 3.1415 * Math.pow(dim[0],3);
        return vol;
    }
    public String toString()
    {
        StringBuilder S = new StringBuilder();
        if(type == Typy.typ.prostopad)
            S.append("Cuboid, volume: " + getVolume());
        else if(type == Typy.typ.walec)
            S.append("Cylinder, volume: " + getVolume());
        else if(type == Typy.typ.szesciakat)
            S.append("Hexagon, volume: " + getVolume());
        else //type == typ.kula
            S.append("Sphere, volume: " + getVolume());
        return S.toString();
    }

}
