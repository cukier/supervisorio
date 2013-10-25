package cuki.frame;

import javax.swing.JFrame;

public class Pivo {

	private Principal frame;

	public static void main(String[] args) {
		Pivo window = new Pivo(0);
		window.frame.setVisible(true);
		System.out.println(window.frame.getSize());
		for (int cont = 0; cont < 10; cont++) {
			synchronized (window) {
				try {
					window.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			window.frame.setPanel(cont);
			window.frame.repaint();
		}
	}

	public Pivo(int init) {
		initialize(init);
	}

	private void initialize(int init) {
		frame = new Principal(init);
		frame.setBounds(100, 100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
