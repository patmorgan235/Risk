import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Write a description of class GameEngine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameEngine extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener
{
    // instance variables 
    static int WIDTH = 800;
    static int HEIGHT = 600;

    JPanel infoPanel = new JPanel();
    JTextField mouseLoc = new JTextField(6);
    Point mouse = new Point(0,0);


    public GameEngine()
    {
        this("", WIDTH, HEIGHT);
    }

    public GameEngine(String title)
    {
        this(title, WIDTH, HEIGHT);
    }

    public GameEngine(String title, int width, int height)
    {
        WIDTH = width;
        HEIGHT = height;
        JFrame frame = new JFrame(title);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        addMouseListener(this);
        addMouseMotionListener(this);

        infoPanel.add(new JLabel("Mouse Position"));
        infoPanel.add(mouseLoc);
        frame.getContentPane().add(infoPanel, BorderLayout.NORTH);
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable( true );

        Timer t = new Timer(5, this);
        t.start();
    }

    public void keyTyped(KeyEvent key) {}

    public void keyPressed(KeyEvent key) { processKey(key.getKeyCode(), true); }

    public void keyReleased(KeyEvent key) { processKey(key.getKeyCode(), false); }

    public void mouseClicked(MouseEvent mouse) {}

    public void mousePressed(MouseEvent mouse) {}

    public void mouseReleased(MouseEvent mouse) {}

    public void mouseEntered(MouseEvent mouse) {}

    public void mouseExited(MouseEvent mouse) {}

    public void mouseDragged(MouseEvent mouse) {}

    public void mouseMoved(MouseEvent mouse)
    {
        this.mouse = mouse.getPoint();
    }

    public void actionPerformed(ActionEvent e)
    {
        mouseLoc.setText(String.format("(%d,%d)",mouse.x,mouse.y));
        update();
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void processKey(int code, boolean pressed)
    {
    }

    public void update()
    {
    }

    public void draw(Graphics g)
    {
    }
}
