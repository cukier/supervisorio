package cuki.frame;

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

import javax.swing.UIManager;

import cuki.bin.OpcConnII;

public class Pivo {

	private Status frame;
	private OpcConnII con;

	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Pivo window = new Pivo();
		window.frame.setVisible(true);
		System.out.println(window.frame.getSize());

		try {
			window.con.connectAndRegister();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		for (;;) {
			synchronized (window) {
				try {
					window.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				window.frame.setAngulo(window.con.getanguloAtual());
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			}

			window.frame.repaint();
		}
	}

	public Pivo() {

		frame = new Status();
		con = new OpcConnII();
	}

}
