import javax.swing.*;

public class MainMenu {
	public static void main(String[] args) {
		JFrame akkuna = new JFrame();
		Pong peli = new Pong(0, akkuna);
		peli.start();
	}
}
