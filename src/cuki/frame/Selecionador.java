package cuki.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Selecionador extends JFrame {

	private JButton btnAbrirpivo;
	private Status[] listaStatusPivo;
	private boolean finalizarPrograma = false;
	private String[] pivos;
	private int cont;

	public Selecionador(String[] servidores) {

		this.pivos = servidores;
		this.listaStatusPivo = new Status[servidores.length];

		if (servidores.length > 0 && servidores != null) {
			getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
			cont = 0;
			for (String servidor : servidores) {
				btnAbrirpivo = new JButton(servidor);
				btnAbrirpivo.setIcon(new ImageIcon(Selecionador.class
						.getResource("/cuki/frame/images/pivoIcon.jpg")));
				getContentPane().add(btnAbrirpivo, "wrap");
				btnAbrirpivo.addActionListener(new BtnListener(servidor));
			}
		}
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				finalizarPrograma = true;
				dispose();
			}
		});
		pack();
	}

	public Status getStatusPivo(String pivo) {
		if (listaStatusPivo[0] != null) {
			for (Status retorno : listaStatusPivo) {
				if (retorno.getPivoName().equals(pivo))
					return retorno;
			}
		}
		return null;
	}

	private class BtnListener implements ActionListener {

		private String pivo;

		public BtnListener(String pivo) {
			this.pivo = pivo;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Status statusPivo = new Status(pivo);
			System.out.println("Location " + getLocation());
			System.out.println("Size " + getSize());
			statusPivo.setLocation(getWidth(), statusPivo.getCont()
					* statusPivo.getHeight());
			listaStatusPivo[cont++] = statusPivo;
			statusPivo.setVisible(true);

		}
	}

	public boolean isFinalizarPrograma() {
		return finalizarPrograma;
	}

	public String[] getPivos() {
		return this.pivos;
	}
}
