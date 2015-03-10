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
    
    public void makeCounties(){
        //North America
    Countries.add(new Contry("Alaska","FF806E00", 10));
    Countries.add(new Contry("North Canada","FF503C27", 10));
    Countries.add(new Contry("West Canada","FFFFFF00", 10));
    Countries.add(new Contry("Central Canada","FF949449", 10));
    Countries.add(new Contry("East Canada","FFFFE6980", 10));
    Countries.add(new Contry("Greenland","FFFFDC00", 10));
    Countries.add(new Contry("West US","FF505027", 10));
    Countries.add(new Contry("East US","FF808000", 10));
    Countries.add(new Contry("Mexico","FFFFFF80", 10));
    //South America
    Countries.add(new Contry("Colombia","FFFF8080", 10));
    Countries.add(new Contry("Brazill","FF804040", 10));
    Countries.add(new Contry("Peru","FF05027", 10));
    Countries.add(new Contry("Argenitina","FFFF0000", 10));
    //Europe
    Countries.add(new Contry("Iceland","FF0000D5", 10));
    Countries.add(new Contry("United Kingdom","FF003A75", 10));
    Countries.add(new Contry("Sweden","FF1591FF", 10));
    Countries.add(new Contry("Western Russia","FF0008A", 10));
    Countries.add(new Contry("Spain","FF0080FF", 10));
    Countries.add(new Contry("Itlay","FF004080", 10));
    //Asia
    Countries.add(new Contry("Less Weastern Russia","FF006015", 10));//11
    Countries.add(new Contry("East US","FF008000", 10));//10
    Countries.add(new Contry("East US","FF009F9F", 10));//12
    Countries.add(new Contry("East US","FF00954A", 10));//6
    Countries.add(new Contry("Kazakhstan","FF80FF80", 10));//1
    Countries.add(new Contry("East US","FF80FF00", 10));//4
    Countries.add(new Contry("East US","FF004000", 10));//8
    Countries.add(new Contry("East US","FF22B14C", 10));//7
    Countries.add(new Contry("East US","FF008080", 10));//3
    Countries.add(new Contry("East US","FF05027", 10));//2
    Countries.add(new Contry("East US","FF05027", 10));//5
    Countries.add(new Contry("East US","FF05027", 10));//9
    //Africa
    Countries.add(new Contry("East US","FF05027", 10));//5
    Countries.add(new Contry("East US","FF05027", 10));//3
    Countries.add(new Contry("East US","FF05027", 10));//1
    Countries.add(new Contry("East US","FF05027", 10));//2
    Countries.add(new Contry("East US","FF05027", 10));//6
    Countries.add(new Contry("East US","FF05027", 10));//4
    //Australlia
    Countries.add(new Contry("East US","FF05027", 10));//2
    Countries.add(new Contry("East US","FF05027", 10));//3
    Countries.add(new Contry("East US","FF05027", 10));//4
    Countries.add(new Contry("East US","FF05027", 10);//1
    }

    public void processKey(int code, boolean pressed){

    }

    public static void main(String[] args) {

        new Risk();

    }


}
