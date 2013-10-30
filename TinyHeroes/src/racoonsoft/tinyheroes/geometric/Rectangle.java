package racoonsoft.tinyheroes.geometric;

public class Rectangle
{
    public int x,y,width,height;

    public boolean intersect(Rectangle or)
    {
         return intersectX(or)&&intersectY(or);
    }
    public boolean intersectX(Rectangle or)
    {
        return x+width>=or.x&&x<=or.x+or.width;
    }
    public boolean intersectY(Rectangle or)
    {
        return y+height>=or.y&&y<=or.y+or.height;
    }

    public boolean intersect(Segment s)
    {
        Segment s1 = new Segment(x,y,x+width,y);
        Segment s2 = new Segment(x,y,x,y+height);
        Segment s3 = new Segment(x+width,y,x+width,y+height);
        Segment s4 = new Segment(x,y+height,x+width,y+height);

        return s.intersect(s1)||s.intersect(s2)||s.intersect(s3)||s.intersect(s4);
    }
}
