package Pong2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class MenuState extends GameState {
	
	String[] menu = {"Yksinpeli", "Kaksinpeli", "Huipputulokset", "Ohjeet", "Lopeta"};
	private int buttonWidth, buttonHeight,fontSize;
	private Font menuFont;
	private Rectangle yksinP, kaksinP, huippuT, ohjeet, lopeta;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		buttonWidth = 400;
		buttonHeight = 100;
		fontSize = 40;
		menuFont = new Font("Arial", Font.BOLD, fontSize);
		yksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 60, buttonWidth, buttonHeight);
		kaksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 180, buttonWidth, buttonHeight);
		huippuT = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 300, buttonWidth, buttonHeight);
		ohjeet = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 420, buttonWidth, buttonHeight);
		lopeta = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 540, buttonWidth, buttonHeight);
	}
	
	public void init() {}
	public void update() {
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.draw(yksinP);
		g.draw(kaksinP);
		g.draw(huippuT);
		g.draw(ohjeet);
		g.draw(lopeta);
		g.setFont(menuFont);
		int menuStartPos = 124;
		for (int i = 0; i < menu.length; i++) {
			int stringLength = (int) g.getFontMetrics().getStringBounds(menu[i], g).getWidth();
			int stringHeight = (int) g.getFontMetrics().getStringBounds(menu[i], g).getHeight();
			g.drawString(menu[i], (Panel.WIDTH - stringLength) / 2, menuStartPos + (174 - (buttonHeight - stringHeight))* (i));
		}
	}
	
	public void keyPressed(int k) {}
	public void keyReleased(int k) {}

	public void mousePressed(MouseEvent k) {
		if (yksinP.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(2);
		}
		if (kaksinP.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(1);
		}
		if (huippuT.contains(Panel.mouseX, Panel.mouseY)) {
			// huipputulokset -tila
		}
		if (ohjeet.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(3);
		}
		if (lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			System.exit(0);
		}
	}

	public void mouseReleased(MouseEvent k) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if (yksinP.contains(Panel.mouseX, Panel.mouseY) || kaksinP.contains(Panel.mouseX, Panel.mouseY) || lopeta.contains(Panel.mouseX, Panel.mouseY) || ohjeet.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}
	}
}
