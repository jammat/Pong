import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/* Pelitilat perivat GameState luokan ja toteuttavat 
 * tarvittavat luokat.
 */
public abstract class GameState {

	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void mousePressed(MouseEvent k);
	public abstract void mouseReleased(MouseEvent k);
	public abstract void mouseMoved(MouseEvent k);
	
}
