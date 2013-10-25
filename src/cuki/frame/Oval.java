package cuki.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oval extends JPanel {

	private Ellipse2D aro = null;
	private int diam, gap = 15;

	public Oval() {
		aro = new Ellipse2D.Double();
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		if (getHeight() < getWidth())
			diam = getHeight();
		else
			diam = getWidth();

		aro.setFrame((getWidth() - diam) / 2, (getHeight() - diam) / 2, diam,
				diam);
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(aro);
	}
}
