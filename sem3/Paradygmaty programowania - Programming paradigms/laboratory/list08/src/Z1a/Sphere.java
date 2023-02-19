package Z1a;

public class Sphere implements Package
{
    private double r;

    public Sphere()
    {
        this.r = 1;
    }
    public Sphere(double r) // r to promien
    {
        if (r > 0)
            this.r = r;
        else
            this.r = 1;
    }

    @Override
    public double getVolume()
    {
        double vol = (4./3.) * 3.1415 * Math.pow(r,3);
        return vol;
    }
    @Override
    public String toString()
    {
        return ("Sphere, volume: " + getVolume());
    }
}
