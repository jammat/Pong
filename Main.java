import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame implements Runnable {
	static final long serialVersionUID = 1L;
	public static JFrame IKKUNA = new JFrame();;
	static GameState currentState = null;
	private boolean kaynnissa = true;
	
	public final int LEVEYS = 1280;
	public final int KORKEUS = LEVEYS / 16 * 9;
	public final Dimension ruudunKoko = new Dimension(LEVEYS, KORKEUS);
	
	
	public Main() {
		IKKUNA.setPreferredSize(ruudunKoko);
		IKKUNA.setResizable(false);
		IKKUNA.setTitle("Bala Maui - Pong");
		IKKUNA.setBackground(Color.BLACK);
		IKKUNA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IKKUNA.pack();
		IKKUNA.setLocationRelativeTo(null);	
		IKKUNA.setVisible(true);
	}
	
	public static void setState(GameState state){
	    currentState = state;
	}
	
	public void run() {
		while(kaynnissa){
			currentState.render();
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Main peli = new Main();
		Main.setState(new MainMenu());
		(new Thread(peli)).start();
		peli.run();
	}
}
