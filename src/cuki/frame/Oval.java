package cuki.frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oval extends JPanel {

	private int angulo = 0;
	private int larg = 240;
	private int alt = 240;
	private int diam = 200;

	public Oval() {
		setPreferredSize(new Dimension(larg, alt));
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D aro = new Ellipse2D.Double((larg - diam) / 2,
				(alt - diam) / 2, diam, diam);

		Point origem = new Point();
		Point dest = new Point();

		int x = (int) ((larg + diam * Math.sin(angulo * Math.PI / 180)) / 2);
		int y = (int) ((alt - diam * Math.cos(angulo * Math.PI / 180)) / 2);

		origem.setLocation(aro.getCenterX(), aro.getCenterY());
		dest.setLocation(x, y);
		Line2D linha = new Line2D.Double(origem, dest);

		g2.draw(aro);
		g2.draw(linha);
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}
}
