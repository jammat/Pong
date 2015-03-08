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
		rajat.setBounds(this.x, this.y, this.koko, this.koko);
	}

	public void liiku(GameOn g) {
		rajat.setBounds(x, y, koko, koko);

		if (x <= 0) {
			vx = vauhti;
		} else if (x + koko >= Panel.WIDTH) {
			vx = -vauhti;
		}

		if (y <= 0) {
			vy = vauhti;
		} else if (y + koko >= Panel.HEIGHT) {
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
	
	public int getKoko() {
		/*
		varmaan joku juttu missa rectanglessa on 1 yksikon 
		mittainen raja kullakin sivulla, joten leveytta tai
		korkeutta mitattaessa taytyy vahentaa 2
		* */
		return this.koko - 2;
	}
}
