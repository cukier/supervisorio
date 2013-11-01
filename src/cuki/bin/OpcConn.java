package cuki.bin;

import java.util.ArrayList;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.browser.JOpcBrowser;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.CoUninitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableBrowseLeafException;
import javafish.clients.opc.exception.UnableIBrowseException;

public class OpcConn {

	private String serverClientHandle;
	private String host = "localhost";
	private String server = null;
	private static int id = 0;
	private JOpc jopc = null;
	OpcGroup group = null;
	OpcItem tempoRestanteMinutos, laminaNominal, cicloAtual, nrSetores,
			contaFase, contaSetor, nrFases, laminaGet, tempoRestanteHoras,
			anguloAtual, byte4, byte6;

	public OpcConn(String host, String server) {
		this.host = host;
		this.server = server;
		serverClientHandle = "JOPCAtos" + String.valueOf(id++);
	}

	private String[] getBranch(String branch, JOpcBrowser opcBrowser)
			throws UnableBrowseBranchException, UnableIBrowseException {

		String[] retorno = null;

		try {
			retorno = opcBrowser.getOpcBranch(branch);
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	private String[] getIten(String leaf, JOpcBrowser opcBrowser)
			throws UnableBrowseLeafException, UnableIBrowseException,
			UnableAddGroupException, UnableAddItemException,
			CoUninitializeException {
		String[] retorno = null;

		try {
			retorno = opcBrowser.getOpcItems(leaf, true);
		} catch (UnableBrowseLeafException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (CoUninitializeException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private String[] getAllItens(String branch, String aux,
			JOpcBrowser opcBrowser) throws UnableBrowseBranchException,
			UnableIBrowseException, UnableAddGroupException {

		String[] branches = null;
		String[] retorno = null;

		try {
			branches = getBranch(branch, opcBrowser);
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		}

		if (branches != null) {
			aux = branch;
			ArrayList<String> pool = new ArrayList<String>();
			for (String i : branches) {
				String[] otherItens = null;
				try {
					otherItens = getAllItens(i, aux, opcBrowser);
				} catch (UnableBrowseBranchException e) {
					e.printStackTrace();
				} catch (UnableIBrowseException e) {
					e.printStackTrace();
				}
				if (otherItens != null) {
					// if (otherItens != null && otherItens[0].contains(";")) {
					for (String j : otherItens) {
						if (j.contains(";")) {
							String[] split = j.split("; ");
							pool.add(split[2]);
						} else
							pool.add(j);
					}
				}
			}
			retorno = null;
			retorno = new String[pool.size()];
			pool.toArray(retorno);
		} else if (!branch.equals("Read Error")
				&& !branch.equals("Configured Aliases")) {
			try {
				String leafPath = (aux != "") ? aux + "." + branch : branch;
				retorno = getIten(leafPath, opcBrowser);
			} catch (UnableBrowseLeafException e) {
				e.printStackTrace();
			} catch (UnableIBrowseException e) {
				e.printStackTrace();
			} catch (UnableAddGroupException e) {
				e.printStackTrace();
			} catch (UnableAddItemException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	public void registerAllItens() throws CoInitializeException,
			ConnectivityException, UnableBrowseBranchException,
			UnableIBrowseException, UnableAddGroupException,
			UnableAddItemException {

		// String[] itens = { "Equipamento1.anguloAtual", "Equipamento1.Byte4",
		// "Equipamento1.Byte6" };
		String[] itens = null;
		JOpcBrowser opcBrowser = new JOpcBrowser(this.host, this.server,
				this.serverClientHandle);

		try {
			JOpcBrowser.coInitialize();
		} catch (CoInitializeException e) {
			e.printStackTrace();
		}

		try {
			opcBrowser.connect();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		try {
			// getAllItens("", null, opcBrowser);
			itens = getAllItens("", null, opcBrowser);
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		}

		JOpcBrowser.coUninitialize();
		opcBrowser = null;

		try {
			JOpc.coInitialize();
		} catch (CoInitializeException e1) {
			e1.printStackTrace();
		}

		jopc = new JOpc(this.host, this.server, this.serverClientHandle);
		group = new OpcGroup("group1", true, 10, 0.0f);

		for (String s : itens) {
			group.addItem(new OpcItem(s, true, ""));
		}

		jopc.addGroup(group);

		try {
			jopc.connect();
			System.out.println("JOPC client is connected...");
		} catch (ConnectivityException e2) {
			e2.printStackTrace();
		}

		try {
			jopc.registerGroups();
			System.out.println("OPCGroup are registered...");
		} catch (UnableAddGroupException e2) {
			e2.printStackTrace();
		} catch (UnableAddItemException e2) {
			e2.printStackTrace();
		}
	}

	public OpcGroup synchResponse() throws ComponentNotFoundException,
			SynchReadException {

		OpcGroup responseGroup = null;

		try {
			responseGroup = jopc.synchReadGroup(group);
			// System.out.println(responseGroup);
		} catch (ComponentNotFoundException e1) {
			e1.printStackTrace();
		} catch (SynchReadException e1) {
			e1.printStackTrace();
		}

		return responseGroup;
	}

	public void disconn() {
		JOpc.coUninitialize();
		jopc = null;
	}
}