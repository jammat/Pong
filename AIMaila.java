



public class AIMaila extends Maila {
	public AIMaila(int x, int y, int leveys, int korkeus) {
		super(x, y, leveys, korkeus);
	}

	static Random luku = new Random();
	static int heitto = luku.nextInt(20);

	@Override
	public void liiku(GameOn g) {
		this.rajat.setLocation(x, y);
		
		// jos pallo on oikealla kenttapuolella
		if (g.pallo.x > 465 && g.pallo.x < 1000) {
			if(g.pallo.getY() + heitto -5 <= y)
				y -= vauhti;
			if(g.pallo.getY() + heitto - 5 >= y && y <= g.pallo.y - korkeus)
				y += vauhti;
		}	
		if (g.pallo.x > 1000 && g.pallo.x < 1216) {
			if(g.pallo.getY() + heitto - 5 <= y)
				y -= vauhti + 1;
			if(g.pallo.getY() + heitto - 5 >= y && y <= g.pallo.y - korkeus)
				y += vauhti + 1;
		}
	}	
