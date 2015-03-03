public class AIMaila extends Maila{

	public AIMaila(int x, int y) {
		super(x,y);
		leveys = 20;
		korkeus = 100;
		vauhti = 5;
	}

	@Override
	public void liiku(Pong peli) {
		rajat.setBounds(x, y, leveys, korkeus);
		if(Pong.pallo.y <= y && y >= 0 && Pong.pallo.x > 465 && Pong.pallo.x < 1105 && Pong.pallo.vx == Pong.pallo.vauhti)
			y -= vauhti;
		if(Pong.pallo.y > y && y + korkeus <= peli.getHeight() && Pong.pallo.x > 465 && Pong.pallo.x < 1105 && Pong.pallo.vx == Pong.pallo.vauhti)
			y += vauhti;
	}

}
