package racoonsoft.tinyheroes.test;

import racoonsoft.tinyheroes.geometric.Segment;

public class GeometricTest
{
    public static void main(String[] args)
    {
        try
        {
            Segment s1 = new Segment(20,14,140,55);
            Segment s2 = new Segment(28,54,111,35);
            Segment s3 = new Segment(24,35,115,51);
            Segment s4 = new Segment(67,67,170,44);
            Segment s5 = new Segment(77,22,170,65);
            Segment s6 = new Segment(20,14,140,55);

            System.out.println(s1.intersect(s2));
            System.out.println(s1.intersect(s3));
            System.out.println(s1.intersect(s4));
            System.out.println(s1.intersect(s5));
            System.out.println(s1.intersect(s6));

        }
        catch (Exception ex)
        {

        }
    }
}
