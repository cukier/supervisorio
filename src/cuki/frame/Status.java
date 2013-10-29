package cuki.frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Status extends JFrame {

	private Oval oval = null;
	private Mostrador most = null;
	private AnguloAtual ang = null;
	private Boolean sentido = true;

	public Status() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Teste Supervisorio");
		MigLayout layout = new MigLayout();

		getContentPane().setLayout(layout);

		oval = new Oval();
		getContentPane().add(oval, "grow");

		most = new Mostrador();
		getContentPane().add(most, "spany 2,grow,wrap");

		ang = new AnguloAtual();
		getContentPane().add(ang, "grow");
		ang.getBto().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sentido = !sentido;
				ang.setSentido(sentido);
			}
		});

		pack();

	}

	public void setAngulo(int angulo) {
		oval.setAngulo(angulo);
		ang.setAngulo(angulo);
	}

	public void setSentido(Boolean sentido) {
		this.sentido = sentido;
	}

}
