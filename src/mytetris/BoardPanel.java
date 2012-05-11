package mytetris;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class BoardPanel extends JPanel
{
    private Board board = new Board();


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        board.paintBoard(g);

        board.getCurrentShape().paint(g);

    }


    public void processCurrentShape()
    {
        board.processCurrentShape(getGraphics());
    }

    public boolean checkGameOver()
    {
        return board.checkGameOver();
    }

    public void init()
    {
        board.setCurrentShape(Shape.getRandomDefaultShape());
    }

    public void moveLeft()
    {
        //board.getCurrentShape().moveLeft();
        board.setMovingLeft(true);
    }

    public void moveRight()
    {
        //board.getCurrentShape().moveRight();
        board.setMovingRight(true);
    }
}
