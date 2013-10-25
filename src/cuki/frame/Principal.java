package cuki.frame;

import cuki.frame.Mostrador;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel panel = null;

	public static void main(String[] args) {
		Principal test = new Principal();
		final Principal frame = new Principal();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		/*
		 * for (int i = 0; i <= 2 * 360; i += 6) { synchronized (test) { try {
		 * test.wait(250); } catch (InterruptedException e) {
		 * e.printStackTrace(); } // panel.seAngulo(i); panel.repaint(); } }
		 */
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\cuki\\Desktop\\logo-K.png"));
		setTitle("Pivo");
		setSize(503, 287);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new Oval();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel, BorderLayout.CENTER);

		JPanel panel_1 = new Mostrador();
		getContentPane().add(panel_1, BorderLayout.EAST);
	}
}
