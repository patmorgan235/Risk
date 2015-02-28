import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
}