package Pong2;

import java.awt.Color;
import java.awt.Graphics;

public class AIMaila extends Maila {
	public AIMaila(int x, int y, int leveys, int korkeus) {
		super(x, y, leveys, korkeus);
	}
	
	@Override
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, leveys, korkeus);
	}

	@Override
	public void liiku(GameOn g) {
		rajat.setBounds(x, y, leveys, korkeus);
		if(g.pallo.y <= y && y >= 0 && g.pallo.x > 465 && g.pallo.x < 1105 && g.pallo.vx == g.pallo.vauhti)
			y -= vauhti;
		if(g.pallo.y > y && y + korkeus <= Panel.HEIGHT && g.pallo.x > 465 && g.pallo.x < 1105 && g.pallo.vx == g.pallo.vauhti)
			y += vauhti;
	}	
}
