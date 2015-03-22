import java.awt.Cursor;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/* GameAI perii GameOn-luokan, joka perii abstraktin
 * GameState-luokan. GameAI luokan oliossa maila2
 * korvataan AImaila oliolla.
 */
public class GameAI extends GameOn{

	public GameAI(GameStateManager gsm) {
		super(gsm);
		maila2 = new AIMaila(Panel.WIDTH - x, y, width, height);
	}

	/* AI:ta vastaan pelattaessa ei tarvitse syottaa kuin oma nimensa
	 */
	@Override
	public void init() {
		resetoiPeli();
		Panel.thread.suspend();
		while (maila1.getNimi() == null){
			String s = (String)JOptionPane.showInputDialog(
					new JFrame(),
	                "Kirjoita nimimerkki",
	                "Keksi itsellesi nimimerkki",
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
		maila2.setNimi("Unbeatable death AI");
		Panel.thread.resume();
		// kutsutaan vaikeusasteen asettavaa metodia
		asetaVaikeus();
	}
	
	@Override
	public void keyPressed(int k) {
		int nappi = k;

		if(nappi == KeyEvent.VK_P){
			gsm.setState(5);
		}
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos = true;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas = true;
		}
		
	}
	
	@Override
	public void keyReleased(int k) {
		int nappi = k;
		
		if(nappi == KeyEvent.VK_W){
			maila1.ylos=false;
		}

		if(nappi == KeyEvent.VK_S){
			maila1.alas = false;
		}

	}

}
