import java.awt.*;
import java.util.*;
/**
 * Write a description of class CombatPanel here.
 * 
 * @author Patric Morgan 
 * @version 5-21-15
 */
public class CombatPanel 
{
    // instance variables
    Img Image;
    HashMap<String, Country> countryMap = new HashMap<String, Country>();

    /**
     * Constructor for objects of class CombatPanel
     */
    public CombatPanel()
    {
        


    }

    public void draw(Graphics g){
        Image.draw(g);
    }
    
    public void setImage(Img i){
        Image = i;
    }
    
    public void setHashMap(HashMap<String, Country> map){
        countryMap = map;
    }
    
}
