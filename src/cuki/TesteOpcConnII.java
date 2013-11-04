package cuki;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.VariantTypeException;
import cuki.bin.OpcConnII;

public class TesteOpcConnII {

	private int minutos = 5;

	public TesteOpcConnII(int minutos) {
		this.minutos = minutos;
		System.out.println("Aguardando " + this.minutos + " minutos");
	}

	public static void main(String[] args) {

		OpcConnII con = new OpcConnII();
		TesteOpcConnII test = new TesteOpcConnII(3);

		try {
			con.connectAndRegister();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}

		for (int cont = 0; cont <= test.minutos * 60; ++cont) {
			synchronized (test) {
				try {
					test.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int anguloAtual = 999;
			
			try {
				anguloAtual = con.getanguloAtual();
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			} catch (VariantTypeException e) {
				e.printStackTrace();
			}

			System.out.println(anguloAtual);
		}
	}
}
