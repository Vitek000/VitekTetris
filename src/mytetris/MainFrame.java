package mytetris;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame implements KeyListener
{
    private BoardPanel boardPanel = new BoardPanel();

    public MainFrame()
    {
        add(boardPanel);
        addKeyListener(this);
    }

    public void processCurrentShape()
    {
        boardPanel.processCurrentShape();
    }

    public boolean checkGameOver()
    {
        return boardPanel.checkGameOver();
    }

    public void init()
    {
        boardPanel.init();
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        System.out.println("TYPED!!!!");
    }

    public void keyPressed(KeyEvent e)
    {
        System.out.println("PRESSED!!!!");
        if(e.getKeyCode() == 37)
            boardPanel.moveLeft();
        else if(e.getKeyCode() == 39)
            boardPanel.moveRight();
    }

    public void keyReleased(KeyEvent e)
    {
        System.out.println("RELEASED!!!!");
        //System.out.println(e.getKeyCode());
    }
}
