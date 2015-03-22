import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/* Luokka vastaa oikean pelitilan yllapidosta, seka
 * listaa kaikki saatavalla olevat pelitilat states attribuutissa.
 * Pelitilaan viitataan kokonaislukumuuttuja attribuutein,
 * missa kokonaisluku on tilan paikka ArrayList oliossa.
 */
public class GameStateManager {
	
	public static ArrayList<GameState> states;
	private int currentState;
	public static int LASTSTATE;
	
	/* maaritetaan attribuutit joilla
	 * oikeaan pelitilaan viitataan
	 */
	public static final int MENU = 0;
	public static final int YKSINPELI = 2;
	public static final int KAKSINPELI = 1;
	public static final int OHJEET = 3;
	public static final int ASETUKSET = 4;
	public static final int PAUSE = 5;
	public static final int HUIPPU = 6;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		states.add(new MenuState(this));
		states.add(new GameOn(this));
		states.add(new GameAI(this));
		states.add(new OhjeetState(this));
		states.add(new AsetuksetState(this));
		states.add(new PauseState(this));
		states.add(new HuipputuloksetState(this));
		states.get(currentState).init();
		states.get(currentState).update();
	}
	
	public void setState(int state) {
		LASTSTATE = currentState;
		Panel.cursorState = Cursor.DEFAULT_CURSOR;
		currentState = state;
		states.get(state).init();
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	public GameState getState(int n) {
		return GameStateManager.states.get(n);
	}
	
	/* Maaritetaan metodit, jotka hakevat tarvittavat metodit 
	 * oikeasta pelitilasta ja valittavat tarvittaessa parametrit.
	 */
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(Graphics2D g) {
		states.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		states.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		states.get(currentState).keyReleased(k);
	}
	
	public void mousePressed(MouseEvent e) {
		states.get(currentState).mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		states.get(currentState).mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		states.get(currentState).mouseMoved(e);
	}
	
}
