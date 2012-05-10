package mytetris;

import javax.swing.JFrame;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 * User: VYuAleksandrov
 * Date: 04.05.12
 * Time: 13:51
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    public static volatile AtomicBoolean gameOver = new AtomicBoolean(false);
    public static final int TICK = 100;
    
    public static int round = 0;
    
    public static void main(String[] args) throws InterruptedException
    {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setTitle("Vitek Tetris");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1024, 768);
        mainFrame.setVisible(true);

        mainFrame.init();
        while (!gameOver.get())
        {
            System.out.println("round =  " + round);
            Thread.sleep(TICK);

            if(mainFrame.checkGameOver())
            {
                break;
            }
            mainFrame.processCurrentShape();

            mainFrame.repaint();

            round++;
        }

        System.out.println("GAME OVER, MAN !!!");
    }


}
