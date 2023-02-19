package pack;

//modyfikacja aka z2 - szesciakat foremny w podstawie

import Z1a.Cuboid;
import Z1a.Cylinder;
import Z1a.Sphere;
import Z1a.Hexagon;
import Z1a.Post;
import Z1b.Paczka;
import Z1b.Typy;
import Z1b.Poczta;

public class Main
{
    public static void main(String[] args)
    {
        double[] t1 = {2,2,2};
        double[] t2 = {1,2};
        double[] t3 = {1};
        Cuboid cub = new Cuboid(2,2,2);
        Cylinder cyl = new Cylinder(1,2);
        Sphere sph = new Sphere(1);
        Hexagon hex = new Hexagon(1,2); //modyfikacja

        System.out.println(cub.toString() + "\n" + cyl.toString() + "\n" + sph.toString());
        System.out.println(hex.toString()+"\n"); //modyfikacja

        Post pos = new Post(5,7);
        pos.sortPackage(cub);
        pos.sortPackage(cyl);
        pos.sortPackage(sph);
        pos.sortPackage(hex); //modyfikacja

        System.out.println(pos.showSmallList());
        System.out.println(pos.showMediumList());
        System.out.println(pos.showLargeList());
        System.out.println("\n\n");

        Paczka p1 = new Paczka(Typy.typ.prostopad,t1);
        Paczka p2 = new Paczka(Typy.typ.walec,t2);
        Paczka p3 = new Paczka(Typy.typ.kula,t3);
        Paczka p4 = new Paczka(Typy.typ.szesciakat, t2);

        System.out.println(p1.toString() + "\n" + p2.toString() + "\n" + p3.toString());
        System.out.println(p4.toString()+"\n"); //modyfikacja

        Poczta pp = new Poczta(5,7);
        pp.sortPackage(p1);
        pp.sortPackage(p2);
        pp.sortPackage(p3);
        pp.sortPackage(p4); //modyfikacja

        System.out.println(pp.showSmallList());
        System.out.println(pp.showMediumList());
        System.out.println(pp.showLargeList());
        System.out.println("\n\n");
    }
}
