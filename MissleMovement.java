
/**
 * Write a description of class MissleMovement here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class MissleMovement extends Thread
{
    private ArrayList<Missle> m;
    public MissleMovement(ArrayList<Missle> a)
    {
       m = a;   
    }
    public void run()
    {
        for(int t = 0; t < m.size();t++)
        {
            if(!(m.get(t).getY() < 0 || hit(m.get(t))))
            {
                g.fillRect(m.get(t).getX(),m.get(t).getY(),5,5);
                m.get(t).moveU();
            }
            else
            {
                m.remove(t);
                t--;
            }
            repaint();
        }
    }
    private boolean hit(Missle x)
    {
        for(int i = 0; i < en.size();i++)
        {
            if(!(x.getX() > en.get(i).getX() +20) && !(x.getX() < en.get(i).getX() - 20) && (!(x.getY() > en.get(i).getY() +10) && !(x.getY() < en.get(i).getY() - 10)))
            {
                en.remove(i);
                Score += 10 + (5 * (Level - 1));
                if(en.size() == 0)
                {
                    Level++;
                    LevelUP();
                }
                return true;
            }
        }
        return false;
    }
}
