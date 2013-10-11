package cuki;

import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import cuki.bin.OpcConn;

public class Teste {

	public static void main(String[] args) throws InterruptedException {

		Teste test = new Teste();

		String host = "localhost";
		String opcServer = "Matrikon.OPC.Simulation.1";
		// String opcServer = "Atos.OPCConnect.1";
		OpcConn con1 = new OpcConn(host, opcServer);

		try {
			con1.registerAllItens();
		} catch (CoInitializeException e) {
			e.printStackTrace();
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 100; i++) {
			synchronized (test) {
				test.wait(1000);
			}
			try {
				con1.response();
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			}
		}
		con1.disconn();
	}
}
