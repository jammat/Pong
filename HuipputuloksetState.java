import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class HuipputuloksetState extends GameState {
	private Rectangle huipputuloksetContainer, takaisin;
	private int width, height;
	private Font otsikko, leipateksti, nappain;
	private boolean yksinP, kaksinP; 
	
	public HuipputuloksetState(GameStateManager gsm) {
		this.gsm = gsm;
		height = Panel.HEIGHT - 100;
		width = 800;
		huipputuloksetContainer = new Rectangle((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width, height);
		takaisin = new Rectangle((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		otsikko = new Font("Arial", Font.BOLD, 30);
		leipateksti = new Font("Arial", Font.PLAIN, 16);
		nappain = new Font("Arial", Font.BOLD, 20);
	}

	public void init() {
		//tarkistetaan, etta onko yksinpelin huipputuloskia olemassa
		File huippuYTiedosto = new File(HSManager.HUIPPUTULOKSET_YKSINPELI);
		try {
			if ( huippuYTiedosto.createNewFile() || huippuYTiedosto.length() == 0) {
				yksinP = false;
			} else {
				yksinP = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// sama moninpelille
		File huippuMTiedosto = new File(HSManager.HUIPPUTULOKSET_KAKSINPELI );
		try {
			if ( huippuMTiedosto.createNewFile() || huippuMTiedosto.length() == 0) {
				kaksinP = false;
			} else {
				kaksinP = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {}

	public void draw(Graphics2D g) {
		g.draw(huipputuloksetContainer);
		g.setColor(Color.GRAY);
		g.fillRect((Panel.WIDTH - width) / 2, (Panel.HEIGHT - height) / 2, width + 1, height + 1);
		g.setColor(Color.WHITE);
		g.draw(takaisin);
		g.fillRect((Panel.WIDTH - width) / 2, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 40, 200, 40);
		g.setFont(otsikko);
		int stringLength = (int) g.getFontMetrics().getStringBounds("Huipputulokset", g).getWidth();
		int stringHeight = (int) g.getFontMetrics().getStringBounds("Huipputulokset", g).getHeight();
		g.drawString("Huipputulokset", (Panel.WIDTH - stringLength) / 2, 20 + stringHeight + (Panel.HEIGHT - height) / 2);
		g.setFont(leipateksti);
		
		g.drawString("Yksinpelin tulokset", (Panel.WIDTH - width + 80) / 2, stringHeight + 60 + (Panel.HEIGHT - height) / 2);
		g.drawString("Kaksinpelin tulokset", (Panel.WIDTH - width + 80) / 2 + 400, stringHeight + 60 + (Panel.HEIGHT - height) / 2);
		
		if (yksinP) {
			for (int i = 0; i < Panel.hm.getScoresYksinpeli().size(); i++) {
				g.drawString((i + 1) + ".\t" + Panel.hm.getScoresYksinpeli().get(i).getNaam() + ",\t\t" + Panel.hm.getScoresYksinpeli().get(i).getScore(), (Panel.WIDTH - width + 80) / 2, stringHeight + 60 + 40 + (Panel.HEIGHT - height) / 2 + stringHeight * i);
			}
		}
		
		if (kaksinP) {
			for (int i = 0; i < Panel.hm.getScoresKaksinpeli().size(); i++) {
				g.drawString((i + 1) + ".\t" + Panel.hm.getScoresKaksinpeli().get(i).getNaam() + ",\t\t" + Panel.hm.getScoresKaksinpeli().get(i).getScore(), (Panel.WIDTH - width + 80) / 2 + 400, stringHeight + 60 + 40 + (Panel.HEIGHT - height) / 2 + stringHeight * i);
			}
		}
		
		g.setFont(nappain);
		g.setColor(Color.BLACK);
		g.drawString("Takaisin", (Panel.WIDTH - width) / 2 + 60, Panel.HEIGHT - ((Panel.HEIGHT - height) / 2) - 12);
	}

	public void keyPressed(int k) {}

	public void keyReleased(int k) {}

	public void mousePressed(MouseEvent k) {
		if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			gsm.setState(0);
		}		
	}

	public void mouseReleased(MouseEvent k) {}

	public void mouseMoved(MouseEvent k) {
		if (takaisin.contains(Panel.mouseX, Panel.mouseY)) {
			Panel.cursorState = Cursor.HAND_CURSOR;
		} else {
			Panel.cursorState = Cursor.DEFAULT_CURSOR;
		}	
	}

}
