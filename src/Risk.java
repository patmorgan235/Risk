import java.awt.*;

@SuppressWarnings("serial")
public class Risk extends GameEngine{
	Img DispMap ;
	
	
	public Risk(){
		super("Risk",1200,612);
		
		DispMap = new Img("RiskMap.png");
		DispMap.setPosition(100, 100);
		
		run();
	}
	
	public void update(){
		
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
