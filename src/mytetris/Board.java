package mytetris;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 * <p/>
 * Доска
 */
public class Board
{
    static final int WIDTH = 60;
    static final int HEIGHT = 60;
    static final int CELL_SIZE = 10;

    static final int offsetX = 5;
    static final int offsetY = 5;

    private boolean[][] field = new boolean[HEIGHT][WIDTH];

    private Shape currentShape = null;


    public boolean isMovingLeft()
    {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft)
    {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight()
    {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight)
    {
        this.movingRight = movingRight;
    }

    private boolean movingLeft = false;
    private boolean movingRight = false;

    public Board()
    {
        setCurrentShape(Shape.getRandomDefaultShape());
    }

    public void paintBoard(Graphics g)
    {
        paintBorder(g);
        paintCells(g);
        paintField(g);
    }

    private void paintField(Graphics g)
    {
        int i=0;
        for(boolean[] boolX : field)
        {
            int j=0;
            for(boolean xy : boolX)
            {
                if(xy)
                    g.fillRect(offsetY + j* CELL_SIZE, offsetX + i*CELL_SIZE, CELL_SIZE, CELL_SIZE);

                j++;
            }
            i++;
        }
    }

    private void paintBorder(Graphics g)
    {
        // рисуем прямоугольник окантовку
        g.setColor(Color.green);
        g.drawRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        g.setColor(Color.black);

    }

    private void paintCells(Graphics g)
    {

        // рисуем горизонтальные линии
        for (int i = 0; i <= HEIGHT; i++)
        {
            g.drawLine(offsetX, i * CELL_SIZE + offsetY,
                    WIDTH * CELL_SIZE + offsetX, i * CELL_SIZE + offsetY);
        }

        // рисуем вертикальные линии
        for (int i = 0; i <= WIDTH; i++)
        {
            g.drawLine(i * CELL_SIZE + offsetX, offsetY,
                    i * CELL_SIZE + offsetX, HEIGHT * CELL_SIZE + offsetY);
        }
    }

    public Shape getCurrentShape()
    {
        return currentShape;
    }

    public void setCurrentShape(Shape currentShape)
    {
        this.currentShape = currentShape;
    }

    /**
     * Установка следа на карте
     * @param shape
     * @param g
     */
    private void setShapeFootPrint(Shape shape, Graphics g)
    {
        for(Point p : shape.points)
        {
            Point globalPoint = TetrisHelper.makeGlobalPoint(shape.getLocation(), p);
            field[globalPoint.getY()][globalPoint.getX()] = true;
        }

        TetrisHelper.printBoolean2DimensionArray(field);
        System.out.println();

    }

    public void processCurrentShape(Graphics g)
    {
        if(canNotMoveDown())
        {
            placeNewShape(g);
        }
        else
        {
            moveShapeDown();
        }

        movingHorizontally();
    }

    public boolean canNotMoveDown()
    {
        Point globalPoint = TetrisHelper.makeGlobalPoint(currentShape.getLocation(), currentShape.getGlobalBottom());
        return globalPoint.getY() >= HEIGHT - 1
                ||
                TetrisHelper.isWallNextDown(currentShape, field);
    }


    public void placeNewShape(Graphics g)
    {
        setShapeFootPrint(currentShape, g);
        currentShape = Shape.getRandomDefaultShape();
    }

    private void movingHorizontally()
    {
        if(movingLeft)
            currentShape.moveLeft();

        if(movingRight)
            currentShape.moveRight();

        clearMovingHorizontallyFlags();
    }

    private void clearMovingHorizontallyFlags()
    {
        movingLeft = false;
        movingRight = false;
    }

    public boolean checkGameOver()
    {
        // заполнено ли поле до верха с учетом текущей фигуры
        return canNotMoveDown()
                &&
                currentShape.getGlobalTop().getY() <= 0;
//            Main.gameOver.set(true);
    }

    public void moveShapeDown()
    {
        getCurrentShape().setLocation(getCurrentShape().getLocation().nextVerticalLocation());
    }
}
