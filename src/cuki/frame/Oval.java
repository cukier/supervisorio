package cuki.frame;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oval extends JPanel {

	public Oval() {
		setPreferredSize(new Dimension(10, 10));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
	}

}
