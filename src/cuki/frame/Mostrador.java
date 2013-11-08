package cuki.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class Mostrador extends JPanel {

	private int larg = 350;
	private int alt = 300;
	private JLabel estado = null;
	private JLabel setor = null;
	private JLabel fase = null;
	private JLabel bruta = null;
	private JLabel duracao = null;
	private JLabel ciclo = null;
	private JButton bto = null;

	public Mostrador() {

		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("bottom:default:grow"), }));

		estado = new JLabel("Estado: ");
		estado.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(estado, "2, 2, center, default");

		setor = new JLabel("Setor: ");
		setor.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(setor, "2, 4, center, default");

		fase = new JLabel("Fase: ");
		fase.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(fase, "2, 6, center, default");

		bruta = new JLabel("Bruta: ");
		bruta.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(bruta, "2, 8, center, default");

		duracao = new JLabel("Duração: ");
		duracao.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(duracao, "2, 10, center, default");

		ciclo = new JLabel("Ciclo: ");
		ciclo.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 20));
		add(ciclo, "2, 12, center, default");

		bto = new JButton("Não Conectado");
		bto.setFont(new Font("Helvetica Neue Light", Font.PLAIN, 15));
		add(bto, "2, 14, center, default");

		setBorder(BorderFactory.createTitledBorder("Situação Atual do Pivô"));
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(larg, alt));
	}

	public void setEstado(int estado) {
		String str = "Não conectado";

		switch (estado) {
		case 0:
			str = "Parado";
			this.estado.setForeground(Color.RED);
			break;
		case 1:
			str = "Motobomba";
			this.estado.setForeground(Color.GREEN);
			break;
		case 2:
			str = "Pressurizando";
			this.estado.setForeground(Color.GREEN);
			break;
		case 3:
			str = "Irrigando";
			this.estado.setForeground(Color.GREEN);
			break;
		case 4:
			str = "Pico energético";
			this.estado.setForeground(Color.RED);
			break;
		case 5:
			str = "ALARME ACIONADO!!!";
			this.estado.setForeground(Color.RED);
			break;
		case 7:
			str = "Horário de trabalho";
			this.estado.setForeground(Color.RED);
			break;
		case 8:
			str = "Irrigação terminada";
			this.estado.setForeground(Color.RED);
			break;
		case 9:
			str = "Pivô em movimento";
			this.estado.setForeground(Color.GREEN);
			break;
		default:
			str = "Parâmetro inválido";
			this.estado.setForeground(Color.RED);
		}

		this.estado.setText("Estado: " + str);
	}

	public void setSetor(int setorAtual, int nrSetores) {
		this.setor.setText("Setor: " + String.valueOf(setorAtual) + "/"
				+ String.valueOf(nrSetores));
	}

	public void setFase(int faseAtual, int nrFases) {
		this.fase.setText("Fase: " + String.valueOf(faseAtual) + "/"
				+ String.valueOf(nrFases));
	}

	public void setBruta(int bruta) {
		this.bruta.setText("Bruta: " + String.valueOf(bruta) + " mm");
	}

	public void setDuracao(int duracaoH, int duracaoM) {
		this.duracao.setText("Duracação: " + String.valueOf(duracaoH) + "h"
				+ String.valueOf(duracaoM));
	}

	public void setCiclo(int ciclo) {
		this.ciclo.setText("Ciclo: " + String.valueOf(ciclo));
	}

	public void setBto(String bto) {
		this.bto.setText(bto);
	}
}
