import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.*;

public class Img
{
    ImageIcon img;
    int x, y;

    public Img()
    {
        img = null;
        setPosition(0,0);
    }

    public Img(String filename)
    {
        img = loadImage(filename);
        setPosition(0,0);
    }
    
    public Img(Image bimg){
        img = new ImageIcon(bimg);
        setPosition(0,0);
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g)
    {
        img.paintIcon(null,g,x,y);
    }

    protected ImageIcon loadImage(String name)
    {
        java.net.URL url = this.getClass().getResource(name);
        return new ImageIcon(url);
    }

    public BufferedImage getBufferedImage()
    {
        int w = img.getIconWidth();
        int h = img.getIconHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        img.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return image;
    }
    public ArrayList<Point> getColorCords(String Color){
        
        ArrayList<Point> Cords = new ArrayList<Point>();
        BufferedImage buffImg = getBufferedImage();
        
        for(int x=0; x < buffImg.getWidth();x++){
                for(int y=0; y < buffImg.getHeight(); y++){
                    int buffColor = buffImg.getRGB(x,y);
                    String buffHex = Integer.toHexString(buffColor).toUpperCase();
                    if ( buffHex == Color){
                        Cords.add(new Point(x,y));
                    }

                }
            }
        return Cords;
    }
    
    public int getHeight(){
        return img.getIconHeight();
    }
    
    public int getWidth(){
        return img.getIconWidth();
    }
}