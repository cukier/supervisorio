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
				System.out.println(item.getItemName());
				// System.out.println(item.toString());
				// System.out.println(item.getValue());
				// System.out.println(item.getDataType());
				// if (item.getDataType() == Variant.VT_I2 || ) {
				// try {
				// Variant aux = new Variant(item.getValue());
				// int nr = aux.getWord();
				// System.out.println("Em int: " + nr);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// }
				// System.out.println(item.toString());
				// System.out.println(Arrays.asList(item.toString().split("; ")));

				for (String aux : item.toString().split(";")) {
					if (aux.contains("itemValue")) {
						for (String aux2 : aux.split(" = ")) {
							if (!aux2.contains("itemValue")) {
								System.out.println(aux2);
								try {
									int nr = Integer.parseInt(aux2);
									System.out.println("nr: " + nr);
								} catch (Exception e) {
									try {
										int nr = Integer.parseInt(aux2, 16);
										System.out.println("nr 16: " + nr);
									} catch (Exception e1) {
										float nr = Float.parseFloat(aux2);
										System.out.println("nr f:" + nr);
									}
								}
							}
						}
					}
				}

				// System.out.printf("%60s", item.getItemName());
				// System.out.printf("40%s", " Value: " + item.getValue());
				// int aux = item.getValue().getInteger();
				// System.out.printf("%40s", " tipo: " + aux);
				// System.out.printf("%s", " Quality: " + item.isQuality());
				System.out.println("");
			}
		}
		con1.disconn();
	}
}
