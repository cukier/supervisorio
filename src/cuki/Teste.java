package cuki;

import java.util.Arrays;

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
import javafish.clients.opc.variant.Variant;
import cuki.bin.OpcConn;

public class Teste {

	public static void main(String[] args) throws InterruptedException {

		Teste test = new Teste();
		String host = "localhost";
		// String opcServer = "Matrikon.OPC.Simulation.1";
		String opcServer = "Atos.OPCConnect.1";
		OpcConn con1 = new OpcConn(host, opcServer);
		OpcGroup group = null;

		try {
			con1.registerAllItens();
		} catch (CoInitializeException e1) {
			e1.printStackTrace();
		} catch (UnableBrowseBranchException e1) {
			e1.printStackTrace();
		} catch (UnableIBrowseException e1) {
			e1.printStackTrace();
		} catch (UnableAddGroupException e1) {
			e1.printStackTrace();
		} catch (UnableAddItemException e1) {
			e1.printStackTrace();
		} catch (ConnectivityException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			synchronized (test) {
				test.wait(1000);
			}
			try {
				group = con1.synchResponse();
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			}

			System.out
					.println("====================================================================================================================================================");
			for (OpcItem item : group.getItems()) {
				System.out.printf("%.40s", item.getItemName());
				// System.out.println("Acess Path " + item.getAccessPath());
				System.out.printf("\t\tValue: " + item.getValue());
				// System.out
				// .println("Value Int: " + item.getValue().getInteger());
				// System.out.println("Value Float: "
				// + (float) item.getValue().getFloat());
				// System.out.println("Type: " + item.getDataType());
				System.out.printf("%20s", "Quality: " + item.isQuality());
				System.out.println("");
			}
		}
		con1.disconn();
	}
}
