

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class AsetuksetState extends GameState {
	
	private Rectangle asetuksetContainer, helppo, normaali, vaikea, takaisin;
	// 0 == nada, 1 == helppo, 2 == normaali, 3 == vaikea, 4 == takaisin
	private int hover;
	private int width, height;
	private Font otsikko, leipateksti, nappain;
	
	public AsetuksetState(GameStateManager gsm) {
		this.gsm = gsm;
		height = Panel.HEIGHT - 100;
		width = 800;
		asetuksetContainer = new Rectangle((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width, height);
		takaisin = new Rectangle((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		helppo = new Rectangle((Panel.WIDTH - width + 80) / 2, 160, 200, 40);
		normaali = new Rectangle((Panel.WIDTH - width + 80) / 2 + 250, 160, 200, 40);
		vaikea = new Rectangle((Panel.WIDTH - width + 80) / 2 + 500, 160, 200, 40);
		otsikko = new Font("Arial", Font.BOLD, 30);
		leipateksti = new Font("Arial", Font.PLAIN, 16);
		nappain = new Font("Arial", Font.BOLD, 20);
	}

	public void init() {
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
	}

	public void update() {}

	public void draw(Graphics2D g) {
		// harmaa tausta
		g.setColor(Color.GRAY);
		g.fillRect((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width + 1, height + 1);
		g.draw(asetuksetContainer);
		
		g.setColor(Color.WHITE);
		// varita helppo-nappi
		if (hover == 1 || Panel.VAIKEUSASTE == 0)
			g.setColor(Color.RED);
		g.fillRect((int)helppo.getX(), (int)helppo.getY(), (int)helppo.getWidth(), (int)helppo.getHeight());
		g.setColor(Color.WHITE);
		// varita normaali-nappi
		if (hover == 2 || Panel.VAIKEUSASTE == 1)
			g.setColor(Color.RED);
		g.fillRect((int)normaali.getX(), (int)normaali.getY(), (int)normaali.getWidth(), (int)normaali.getHeight());
		g.setColor(Color.WHITE);
		// varita vaikea-nappi
		if (hover == 3 || Panel.VAIKEUSASTE == 2)
			g.setColor(Color.RED);
		g.fillRect((int)vaikea.getX(), (int)vaikea.getY(), (int)vaikea.getWidth(), (int)vaikea.getHeight());
		g.setColor(Color.WHITE);
		// varita takaisin-nappi
		if (hover == 4)
			g.setColor(Color.RED);
		g.fillRect((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		g.setColor(Color.WHITE);
		
		// piiretaan napit
		g.draw(helppo);
		g.draw(normaali);
		g.draw(vaikea);
		g.draw(takaisin);
		
		// lisataan tekstit
		g.setFont(otsikko);
		int stringLength = (int) g.getFontMetrics().getStringBounds("Ohjeet", g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds("Ohjeet", g).getHeight();
		g.drawString("Asetukset", (Panel.WIDTH - stringLength) / 2, 20 + stringHeight + (Panel.HEIGHT - height) / 2);
		g.setFont(leipateksti);
		g.drawString("Vaikeustaso", (Panel.WIDTH - width + 80) / 2, stringHeight + 60 + (Panel.HEIGHT - height) / 2);
		g.setFont(nappain);
		g.setColor(Color.BLACK);
		g.drawString("Helppo", (Panel.WIDTH - width + 80) / 2 + 70, 187);
		g.drawString("Normaali", (Panel.WIDTH - width + 80) / 2 + 250 + 60, 187);
		g.drawString("Vaikea", (Panel.WIDTH - width + 80) / 2 + 500 + 70, 187);
		g.drawString("Takaisin", (Panel.WIDTH - width) / 2 + 60, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 12);
	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	
	}

	public void mousePressed(MouseEvent k) {
		/* Seuraavat kolme ehtoa asettavat pelin vaikeustilan
		 * miten se tullaankaan maarittamaan. Tahan hataan
		 * se asettaa vain pallon nopeuden.
		 */
		if (helppo.contains(Panel.mouseX, Panel.mouseY)){
			Panel.setVaikeusAste(0);
		}
		if (normaali.contains(Panel.mouseX, Panel.mouseY)){
			Panel.setVaikeusAste(1);
		}
		if (vaikea.contains(Panel.mouseX, Panel.mouseY)){
			Panel.setVaikeusAste(2);
		}
		if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(0);
		}		
	}

	public void mouseReleased(MouseEvent k) {
	
	}

	public void mouseMoved(MouseEvent k) {
		if (helppo.contains(Panel.mouseX, Panel.mouseY) || normaali.contains(Panel.mouseX, Panel.mouseY) || vaikea.contains(Panel.mouseX, Panel.mouseY) || takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}
		if (helppo.contains(Panel.mouseX, Panel.mouseY)){
			hover = 1;
		} else if (normaali.contains(Panel.mouseX, Panel.mouseY)){
			hover = 2;
		} else if (vaikea.contains(Panel.mouseX, Panel.mouseY)){
			hover = 3;
		} else if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			hover = 4;
		} else {
			hover = 0;
		}
	}

}
