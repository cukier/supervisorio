package cuki.opc;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;

public class ItensOPC {

	private String pivo;

	private OpcItem cicloAtual;
	private OpcItem nrSetores;
	private OpcItem contaFase;
	private OpcItem contaSetor;
	private OpcItem nrFases;
	private OpcItem laminaGet;
	private OpcItem tempoRestanteHoras;
	private OpcItem anguloAtual;
	private OpcItem word0;
	private OpcItem word4;
	private OpcItem word6;
	private OpcItem statusPivo;
	private OpcItem setorIndice;
	private OpcItem tempoRestanteMinutos;

	public ItensOPC(String equipamento, OpcGroup group, JOpc jopc, String pivo) {

		this.pivo = pivo;

		tempoRestanteMinutos = new OpcItem(equipamento
				+ ".ASYNC.tempoRestanteMinutos", true, "");
		cicloAtual = new OpcItem(equipamento + ".ASYNC.cicloAtual", true, "");
		nrSetores = new OpcItem(equipamento + ".ASYNC.nrSetores", true, "");
		contaFase = new OpcItem(equipamento + ".ASYNC.contaFase", true, "");
		contaSetor = new OpcItem(equipamento + ".ASYNC.contaSetor", true, "");
		nrFases = new OpcItem(equipamento + ".ASYNC.nrFases", true, "");
		laminaGet = new OpcItem(equipamento + ".ASYNC.laminaGet", true, "");
		tempoRestanteHoras = new OpcItem(equipamento
				+ ".ASYNC.tempoRestanteHoras", true, "");
		anguloAtual = new OpcItem(equipamento + ".SYNC.anguloAtual", true, "");
		word0 = new OpcItem(equipamento + ".SYNC.Word0", true, "");
		word4 = new OpcItem(equipamento + ".SYNC.Word4", true, "");
		word6 = new OpcItem(equipamento + ".SYNC.Word6", true, "");
		statusPivo = new OpcItem(equipamento + ".ASYNC.statusPivo", true, "");
		setorIndice = new OpcItem(equipamento + ".SYNC.setorIndice", true, "");

		group.addItem(tempoRestanteMinutos);
		group.addItem(cicloAtual);
		group.addItem(nrSetores);
		group.addItem(contaFase);
		group.addItem(contaSetor);
		group.addItem(nrFases);
		group.addItem(laminaGet);
		group.addItem(tempoRestanteHoras);
		group.addItem(anguloAtual);
		group.addItem(word0);
		group.addItem(word4);
		group.addItem(word6);
		group.addItem(statusPivo);
		group.addItem(setorIndice);

		jopc.addGroup(group);

		System.out.println(equipamento + "Itens created and added");

	}

	public String getPivo() {
		return this.pivo;
	}

	public OpcItem getCicloAtual() {
		return cicloAtual;
	}

	public OpcItem getNrSetores() {
		return nrSetores;
	}

	public OpcItem getContaFase() {
		return contaFase;
	}

	public OpcItem getContaSetor() {
		return contaSetor;
	}

	public OpcItem getNrFases() {
		return nrFases;
	}

	public OpcItem getLaminaGet() {
		return laminaGet;
	}

	public OpcItem getTempoRestanteHoras() {
		return tempoRestanteHoras;
	}

	public OpcItem getAnguloAtual() {
		return anguloAtual;
	}

	public OpcItem getWord0() {
		return word0;
	}

	public OpcItem getWord4() {
		return word4;
	}

	public OpcItem getWord6() {
		return word6;
	}

	public OpcItem getStatusPivo() {
		return statusPivo;
	}

	public OpcItem getSetorIndice() {
		return setorIndice;
	}

	public OpcItem getTempoRestanteMinutos() {
		return tempoRestanteMinutos;
	}

}
