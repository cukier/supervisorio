package cuki.frame;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TesteJanela extends JFrame {

	private Oval oval = null;
	private Mostrador most = null;
	private AnguloAtual ang = null;

	public static void main(String[] args) {

		TesteJanela frame = new TesteJanela();
		frame.setVisible(true);

		for (int cont = 0; cont <= 359; cont++) {
			synchronized (frame) {
				try {
					frame.wait(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			frame.setAngulo(cont);
			frame.repaint();
		}

	}

	public TesteJanela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MigLayout layout = new MigLayout();

		setLayout(layout);

		oval = new Oval();
		add(oval);

		most = new Mostrador();
		add(most, "wrap, span 1 2");

		ang = new AnguloAtual();
		add(ang);

		pack();

	}

	public void setAngulo(int angulo) {
		oval.setAngulo(angulo);
		ang.setAngulo(angulo);
	}

}
