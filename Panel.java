

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	// kursorin paikka naytolla
	public static int mouseX, mouseY;
	
	// kursorin tila
	public static int cursorState = Cursor.DEFAULT_CURSOR;
	
	// paneelin mitat
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	// pelilooppi
	private Thread thread;
	private boolean running = false;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// piirto
	private Graphics2D g;
	public static BufferedImage image;
	
	// GameStateManager
	private GameStateManager gsm;
	
	// Asetustiedosto
	public static Properties ASETUKSET;
	
	// Asetukset
	public static int VAIKEUSASTE;

	// Polku tiedostojuureen
	public static final String BASEPATH = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	// Polku asetuksiin
	public static final String SETTINGSPATH = BASEPATH + "//settings.cfg";
	
	public Panel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		Panel.ASETUKSET = new Properties();
	}
	
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			running = true;
			addKeyListener(this);
			addMouseListener(this);
			addMouseMotionListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void init() {
		File asetuksetTiedosto = new File(SETTINGSPATH);
		try {
			if (asetuksetTiedosto.createNewFile()) {
				ASETUKSET.load(new FileInputStream(asetuksetTiedosto));
				FileOutputStream fos = new FileOutputStream(asetuksetTiedosto);
				ASETUKSET.put("difficulty", "1");
				ASETUKSET.store(fos, "Difficulty level");
				fos.close();
				VAIKEUSASTE = 1;
			} else {
				ASETUKSET.load(new FileInputStream(asetuksetTiedosto));
				VAIKEUSASTE = Integer.parseInt(Panel.ASETUKSET.getProperty("difficulty"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
	}
	
	public void update() {
		gsm.update();
		this.setCursor(new Cursor(cursorState));
	}
	
	public void draw() {
		g.clearRect(4, 4, WIDTH, HEIGHT);
		gsm.draw(g);
	}
	
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	/* Kayta tata metodia, kun suljet ohjelman, silla
	 * se tallentaa asetukset
	 */
	public static void quit() {
		File asetuksetTiedosto = new File(SETTINGSPATH);
		try {
			if (VAIKEUSASTE != Integer.parseInt(Panel.ASETUKSET.getProperty("difficulty"))) {
				ASETUKSET.load(new FileInputStream(asetuksetTiedosto));
				FileOutputStream fos = new FileOutputStream(asetuksetTiedosto);
				ASETUKSET.put("difficulty", Integer.toString(VAIKEUSASTE));
				ASETUKSET.store(fos, "difficulty");
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while (running) {
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if ( wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			} catch ( Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setVaikeusAste(int n) {
		Panel.VAIKEUSASTE = n;
	}
	
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}
	
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());
	}
	
	public void keyTyped(KeyEvent k) {
		
	}
	
	public void mouseDragged(MouseEvent e) {	
	}

	
	public void mouseMoved(MouseEvent e) {	
		mouseX = e.getX();
		mouseY = e.getY();
		try {
			gsm.mouseMoved(e);
		} catch (NullPointerException ex) {
			// ei ideaa miksi heittaa erroria
		}
	}

	
	public void mouseClicked(MouseEvent e) {	
		
	}

	
	public void mouseEntered(MouseEvent e) {	
		
	}

	
	public void mouseExited(MouseEvent e) {	
		
	}

	
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}

	
	public void mouseReleased(MouseEvent e) {	
		gsm.mouseReleased(e);
	}
}
