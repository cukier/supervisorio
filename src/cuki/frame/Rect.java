package cuki.frame;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Rect extends JPanel {

	public Rect() {
		setPreferredSize(new Dimension(150, 150));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, 150, 150);
	}
}
