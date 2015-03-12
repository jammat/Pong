

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class PauseState extends GameState {
	
	private Rectangle pauseContainer, jatka, menu, lopeta;
	// 0 == nada, 1 == helppo, 2 == normaali, 3 == vaikea, 4 == takaisin
	private int hover;
	private int width, height, btnWidth, btnHeight;
	private Font otsikko, nappain;
	
	public PauseState(GameStateManager gsm) {
		this.gsm = gsm;
		height = Panel.HEIGHT - 100;
		width = 800;
		btnWidth = 400;
		btnHeight = 80;
		pauseContainer = new Rectangle((Panel.WIDTH) / 2, (Panel.HEIGHT - height) / 2, btnWidth , height);
		jatka = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 160, btnWidth, btnHeight);
		menu = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 320, btnWidth, btnHeight);
		lopeta = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 480, btnWidth, btnHeight);
		otsikko = new Font("Arial", Font.BOLD, 30);
		nappain = new Font("Arial", Font.BOLD, 30);
	}

	public void init() {
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
	}

	public void update() {}

	public void draw(Graphics2D g) {
		// harmaa tausta
		g.setColor(Color.GRAY);
		g.fillRect((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width + 1, height + 1);
		g.draw(pauseContainer);
		
		g.setColor(Color.WHITE);
		// varita helppo-nappi
		if (hover == 1)
			g.setColor(Color.RED);
		g.fillRect((int)jatka.getX(), (int)jatka.getY(), (int)jatka.getWidth(), (int)jatka.getHeight());
		g.setColor(Color.WHITE);
		// varita normaali-nappi
		if (hover == 2 )
			g.setColor(Color.RED);
		g.fillRect((int)menu.getX(), (int)menu.getY(), (int)menu.getWidth(), (int)menu.getHeight());
		g.setColor(Color.WHITE);
		// varita vaikea-nappi
		if (hover == 3)
			g.setColor(Color.RED);
		g.fillRect((int)lopeta.getX(), (int)lopeta.getY(), (int)lopeta.getWidth(), (int)lopeta.getHeight());
		g.setColor(Color.WHITE);
		
		// piiretaan napit
		g.draw(jatka);
		g.draw(menu);
		g.draw(lopeta);
		
		// lisataan tekstit
		g.setFont(otsikko);
		int stringLength = (int) g.getFontMetrics().getStringBounds("Paused", g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds("Paused", g).getHeight();
		g.drawString("Paused", (Panel.WIDTH - stringLength) / 2, 20 + stringHeight + (Panel.HEIGHT - height) / 2);
		g.setFont(nappain);
		g.setColor(Color.BLACK);
		g.drawString("Jatka", (int)jatka.getX() + 156, (int)jatka.getY() + 50);
		g.drawString("Menu", (int)menu.getX() + 158, (int)menu.getY() + 50);
		g.drawString("Lopeta", (int)lopeta.getX() + 150, (int)lopeta.getY() + 50);
	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	
	}

	public void mousePressed(MouseEvent k) {
		if (jatka.contains(Panel.mouseX, Panel.mouseY)){
			gsm.setState(2);
		}
		if (menu.contains(Panel.mouseX, Panel.mouseY)){
			
			/* if () {
				Panel.hm.addScore("Testipelaaja", );
			} */
			
			gsm.setState(0);
		}
		if (lopeta.contains(Panel.mouseX, Panel.mouseY)){
			Panel.quit();
		}	
	}

	public void mouseReleased(MouseEvent k) {
	
	}

	public void mouseMoved(MouseEvent k) {
		if (jatka.contains(Panel.mouseX, Panel.mouseY) || menu.contains(Panel.mouseX, Panel.mouseY) || lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}
		if (jatka.contains(Panel.mouseX, Panel.mouseY)){
			hover = 1;
		} else if (menu.contains(Panel.mouseX, Panel.mouseY)){
			hover = 2;
		} else if (lopeta.contains(Panel.mouseX, Panel.mouseY)){
			hover = 3;
		} else {
			hover = 0;
		}
	}

}
