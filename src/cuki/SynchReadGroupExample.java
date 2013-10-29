package cuki;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.CoUninitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;

public class SynchReadGroupExample {

	private static void delay_ms(int delay, Object obj)
			throws InterruptedException {
		synchronized (obj) {
			obj.wait(delay);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SynchReadGroupExample test = new SynchReadGroupExample();

		try {
			JOpc.coInitialize();
		} catch (CoInitializeException e1) {
			e1.printStackTrace();
		}

		JOpc jopc = new JOpc("localhost", "Atos.OPCConnect.1", "JOPC1");
		OpcGroup group = new OpcGroup("group1", true, 10, 0.0f);
		String[] itens = { "Equipamento1.anguloAtual", "Equipamento1.Byte4",
				"Equipamento1.Byte6" };
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

		synchronized (test) {
			test.wait(50);
		}

		// Synchronous reading of group
		int cycles = 100;
		int acycle = 0;
		while (acycle++ < cycles) {
			delay_ms(1000, test);

			try {
				OpcGroup responseGroup = jopc.synchReadGroup(group);
				System.out.println(responseGroup);
			} catch (ComponentNotFoundException e1) {
				e1.printStackTrace();
			} catch (SynchReadException e1) {
				e1.printStackTrace();
			}
		}

		try {
			JOpc.coUninitialize();
		} catch (CoUninitializeException e) {
			e.printStackTrace();
		}
	}
}
