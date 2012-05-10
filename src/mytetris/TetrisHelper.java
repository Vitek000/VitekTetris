package mytetris;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 05.05.12
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class TetrisHelper
{
    static boolean[][] testArray =
    {
        {true, false, true, true},
        {true, true, true, true},
        {false, false, false, false}
    };

    public static void printBoolean2DimensionArray(boolean[][] bool)
    {
        testArray[2][1] = true;

        int i=0;
        for(boolean[] a1 : bool)
        {
            int j=0;
            for(boolean a2 : a1)
            {
                j++;
                System.out.print(a2 + " " + (i) + ":" + (j) + (a2 ? "   " : "  "));
            }
            i++;
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        printBoolean2DimensionArray(testArray);
    }

    public static boolean isWallNextDown(Shape currentShape, boolean[][] bool)
    {
        for(Point point : currentShape.points) 
        {
            Point globalPoint = makeGlobalPoint(currentShape.getLocation(), point);
            int checkY = Math.min(globalPoint.getY() + 1, Board.HEIGHT - 1);
            if(bool[checkY][globalPoint.getX()])
                return true;
        }
        return false;

    }


    public static Point makeGlobalPoint(Location location, Point p)
    {
        return new Point(location.getX() + p.getX(), location.getY() + p.getY());
    }
}
