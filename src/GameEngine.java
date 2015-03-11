import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameEngine extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    private boolean DEBUG = false;
    protected Point mouse = new Point();
    JTextField mouseLoc = new JTextField(20);
    JFrame frame;
    
    String name ="";
    int x = 640;
    int y = 640;
    
    public GameEngine(String name, int x, int y)
    {
    	
    	this.name = name;
    	this.x = x;
    	this.y = y;
    	
        setPreferredSize(new Dimension(x,y));
        setBackground(Color.BLACK);
        addMouseListener(this);
        addMouseMotionListener(this);
        
        
        frame = new JFrame(name);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mouseTrack = new JPanel();
        mouseTrack.add(new JLabel("Mouse: "));
        mouseTrack.add(mouseLoc);
        addPanel(mouseTrack,BorderLayout.NORTH);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addPanel(JPanel panel, String location)
    {
        frame.setVisible(false);
        frame.getContentPane().add(panel, location);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void mouseMoved(MouseEvent mouse) 
    {
        this.mouse = mouse.getPoint();
    }
    public void mouseEntered(MouseEvent mouse) {}
    public void mouseExited(MouseEvent mouse) {}
    public void mousePressed(MouseEvent mouse) {}
    public void mouseReleased(MouseEvent mouse) {}
    public void mouseClicked(MouseEvent mouse) {}
    public void mouseDragged(MouseEvent mouse) 
    {
        this.mouse = mouse.getPoint();
    }
    public void keyTyped(KeyEvent key) {}
    public void keyPressed(KeyEvent key) 
    { 
        processKey(key.getKeyCode(), true);
    }
    public void keyReleased(KeyEvent key)
    {
        processKey(key.getKeyCode(), false);
    }
    
    public void processKey(int code, boolean pressed)
    {
        if(DEBUG) System.out.println(code +":"+ pressed);
    }
    
    public void run()
    {
        Timer t = new Timer(5, this);
        t.start();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        mouseLoc.setText(String.format("(%d,%d)",mouse.x, mouse.y));
        update();
        repaint();
    }
    
    public void update()
    {
    }
    
    public void draw(Graphics g)
    {
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
}
