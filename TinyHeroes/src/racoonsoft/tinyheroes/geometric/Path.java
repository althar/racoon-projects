package racoonsoft.tinyheroes.geometric;

import java.util.ArrayList;

public class Path
{
    public ArrayList<Segment> segments = new ArrayList<Segment>();
    public Position start;

    public Path(int start_x, int start_y)
    {
        start = new Position(start_x,start_y);
    }
    public void addPoint(int x,int y)
    {
        Segment s = new Segment();
        if(segments.isEmpty())
        {
            s.start_x = start.x;
            s.start_y = start.y;
        }
        else
        {
            s.start_x = segments.get(segments.size()-1).start_x;
            s.start_y = segments.get(segments.size()-1).start_y;
        }
        s.end_x = x;
        s.end_y = y;
        segments.add(s);
    }
}
