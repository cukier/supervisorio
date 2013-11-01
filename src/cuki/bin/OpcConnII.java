package cuki.bin;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.VariantTypeException;
import javafish.clients.opc.variant.Variant;

public class OpcConnII {

	private String serverClientHandle;
	private String host;
	private String server;
	private JOpc jopc;
	private OpcGroup group;
	private OpcItem tempoRestanteMinutos;
	private OpcItem laminaNominal;
	private OpcItem cicloAtual;
	private OpcItem nrSetores;
	private OpcItem contaFase;
	private OpcItem contaSetor;
	private OpcItem nrFases;
	private OpcItem laminaGet;
	private OpcItem tempoRestanteHoras;
	private OpcItem anguloAtual;
	private OpcItem byte4;
	private OpcItem byte6;

	public OpcConnII() {
		this("localhost", "Atos.OPCConnect.1");
	}

	public OpcConnII(String host, String server) {

		this.host = host;
		this.server = server;
		this.serverClientHandle = "JOpcAtos1";

		jopc = new JOpc(this.host, this.server, serverClientHandle);

		tempoRestanteMinutos = new OpcItem(
				"Equipamento1.ASYNC.tempoRestanteMinutos", true, "");
		laminaNominal = new OpcItem("Equipamento1.ASYNC.laminaNominal", true,
				"");
		cicloAtual = new OpcItem("Equipamento1.ASYNC.cicloAtual", true, "");
		nrSetores = new OpcItem("Equipamento1.ASYNC.nrSetores", true, "");
		contaFase = new OpcItem("Equipamento1.ASYNC.contaFase", true, "");
		contaSetor = new OpcItem("Equipamento1.ASYNC.contaSetor", true, "");
		nrFases = new OpcItem("Equipamento1.ASYNC.nrFases", true, "");
		laminaGet = new OpcItem("Equipamento1.ASYNC.laminaGet", true, "");
		tempoRestanteHoras = new OpcItem(
				"Equipamento1.ASYNC.tempoRestanteHoras", true, "");
		anguloAtual = new OpcItem("Equipamento1.SYNC.anguloAtual", true, "");
		byte4 = new OpcItem("Equipamento1.ASYNC.Byte4", true, "");
		byte6 = new OpcItem("Equipamento1.ASYNC.Byte6", true, "");

		group = new OpcGroup("group1", true, 500, 0.0f);

		group.addItem(tempoRestanteMinutos);
		group.addItem(laminaNominal);
		group.addItem(cicloAtual);
		group.addItem(nrSetores);
		group.addItem(contaFase);
		group.addItem(contaSetor);
		group.addItem(nrFases);
		group.addItem(laminaGet);
		group.addItem(tempoRestanteHoras);
		group.addItem(anguloAtual);
		group.addItem(byte4);
		group.addItem(byte6);

		jopc.addGroup(group);

		System.out.println("Itens created and added");

	}

	public void connectAndRegister() throws ConnectivityException,
			UnableAddGroupException, UnableAddItemException {

		JOpc.coInitialize();

		try {
			jopc.connect();
			System.out.println("JOPC client is connected...");
		} catch (ConnectivityException e) {
			throw new ConnectivityException("Not Connected!");
		}

		try {
			jopc.registerGroups();
			System.out.println("OPCGroup are registered...");
		} catch (UnableAddGroupException e) {
			throw new UnableAddGroupException("No group added!");
		} catch (UnableAddItemException e) {
			throw new UnableAddItemException("No item added!");
		}
	}

	public int getTempoRestanteMinutos() throws ComponentNotFoundException,
			SynchReadException {
		int retorno;

		try {
			OpcItem responseItem = jopc.synchReadItem(group,
					tempoRestanteMinutos);
			retorno = responseItem.getValue().getInteger();
		} catch (ComponentNotFoundException e) {
			throw new ComponentNotFoundException("No component found");
		} catch (SynchReadException e) {
			throw new SynchReadException("responseItem Synch read error");
		}

		return retorno;
	}

	public int getanguloAtual() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno;
		Variant resposta;

		try {
			OpcItem responseItem = jopc.synchReadItem(group, anguloAtual);
			resposta = responseItem.getValue();
		} catch (ComponentNotFoundException e) {
			throw new ComponentNotFoundException("No component found");
		} catch (SynchReadException e) {
			throw new SynchReadException("getanguloAtual Synch read error");
		}

		try {
			String aux = resposta.getString();
			System.out.println(aux);
		} catch (VariantTypeException e) {
			throw new VariantTypeException("Problemas de conversao de tipo");
		}

		// return retorno;
		return 105;
	}

	public int getlaminaGet() throws ComponentNotFoundException,
			SynchReadException {
		int retorno;

		try {
			OpcItem responseItem = jopc.synchReadItem(group, laminaGet);
			retorno = responseItem.getValue().getInteger();
		} catch (ComponentNotFoundException e) {
			throw new ComponentNotFoundException("No component found");
		} catch (SynchReadException e) {
			throw new SynchReadException("Synch read error");
		}

		return retorno;
	}
}
