package cuki.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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
		Point origem = new Point();
		Point dest = new Point();

		if (larg < alt)
			diam = larg;
		else
			diam = alt;

		int x = (int) (diam / 2 * (Math.cos(angulo * Math.PI / 180) + 1));
		int y = (int) (diam / 2 * (Math.sin(angulo * Math.PI / 180) + 1));

		Ellipse2D aro = new Ellipse2D.Double(0, 0, diam, diam);
		origem.setLocation(aro.getCenterX(), aro.getCenterY());
		dest.setLocation(x, y);
		Line2D linha = new Line2D.Double(origem, dest);

		g2.draw(aro);
		g2.draw(linha);
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo * 10;
	}
}
