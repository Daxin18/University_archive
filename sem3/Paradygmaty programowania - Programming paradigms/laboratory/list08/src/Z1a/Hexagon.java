package Z1a;

public class Hexagon implements Package
{
    private double[] dims;

    public Hexagon()
    {
        this.dims = new double[2];
    }
    public Hexagon(double a, double h)
    {
        this.dims = new double[2];
        this.dims[0] = a;
        this.dims[1] = h;
    }
    public Hexagon(double[] x) //najpierw bok podstawy[0], potem wysokosc[1]
    {
        if(x.length == 2)
            this.dims = x;
        else
            this.dims = new double[2];
    }

    @Override
    public double getVolume()
    {
        double vol = (((Math.pow(dims[0],2)*Math.pow(3,1./2.))/4.)*6)*dims[1];
        return vol;
    }
    @Override
    public String toString()
    {
        return ("Hexagon, volume: " + getVolume());
    }
}
