package cuki.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Pivo {

	private Principal frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pivo window = new Pivo();
					window.frame.setVisible(true);
					for (int cont = 0; cont < 10; cont++) {
						synchronized (window) {
							window.wait(1000);
						}
						window.frame.setPanel(cont);
						window.frame.repaint();
						System.out.println(cont);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pivo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new Principal();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
