
import java.awt.EventQueue;
/*
 * Jacob Dickinson
 * Main
 * 8/28/15
 * Simulates reactions based on variables for users to solve asymptotes
 * through mathematical and visual means
 */

public class Main {
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MasterView mainFrame = new MasterView();
				mainFrame.setVisible(true);		
			}
		});
	}
}
