package mytetris;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 * <p/>
 * <p/>
 * Предполагаются следующие типы фигур
 * Палка, размер 1 х 5
 */
public class Shape
{
    private String name;

    private static List<Shape> defaultShapes = new ArrayList<Shape>();

    // 1
    static final Shape LINE = new Shape("LINE", Location.DEFAULT_LOCATION, new Point[]{new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)});
    // 2
    static final Shape T = new Shape("T", Location.DEFAULT_LOCATION, new Point[]{new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)});
    // 3
    static final Shape ZL = new Shape("ZL", Location.DEFAULT_LOCATION, new Point[]{new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)});
    // 4
    static final Shape ZP = new Shape("ZP", Location.DEFAULT_LOCATION, new Point[]{new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)
    });
    // 5
    static final Shape LL = new Shape("LL", Location.DEFAULT_LOCATION, new Point[]{new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 2)});
    // 6
    static final Shape LP = new Shape("LP", Location.DEFAULT_LOCATION, new Point[]{new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)});

    static
    {
        defaultShapes.add(LINE);
        defaultShapes.add(T);
        defaultShapes.add(ZL);
        defaultShapes.add(ZP);
        defaultShapes.add(LL);
        defaultShapes.add(LP);
    }

    public volatile Location location;
    public Point[] points;


    public Shape(Shape shape)
    {
        this.name = shape.getName();
        this.location = new Location(shape.getLocation());

        this.points = new Point[shape.points.length];
        int i = 0;
        for (Point p : shape.points)
        {
            this.points[i++] = new Point(p);
        }
    }

    protected Shape(String name, Location location, Point[] points)
    {
        this.name = name;
        this.location = location;
        this.points = points;
    }

    //public abstract void draw(Graphics g);
    //public abstract void rotate();

    public void paint(Graphics g)
    {
        for (Point p : points)
        {
            // заполняем квадрат

            g.fillRect(location.getX() * Board.CELL_SIZE + p.getX() * Board.CELL_SIZE + Board.offsetX,
                    location.getY() * Board.CELL_SIZE + p.getY() * Board.CELL_SIZE + Board.offsetY,
                    Board.CELL_SIZE, Board.CELL_SIZE);
        }
    }


    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    /**
     * @return Максимальный по Y
     */
    public Point getGlobalBottom()
    {
        Point maxP = new Point(-1, -1);
        for (Point p : points)
        {
            if (p.getY() > maxP.getY())
                maxP = p;
        }

        return maxP;
    }

    /**
     * @return Минимальный по Y, в данном случае просто location.getY()
     */
    public Point getGlobalTop()
    {
        return new Point(getLocation().getX(), getLocation().getY());
    }

    /**
     * Получение начальной случайной фигуры
     * @return
     */
    public static Shape getRandomDefaultShape()
    {
        return new Shape(defaultShapes.get(new Random().nextInt(defaultShapes.size())));
    }
    
    public void moveLeft()
    {
        Location previous = location;
        int newX = Math.max(0, location.getX() - 1);
        Location newLocation = new Location(newX, location.getY());
        setLocation(newLocation);

        if(previous.getX() != newLocation.getX() + 1)
        {
            System.out.println("LEFT: was " + previous + " , new " + newLocation);
        }


    }

    public void moveRight()
    {
        Location previous = location;
        int newX = Math.min(Board.WIDTH - 1, location.getX() + 1);
        Location newLocation = new Location(newX, location.getY());
        setLocation(newLocation);

        System.out.println("RIGHT: was " + previous + " , new " + newLocation);
    }

}
