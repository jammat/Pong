

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> states;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int WAVE1STATE = 1;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		states.add(new MenuState(this));
		states.add(new GameOn(this));
		states.add(new GameAI(this));
		states.add(new OhjeetState(this));
		states.add(new AsetuksetState(this));
		states.add(new PauseState(this));
		states.add(new HuipputuloksetState(this));
		states.get(currentState).update();
	}
	
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
	
	public void setState(int state) {
		currentState = state;
		states.get(state).init();
	}
	
	public int getCurrentState() {
		return currentState;
	}
	
	public GameState getState(int n) {
		return this.states.get(n);
	}
}
