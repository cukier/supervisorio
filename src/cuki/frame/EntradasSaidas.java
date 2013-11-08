package cuki.frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cuki.bin.BitField;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EntradasSaidas extends JFrame {

	private JLabel lblBombaDgua = null;
	private JLabel lblRbombadagua = null;

	private JLabel lblAvanco = null;
	private JLabel lblRavanco = null;
	private JLabel lblReversao = null;
	private JLabel lblRreversao = null;
	private JLabel lblUltimaTorre = null;
	private JLabel lblRultimatorre = null;
	private JLabel lblBombaInjetora = null;
	private JLabel lblRbombainjetora = null;
	private JLabel lblSeguranca = null;
	private JLabel lblRseguranca = null;
	private JLabel lblPicoEnergtico = null;
	private JLabel lblRpicoenergetico = null;
	private JLabel lblHorrioDeTrabalho = null;
	private JLabel lblRhorariotrabalho = null;
	private JLabel lblPressostato = null;
	private JLabel lblRpressostato = null;
	private JLabel lblBombaBooster = null;
	private JLabel lblRbombabooster = null;
	private JLabel lblSetorRestrito = null;
	private JLabel lblRsetorrestrito = null;
	private JLabel lblPivEmEspera = null;
	private JLabel lblRemespera = null;
	private JLabel lblTurnoDeRega = null;
	private JLabel lblRturnorega = null;
	private JLabel lblAutomtico = null;
	private JLabel lblRautomatico = null;
	private JLabel lblMovimentoASeco = null;
	private JLabel lblAlarmeAlinhamento = null;
	private JLabel lblRalarmealinhamento = null;
	private JLabel lblAlarmePresso = null;
	private JLabel lblVoltarASeco = null;
	private JLabel lblRvoltarseco = null;
	private JLabel lblRmovseco = null;
	private JLabel lblRalarmepressao = null;

	public EntradasSaidas(String text) {
		this();
		setTitle(text);
	}

	public EntradasSaidas() {
		getContentPane().setLayout(
				new MigLayout("", "[74px][75px]", "[][][][][][][][][][][][][][][][][][][]"));

		lblAvanco = new JLabel("Avan\u00E7o");
		lblAvanco.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblAvanco, "cell 0 0,alignx right,aligny center");

		lblRavanco = new JLabel("rAvanco");
		lblRavanco.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblRavanco, "cell 1 0,alignx left,aligny center");

		lblReversao = new JLabel("Revers\u00E3o");
		lblReversao.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane()
				.add(lblReversao, "cell 0 1,alignx right,aligny center");

		lblRreversao = new JLabel("rReversao");
		lblRreversao.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane()
				.add(lblRreversao, "cell 1 1,alignx left,aligny center");

		lblUltimaTorre = new JLabel("Ultima Torre");
		lblUltimaTorre.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblUltimaTorre,
				"cell 0 2,alignx right,aligny center");

		lblRultimatorre = new JLabel("rUltimaTorre");
		lblRultimatorre.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblRultimatorre,
				"cell 1 2,alignx left,aligny center");

		lblSeguranca = new JLabel("Seguran\u00E7a");
		lblSeguranca.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblSeguranca,
				"cell 0 3,alignx right,aligny center");

		lblRseguranca = new JLabel("rSeguranca");
		lblRseguranca.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblRseguranca,
				"cell 1 3,alignx left,aligny center");

		lblPicoEnergtico = new JLabel("Pico Energetico");
		getContentPane().add(lblPicoEnergtico,
				"cell 0 4,alignx right,aligny center");

		lblRpicoenergetico = new JLabel("rPicoEnergetico");
		getContentPane().add(lblRpicoenergetico, "cell 1 4");

		lblHorrioDeTrabalho = new JLabel("Hor\u00E1rio de Trabalho");
		getContentPane().add(lblHorrioDeTrabalho,
				"cell 0 5,alignx right,aligny center");

		lblRhorariotrabalho = new JLabel("rHorarioTrabalho");
		getContentPane().add(lblRhorariotrabalho, "cell 1 5");

		lblPressostato = new JLabel("Pressostato");
		getContentPane().add(lblPressostato,
				"cell 0 6,alignx right,aligny center");

		lblRpressostato = new JLabel("rPressostato");
		getContentPane().add(lblRpressostato,
				"cell 1 6,alignx left,aligny center");

		lblBombaInjetora = new JLabel("Bomba Injetora");
		lblBombaInjetora.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblBombaInjetora,
				"cell 0 7,alignx right,aligny center");

		lblRbombainjetora = new JLabel("rBombaInjetora");
		lblRbombainjetora.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblRbombainjetora,
				"cell 1 7,alignx left,aligny center");

		lblBombaBooster = new JLabel("Bomba Booster");
		getContentPane().add(lblBombaBooster,
				"flowy,cell 0 8,alignx right,aligny center");

		lblRbombabooster = new JLabel("rBombaBooster");
		getContentPane().add(lblRbombabooster,
				"cell 1 8,alignx left,aligny center");

		lblBombaDgua = new JLabel("Bomba D'\u00C1gua");
		lblBombaDgua.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblBombaDgua,
				"cell 0 9,alignx right,aligny center");

		lblRbombadagua = new JLabel("rBombaDagua");
		lblRbombadagua.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblRbombadagua,
				"cell 1 9,alignx left,aligny center");

		lblSetorRestrito = new JLabel("Setor Restrito");
		getContentPane().add(lblSetorRestrito,
				"cell 0 10,alignx right,aligny center");

		lblRsetorrestrito = new JLabel("rSetorRestrito");
		getContentPane().add(lblRsetorrestrito,
				"cell 1 10,alignx left,aligny center");

		lblPivEmEspera = new JLabel("Piv\u00F4 em Espera");
		getContentPane().add(lblPivEmEspera,
				"cell 0 11,alignx right,aligny center");

		lblRemespera = new JLabel("rEmEspera");
		getContentPane().add(lblRemespera,
				"cell 1 11,alignx left,aligny center");

		lblTurnoDeRega = new JLabel("Turno de Rega");
		getContentPane().add(lblTurnoDeRega,
				"cell 0 12,alignx right,aligny center");

		lblRturnorega = new JLabel("rTurnoRega");
		getContentPane().add(lblRturnorega,
				"cell 1 12,alignx left,aligny center");

		lblAutomtico = new JLabel("Autom\u00E1tico");
		getContentPane().add(lblAutomtico,
				"cell 0 13,alignx right,aligny center");

		lblRautomatico = new JLabel("rAutomatico");
		getContentPane().add(lblRautomatico,
				"cell 1 13,alignx left,aligny center");

		lblMovimentoASeco = new JLabel("Movimento a Seco");
		getContentPane().add(lblMovimentoASeco,
				"cell 0 14,alignx right,aligny center");

		lblRmovseco = new JLabel("rMovSeco");
		getContentPane()
				.add(lblRmovseco, "cell 1 14,alignx left,aligny center");

		lblAlarmeAlinhamento = new JLabel("Alarme Alinhamento");
		getContentPane().add(lblAlarmeAlinhamento,
				"cell 0 15,alignx right,aligny center");

		lblRalarmealinhamento = new JLabel("rAlarmeAlinhamento");
		getContentPane().add(lblRalarmealinhamento,
				"cell 1 15,alignx left,aligny center");

		lblAlarmePresso = new JLabel("Alarme Press\u00E3o");
		getContentPane().add(lblAlarmePresso,
				"cell 0 16,alignx right,aligny center");

		lblRalarmepressao = new JLabel("rAlarmePressao");
		getContentPane().add(lblRalarmepressao,
				"cell 1 16,alignx left,aligny center");

		lblVoltarASeco = new JLabel("Voltar a Seco");
		getContentPane().add(lblVoltarASeco,
				"cell 0 17,alignx right,aligny center");

		lblRvoltarseco = new JLabel("rVoltarSeco");
		getContentPane().add(lblRvoltarseco,
				"cell 1 17,alignx left,aligny center");

		pack();
	}

	public void setBytes(int iWord0, int iWord4, int iWord6) {

		BitField word0 = new BitField(iWord0);

		lblRautomatico
				.setText((word0.getBit(BitField.saidaAutomatico)) ? "Ligado"
						: "Desligado");
		lblRpicoenergetico
				.setText((word0.getBit(BitField.picoEnergetico)) ? "Ativo"
						: "Inativo");
		lblRhorariotrabalho
				.setText((word0.getBit(BitField.horarioTrabalhoOk)) ? "OK"
						: "Esperando");
		lblRmovseco.setText((word0.getBit(BitField.partidaSeca)) ? "Sim"
				: "Não");
		lblRbombabooster
				.setText((word0.getBit(BitField.sBombaBooster)) ? "Ligada"
						: "Desligada");
		lblRbombadagua.setText((word0.getBit(BitField.saidaBDagua)) ? "Ligada"
				: "Desligada");

		BitField word4 = new BitField(iWord4);

		lblRavanco.setText((word4.getBit(BitField.sAvanco)) ? "Ligado"
				: "Desligado");
		lblRreversao.setText((word4.getBit(BitField.sReversao)) ? "Ligado"
				: "Desligado");
		lblRultimatorre
				.setText((word4.getBit(BitField.sUltimaTorre)) ? "Ligada"
						: "Desligada");
		lblRbombainjetora
				.setText((word4.getBit(BitField.sBombaInjetora)) ? "Ligada"
						: "Desligada");
		lblRseguranca.setText((word4.getBit(BitField.saidaSeguranca)) ? "OK"
				: "Sem Retorno");
		lblRsetorrestrito
				.setText((word4.getBit(BitField.ultimoSetorRestrito)) ? "Ativo"
						: "Inativo");
		lblRpressostato
				.setText((word4.getBit(BitField.saidaPressostato)) ? "Pressurizado"
						: "Falta Pressão");

		BitField word6 = new BitField(iWord6);

		lblRemespera.setText((word6.getBit(BitField.emEspera)) ? "Sim" : "Não");
		lblRbombabooster.setText((word6.getBit(BitField.bBoosterOk)) ? "Ligada"
				: "Desligada");
		lblRturnorega.setText((word6.getBit(BitField.turnoRegaOk)) ? "Sim"
				: "Não");
		lblRalarmealinhamento
				.setText((word6.getBit(BitField.alarmeAlinhamento)) ? "ACIONADO"
						: "Normal");
		lblRalarmepressao
				.setText((word6.getBit(BitField.alarmePressao)) ? "ACIONADO"
						: "Normal");
	}

}
