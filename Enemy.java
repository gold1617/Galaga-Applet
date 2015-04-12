
/**
 * Authors: Sean MacLarion, Rishab Chhabra, Billy Sherwood
 */
public class Enemy
{
    private int y,x,speed;
    
    public Enemy(int a,int z)
    {
        x = a;
        speed = 3;
        y = z;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void move(double X,double Y)
    {
        x += speed*X;
        y += speed*Y;
    }
    
    public void moveY(int d)
    {
        y += speed*d;
    }
}