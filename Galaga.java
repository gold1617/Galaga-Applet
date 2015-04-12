
/**
 * Authors: Sean MacLarion, Rishab Chhabra, Billy Sherwood
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
public class Galaga extends JApplet
implements KeyListener 
{
    private ImageIcon ship,enemy;
    private JPanel pic;
    private Point[] stars;
    private Ship player;
    private ArrayList<Enemy> en;
    private ArrayList<Missle> m;
    private int Emove = 1,Score = 0,Level = 1;
    private boolean paused;
    public void init()
    {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        pic = new JPanel();
        pic.setFocusable(true);
        pic.addKeyListener(this);
        pic.requestFocus();
        Container c = getContentPane();
        c.add(p1, BorderLayout.NORTH);
        c.add(pic, BorderLayout.CENTER);
        player  = new Ship(getWidth());

        setup();
        repaint();
    }

    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyChar() == 'a' && !(player.getX() < 30))
        {
            player.move(-1);
        }
        else if(e.getKeyChar() == 'd'&& !(player.getX() > getWidth() - 20))
        {
            player.move(1);            
        }
        else if(e.getKeyChar() ==  's')
        {
            m.add(new Missle(player.getX(), getHeight() - 30));
        }
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd')
        {
            if(!en.isEmpty())
            {
                if(en.get(en.size()-1).getX() > getWidth()-50)
                {
                    Emove = -1;
                }
                else if(en.get(0).getX() < 50)
                {   
                    Emove = 1;
                }
                for(int i = 0; i < en.size();i++)
                {
                    en.get(i).move(Emove,0);
                }
            }
            paused = false;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'd')
        {
            paused = true;
            repaint();
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void paint(Graphics g)
    {            
        super.paint(g);
        int w = pic.getWidth();
        int h = pic.getHeight();
        int x = pic.getX();
        int y = pic.getY();
        int l = 0;
        g.setColor(Color.BLACK);
        g.fillRect(x,y,w,h);
        g.setColor(Color.WHITE);
        Random r = new Random();
        for(int i = 0; i < 100; i++)
        {
            l = r.nextInt(3);
            g.fillRect(stars[i].getX(), stars[i].getY(), l, l);
        }
        g.setColor(Color.WHITE);
//         for(int t = 0; t < m.size();t++)
//         {
//             if(!(m.get(t).getY() < 0 || hit(m.get(t))))
//             {
//                 g.fillRect(m.get(t).getX(),m.get(t).getY(),5,5);
//                 m.get(t).moveU();
//             }
//             else
//             {
//                 m.remove(t);
//                 t--;
//             }
//             try
//             {
//                 Thread.sleep(2);
//             }
//             catch(InterruptedException ie)
//             {
//                 System.out.println();
//             }
//             repaint();
//         }
        if(paused)
        {
            g.setColor(Color.YELLOW);
            g.drawString("Idle Pause", w/2,h/2);
        }
        ship.paintIcon(pic,g,player.getX()-15,h-40);
        for(int i = 0; i < en.size();i++)
        {
            enemy.paintIcon(pic,g,en.get(i).getX()-10,en.get(i).getY() + 10);
        }
        g.setColor(Color.RED);
        g.drawString("Score: " + Score, 10,h - 10);
        g.drawString("Level: " + Level,10, h -30);
    }

//     private boolean hit(Missle x)
//     {
//         for(int i = 0; i < en.size();i++)
//         {
//             if(!(x.getX() > en.get(i).getX() +20) && !(x.getX() < en.get(i).getX() - 20) && (!(x.getY() > en.get(i).getY() +10) && !(x.getY() < en.get(i).getY() - 10)))
//             {
//                 en.remove(i);
//                 Score += 10 + (5 * (Level - 1));
//                 if(en.size() == 0)
//                 {
//                     Level++;
//                     LevelUP();
//                 }
//                 return true;
//             }
//         }
//         return false;
//     }

    public void setup()
    {
        paused = true;
        stars = new Point[100];
        int x, y;
        int w = getWidth();
        int h = getHeight();
        Random r = new Random();
        m = new ArrayList<Missle>();
        en = new ArrayList<Enemy>();
        LevelUP();

        ship = new ImageIcon("Galaga sprites.jpg");
        enemy = new ImageIcon("enemy.gif");
        for(int i = 0; i < 100; i++)
        {
            x = r.nextInt(h);
            y = r.nextInt(w);
            stars[i] = new Point(x,y);            
        }
    }

    public void LevelUP()
    {
        int w = 50, h = 20;
        for(int i = 0; i < 5*Level; i++)
        {  
            if(i > 0 && i%5 == 0)
            {
                h += 30;
                w = 50;
            }
            en.add(new Enemy(w, h));
            w += 100; 
        }
    }
}