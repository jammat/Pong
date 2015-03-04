import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu {

	private JFrame ikkuna;
	private static final JPanel P1 = new JPanel();

	public final int LEVEYS = 1280;
	public final int KORKEUS = LEVEYS / 16 * 9;
	public final Dimension ruudunKoko = new Dimension(LEVEYS, KORKEUS);


	public MainMenu(JFrame akkuna) {
		this.ikkuna = akkuna;
		
		//p1.setLayout(new BoxLayout(ikkuna, 1280));
		P1.setPreferredSize(new Dimension(400, 40));
		P1.setBackground(Color.RED);
		JButton b1 = new JButton("Yksinpeli");
		JButton b2 = new JButton("Moninpeli");
		JButton b3 = new JButton("Lataa peli");
		JButton b4 = new JButton("Huipputulokset");

		b1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ikkuna.remove(P1);
				Pong peli = new Pong(1, ikkuna);
				peli.start();
			}
		});
		b2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ikkuna.remove(P1);
				Pong peli = new Pong(0, ikkuna);
				peli.start();
			}
		});
		
		P1.add(b1);
		P1.add(b2);
		P1.add(b3);
		P1.add(b4);
		ikkuna.add(P1);

		ikkuna.setPreferredSize(ruudunKoko);
		ikkuna.setResizable(false);
		ikkuna.setTitle("Balamaui - Pong");
		ikkuna.setBackground(Color.BLACK);
		ikkuna.pack();
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.setVisible(true);
		ikkuna.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		JFrame ikkuna = new JFrame();
		MainMenu spela = new MainMenu(ikkuna);
	}
}
