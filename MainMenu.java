import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu implements GameState {

	private static final JPanel P1 = new JPanel();
	private boolean showMenu = false;
	
	public void buildMenu() {
		P1.setPreferredSize(new Dimension(400, 40));
		P1.setBackground(Color.RED);
		JButton b1 = new JButton("Yksinpeli");
		JButton b2 = new JButton("Moninpeli");
		JButton b3 = new JButton("Lataa peli");
		JButton b4 = new JButton("Huipputulokset");
		JButton b5 = new JButton("Lopeta peli");

		b1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setState(new Pong(1));
			}
		});
		b2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		b5.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		P1.add(b1);
		P1.add(b2);
		P1.add(b3);
		P1.add(b4);
		P1.add(b5);	
	}

	public void render() {
		if (!showMenu) {
			this.buildMenu();
			Main.IKKUNA.add(P1);	
			Main.IKKUNA.pack();
			showMenu = true;
		}
	}
}
