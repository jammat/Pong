import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* GameOn-luokka perii abstraktin GameState-luokan.
 * Luokassa toteutetaan pelin piirto, seka sen
 * kautta hallitaan pisteytysta seka vaikeusastetta.
 */
public class GameOn extends GameState{
	
	// tausta kuva
	protected Image image;
	protected static String IMAGEPOLKU = Panel.BASEPATH + "//kentta.jpg";
	
	// mailojen tiedot
	protected int width, height, x, y;
	protected Maila maila1, maila2;
	
	// pallo
	public Pallo pallo;
	
	public GameOn(GameStateManager gsm) {
		this.gsm = gsm;
		ImageIcon i = new ImageIcon(IMAGEPOLKU);
		this.image = i.getImage();
		width = 20;
		height = 100;
		x = 100;
		y = (Panel.HEIGHT - height) / 2;
		maila1 = new Maila(x, y, width, height);
		maila2 = new Maila(Panel.WIDTH - x, y, width, height);
		pallo = new Pallo((Panel.WIDTH / 2) - 14, Panel.HEIGHT / 2);
	}
	
	public void init() {
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
		Panel.thread.suspend();
		while (maila1.getNimi() == null){
			String s = (String)JOptionPane.showInputDialog(
					new JFrame(),
	                "Kirjoita nimesi",
	                "Anna nimi",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "Bala Mui");
			// cancel-nappia on painettu silloin kun s on null
			if (s == null) {
			    // palataan MENU-tilaan
				gsm.setState(GameStateManager.MENU);
				Panel.thread.resume();
			    return;
			}
			maila1.setNimi(s);
		}
		while (maila2.getNimi() == null){
			String s = (String)JOptionPane.showInputDialog(
					new JFrame(),
	                "Kirjoita nimimerkki",
	                "Pelaaja2 nimi",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "Bala Mui");
			// cancel-nappia on painettu silloin kun s on null
			if (s == null) {
			    // palataan MENU-tilaan
				gsm.setState(GameStateManager.MENU);
				Panel.thread.resume();
			    return;
			}
			maila2.setNimi(s);
		}
		Panel.thread.resume();
		// kutsutaan vaikeusasteen asettavaa metodia
		asetaVaikeus();
	}
	
	protected void asetaVaikeus() {
		// asetetaan vaikeusaste tassa
		if ( Panel.VAIKEUSASTE == 0 ) {
			this.pallo.setVauhti(5);
			this.maila1.setVauhti(4);
			this.maila2.setVauhti(4);
			maila1.korkeus = 100;
			maila2.korkeus = 100;
		} else if ( Panel.VAIKEUSASTE == 1 ) {
			this.pallo.setVauhti(6);
			this.maila1.setVauhti(5);
			this.maila2.setVauhti(5);
			maila1.korkeus = 80;
			maila2.korkeus = 80;
		} else if (Panel.VAIKEUSASTE == 2) {
			this.pallo.setVauhti(7);
			this.maila1.setVauhti(6);
			this.maila2.setVauhti(6);
			maila1.korkeus = 60;
			maila2.korkeus = 60;
		}
	}

	public void update() {
		maila1.liiku(this);
		maila2.liiku(this);
		pallo.liiku(this);
		Pallo p = this.pallo;
		if (p.getX() <= 0) {
			this.maila2.lisaaPiste();
			this.resetGame();
		}
		if (p.getX() >= (Panel.WIDTH - p.getKoko())){
			this.maila1.lisaaPiste();
			this.resetGame();
		}
	}
	
	/* Kutsutaan kun juompi kumpi 
	 * pelaajista saa pisteen
	 */
	public void resetGame() {
		// palauttaa pallon keskipisteeseen maalin jalkeen
		this.pallo.resetPallo();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		// tausta
		g.drawImage(image, 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		// Pause teksti
		int stringLength = (int) g.getFontMetrics().getStringBounds(Integer.toString(this.maila1.getPisteet()), g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds(Integer.toString(this.maila1.getPisteet()), g).getHeight();
		g.drawString(Integer.toString(this.maila1.getPisteet()), (Panel.WIDTH / 2) - 20 - stringLength, 20 + stringHeight);
		int stringLengthNimi1 = (int) g.getFontMetrics().getStringBounds(this.maila1.getNimi(), g).getWidth();
		g.drawString(this.maila1.getNimi(), (Panel.WIDTH / 2) - 2*20 - stringLength - stringLengthNimi1, 20 + stringHeight);
		g.drawString(Integer.toString(this.maila2.getPisteet()), (Panel.WIDTH / 2) + 20, 20 + stringHeight);
		int stringLengthNimi2 = (int) g.getFontMetrics().getStringBounds(this.maila1.getNimi(), g).getWidth();
		g.drawString(this.maila2.getNimi(), (Panel.WIDTH / 2) - 2*20 + stringLength + stringLengthNimi2, 20 + stringHeight);
		maila1.render(g);
		maila2.render(g);
		pallo.render(g);
	}
	
	public void keyPressed(int k) {
		int nappi = k;

		if(nappi == KeyEvent.VK_P){
			gsm.setState(5);
		}
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos=true;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas=true;
		}
		
		if(nappi == KeyEvent.VK_UP){
			maila2.ylos=true;
		}

		if(nappi == KeyEvent.VK_DOWN){
			maila2.alas=true;
		}
	}

	public void keyReleased(int k) {
		int nappi = k;
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos=false;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas=false;
		}

		if(nappi == KeyEvent.VK_UP){
			maila2.ylos=false;
		}

		if(nappi == KeyEvent.VK_DOWN){
			maila2.alas=false;
		}
	}

	public void mousePressed(MouseEvent k) {}
	
	public void mouseReleased(MouseEvent k) {}

	public void mouseMoved(MouseEvent k) {}
	
	public Maila getMaila1() {
		return maila1;
	}
	
	public Maila getMaila2() {
		return maila1;
	}
	
	public Pallo getPallo() {
		return pallo;
	}

}
