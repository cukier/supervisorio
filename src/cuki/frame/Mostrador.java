package cuki.frame;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Mostrador extends JPanel {

	private String estado;
	private int setorAtual, setorTotais;
	private int faseAtual, faseTotais;
	private int lamina, duracao, ciclo;
	private JLabel lblSetor = null;

	public Mostrador() {
		setLayout(new MigLayout("", "[grow,center]", "[grow,center][grow,center][grow,center][grow,center][grow,center][grow,center][center]"));

		JLabel lblEstado = new JLabel("Estado: " + estado);
		add(lblEstado, "cell 0 0,alignx center,aligny center");

		lblSetor = new JLabel("Setor: " + setorAtual + " / " + setorTotais);
		add(lblSetor, "cell 0 1,alignx center,aligny center");

		JLabel lblFase = new JLabel("Fase");
		add(lblFase, "cell 0 2,alignx center,aligny center");

		JLabel lblBruta = new JLabel("Bruta");
		add(lblBruta, "cell 0 3,alignx center,aligny center");

		JLabel lblDuracao = new JLabel("Duracao");
		add(lblDuracao, "cell 0 4,alignx center,aligny center");

		JLabel lblCiclo = new JLabel("Ciclo");
		lblCiclo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCiclo, "cell 0 5,alignx center,aligny center");

		JButton btnStartstop = new JButton("start_stop");
		add(btnStartstop, "cell 0 6,alignx center,aligny center");
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		lblSetor.setText("Estado: " + estado);

	}

	public void setSetorAtual(int setorAtual) {
		this.setorAtual = setorAtual;
	}

}
