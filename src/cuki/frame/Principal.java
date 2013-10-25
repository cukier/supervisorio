package cuki.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Oval panel;
	private Mostrador panel_1;

	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

		panel = new Oval();
		contentPane.add(panel, "cell 0 0,grow");

		panel_1 = new Mostrador();
		contentPane.add(panel_1, "cell 1 0,grow");
	}

	public Principal(int init) {
		this();
		setPanel(init);
	}

	public void setPanel(int angulo) {
		panel.setAngulo(angulo);
		panel_1.setAngulo(angulo);
	}

}
