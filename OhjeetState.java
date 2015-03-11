

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class OhjeetState extends GameState {
	private Rectangle ohjeetContainer, takaisin;
	private int width, height;
	private Font otsikko, leipateksti, nappain;
	
	public OhjeetState(GameStateManager gsm) {
		this.gsm = gsm;
		height = Panel.HEIGHT - 100;
		width = 800;
		ohjeetContainer = new Rectangle((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width, height);
		takaisin = new Rectangle((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		otsikko = new Font("Arial", Font.BOLD, 30);
		leipateksti = new Font("Arial", Font.PLAIN, 16);
		nappain = new Font("Arial", Font.BOLD, 20);
	}

	public void init() {
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
	}

	public void update() {}

	public void draw(Graphics2D g) {
		g.draw(ohjeetContainer);
		g.setColor(Color.GRAY);
		g.fillRect((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width + 1, height + 1);
		g.setColor(Color.WHITE);
		g.draw(takaisin);
		g.fillRect((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		g.setFont(otsikko);
		int stringLength = (int) g.getFontMetrics().getStringBounds("Ohjeet", g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds("Ohjeet", g).getHeight();
		g.drawString("Ohjeet", (Panel.WIDTH - stringLength) / 2, 20 + stringHeight + (Panel.HEIGHT - height) / 2);
		g.setFont(leipateksti);
		g.drawString("Yksinpeliss\u00E4 vasemman puolen mailaa ohjataan W- ja S-n\u00E4pp\u00E4imill\u00E4.", (Panel.WIDTH - width + 80) / 2, stringHeight + 60 + (Panel.HEIGHT - height) / 2);
		g.drawString("Moninpeliss\u00E4 vasenta mailaa ohjataan kuten yksinpeliss\u00E4, oikeaa mailaa nuolin\u00E4pp\u00E4imill\u00E4.", (Panel.WIDTH - width + 80) / 2, stringHeight + 80 +(Panel.HEIGHT - height) / 2);
		g.drawString("Pelin saa tauotettua P-n\u00E4pp\u00E4imest\u00E4.", (Panel.WIDTH - width + 80) / 2, stringHeight + 100 + (Panel.HEIGHT - height) / 2);
		g.setFont(nappain);
		g.setColor(Color.BLACK);
		g.drawString("Takaisin", (Panel.WIDTH - width) / 2 + 60, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 12);
	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	
	}

	public void mousePressed(MouseEvent k) {
		if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(0);
		}		
	}

	public void mouseReleased(MouseEvent k) {
	
	}

	public void mouseMoved(MouseEvent k) {
		if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}	
	}

}
