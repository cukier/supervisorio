package cuki.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Oval extends JPanel {

	private int angulo;

	public Oval() {

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int larg = getSize().width;
		int alt = getSize().height;
		int diam = 0;

		if (larg < alt)
			diam = larg;
		else
			diam = alt;

		Ellipse2D aro = new Ellipse2D.Double(0, 0, diam, diam);

		g2.draw(aro);
	}

}
