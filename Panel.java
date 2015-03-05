package Pong2;

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

	public Panel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
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
		gsm.mouseMoved(e);
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
