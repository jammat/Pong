import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PauseState extends GameState {
	
	private Rectangle pauseContainer, jatka, menu, saveG, lopeta;
	// 0 == default, 1 == jatka, 2 == menu, 3 == lopeta, 4 == saveG
	private int hover;
	private boolean tallennettu;
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
		menu = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 260, btnWidth, btnHeight);
		saveG = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 360, btnWidth, btnHeight);
		lopeta = new Rectangle((Panel.WIDTH) / 2 - (btnWidth / 2), 460, btnWidth, btnHeight);
		otsikko = new Font("Arial", Font.BOLD, 30);
		nappain = new Font("Arial", Font.BOLD, 30);
	}

	public void init() {
		tallennettu = false;
	}

	public void update() {}

	public void draw(Graphics2D g) {
		// varitetaan musta tausta
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Panel.WIDTH, Panel.HEIGHT);
		
		// harmaa tausta
		g.setColor(Color.GRAY);
		g.fillRect((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width + 1, height + 1);
		g.draw(pauseContainer);
		
		g.setColor(Color.WHITE);
		// varita jatka-nappi
		if (hover == 1)
			g.setColor(Color.RED);
		g.fillRect((int)jatka.getX(), (int)jatka.getY(), (int)jatka.getWidth(), (int)jatka.getHeight());
		g.setColor(Color.WHITE);
		// varita menu-nappi
		if (hover == 2 )
			g.setColor(Color.RED);
		g.fillRect((int)menu.getX(), (int)menu.getY(), (int)menu.getWidth(), (int)menu.getHeight());
		g.setColor(Color.WHITE);
		// varita lopeta-nappi
		if (hover == 3)
			g.setColor(Color.RED);
		g.fillRect((int)lopeta.getX(), (int)lopeta.getY(), (int)lopeta.getWidth(), (int)lopeta.getHeight());
		g.setColor(Color.WHITE);
		// varita lataa peli -nappi
		if (hover == 4 )
			g.setColor(Color.RED);
		g.fillRect((int)saveG.getX(), (int)saveG.getY(), (int)saveG.getWidth(), (int)saveG.getHeight());
		g.setColor(Color.WHITE);
		
		// piiretaan napit
		g.draw(jatka);
		g.draw(menu);
		g.draw(saveG);
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
		g.drawString("Tallenna peli", (int)saveG.getX() + 108, (int)saveG.getY() + 50);
		g.drawString("Lopeta", (int)lopeta.getX() + 150, (int)lopeta.getY() + 50);
		
		/* piirretaan teksti, joka osoittaa
		 * tallennuksen onnistuneeksi
		 */
		if (tallennettu) {
			g.setColor(Color.WHITE);
			g.drawString("Peli tallennettu", 525, 600);
		}
	}
	
	// Metodia ktusutaan, kun halutaan tallentaa tulos huipputuloksiin
	public void tallennaHuipputuloksiin() {
		if (GameStateManager.LASTSTATE == 2) {
			Panel.hm.addScore(((GameOn)GameStateManager.states.get(2)).getMaila1().getNimi(), ((GameOn)GameStateManager.states.get(2)).getMaila1().getPisteet());
		}
		if (GameStateManager.LASTSTATE == 1) {
			Panel.hm.addScore("Pelaajan 1 nimi", ((GameOn)GameStateManager.states.get(1)).getMaila1().getPisteet());
			Panel.hm.addScore("Pelaajan 2 nimi", ((GameOn)GameStateManager.states.get(1)).getMaila2().getPisteet());
		}
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mousePressed(MouseEvent k) {
		if (jatka.contains(Panel.mouseX, Panel.mouseY)){
			gsm.setState(GameStateManager.LASTSTATE);
		}
		if (menu.contains(Panel.mouseX, Panel.mouseY)){
			/* Kun poistutaan Menuun, niin pelin tila menetetaan (ellei 
			 * tallennettu), joten tallennetaan huipputulokset tassakin.
			 */
			tallennaHuipputuloksiin();
			gsm.setState(GameStateManager.MENU);
		}
		
		if (lopeta.contains(Panel.mouseX, Panel.mouseY)){
			tallennaHuipputuloksiin();
			Panel.quit();
		}	
		
		// tallennetaan pelin tila
		if (saveG.contains(Panel.mouseX, Panel.mouseY)){
			File savegTiedosto = new File(Panel.SAVEPATH);
			try {
				savegTiedosto.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Properties savegame = new Properties();
			GameState game = gsm.getState(GameStateManager.LASTSTATE);
			try {
				// valmistellaan tiedosto kirjoitettavaksi
				savegame.load(new FileInputStream(savegTiedosto));
				FileOutputStream fos = new FileOutputStream(savegTiedosto);
				// tallennetaan tarvittavat muuttujat
				if (game instanceof GameOn) {
					GameOn gameOn = (GameOn) game;
					// yksinpeli vai kaksinpeli
					if (gameOn instanceof GameAI) {
						savegame.put("pelitila", Integer.toString(GameStateManager.YKSINPELI));
					} else {
						savegame.put("pelitila", Integer.toString(GameStateManager.KAKSINPELI));
					}
					// pelaaja1
					Maila pelaaja1 = gameOn.getMaila1();
					savegame.put("pelaaja1Pisteet", Integer.toString(pelaaja1.getPisteet()));
					savegame.put("pelaaja1Nimi", pelaaja1.getNimi());
					savegame.put("pelaaja1X", Integer.toString(pelaaja1.getX()));
					savegame.put("pelaaja1Y", Integer.toString(pelaaja1.getY()));
					// pelaaja2
					Maila pelaaja2 = gameOn.getMaila2();
					savegame.put("pelaaja2Pisteet", Integer.toString(pelaaja2.getPisteet()));
					savegame.put("pelaaja2Nimi", pelaaja2.getNimi());
					savegame.put("pelaaja2X", Integer.toString(pelaaja2.getX()));
					savegame.put("pelaaja2Y", Integer.toString(pelaaja2.getY()));
					// pallo
					Pallo pallo = gameOn.getPallo();
					savegame.put("palloX", Integer.toString(pallo.getX()));
					savegame.put("palloY", Integer.toString(pallo.getY()));
					
					tallennettu = true;
				}
				savegame.store(fos, "pelin tila");
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	public void mouseReleased(MouseEvent k) {}

	public void mouseMoved(MouseEvent k) {
		if (jatka.contains(Panel.mouseX, Panel.mouseY) || menu.contains(Panel.mouseX, Panel.mouseY) || lopeta.contains(Panel.mouseX, Panel.mouseY) || saveG.contains(Panel.mouseX, Panel.mouseY)) {
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
		} else if (saveG.contains(Panel.mouseX, Panel.mouseY)){
			hover = 4;
		} else {
			hover = 0;
		}
	}

}
