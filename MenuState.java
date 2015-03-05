package Pong2;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class MenuState extends GameState {
	
	String[] menu = {"Yksinpeli", "Kaksinpeli", "Lopeta"};
	private int buttonWidth;
	private int buttonHeight;
	private int fontSize;
	private Font menuFont;
	private Rectangle yksinP, kaksinP, lopeta;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		buttonWidth = 300;
		buttonHeight = 100;
		fontSize = 50;
		menuFont = new Font("Arial", Font.BOLD, fontSize);
		yksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 160, buttonWidth, buttonHeight);
		kaksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 280, buttonWidth, buttonHeight);
		lopeta = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 400, buttonWidth, buttonHeight);
	}
	
	public void init() {}
	public void update() {
	}
	
	public void draw(Graphics2D g) {
		g.draw(yksinP);
		g.draw(kaksinP);
		g.draw(lopeta);
		g.setFont(menuFont);
		int menuStartPos = 230;
		for (int i = 0; i < menu.length; i++) {
			int stringLength = (int) g.getFontMetrics().getStringBounds(menu[i], g).getWidth();
			int stringHeight = (int) g.getFontMetrics().getStringBounds(menu[i], g).getHeight();
			g.drawString(menu[i], (Panel.WIDTH - stringLength) / 2, menuStartPos + (160 - (buttonHeight - stringHeight))* (i));
		}
		/*
		g.drawString("Yksinpeli", (Panel.WIDTH - buttonWidth) / 2 + 40, 227);
		g.drawString("Moninpeli", (Panel.WIDTH - buttonWidth) / 2 + 34, 350);
		g.drawString("Lopeta", (Panel.WIDTH - buttonWidth) / 2 + 66, 468);*/
	}
	public void keyPressed(int k) {}
	public void keyReleased(int k) {}

	public void mousePressed(MouseEvent k) {
		if (yksinP.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(1);
		}
		if (lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			System.exit(0);
		}
	}

	public void mouseReleased(MouseEvent k) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if (yksinP.contains(Panel.mouseX, Panel.mouseY) || kaksinP.contains(Panel.mouseX, Panel.mouseY) || lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}
	}
}
