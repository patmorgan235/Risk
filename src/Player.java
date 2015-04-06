import java.awt.*;
/**
 * Write a description of class Player here.
 * 
 * @author Patric Morgan
 * @version 3/13/14
 */
public class Player
{
    // instance variables

    int num;
    int units;
    Color color;
    /**
     * Constructor for objects of class Player
     */
    public Player(int num, int units,Color color)
    {
        this.num = num;
        this.units = units;
        this.color = color;
    }
    
    public void setPlayer(int num){this.num = num;}

}
