package Z1a;

public class Cylinder implements Package
{
    private double[] dims;

    public Cylinder()
    {
        this.dims = new double[2];
    }
    public Cylinder(double r, double h)
    {
        this.dims = new double[2];
        this.dims[0] = r;
        this.dims[1] = h;
    }
    public Cylinder(double[] x) //najpierw promien[0], potem wysokosc[1]
    {
        if(x.length == 2)
            this.dims = x;
        else
            this.dims = new double[2];
    }

    @Override
    public double getVolume()
    {
        double vol = (Math.pow(dims[0],2)*3.1415)*dims[1];
        return vol;
    }
    @Override
    public String toString()
    {
        return ("Cylinder, volume: " + getVolume());
    }
}
