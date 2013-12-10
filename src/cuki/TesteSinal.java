package cuki;

import java.awt.Color;

import javax.swing.JFrame;

import cuki.frame.Sinal;

@SuppressWarnings("serial")
public class TesteSinal extends JFrame {

	Sinal sinal = new Sinal(Color.BLUE);

	public TesteSinal() {
		getContentPane().add(sinal);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}

	public void setCor(Color cor) {
		sinal.setCor(cor);
		sinal.repaint();
	}

	public static void main(String[] args) throws InterruptedException {

		TesteSinal janela = new TesteSinal();
		Color cor = Color.RED;

		int cont = 0;
		while (true) {
			switch (cont++) {
			case 0:
				cor = Color.GREEN;
				break;
			case 1:
				cor = Color.YELLOW;
				break;
			case 2:
				cor = Color.RED;

				cont = 0;
				break;
			default:
				cont = 0;
			}
			System.out.println(cor);
			janela.setCor(cor);
//			janela.repaint();
			Thread.sleep(1000);
		}
	}

}
