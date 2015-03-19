import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/* Luokan avulla voidaan luodaan mailat 
 * pelia varten.
 */
public class Maila {

	protected int x, y, leveys, korkeus, vauhti;
	protected Rectangle rajat;	
	protected boolean ylos, alas;
	protected int pisteet;
	protected String nimi;

	public Maila(int x, int y, int leveys, int korkeus) {
		this.x=x;
		this.y=y;
		this.korkeus = korkeus;
		this.leveys = leveys;
		pisteet = 0;
		rajat = new Rectangle(x, y, leveys, korkeus);
		vauhti = 5;
		ylos = false;
		alas = false;
		// 4testing
		nimi = "Makkonen";
	}

	public void liiku(GameOn g) {
		rajat.setLocation(x, y);
		if(ylos && y > 0)
			y -= vauhti;
		if(alas && y < Panel.HEIGHT - korkeus)
			y += vauhti;
	}

	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, leveys, korkeus);
	}
	
	public void setVauhti(int n) {
		this.vauhti = n;
	}
	
	public void lisaaPiste() {
		this.pisteet++;
	}
	
	public int getPisteet() {
		return this.pisteet;
	}
	
	public void setNimi(String s) {
		nimi = s;
	}
	
	public String getNimi() {
		return nimi;
	}

}
