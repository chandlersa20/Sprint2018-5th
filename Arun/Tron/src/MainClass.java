import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainClass extends JFrame implements Runnable, KeyListener {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	private BufferedImage offScreen;
	private Graphics bg;
	private Car1 myCar1;
	private Car2 myCar2;
	private ArrayList<Spark> mySparks;
	private boolean gameOver, allOver;
	public int Car1score, Car2score;
	public static final int maxscore = 3;

	public MainClass() {
		offScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		bg = offScreen.getGraphics();
		Font f = bg.getFont().deriveFont(30f);
		bg.setFont(f);
		restart();
		new Thread(this).start();
		this.addKeyListener(this);
	}

	public void restart() {
		if(gameOver)paint2(bg);
		if(gameOver)scoreboard(bg);
		gameOver = false;
		myCar1 = new Car1();
		myCar2 = new Car2();
		mySparks = new ArrayList<Spark>();
		this.addKeyListener(myCar1);
		this.addKeyListener(myCar2);
		if(allOver){
			Car1score = 0;
			Car2score = 0;
			bg.setColor(Color.black);
			bg.fillRect(0, 0, WIDTH, HEIGHT);
			allOver = false;
		}
	}

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		mc.setSize(WIDTH, HEIGHT);
		mc.setResizable(false);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setVisible(true);
	}

	public void paint(Graphics g) {
		//bg.setColor(Color.black);
		//bg.fillRect(0, 0, WIDTH, HEIGHT);
		myCar1.draw(bg);
		myCar2.draw(bg);
		//end game
		if(gameOver){
			bg.setColor(Color.white);
			bg.drawString("PRESS SPACE TO RESTART", WIDTH/2-200, HEIGHT/2+150);
		}
		if(Car1score==maxscore||Car2score==maxscore){
			victoryRoyale(bg);
		}
		//borders/obstacles/Score board
		bg.setColor(Color.white);
		bg.fillRect(0, 25, 10, HEIGHT);
		bg.fillRect(WIDTH/2-5, 0, 10, 75);
		bg.fillRect(0, 1070, WIDTH, 10);
		bg.fillRect(1910, 25, 10, HEIGHT);
		bg.fillRect(0, 25, WIDTH, 10);
		bg.fillRect(0, 75, WIDTH, 10);
		bg.fillRect(755, 350, 400, 10);
		bg.fillRect(755, 805, 400, 10);
		bg.fillRect(1600, 250, 150, 10);
		bg.fillRect(1750, 250, 10, 150);
		bg.fillRect(1600, HEIGHT-165, 150, 10);
		bg.fillRect(1750, HEIGHT-305, 10, 150);
		bg.fillRect(170, 250, 150, 10);
		bg.fillRect(170, 250, 10, 150);
		bg.fillRect(180, HEIGHT-165, 150, 10);
		bg.fillRect(170, HEIGHT-305, 10, 150);
		bg.fillRect(WIDTH/2-5, 175, 10, 100);
		bg.fillRect(WIDTH/2-5, HEIGHT-200, 10, 100);
		bg.fillRect(500, 460, 10, 250);
		bg.fillRect(WIDTH-500, 460, 10, 250);
		bg.setColor(Color.red);
		bg.drawRect(15, 40, 308, 30);
		bg.drawRect(328, 40, 308, 30);
		bg.drawRect(641, 40, 308, 30);
		bg.setColor(Color.blue);
		bg.drawRect(969, 40, 308, 30);
		bg.drawRect(1282, 40, 308, 30);
		bg.drawRect(1595, 40, 308, 30);
		//sparks
		if (mySparks != null){
			for (Spark s : mySparks)
				s.draw(bg);
		}
		g.drawImage(offScreen, 0, 0, null);
	}
	
	public void paint2(Graphics g){
		bg.setColor(Color.black);
		bg.fillRect(0, 85, WIDTH, HEIGHT);
	}
	
	public void scoreboard(Graphics g){
		if(Car1score==1){
			g.setColor(Color.red);
			g.fillRect(15, 40, 308, 30);
		}
		if(Car1score==2){
			g.setColor(Color.red);
			g.fillRect(328, 40, 308, 30);
		}
		if(Car1score==3){
			g.setColor(Color.red);
			g.fillRect(641, 40, 308, 30);
		}
		if(Car2score==1){
			g.setColor(Color.blue);
			g.fillRect(969, 40, 308, 30);
		}
		if(Car2score==2){
			g.setColor(Color.blue);
			g.fillRect(1282, 40, 308, 30);
		}
		if(Car2score==3){
			g.setColor(Color.blue);
			g.fillRect(1595, 40, 308, 30);
		}
	}
	
	public void victoryRoyale(Graphics g){
		if(Car1score==maxscore){
			g.setColor(Color.red);
			g.fillRect(WIDTH/2-145, HEIGHT/2-35, 290, 50);
			g.setColor(Color.yellow);
			g.drawString("#1 Victory Royale!", WIDTH/2-120, HEIGHT/2);
			g.setColor(Color.white);
		}
		if(Car2score==maxscore){
			g.setColor(Color.blue);
			g.fillRect(WIDTH/2-145, HEIGHT/2-35, 290, 50);
			g.setColor(Color.yellow);
			g.drawString("#1 Victory Royale!", WIDTH/2-120, HEIGHT/2);
			g.setColor(Color.white);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
				if (!gameOver&&!allOver) {
					myCar1.update();
					myCar2.update();
					if (myCar1.checkHit(offScreen)) {
						Car2score++;
						gameOver = true;
						mySparks.addAll(myCar1.explode());
					}
					if (myCar2.checkHit(offScreen)) {
						Car1score++;
						gameOver = true;
						mySparks.addAll(myCar2.explode());
					}
					if(myCar1.checkHit(offScreen)&&myCar2.checkHit(offScreen)){
						Car1score--;
						Car2score--;
					}
				} else {
					for(int i=0 ; i<mySparks.size() ; i++){
						Spark s = mySparks.get(i);
						if(s.getX()>1910)mySparks.remove(s);
						if(s.getX()<10)mySparks.remove(s);
						if(s.getY()>1070)mySparks.remove(s);
						if(s.getY()<80)mySparks.remove(s);
						s.update();
					}
						
				}
				if(Car1score==maxscore||Car2score==maxscore){
					allOver = true;
				}
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver && e.getKeyCode()==KeyEvent.VK_SPACE) restart();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
