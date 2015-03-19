import java.awt.event.KeyEvent;

/* GameAI perii GameOn-luokan, joka perii abstraktin
 * GameState-luokan. GameAI luokan oliossa maila2
 * korvataan AImaila oliolla.
 */
public class GameAI extends GameOn{

	public GameAI(GameStateManager gsm) {
		super(gsm);
		maila2 = new AIMaila(Panel.WIDTH - x, y, width, height);
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
