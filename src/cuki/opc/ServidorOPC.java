package cuki.opc;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.browser.JOpcBrowser;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.VariantTypeException;
import cuki.opc.ItensOPC;

public class ServidorOPC {

	private String serverClientHandle;
	private String host;
	private String server;
	private JOpc jopc;
	private OpcGroup group;
	private String[] servidores;
	private ItensOPC[] itensPivo;

	public ServidorOPC() {
		this("localhost", "Atos.OPCConnect.1", "JOpcAtos1");
	}

	public ServidorOPC(String host, String server, String serverClientHandle) {

		this.host = host;
		this.server = server;
		this.serverClientHandle = serverClientHandle;

		jopc = new JOpc(this.host, this.server, this.serverClientHandle);
		group = new OpcGroup("group1", true, 500, 0.0f);
	}

	private String[] getServers() throws ConnectivityException,
			UnableBrowseBranchException, UnableIBrowseException {

		String[] retorno = null;

		JOpcBrowser opcBrowser = new JOpcBrowser(this.host, this.server,
				this.serverClientHandle);

		try {
			JOpcBrowser.coInitialize();
		} catch (CoInitializeException e) {
			throw new CoInitializeException(
					"Falha ao buscar equipamentos de comunicação");
		}

		try {
			opcBrowser.connect();
		} catch (ConnectivityException e) {
			throw new ConnectivityException(this.host + " " + this.server + " "
					+ this.serverClientHandle);
		}

		try {
			retorno = opcBrowser.getOpcBranch("");
		} catch (UnableBrowseBranchException e) {
			throw new UnableBrowseBranchException(
					"Falha ao buscar equipamentos de comunicação (2)");
		} catch (UnableIBrowseException e) {
			throw new UnableIBrowseException(
					"Falha ao buscar equipamentos de comunicação (3)");
		}

		return retorno;
	}

	public void connectAndRegister() throws ConnectivityException,
			UnableAddGroupException, UnableAddItemException,
			UnableBrowseBranchException, UnableIBrowseException {

		try {
			this.servidores = getServers();
		} catch (UnableBrowseBranchException e) {
			throw e;
		} catch (UnableIBrowseException e) {
			throw e;
		}

		itensPivo = new ItensOPC[servidores.length];

		int cont = 0;
		// for (String servidor : servidores) {
		// itensPivo[cont++] = new ItensOPC(servidor, group, jopc);
		// }

		for (String servidor : servidores) {

			itensPivo[cont] = new ItensOPC(servidor);

			for (OpcItem item : itensPivo[cont++].createItens(servidor))
				group.addItem(item);
		}
		jopc.addGroup(group);

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

	public String[] getServidores() {
		return this.servidores;
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

	private int indice(String pivo) {
		int cont = 0;
		for (ItensOPC item : itensPivo) {
			if (pivo.equals(item.getPivo()))
				break;
			else
				cont++;
		}
		return cont;
	}

	public int getTempoRestanteMinutos(String pivo)
			throws ComponentNotFoundException, SynchReadException {
		int retorno = 0;

		try {
			retorno = getResponse(
					itensPivo[indice(pivo)].getTempoRestanteMinutos(),
					"tempoRestanteMinutos");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getanguloAtual(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getAnguloAtual(),
					"anguloAtual");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcicloAtual(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getCicloAtual(),
					"cicloAtual");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getnrSetores(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getNrSetores(),
					"nrSetores");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcontaFase(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getContaFase(),
					"contaFase");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getcontaSetor(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getContaSetor(),
					"contaSetor");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getnrFases(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getNrFases(),
					"nrFases");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getlaminaGet(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getLaminaGet(),
					"laminaGet");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int gettempoRestanteHoras(String pivo)
			throws ComponentNotFoundException, SynchReadException,
			VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(
					itensPivo[indice(pivo)].getTempoRestanteHoras(),
					"tempoRestanteHoras");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getword0(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getWord0(), "word0");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getword4(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getWord4(), "word4");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getword6(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getWord6(), "word6");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getstatusPivo(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getStatusPivo(),
					"statusPivo");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public int getsetorIndice(String pivo) throws ComponentNotFoundException,
			SynchReadException, VariantTypeException {
		int retorno = 0;

		try {
			retorno = getResponse(itensPivo[indice(pivo)].getSetorIndice(),
					"setorIndice");
		} catch (ComponentNotFoundException e) {
			throw e;
		} catch (SynchReadException e) {
			throw e;
		}

		return retorno;
	}

	public OpcGroup getGroup() {
		return this.group;
	}

	public JOpc getJOpc() {
		return this.jopc;
	}
}
