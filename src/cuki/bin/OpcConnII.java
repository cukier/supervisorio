package cuki.bin;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.VariantTypeException;

public class OpcConnII {

	private String serverClientHandle;
	private String host;
	private String server;
	private JOpc jopc;
	private OpcGroup group;
	private OpcItem tempoRestanteMinutos;
	// private OpcItem laminaNominal;
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
		this("localhost", "Atos.OPCConnect.1", "JOpcAtos1");
	}

	public OpcConnII(String host, String server, String serverClientHandle) {

		this.host = host;
		this.server = server;
		this.serverClientHandle = serverClientHandle;

		jopc = new JOpc(this.host, this.server, this.serverClientHandle);

		tempoRestanteMinutos = new OpcItem(
				"Equipamento1.ASYNC.tempoRestanteMinutos", true, "");
		// laminaNominal = new OpcItem("Equipamento1.ASYNC.laminaNominal", true,
		// "");
		cicloAtual = new OpcItem("Equipamento1.ASYNC.cicloAtual", true, "");
		nrSetores = new OpcItem("Equipamento1.ASYNC.nrSetores", true, "");
		contaFase = new OpcItem("Equipamento1.ASYNC.contaFase", true, "");
		contaSetor = new OpcItem("Equipamento1.ASYNC.contaSetor", true, "");
		nrFases = new OpcItem("Equipamento1.ASYNC.nrFases", true, "");
		laminaGet = new OpcItem("Equipamento1.ASYNC.laminaGet", true, "");
		tempoRestanteHoras = new OpcItem(
				"Equipamento1.ASYNC.tempoRestanteHoras", true, "");
		anguloAtual = new OpcItem("Equipamento1.SYNC.anguloAtual", true, "");
		byte4 = new OpcItem("Equipamento1.SYNC.Byte4", true, "");
		byte6 = new OpcItem("Equipamento1.SYNC.Byte6", true, "");

		group = new OpcGroup("group1", true, 500, 0.0f);

		group.addItem(tempoRestanteMinutos);
		// group.addItem(laminaNominal);
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

	public void disconnect() throws ComponentNotFoundException,
			UnableRemoveGroupException {
		try {
			jopc.unregisterGroup(group);
		} catch (ComponentNotFoundException e) {
			throw new ComponentNotFoundException("Disconnect Error");
		} catch (UnableRemoveGroupException e) {
			throw new UnableRemoveGroupException("Remove group error");
		}

		JOpc.coUninitialize();

		jopc = null;

		System.out.println("Conexão encerrada");
	}

	private int parseItem(OpcItem item) throws NumberFormatException {
		int retorno = 0;
		boolean hex = false;

		hex = item.getItemName().contains("ASYNC");

		for (String aux : item.toString().split(";")) {
			if (aux.contains("itemValue")) {
				for (String aux2 : aux.split(" = ")) {
					if (!aux2.contains("itemValue")) {
						try {
							if (hex)
								retorno = Integer.parseInt(aux2, 16);
							else
								retorno = Integer.parseInt(aux2);
						} catch (NumberFormatException e) {
							throw new NumberFormatException("Formato errado");
						}
					}
				}
			}
		}
		return retorno;
	}

	private int getResponse(OpcItem item, String itemName)
			throws ComponentNotFoundException, SynchReadException {
		int retorno = 0;

		try {
			OpcItem response = jopc.synchReadItem(group, item);
			retorno = parseItem(response);
		} catch (ComponentNotFoundException e) {
			throw new ComponentNotFoundException("No component found");
		} catch (SynchReadException e) {
			throw new SynchReadException(itemName + " Synch read error");
		}

		return retorno;
	}

	public int getTempoRestanteMinutos() throws ComponentNotFoundException,
			SynchReadException {
		int retorno = 0;

		try {
			retorno = getResponse(tempoRestanteMinutos, "tempoRestanteMinutos");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getanguloAtual() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(anguloAtual, "anguloAtual");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcicloAtuall() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(cicloAtual, "cicloAtual");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getnrSetores() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(nrSetores, "nrSetores");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcontaFase() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(contaFase, "contaFase");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcontaSetor() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(contaSetor, "contaSetor");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getnrFases() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(nrFases, "nrFases");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getlaminaGet() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(laminaGet, "laminaGet");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int gettempoRestanteHoras() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(tempoRestanteHoras, "tempoRestanteHoras");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getbyte4() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(byte4, "byte4");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getbyte6() throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(byte6, "byte6");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}
}
