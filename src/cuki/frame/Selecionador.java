package cuki.frame;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Selecionador extends JFrame {

	JButton btnAbrirpivo;

	public Selecionador(String[] servidores) {

		if (servidores.length > 0 && servidores != null) {
			getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

			for (String servidor : servidores) {
				btnAbrirpivo = new JButton(servidor);
				btnAbrirpivo.setIcon(new ImageIcon(Selecionador.class
						.getResource("/cuki/frame/images/pivoIcon.jpg")));
				getContentPane().add(btnAbrirpivo,
						"cell 0 0,alignx center,aligny center wrap");
			}

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

}
