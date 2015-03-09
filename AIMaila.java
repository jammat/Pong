package Pong2;

public class AIMaila extends Maila {
	public AIMaila(int x, int y, int leveys, int korkeus) {
		super(x, y, leveys, korkeus);
	}

	@Override
	public void liiku(GameOn g) {
		this.rajat.setLocation(x, y);
		// jos pallo on oikealla kenttapuolella
		if (g.pallo.x > 465 && g.pallo.x < 1216) {
			if(g.pallo.getY() <= y)
				y -= vauhti + 2;
			if(g.pallo.getY() >= y && y <= g.pallo.y - korkeus)
				y += vauhti + 2;
		}
	}	
}
