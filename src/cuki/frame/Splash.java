package cuki.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

import sun.security.jca.GetInstance.Instance;

public class Splash extends Window {

	private final String message = "Splash Screen";
	private final String imgName = "/cuki/frame/images/splash-screen-sem-texto.png";
	Image splashImage;
	Toolkit toolkit;
	private static Splash splash;

	private Splash() {
		initSplash();
	}

	public static Splash getInstance() {
		if (splash == null)
			splash = new Splash();
		return splash;
	}

	private void initSplash() {

		MediaTracker media = new MediaTracker(this);
		splashImage = toolkit.getImage(imgName);

		if (splashImage != null) {
			media.addImage(splashImage, 0);
			try {
				media.waitForID(0);
			} catch (InterruptedException ie) {
			}
		}

		setSize(splashImage.getWidth(this), splashImage.getHeight(this));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();

		if (size.width > screenSize.width)
			size.width = screenSize.width;

		if (size.height > screenSize.height)
			size.height = screenSize.height;

		setLocation((screenSize.width - size.width) / 2,
				(screenSize.height - size.height) / 2);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(splashImage, 0, 0, getBackground(), this);
		g.setFont(new Font("Arial", Font.BOLD, 26));
		g.drawString(message, (int) (splashImage.getWidth(this) / 2) - 80, 30);
	}

}
