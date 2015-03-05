import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Maila {

	int x, y, leveys, korkeus, vauhti;
	Rectangle rajat;	
	boolean ylos, alas;

	public Maila(int x, int y) {
		this.x=x;
		this.y=y;
		rajat = new Rectangle(x, y, leveys, korkeus);
		rajat.setBounds(x, y, leveys, korkeus);
		leveys = 20;
		korkeus = 100;
		vauhti = 5;
		ylos = false;
		alas = false;
	}


	public void liiku(Pong peli) {
		rajat.setBounds(x, y, leveys, korkeus);
		if(ylos && y > 0)
			y -= vauhti;
		if(alas && y < peli.getHeight() - korkeus)
			y += vauhti;
	}

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, leveys, korkeus);
	}


}
