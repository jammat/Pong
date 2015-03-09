package Pong2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pallo {
	int x, y;
	int koko = 16;
	int vauhti = 6; // varmuuden vuoksi oletusarvo
	int vx, vy;
	Rectangle rajat;

	public Pallo(int x, int y) {
		this.x = x;
		this.y = y;
		vx=vauhti;
		vy=vauhti;
		rajat = new Rectangle(x, y, koko, koko);
	}

	public void liiku(GameOn g) {
		rajat.setLocation(x, y);

		if (x <= 0) {
			vx = vauhti;
		}
		if (x >= Panel.WIDTH - this.getKoko()) {
			vx = -vauhti;
		}

		if (y <= 0) {
			vy = vauhti;
		}
		if (y >= Panel.HEIGHT - this.getKoko()) {
			vy = -vauhti;
		}

		x += vx;
		y += vy;

		mailaosuma(g);
	}

	private void mailaosuma(GameOn g){
		if (rajat.intersects(g.maila1.rajat)) {
			vx = vauhti;
		} else if (rajat.intersects(g.maila2.rajat)) {
			vx = -vauhti;
		}
	}

	public void render(Graphics2D g) {
		rajat.setLocation(x, y);
		g.setColor(Color.BLUE);
		g.fillOval(x, y, koko, koko);
	}
	
	public void setVauhti(int n) {
		this.vauhti = n;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int n) {
		this.x = n;
	}
	
	public void setY(int n) {
		this.y = n;
	}
	
	public void resetPallo() {
		this.setY(Panel.HEIGHT / 2);
		this.setX((Panel.WIDTH / 2) - (this.koko / 2));
	}
	
	public int getKoko() {
		return this.koko;
	}
}
