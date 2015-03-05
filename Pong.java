import java.awt.*;
import java.awt.image.BufferStrategy;

import javax.swing.*;

import java.io.File;

interface GameState{	
    public void render();
}

public class Pong extends JPanel implements GameState {
	static final long serialVersionUID = 1L;

	Image tausta;
	public MainMenu menu;
	static boolean kaynnissa = false;
	public static Maila pelaaja;
	public static Maila pelaaja2;
	public static Pallo pallo;
	public static Painallukset avain;

	public Pong(int n){
		Main.IKKUNA.add(this, BorderLayout.CENTER);
		Main.IKKUNA.pack();
		String basePath = new File("").getAbsolutePath();
		ImageIcon i = new ImageIcon(basePath + "/bin/kentta.jpg");
		tausta = i.getImage();
		avain = new Painallukset(this);
		pelaaja = new Maila(165, 265);
		if ( n == 0) {
			pelaaja2 = new Maila(1105, 265);
		} else if (n == 1) {
			pelaaja2 = new AIMaila(1105, 265);
		}
		pallo = new Pallo(Main.IKKUNA.getWidth() / 2, Main.IKKUNA.getHeight() / 2);
	    Action a = new AbstractAction() {
	        private static final long serialVersionUID = 1L;
	        @Override public void actionPerformed(ActionEvent e) {

	        }
	    };
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"),
				Main.setState(new MainMenu()));
	}
	
	public synchronized void start() {
		kaynnissa = true;
	}

	public static synchronized void stop() {
		kaynnissa = false;
	}

	public void liiku(){
		pelaaja.liiku(this);
		pelaaja2.liiku(this);
		pallo.liiku(this);
	}
	
	public void render() {
		liiku();
        BufferStrategy bs = Main.IKKUNA.getBufferStrategy();
		if (bs == null) {
			Main.IKKUNA.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();	
		g.drawImage(tausta, 0, 0, Main.IKKUNA.getWidth(), Main.IKKUNA.getHeight(), null);	
		pelaaja.render(g);
		pelaaja2.render(g);
		pallo.render(g);
		g.dispose();
		bs.show();
	}
	
}
