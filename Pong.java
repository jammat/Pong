import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.io.File;

public class Pong extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;

	JFrame ikkuna;
	public static Maila pelaaja;
	public static Maila pelaaja2;
	public static Painallukset avain;
	public static Pallo pallo;


	public final int LEVEYS = 1280;
	public final int KORKEUS = LEVEYS / 16 * 9;
	public final Dimension ruudunKoko = new Dimension(LEVEYS, KORKEUS);
	static boolean kaynnissa = false;
	Image tausta;


	public Pong(int n, JFrame ikkuna){
		this.ikkuna = ikkuna;
		setPreferredSize(ruudunKoko);
		ikkuna.add(this, BorderLayout.CENTER);
		ikkuna.pack();
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.setVisible(true);
		ikkuna.setResizable(false);
		ikkuna.setTitle("Balamaui - Pong");
		ikkuna.setBackground(Color.BLACK);
		ikkuna.setLocationRelativeTo(null);
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
		pallo = new Pallo(getWidth() / 2, getHeight() / 2);
	}

	public void run() {
		while(kaynnissa){
			liiku();
			render();
			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	public synchronized void start() {
		kaynnissa = true;
		new Thread(this).start();
	} // End start method

	public static synchronized void stop() {
		kaynnissa = false;
		System.exit(0);
	}

	public void liiku(){
		pelaaja.liiku(this);
		pelaaja2.liiku(this);
		pallo.liiku(this);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();	
		g.drawImage(tausta, 0, 0, getWidth(), getHeight(), null);	
		pelaaja.render(g);
		pelaaja2.render(g);
		pallo.render(g);
		g.dispose();
		bs.show();

	}
	
}
