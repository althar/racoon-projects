package racoonsoft.tinyheroes.service;

import racoonsoft.tinyheroes.geometric.Rectangle;
import racoonsoft.tinyheroes.geometric.Path;
import racoonsoft.tinyheroes.geometric.Segment;

public class GeometricService
{
    public static boolean intersectX(Rectangle or1,Rectangle or2)
    {
        return or1.intersectX(or2);
    }
    public static boolean intersectY(Rectangle or1,Rectangle or2)
    {
        return or1.intersectY(or2);
    }
    public static boolean intersect(Rectangle or1,Rectangle or2)
    {
        return or1.intersect(or2);
    }
    public static boolean intersect(Rectangle or,Segment s)
    {
        return or.intersect(s);
    }

    public static Path generatePath(int x,int y,int x1,int y1,int edge_len)
    {
        Path p = new Path(x,y);
        int x_c = (x1-x)/edge_len;
        int y_c = (y1-y)/edge_len;
        int count = x_c>y_c?x_c:y_c;

        int step_x = (x1-x)/count;
        int step_y = (y1-y)/count;

        for(int i=1; i<count; i++)
        {
            p.addPoint(x+step_x*i,y+step_y*i);
        }
        p.addPoint(x1,y1);

        return p;
    }
}
