package racoonsoft.tinyheroes.geometric;

public class Segment
{
    public int start_x,start_y,end_x,end_y;

    public Segment()
    {

    }
    public Segment(int x,int y,int x1,int y1)
    {
        start_x = x;
        start_y = y;
        end_x = x1;
        end_y = y1;
    }
    public boolean intersect(Rectangle or)
    {
        return or.intersect(this);
    }
    public boolean intersect(Segment s)
    {
        Position dir1 = new Position(s.end_x-s.start_x,s.end_y-s.start_y);
        Position dir2 = new Position(end_x-start_x,end_y-start_y);

        //считаем уравнения прямых проходящих через отрезки
        float a1 = -dir1.y;
        float b1 = +dir1.x;
        float d1 = -(a1*s.start_x + b1*s.start_y);

        float a2 = -dir2.y;
        float b2 = +dir2.x;
        float d2 = -(a2*start_x + b2*start_y);

        //подставляем концы отрезков, для выяснения в каких полуплоскотях они
        float seg1_line2_start = a2*s.start_x + b2*s.start_y + d2;
        float seg1_line2_end = a2*s.end_x + b2*s.end_y + d2;

        float seg2_line1_start = a1*start_x + b1*start_y + d1;
        float seg2_line1_end = a1*end_x + b1*end_y + d1;

        //если концы одного отрезка имеют один знак, значит он в одной полуплоскости и пересечения нет.
        if (seg1_line2_start * seg1_line2_end >= 0 || seg2_line1_start * seg2_line1_end >= 0)
        {
            return false;
        }
        return true;
//        float u = seg1_line2_start / (seg1_line2_start - seg1_line2_end);
//        *out_intersection =  start1 + u*dir1;
    }
    public int lenght()
    {
        return (int)Math.sqrt((end_x-start_x)*(end_x-start_x)+(end_y-start_y)*(end_y-start_y));
    }
}
