package cuki.frame;

public class Pivo {

	private Principal frame;

	public static void main(String[] args) {
		Pivo window = new Pivo();
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

	public Pivo() {
		initialize();
	}

	private void initialize() {
		frame = new Principal();
	}

}
