package cuki;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.variant.Variant;

public class OpcConn {

	private OpcItem responseItem = null;
	private JOpc jopc = null;
	private OpcItem item1 = null;
	private OpcGroup group = null;

	public void connect() throws InterruptedException {

		JOpc.coInitialize();

		jopc = new JOpc("localhost", "Atos.OPCConnect.1", "JOPC1");
		item1 = new OpcItem("Equipamento1.anguloAtual", true, "");
		group = new OpcGroup("group1", true, 500, 0.0f);

		group.addItem(item1);
		jopc.addGroup(group);

		try {
			jopc.connect();
			System.out.println("Opc connected: " + jopc.getFullOpcServerName());
		} catch (ConnectivityException e2) {
			e2.printStackTrace();
			System.out.println("Concection error!");
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

	private void read() {
		try {
			responseItem = jopc.synchReadItem(group, item1);
			System.out.println(responseItem);
			System.out.println(Variant.getVariantName(responseItem
					.getDataType()) + ": " + responseItem.getValue());
		} catch (ComponentNotFoundException e1) {
			e1.printStackTrace();
		} catch (SynchReadException e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		JOpc.coUninitialize();
	}

	public int getResp() {
		read();
		return (int) Integer.parseInt(responseItem.getValue().toString());
	}
}
