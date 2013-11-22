package cuki;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.VariantTypeException;
import cuki.opc.ServidorOPC;

public class ServidorOPCExample {

	public static void main(String[] args) {

		ServidorOPC servidor = new ServidorOPC();
		ServidorOPCExample test = new ServidorOPCExample();

		try {
			servidor.connectAndRegister();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (UnableBrowseBranchException e) {
			e.printStackTrace();
		} catch (UnableIBrowseException e) {
			e.printStackTrace();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		// String pivo = servidor.getServidores()[0];

		for (int cont = 0; cont < 120; cont++) {
			synchronized (test) {
				try {
					test.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				System.out.println(servidor.getJOpc().synchReadGroup(
						servidor.getGroup()));
				String pivo = servidor.getServidores()[0];
				System.out.println(servidor.getanguloAtual(pivo));
				System.out.println(servidor.getword0(pivo));
				System.out.println(servidor.getlaminaGet(pivo));
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			} catch (VariantTypeException e) {
				e.printStackTrace();
			}
		}

		try {
			servidor.disconnect();
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
		} catch (UnableRemoveGroupException e) {
			e.printStackTrace();
		}
	}
}
