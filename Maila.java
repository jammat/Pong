package Pong2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Maila {

	protected int x, y, leveys, korkeus, vauhti;
	protected Rectangle rajat;	
	protected boolean ylos, alas;

	public Maila(int x, int y, int leveys, int korkeus) {
		this.x=x;
		this.y=y;
		this.korkeus = korkeus;
		this.leveys = leveys;
		rajat = new Rectangle(x, y, leveys, korkeus);
		rajat.setBounds(x, y, leveys, korkeus);
		vauhti = 5;
		ylos = false;
		alas = false;
	}


	public void liiku(GameOn g) {
		rajat.setBounds(x, y, leveys, korkeus);
		if(ylos && y > 0)
			y -= vauhti;
		if(alas && y < Panel.HEIGHT - korkeus)
			y += vauhti;
	}

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, leveys, korkeus);
	}


}
