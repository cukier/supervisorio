package cuki.frame;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AnguloAtual extends JPanel {

	private int larg = 240;
	private int alt = 60;
	String angulo = "";
	private JLabel label = null;

	public AnguloAtual() {
		setPreferredSize(new Dimension(larg, alt));

		label = new JLabel(angulo);
		label.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(label);
	}

	public void setAngulo(int angulo) {
		label.setText("Pos: " + String.valueOf(angulo) + "°");
	}
}
