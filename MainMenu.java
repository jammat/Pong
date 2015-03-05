import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu {

	public JFrame ikkuna;
	private static final JPanel P1 = new JPanel();
	public boolean MENU = false;
	public boolean menuState = true;

	public final int LEVEYS = 1280;
	public final int KORKEUS = LEVEYS / 16 * 9;
	public final Dimension ruudunKoko = new Dimension(LEVEYS, KORKEUS);


	public MainMenu(JFrame akkuna) {
		this.ikkuna = akkuna;
		ikkuna.setPreferredSize(ruudunKoko);
		ikkuna.setResizable(false);
		ikkuna.setTitle("Bala Maui - Pong");
		ikkuna.setBackground(Color.BLACK);
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.pack();
		ikkuna.setLocationRelativeTo(null);	
		ikkuna.setVisible(true);
	}
	
	public void buildMenu() {
		final MainMenu menu = this;
		P1.setPreferredSize(new Dimension(400, 40));
		P1.setBackground(Color.RED);
		JButton b1 = new JButton("Yksinpeli");
		JButton b2 = new JButton("Moninpeli");
		JButton b3 = new JButton("Lataa peli");
		JButton b4 = new JButton("Huipputulokset");
		JButton b5 = new JButton("Ohjeet");
		JButton b6 = new JButton("Lopeta peli");
		
		b1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.hideMenu();
				Pong peli = new Pong(1, menu);
				peli.start();
			}
		});
		b2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.hideMenu();
				Pong peli = new Pong(0, menu);
				peli.start();
			}
		});
		b5.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Yksinpelissä vasemman puolen mailaa ohjataan W- ja S-näppäimillä.\n"
						+ "Moninpelissä vasenta mailaa ohjataan kuten yksinpelissä, oikeaa mailaa nuolinäppäimillä.\n\n"
						+ "Pelin saa tauotettua P-näppäimestä.", "Bala Maui - Pong - Ohjeet", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		b6.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		P1.add(b1);
		P1.add(b2);
		P1.add(b3);
		P1.add(b4);
		P1.add(b5);
		P1.add(b6);
		
		this.MENU = true;
	}
	
	public void startMenu() {
		if (!(this.MENU)) {
			this.buildMenu();
		}
	}
	
	public void showMenu() {
		this.ikkuna.add(P1);	
		this.ikkuna.pack();
	}
	
	public void hideMenu() {
		this.ikkuna.remove(P1);	
		this.ikkuna.pack();
	}

	public static void main(String[] args) {
		JFrame ikkuna = new JFrame();
		MainMenu spela = new MainMenu(ikkuna);
		spela.startMenu();
		spela.showMenu();
	}
}
