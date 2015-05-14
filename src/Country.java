import java.awt.*;
/**
 * Write a description of class Contry here.
 * 
 * @author Patric Morgan 
 * @version 3/9/15
 */
public class Country
{
    // instance variables
    String name;
    String color;
    int units ;
    int player;

    /**
     * Constructor for objects of class Contry
     */
    public Country(String name, String color, int units)
    {
        this.name = name;
        this.color = color;
        this.units = units;
        player = -1;
    }
    
    public Country(String name, String color)
    {
        this.name = name;
        this.color = color;
        units = 10;
        player = -1;
    }
    
    public int getUnits(){return units;}
    
    public void setUnits(int num){units = num;}
    
    public int getPlayer(){return player;}
    
    public void setPlayer(int player){this.player = player;}
    
}
