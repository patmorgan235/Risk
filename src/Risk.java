import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Risk extends GameEngine{
    Img DispMap ;
    Img ColorMap ;
    ArrayList<Contry> Countries = new ArrayList<Contry>();
    JTextField countryColor = new JTextField(30);
    JTextField breakOut = new JTextField(30);

    public Risk(){
        super("Risk",1000,576);

        setBackground(Color.BLUE);

        DispMap = new Img("RiskMap.png");
        ColorMap = new Img("RiskColorMap.png");
        DispMap.setPosition(0,0);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Country Color: "));
        panel.add(countryColor);
        panel.add(breakOut);
        addPanel(panel, BorderLayout.SOUTH);

        //JTextField mouse = new JTextField("( " + mouse.getX() + "," + mouse.getY() + " )");

        run();
    }

    public void update(){
        BufferedImage imagemap = ColorMap.getBufferedImage();
        int x = mouse.x;
        int y = mouse.y;
        if(x > 0 && x < imagemap.getWidth() && 
        y > 0 && y < imagemap.getHeight())
        {
            int curentColor = imagemap.getRGB(x,y);
            int alpha = curentColor & 0xFF000000;
            int red = curentColor & 0x00FF0000;
            int green = curentColor & 0x0000FF00;
            int blue = curentColor & 0x000000FF;
            breakOut.setText(String.format("R:%s B:%s G:%s A:%s", 
                Integer.toHexString(red), 
                Integer.toHexString(green), 
                Integer.toHexString(blue), 
                Integer.toHexString(alpha)));
            countryColor.setText(Integer.toHexString(curentColor).toUpperCase());
            Color c= new Color(curentColor);
            countryColor.setBackground(c);
        }

    }

    public void draw(Graphics g){
        if(DispMap == null)return;

        DispMap.draw(g);

    }

    public void processKey(int code, boolean pressed){

    }

    public static void main(String[] args) {

        new Risk();

    }


}
