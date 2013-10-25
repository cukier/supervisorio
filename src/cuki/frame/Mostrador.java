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

	/**
	 * Create the panel.
	 */
	public Mostrador(String msg) {

		JLabel lblNewLabel = new JLabel("Contador: " + msg);
		add(lblNewLabel);

	}

	public Border getThisBorder() {
		return getBorder();
	}

	public void setThisBorder(Border border) {
		setBorder(border);
	}
}
