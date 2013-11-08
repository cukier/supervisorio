package cuki.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oval extends JPanel {

	private int angulo = 0;
	private int larg = 240;
	private int alt = 240;
	private int diam = 200;

	public Oval() {
		setBorder(BorderFactory.createTitledBorder("Pivo nr 1"));
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(larg, alt));
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D aro = new Ellipse2D.Double((larg - diam) / 2,
				(alt - diam) / 2, diam, diam);

		Point origem = new Point();
		Point dest = new Point();

		// int x = (int) ((larg + diam * Math.sin(angulo * Math.PI / 180)) / 2);
		// int y = (int) ((alt - diam * Math.cos(angulo * Math.PI / 180)) / 2);
		int x = getDistX(larg, diam);
		int y = getDistY(alt, diam);

		origem.setLocation(aro.getCenterX(), aro.getCenterY());
		dest.setLocation(x, y);
		Line2D linha = new Line2D.Double(origem, dest);

		Arc2D arco = new Arc2D.Double(aro.getBounds2D(), 360 - this.angulo, 90,
				Arc2D.PIE);

		g2.setPaint(Color.GREEN);
		g2.fill(aro);
		g2.draw(aro);
		g2.setPaint(Color.GRAY);
		g2.fill(arco);
		g2.draw(arco);
		g2.setPaint(Color.RED);
		g2.draw(linha);

	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	private int getDistX(int larg, int diam) {
		return (int) ((larg + diam * Math.sin(angulo * Math.PI / 180)) / 2);
	}

	private int getDistY(int alt, int diam) {
		return (int) ((alt - diam * Math.cos(angulo * Math.PI / 180)) / 2);
	}
}
