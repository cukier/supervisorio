package cuki.frame;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	Oval panel = null;
	Mostrador panel_1 = null;

	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		panel = new Oval();
		panel.setBounds(10, 10, panel.getBounds().width,
				panel.getBounds().height);
		getContentPane().add(panel);

		panel_1 = new Mostrador();
		panel_1.setBounds(178, 91, 10, 10);
		getContentPane().add(panel_1);

	}

	public void setPanel(int angulo) {
		panel.setAngulo(angulo);
		// panel_1.setAngulo(angulo);
	}
}
