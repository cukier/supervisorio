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
import javafish.clients.opc.exception.HostException;
import javafish.clients.opc.exception.NotFoundServersException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableBrowseLeafException;
import javafish.clients.opc.exception.UnableIBrowseException;

public class CopyOfOpcConn {

	private JOpcBrowser opcBrowser = null;
	private JOpc jopc = null;
	private static int id;
	private String serverClientHandle;
	private String host = "localhost";
	private String server;
	// private OpcItem itens = null;
	private OpcGroup group = null;

	public CopyOfOpcConn(String host, String server) {
		this();
		this.host = host;
		this.server = server;
	}

	public CopyOfOpcConn() {
		try {
			JOpcBrowser.coInitialize();
		} catch (CoInitializeException e) {
			e.printStackTrace();
		}
		serverClientHandle = "JOPCAtos" + String.valueOf(id);
		id++;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private void serverBrowserConnect() throws ConnectivityException {
		serverBrowserConnect(this.server, this.host);
	}

	private void serverBrowserConnect(String server, String host)
			throws ConnectivityException {
		this.server = server;
		this.host = host;
		opcBrowser = null;
		opcBrowser = new JOpcBrowser(host, server, serverClientHandle);
		try {
			opcBrowser.connect();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}
	}

	private void disconnectBrowser() {
		JOpcBrowser.coUninitialize();
		opcBrowser = null;
		System.out.println("JOpc off-line");
	}

	public String[] getAllServers(String ip) {
		String[] retorno = null;

		try {
			retorno = JOpcBrowser.getOpcServers(ip);
		} catch (HostException ex) {
			ex.printStackTrace();
		} catch (NotFoundServersException ex) {
			ex.printStackTrace();
		}
		return retorno;
	}

	public String[] getBranch(String branch)
			throws UnableBrowseBranchException, UnableIBrowseException {
		String[] retorno = null;

		if (opcBrowser != null) {
			try {
				retorno = opcBrowser.getOpcBranch(branch);
			} catch (UnableBrowseBranchException e) {
				// e.printStackTrace();
			} catch (UnableIBrowseException e) {
				// e.printStackTrace();
			}
		} else {
			System.out.println("Browser inexistente!");
		}

		return retorno;
	}

	private String[] getIten(String leaf) throws UnableBrowseLeafException,
			UnableIBrowseException, UnableAddGroupException,
			UnableAddItemException, CoUninitializeException {
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

	public void registerAllItens() throws UnableBrowseBranchException,
			CoInitializeException, ConnectivityException,
			UnableAddGroupException, UnableAddItemException {

		String[] itens = null;

		try {
			itens = getAllItens("", null);
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		}
		if (itens != null) {
			try {
				JOpc.coInitialize();
			} catch (CoInitializeException e1) {
				e1.printStackTrace();
			}
			jopc = new JOpc(host, server, serverClientHandle);
			group = new OpcGroup("group1", true, 10, 0.0f);
			for (String s : itens) {
				String[] aux = s.split(";");
				group.addItem(new OpcItem(aux[2], true, ""));
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
				System.out.println("OPC group is registered...");
			} catch (UnableAddGroupException e) {
				e.printStackTrace();
			} catch (UnableAddItemException e) {
				e.printStackTrace();
			}
		}
	}

	private String[] getAllItens(String branch, String aux)
			throws UnableBrowseBranchException {

		String[] branches = null;
		String[] retorno = null;

		try {
			serverBrowserConnect();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		try {
			branches = getBranch(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (branches != null) {
			aux = branch;
			ArrayList<String> pool = new ArrayList<String>();
			for (String i : branches) {
				try {
					String[] otherItens = getAllItens(i, aux);
					if (otherItens != null) {
						for (String j : otherItens) {
							pool.add(j);
						}
					}
				} catch (UnableBrowseBranchException e) {
					e.printStackTrace();
				}
			}
			retorno = null;
			retorno = new String[pool.size()];
			pool.toArray(retorno);
		} else if (!branch.equals("Read Error")
				&& !branch.equals("Configured Aliases")) {
			try {
				String leafPath = (aux != "") ? aux + "." + branch : branch;
				retorno = getIten(leafPath);
			} catch (UnableBrowseLeafException | UnableAddGroupException
					| UnableAddItemException | UnableIBrowseException
					| CoUninitializeException e) {
				e.printStackTrace();
			}
		}

		disconnectBrowser();

		return retorno;
	}

	public void getResp() throws ComponentNotFoundException, SynchReadException {
		if (group != null) {
			try {
				OpcGroup responseGroup = jopc.synchReadGroup(group);
				System.out.println(responseGroup);
			} catch (ComponentNotFoundException e1) {
				e1.printStackTrace();
			} catch (SynchReadException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("Nenhum grup registrado");
		}
	}
}
