package mytetris;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class Location
{
    public static Location DEFAULT_LOCATION = new Location(Board.WIDTH / 2, 0);

    private int x;
    private int y;

    public Location nextVerticalLocation()
    {
        return new Location(x, y + 1);
    }

    public Location(Location location)
    {
        this.x = location.getX();
        this.y = location.getY();
    }

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
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
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
