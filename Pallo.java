package Pong2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pallo {
	int x, y;
	int koko = 16;
	int vauhti = 6;
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
			vx = -vx;
		}
		if (x >= Panel.WIDTH - this.getKoko()) {
			vx = -vx;
		}

		if (y <= 0) {
			vy = -vy;
		}
		if (y >= Panel.HEIGHT - this.getKoko()) {
			vy = -vy;
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

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, koko, koko);
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
		/*
		varmaan joku juttu missa rectanglessa on 1 yksikon 
		mittainen raja kullakin sivulla, joten leveytta tai
		korkeutta mitattaessa taytyy vahentaa 2
		* */
		return this.koko - 2;
	}
}
