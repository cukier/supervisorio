package cuki.frame;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AnguloAtual extends JPanel {

	private int larg = 240;
	private int alt = 60;
	private JLabel lblAngulo;
	private JButton BtoSentido;

	public AnguloAtual() {
		setPreferredSize(new Dimension(larg, alt));
		setLayout(new MigLayout("", "[46px,grow][95px,grow]", "[27px,grow]"));

		lblAngulo = new JLabel("Pos: ");
		lblAngulo.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(lblAngulo, "cell 0 0,alignx center,aligny center");

		BtoSentido = new JButton();
		BtoSentido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		// BtoSentido.setText("Sentido Horário");
		BtoSentido.setIcon(new ImageIcon(Status.class
				.getResource("/icones/horario-20x18.png")));
		add(BtoSentido, "cell 1 0,alignx center,aligny center");

	}

	public void setAngulo(int angulo) {
		lblAngulo.setText("Pos: " + String.valueOf(angulo) + "°");
	}

	public void setSentido(Boolean sentido) {
		if (sentido) {
			// BtoSentido.setText("Sentido Horário");
			BtoSentido.setIcon(new ImageIcon(Status.class
					.getResource("/icones/horario-20x18.png")));
		} else {
			// BtoSentido.setText("Sentido Anti-Horário");
			BtoSentido.setIcon(new ImageIcon(Status.class
					.getResource("/icones/anti-horario-20x18.png")));
		}
	}

	public JButton getBto() {
		return this.BtoSentido;
	}
}
