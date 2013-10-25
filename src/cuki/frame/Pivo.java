package cuki.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Pivo {

	private Principal frame;

	public static void main(String[] args) {
		Pivo window = new Pivo();
		window.frame.setVisible(true);
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
			System.out.println(cont);
		}
	}

	public Pivo() {
		initialize();
	}

	private void initialize() {
		frame = new Principal();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
