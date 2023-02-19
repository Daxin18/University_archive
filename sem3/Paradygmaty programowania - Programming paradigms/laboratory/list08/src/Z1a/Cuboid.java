package Z1a;

public class Cuboid implements Package
{
    private double[] dims;

    public Cuboid()
    {
        this.dims = new double[3];
    }
    public Cuboid(double a, double b, double c)
    {
        this.dims = new double[3];
        this.dims[0] = a;
        this.dims[1] = b;
        this.dims[2] = c;
    }
    public Cuboid(double[] x)
    {
        if(x.length == 3)
            this.dims = x;
        else
            this.dims = new double[3];
    }

    @Override
    public double getVolume()
    {
        double vol = dims[0]*dims[1]*dims[2];
        return vol;
    }
    @Override
    public String toString()
    {
        return ("Cuboid, volume: " + getVolume());
    }
}
