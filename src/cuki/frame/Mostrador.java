package cuki.frame;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

public class Mostrador extends JPanel {

	private JLabel lblNewLabel = null;

	public Mostrador() {

		lblNewLabel = new JLabel("Contador: ");
		add(lblNewLabel);

	}

	public Border getThisBorder() {
		return getBorder();
	}

	public void setThisBorder(Border border) {
		setBorder(border);
	}

	public void setAngulo(int angulo) {
		lblNewLabel.setText("Contador: " + String.valueOf(angulo));
	}
}
