import javax.swing.JFrame;

/* Luokkaa kaytetaan pelin kutsumiseen. */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Bala Maui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		}
}
