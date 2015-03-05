package Pong2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Maila {

	int x, y, leveys, korkeus, vauhti;
	Rectangle rajat;	
	boolean ylos, alas;

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


	public void liiku() {
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

class AIMaila extends Maila {
	public AIMaila(int x, int y, int leveys, int korkeus) {
		super(x, y, leveys, korkeus);
		vauhti = 5;
	}

	@Override
	public void liiku() {
		rajat.setBounds(x, y, leveys, korkeus);
		if(GameOn.pallo.y <= y && y >= 0 && GameOn.pallo.x > 465 && GameOn.pallo.x < 1105 && GameOn.pallo.vx == GameOn.pallo.vauhti)
			y -= vauhti;
		if(GameOn.pallo.y > y && y + korkeus <= Panel.HEIGHT && GameOn.pallo.x > 465 && GameOn.pallo.x < 1105 && GameOn.pallo.vx == GameOn.pallo.vauhti)
			y += vauhti;
	}	
}
