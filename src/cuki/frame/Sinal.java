package cuki.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class Sinal extends JApplet {

	private static int size = 40;
	private Color cor = Color.RED;

	public Sinal() {
		setPreferredSize(new Dimension(size, size));
		setBackground(Color.WHITE);
	}

	public Sinal(Color cor) {
		this();
		this.cor = cor;
	}

	@Override
	public void init() {
		super.init();
		setBackground(Color.WHITE);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D shape = new Ellipse2D.Double(0, 0, size, size);

		g2.setColor(this.cor);
		g2.fill(shape);
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
}
