

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

	public void liiku(Pong peli) {
		rajat.setBounds(x, y, koko, koko);

		if (x <= 0) {
			vx = vauhti;
		} else if (x + koko >= peli.getWidth()) {
			vx = -vauhti;
		}

		if (y <= 0) {
			vy = vauhti;
		} else if (y + koko >= peli.getHeight()) {
			vy = -vauhti;
		}

		x += vx;
		y += vy;

		mailaosuma(peli);
	}

	private void mailaosuma(Pong peli){
		if (rajat.intersects(Pong.pelaaja.rajat)) {
			vx = vauhti;
		} else if (rajat.intersects(Pong.AIPelaaja.rajat)) {
			vx = -vauhti;
		}
	}


	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillOval(x, y, koko, koko);

	}

}
