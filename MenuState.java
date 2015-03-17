

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.MouseEvent;

public class MenuState extends GameState {
	
	String[] menu = {"Yksinpeli", "Kaksinpeli", "Lataa peli", "Huipputulokset", "Ohjeet", "Asetukset", "Lopeta"};
	private int buttonWidth, buttonHeight,fontSize,loopnumber;
	private Font menuFont;
	private Rectangle tausta, yksinP, kaksinP, loadN, huippuT, ohjeet, asetukset, lopeta;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		loopnumber = 0;
		buttonWidth = 400;
		buttonHeight = 80;
		fontSize = 30;
		menuFont = new Font("Arial", Font.BOLD, fontSize);
		tausta = new Rectangle(0,0,Panel.WIDTH, Panel.HEIGHT);
		yksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 40, buttonWidth, buttonHeight);
		kaksinP = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 125, buttonWidth, buttonHeight);
		loadN = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 210, buttonWidth, buttonHeight);
		huippuT = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 295, buttonWidth, buttonHeight);
		ohjeet = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 380, buttonWidth, buttonHeight);
		asetukset = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 465, buttonWidth, buttonHeight);
		lopeta = new Rectangle((Panel.WIDTH - buttonWidth) / 2, 550, buttonWidth, buttonHeight);
	}
	
	public void init() {}
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		this.loopnumber++;
		g.setColor(Color.BLACK);
		g.fillRect((int)tausta.getX(), (int)tausta.getY(), (int)tausta.getWidth(), (int)tausta.getHeight());
		g.draw(tausta);
		g.setColor(Color.WHITE);
		g.draw(yksinP);
		g.draw(kaksinP);
		g.draw(loadN);
		g.draw(huippuT);
		g.draw(ohjeet);
		g.draw(asetukset);
		g.draw(lopeta);
		g.setFont(menuFont);
		int menuStartPos = 92;
		for (int i = 0; i < menu.length; i++) {
			int stringLength = (int) g.getFontMetrics().getStringBounds(menu[i], g).getWidth();
			int stringHeight = (int) g.getFontMetrics().getStringBounds(menu[i], g).getHeight();
			g.drawString(menu[i], (Panel.WIDTH - stringLength) / 2, menuStartPos + (130 - (buttonHeight - stringHeight))* (i));
		}
		g.drawString(Integer.toString(loopnumber), 100, 100);
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
			gsm.setState(6);
		}
		if (ohjeet.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(3);
		}
		if (asetukset.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(4);
		}
		if (lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.quit();
		}
		if (loadN.contains(Panel.mouseX, Panel.mouseY)) {
			// tŠhŠn skripta, joka lataa pelin
		}
	}

	public void mouseReleased(MouseEvent k) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		if (yksinP.contains(Panel.mouseX, Panel.mouseY) || kaksinP.contains(Panel.mouseX, Panel.mouseY) || ohjeet.contains(Panel.mouseX, Panel.mouseY) || asetukset.contains(Panel.mouseX, Panel.mouseY) || lopeta.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}
	}
}
