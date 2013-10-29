package cuki.frame;

import javax.swing.UIManager;

public class Pivo {

	private Status frame;

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Pivo window = new Pivo();
		window.frame.setVisible(true);
		System.out.println(window.frame.getSize());
		for (int cont = 0; cont <= 360; cont++) {
			synchronized (window) {
				try {
					window.wait(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			window.frame.setAngulo(cont);
			window.frame.repaint();
		}
	}

	public Pivo() {
		initialize();
	}

	private void initialize() {
		frame = new Status();
	}

}
