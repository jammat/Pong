package Pong2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;

public class GameOn extends GameState{
	
	// tausta kuva
	protected Image image;
	
	// mailojen tiedot
	protected int width, height, x, y;
	protected Maila maila1, maila2;
	
	// pallo
	public Pallo pallo;
	
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
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
	}

	public void update() {	
		maila1.liiku(this);
		maila2.liiku(this);
		pallo.liiku(this);
		Pallo p = this.pallo;
		if (p.getX() == 0) {
			this.maila1.lisaaPiste();
		}
		if (p.getX() == (Panel.WIDTH - p.getKoko())){
			this.maila2.lisaaPiste();
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		// tausta
		g.drawImage(image, 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		// Pause teksti
		int stringLength = (int) g.getFontMetrics().getStringBounds("Pause", g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds("Pause", g).getHeight();
		g.drawString("Pause", Panel.WIDTH - 40 - stringLength, Panel.HEIGHT - stringHeight);
		stringLength = (int) g.getFontMetrics().getStringBounds(Integer.toString(this.maila1.getPisteet()), g).getWidth();
		g.drawString(Integer.toString(this.maila1.getPisteet()), (Panel.WIDTH / 2) - 40 - stringLength, 50);
		g.drawString(Integer.toString(this.maila2.getPisteet()), (Panel.WIDTH / 2) + 40, 50);
		maila1.render(g);
		maila2.render(g);
		pallo.render(g);
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
		
		if(nappi == KeyEvent.VK_UP){
			maila2.ylos=true;
		}

		if(nappi == KeyEvent.VK_DOWN){
			maila2.alas=true;
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

		if(nappi == KeyEvent.VK_UP){
			maila2.ylos=false;
		}

		if(nappi == KeyEvent.VK_DOWN){
			maila2.alas=false;
		}
	}

	public void mousePressed(MouseEvent k) {}
	
	public void mouseReleased(MouseEvent k) {}

	public void mouseMoved(MouseEvent k) {}

}
