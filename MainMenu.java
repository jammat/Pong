import java.awt.*;
import javax.swing.*;

public class MainMenu extends Canvas {
	
	private JFrame ikkuna;
	public boolean peliKaynnissa;
	
	public final int LEVEYS = 1280;
	public final int KORKEUS = LEVEYS / 16 * 9;
	public final Dimension ruudunKoko = new Dimension(LEVEYS, KORKEUS);


	public MainMenu(JFrame ikkuna) {
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
		
		/* JMenuBar jmb = new JMenuBar();

	    JMenu jmFile = new JMenu("File");
	    JMenuItem jmiStart = new JMenuItem("Start");
	    JMenuItem jmiSave = new JMenuItem("Save");
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    jmFile.add(jmiStart);
	    jmFile.add(jmiSave);
	    jmFile.addSeparator();
	    jmFile.add(jmiExit);
	    jmb.add(jmFile);

	    JMenu jmHelp = new JMenu("Help");
	    JMenuItem jmiAbout = new JMenuItem("About");
	    jmHelp.add(jmiAbout);
	    jmb.add(jmHelp);
	    
	    jmiStart.addActionListener(this);

	    ikkuna.setJMenuBar(jmb);
	    ikkuna.setVisible(true); */
		
		JPanel b1 = new JPanel();
				
		JButton c1 = new JButton("Disable middle button");
		b1.add(c1);
	    ikkuna.add(b1);
		
		this.peliKaynnissa = false;
	}
	
	public static void main(String[] args) {
		JFrame ikkuna = new JFrame();
		MainMenu spela = new MainMenu(ikkuna);
		
		if (spela.peliKaynnissa) {
			Pong peli = new Pong(0, ikkuna);
			peli.start();
		}
	}
}
