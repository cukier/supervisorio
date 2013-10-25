package cuki.bin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cuki.frame.Mostrador;
import cuki.frame.Oval;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.Component;

public class Pivo {

	private static JFrame frame;
	private static Oval pizza = null;
	private static Mostrador mostrador = null;
	private static Pivo test = new Pivo();

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pivo window = new Pivo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// for (int cont = 0; cont < 10; cont++) {
		// synchronized (test) {
		// try {
		// test.wait(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// mostrador.setSetorAtual(cont);
		// System.out.println(cont);
		// frame.repaint();
		// }
	}

	public Pivo() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		pizza = new Oval();
		frame.getContentPane().add(pizza);

		mostrador = new Mostrador();
		frame.getContentPane().add(mostrador);
	}
}
