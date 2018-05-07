import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Car1 implements KeyListener{
	
	protected double health,vX,vY,SIZE,speed;;
	public double x,y;
	public boolean leftPressed,rightPressed,upPressed,downPressed;
	protected Color myColor;
	
	public Car1(){
		myColor = Color.red;
		health = 1;
		SIZE = 5;
		x = 150;
		y = MainClass.HEIGHT/2+40-SIZE/2;
		speed = 10;
		leftPressed = rightPressed = upPressed = downPressed = false;
		vX = speed;
		vY = 0;
	}
	
	public ArrayList<Spark> explode(){
		ArrayList<Spark> retVal = new ArrayList<Spark>();
		for(int i=0 ; i<50 ; i++)retVal.add(new Spark(x,y,myColor));
		return retVal;
	}
	
	public void draw(Graphics g){
		g.setColor(myColor);
		g.fillRect((int)x, (int)y, (int)SIZE, (int)SIZE);
	}
	
	public boolean checkHit(BufferedImage background){
		Color c1=null,c2=null;
		if(vX>0){
			c1 = new Color(background.getRGB((int)(x+SIZE+1), (int)y));
			c2 = new Color(background.getRGB((int)(x+SIZE+1), (int)(y+SIZE)));
		}
		if(vX<0){
			c1 = new Color(background.getRGB((int)(x-1), (int)y));
			c2 = new Color(background.getRGB((int)(x-1), (int)(y+SIZE)));
		}
		if(vY>0){
			c1 = new Color(background.getRGB((int)(x), (int)(y+SIZE+1)));
			c2 = new Color(background.getRGB((int)(x+SIZE), (int)(y+SIZE+1)));
		}
		if(vY<0){
			c1 = new Color(background.getRGB((int)(x), (int)(y-1)));
			c2 = new Color(background.getRGB((int)(x+SIZE), (int)(y-1)));
		}
		return !c1.equals(Color.black) || !c2.equals(Color.black);
	}
	
	
	
	public void update(){
		x += vX;
		y += vY;
		if(leftPressed){
			vX=-speed;
			vY = 0;
		}
		if(rightPressed){
			vX=speed;
			vY = 0;
		}
		if(upPressed){
			vY=-speed;
			vX = 0;
		}
		if(downPressed){
			vY=speed;
			vX = 0;
		}
		
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A){
			leftPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			rightPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_W){
			upPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			downPressed = true;
		}
		if(vX==speed){
			leftPressed = false;
		}
		if(vX==-speed){
			rightPressed = false;
		}
		if(vY==speed){
			upPressed = false;
		}
		if(vY==-speed){
			downPressed = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_A){
			leftPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			rightPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_W){
			upPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			downPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
