import java.awt.Color;
import java.awt.event.KeyEvent;


public class Car2 extends Car1{
	
	
	public Car2(){
		myColor = Color.blue;
		x = MainClass.WIDTH-150-SIZE;
		vX = -speed;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			upPressed = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
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
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			upPressed = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			downPressed = false;
		}
	}	
}