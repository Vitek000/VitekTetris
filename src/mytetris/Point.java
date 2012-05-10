package mytetris;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 * Точка в системе координат 4х4
 */
public class Point
{
    private int x;
    private int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point p)
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
