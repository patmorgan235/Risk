import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Risk extends GameEngine{
    Img DispMap ;
    Img ColorMap ;
    //Player things
    Player[] players;
    int num_players = 2;
    int player = 0;
    //Country HashMap
    HashMap<String, Contry> countryMap = new HashMap<String, Contry>();
    int curentColor;
    //misc Text Fields
    JTextField countryName = new JTextField(15);
    JTextField countryColor = new JTextField(10);
    JTextField breakOut = new JTextField(30);
    JTextField countryUnits = new JTextField(10);
    JLabel controlingPlayer = new JLabel();

    JLabel playerUnits = new JLabel();
    JTextField playerInfo = new JTextField(30);
    JLabel currPlayer = new JLabel();
    JLabel attackingInfo = new JLabel();
    JLabel defendingInfo = new JLabel();

    boolean Clicked = false;
    boolean contorlChanged = false;

    JButton endTurn = new JButton("End Turn");

    Contry attackCounrty;
    Contry defendCounrty;

    public Risk(){
        super("Risk",1050,576);

        setBackground(Color.WHITE);

        DispMap = new Img("RiskMap.png");
        ColorMap = new Img("RiskColorMap.png");
        DispMap.setPosition(0,0);

        players = new Player[2];

        players[0] = new Player(0 , 20, Color.BLUE);
        players[1] = new Player(1 , 21, Color.RED);

        JPanel panel = new JPanel();
        endTurn.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    player++;
                    player %= num_players;
                    currPlayer.setText("Player " + player);
                    playerUnits.setText("Units Avalible: " + players[player].units);
                    resetAttack();
                    if(isGameOver() == 0){currPlayer.setText("Player 0 Won");}
                    else if(isGameOver() == 1){currPlayer.setText("Player 1 Won");}
                    
                }
            });
        panel.add(endTurn);
        panel.add(new JLabel("Country Color: "));
        panel.add(countryColor);
        panel.add(breakOut);
        addPanel(panel, BorderLayout.SOUTH);

        makeInfoText();

        makeCounties();
        run();
    }

    public void update(){
        BufferedImage imagemap = ColorMap.getBufferedImage();
        int x = mouse.x;
        int y = mouse.y;
        if(x > 0 && x < imagemap.getWidth() && 
        y > 0 && y < imagemap.getHeight())
        {
            curentColor = imagemap.getRGB(x,y);
            int alpha = curentColor & 0xFF000000;
            int red = curentColor & 0x00FF0000;
            int green = curentColor & 0x0000FF00;
            int blue = curentColor & 0x000000FF;
            breakOut.setText(String.format("R:%s B:%s G:%s A:%s", 
                    Integer.toHexString(red), 
                    Integer.toHexString(green), 
                    Integer.toHexString(blue), 
                    Integer.toHexString(alpha)));
            String clr = Integer.toHexString(curentColor).toUpperCase();
            countryColor.setText(clr);
            Color c = new Color(curentColor);
            countryColor.setBackground(c);
            Contry ctry = countryMap.get(clr);

            updateTextFields(ctry);

            //sets attacking and defending counrties on click
            if(Clicked == true){
                if(players[player] != null){
                    if(players[player].units > 0){

                        Contry a = ctry;
                        a.setPlayer(player);
                        a.setUnits(a.units+1);
                        countryMap.remove(a.color);
                        countryMap.put(a.color,a);
                        players[player].units--;
                        //contorlChanged = true;
                        Clicked = false;

                    }else{
                        if(attackCounrty == null){
                            attackCounrty = countryMap.get(clr);
                            if(attackCounrty != null && attackCounrty.getPlayer() == player){
                                attackingInfo.setText("Attacking Country: "+ attackCounrty.name);
                                defendingInfo.setText("Defending Country: ");
                            }

                        }
                        else{
                            defendCounrty = countryMap.get(clr);
                            if(defendCounrty != null && defendCounrty.getPlayer() != player){
                                defendingInfo.setText("Defending Country: "+ defendCounrty.name);//todo set both to null 

                            }else{
                                resetAttack();
                            }

                        }

                        Clicked = false;
                    }
                }
            }
            updateTextFields(ctry);

        }
        Clicked = false;

        //dice roll for units lost
        if((attackCounrty!=null) && (defendCounrty!=null)){

            if(attackCounrty.units == 0){
                playerInfo.setText(attackCounrty.name + " doesn't have any units to attack with!");
                resetAttack();
            }else if(defendCounrty.units == 0){
                playerInfo.setText(defendCounrty.name + " doesn't have any units to attack!");
                resetAttack();
            }else if(attackCounrty.player == defendCounrty.player){
                playerInfo.setText("You can't attack your self!");
                resetAttack();
            }else if((defendCounrty.units > 0) && (attackCounrty.units > 0)){
                attack();

            }

        }

    }

    public void makeInfoText(){

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(Box.createRigidArea(new Dimension(0,5)));

        currPlayer.setText("Current Player: Player " + player);
        top.add(currPlayer);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        playerUnits.setText("Units Avalible: " + players[player].units);
        top.add(playerUnits);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        top.add(new JLabel("Country"));
        countryName.setMaximumSize(new Dimension(300, 25));
        top.add(countryName);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        controlingPlayer.setText("Controling Player: " );
        top.add(controlingPlayer);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        top.add(new JLabel("Units: "));
        countryUnits.setMaximumSize(new Dimension(200, 25));
        top.add(countryUnits);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        top.add(attackingInfo);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        top.add(defendingInfo);

        top.add(Box.createRigidArea(new Dimension(0,5)));

        top.add(new JLabel("Info:"));
        playerInfo.setMaximumSize(new Dimension(500, 250));
        top.add(playerInfo);

        top.add(Box.createVerticalGlue());
        addPanel(top, BorderLayout.EAST);

    }

    public void updateTextFields(Contry ctry){

        JTextField playerInfo = new JTextField(50);

        if(ctry != null){
            countryName.setText(ctry.name);
            countryUnits.setText(""+ctry.units);
            controlingPlayer.setText("Controling Player: "+ctry.player);
            playerUnits.setText("Units Avalible: " + players[player].units);

        }

        else{
            countryName.setText("");
            countryUnits.setText("");
            controlingPlayer.setText("Controling Player:");
            playerUnits.setText("Units Avalible: ");
        }

    }

    public int isGameOver(){
        int count =0;
        for(Contry country: countryMap.values()){
            if(country.player == 0){count++;}

        }
        if(count == 0 ){return 1;}
        else if(count == countryMap.size() ){return 0; }
        else{return -1;}

    }

    public void resetAttack(){
        attackCounrty = null;
        defendCounrty = null;
        attackingInfo.setText("Attacking Country: ");
        defendingInfo.setText("Defending Country: ");
    }

    public void attack(){

        Contry a = countryMap.get(defendCounrty.color);

        a.setUnits(defendCounrty.units - 1);
        countryMap.remove(a.color);
        countryMap.put(a.color,a);
        System.out.println("Units Left: "+ countryMap.get(defendCounrty.color).units);
        resetAttack();
    }

    public void draw(Graphics g){
        if(DispMap == null)return;

        DispMap.draw(g);
        if(contorlChanged)changeColors(g);
    }

    public void changeColors(Graphics g){
        ArrayList<Point> cntColorPoints;
        for(Contry country: countryMap.values()){
            cntColorPoints = ColorMap.getColorCords(country.color);
            for(Point p: cntColorPoints){
                if(country.player > 0){
                    Player control = players[country.player];
                    g.setColor(control.color);
                    g.drawLine(p.x,p.y,p.x,p.y);

                    //drawpointfunc(p);
                }else{}
            }
        }

    }

    public void makeCounties(){
        //TODO:

        //North America

        countryMap.put("FF806E00", new Contry("Alaska","FF806E00", 10));
        countryMap.put("FF503C27",new Contry("North Canada","FF503C27", 10));
        countryMap.put("FFFFFF00",new Contry("West Canada","FFFFFF00", 10));
        countryMap.put("FF949449",new Contry("Central Canada","FF949449", 10));
        countryMap.put("FFFFE6980",new Contry("East Canada","FFFFE6980", 10));
        countryMap.put("FFFFDC00",new Contry("Greenland","FFFFDC00", 10));
        countryMap.put("FF505027",new Contry("West US","FF505027", 10));
        countryMap.put("FF808000",new Contry("East US","FF808000", 10));
        countryMap.put("FFFFFF80",new Contry("Mexico","FFFFFF80", 10));
        //South America
        countryMap.put("FFFF8080",new Contry("Colombia","FFFF8080", 10));
        countryMap.put("FF804040",new Contry("Brazill","FF804040", 10));
        countryMap.put("FF800000",new Contry("Peru","FF05027", 10));
        countryMap.put("FFFF0000",new Contry("Argenitina","FFFF0000", 10));
        //Europe
        countryMap.put("FF0000D5",new Contry("Iceland","FF0000D5", 10));
        countryMap.put("FF003A75",new Contry("United Kingdom","FF003A75", 10));
        countryMap.put("FF1591FF",new Contry("Sweden","FF1591FF", 10));
        countryMap.put("FF00008A",new Contry("Western Russia","FF00008A", 10));
        countryMap.put("FF0000FF",new Contry("Germany","FF0000FF", 10));
        countryMap.put("FF0080FF",new Contry("Spain","FF0080FF", 10));
        countryMap.put("FF004080",new Contry("Itlay","FF004080", 10));
        //Asia
        countryMap.put("FF006015",new Contry("Less Western Russia","FF006015", 10));
        countryMap.put("FF008000",new Contry("Central Russia","FF008000", 10));
        countryMap.put("FF009F9F",new Contry("Less Easter Russia","FF009F9F", 10));
        countryMap.put("FF00954A",new Contry("Easter Russia","FF00954A", 10));
        countryMap.put("FF80FF80",new Contry("Kazakhstan","FF80FF80", 10));
        countryMap.put("FF80FF00",new Contry("South Russia","FF80FF00", 10));
        countryMap.put("FF004000",new Contry("Mongolia","FF004000", 10));
        countryMap.put("FF22B14C",new Contry("Middle East","FF22B14C", 10));
        countryMap.put("FF008080",new Contry("South Asia","FF008080", 10));
        countryMap.put("FF008050",new Contry("China","FF008050", 10));
        countryMap.put("FF6AD500",new Contry("Japan","FF6AD500", 10));
        countryMap.put("FF55FF55",new Contry("South East Asia","FF55FF55", 10));
        //Africa
        countryMap.put("FFFF915B",new Contry("North West Africa","FFFF915B", 10));//5
        countryMap.put("FF9F4400",new Contry("North Africa","FF9F4400", 10));//3
        countryMap.put("FFAE5700",new Contry("Central Africa","FFAE5700", 10));//1
        countryMap.put("FFFF8000",new Contry("East Africa","FFFF8000", 10));//2
        countryMap.put("FF804000",new Contry("South Asia","FF804000", 10));//6
        countryMap.put("FFAE5700",new Contry("Madigascar","FFAE5700", 10));//4
        //Austrailla 
        countryMap.put("FF8000FF",new Contry("Indonesia","FF8000FF", 10));//2
        countryMap.put("FFFF00FF",new Contry("East Indonesia","FFFF00FF", 10));//3
        countryMap.put("FF800040",new Contry("East Australia","FF800040", 10));//4
        countryMap.put("FF400040",new Contry("West Australia","FF400040", 10));//1
        ////////////////////////////////////////////////////// countryMap.put("",new Contry("","FF", 10));

    }

    public void processKey(int code, boolean pressed){
    }

    public void mouseClicked(MouseEvent mouse) {
        if(mouse.getButton() ==  MouseEvent.BUTTON1){
            Clicked = true;}

    }

    public static void main(String[] args) {
        new Risk();
    }
}
