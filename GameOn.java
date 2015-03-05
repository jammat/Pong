package Pong2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;

public class GameOn extends GameState{
	
	Image image;
	// mailojen tiedot
	int width, height, x, y;
	static Maila maila1, maila2;
	static Pallo pallo;

	public GameOn(GameStateManager gsm) {
		this.gsm = gsm;
		String basePath = new File("").getAbsolutePath();
		ImageIcon i = new ImageIcon(basePath + "/Pong2/kentta.jpg");
		this.image = i.getImage();
		width = 20;
		height = 100;
		x = 100;
		y = (Panel.HEIGHT - height) / 2;
		maila1 = new Maila(x, y, width, height);
		maila2 = new Maila(Panel.WIDTH - x, y, width, height);
		pallo = new Pallo(Panel.HEIGHT / 2, Panel.WIDTH / 2);
	}
	
	public void init() {
	}

	public void update() {	
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		maila1.render(g);
		maila1.liiku();
		maila2.render(g);
		maila2.liiku();
		pallo.render(g);
		pallo.liiku();
		g.setColor(Color.WHITE);
	}
	
	public void keyPressed(int k) {
		int nappi = k;

		if(nappi == KeyEvent.VK_P){
			gsm.setState(0);
		}
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos=true;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas=true;
		}
		
		if (!(maila2 instanceof AIMaila)) {
			if(nappi == KeyEvent.VK_UP){
				maila2.ylos=true;
			}
	
			if(nappi == KeyEvent.VK_DOWN){
				maila2.alas=true;
			}
		}
	}

	public void keyReleased(int k) {
		int nappi = k;
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos=false;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas=false;
		}

		if (!(maila2 instanceof AIMaila)) {
			if(nappi == KeyEvent.VK_UP){
				maila2.ylos=false;
			}
	
			if(nappi == KeyEvent.VK_DOWN){
				maila2.alas=false;
			}
		}
	}

	public void mousePressed(MouseEvent k) {	
	}
	
	public void mouseReleased(MouseEvent k) {	
	}

	public void mouseMoved(MouseEvent k) {
	}

}
