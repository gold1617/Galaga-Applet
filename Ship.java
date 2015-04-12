
/**
 * Authors: Sean MacLarion, Rishab Chhabra, Billy Sherwood
 */
public class Ship
{
    private int x,speed;
    public Ship(int width)
    {
        x = width/2;
        speed = 10;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void move(int d)
    {
        x += d*speed;
    }
}
