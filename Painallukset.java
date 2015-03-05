import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Painallukset implements KeyListener {
	private Pong peli;

	public Painallukset(Pong peli) {
		peli.addKeyListener(this);
		this.peli = peli;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int nappi = e.getKeyCode();
		
		if(nappi == KeyEvent.VK_P){
			if (Pong.kaynnissa) {
				Pong.stop();
			} else {
				peli.start();
			}
		}

		if(nappi == KeyEvent.VK_W){
			Pong.pelaaja.ylos=true;
		}

		if(nappi == KeyEvent.VK_S){
			Pong.pelaaja.alas=true;
		}
		
		if (!(Pong.pelaaja2 instanceof AIMaila)) {
			if(nappi == KeyEvent.VK_UP){
				Pong.pelaaja2.ylos=true;
			}
	
			if(nappi == KeyEvent.VK_DOWN){
				Pong.pelaaja2.alas=true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int nappi = e.getKeyCode();
		
		if(nappi == KeyEvent.VK_W){
			Pong.pelaaja.ylos=false;
		}

		if(nappi == KeyEvent.VK_S){
			Pong.pelaaja.alas=false;
		}

		if (!(Pong.pelaaja2 instanceof AIMaila)) {
			if(nappi == KeyEvent.VK_UP){
				Pong.pelaaja2.ylos=false;
			}
	
			if(nappi == KeyEvent.VK_DOWN){
				Pong.pelaaja2.alas=false;
			}
		}

	}

}
