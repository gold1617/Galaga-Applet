
/**
 * Authors: Sean MacLarion, Rishab Chhabra, Billy Sherwood
 */
public class Missle
{
    private int x,y;
    public Missle(int z,int q)
    {
        x = z;
        y = q;
    }
    public void moveD()
    {
        y += 15;
    }
    public void moveU()
    {
        y -= 15;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
}