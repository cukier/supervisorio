package cuki.bin;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import javafish.clients.opc.exception.UnableRemoveGroupException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cuki.exception.NoPivotFoundException;
import cuki.frame.Selecionador;
import cuki.opc.ServidorOPC;

public class Pivo {

	private Selecionador selecionador;
	private ServidorOPC conn;

	protected void finalize() {
		try {
			conn.disconnect();
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
		} catch (UnableRemoveGroupException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) throws NoPivotFoundException {

		Pivo window;

		try {
			window = new Pivo();
		} catch (NoPivotFoundException e1) {
			throw e1;
		}

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		window.selecionador.setVisible(true);

	}

	public Pivo() throws NoPivotFoundException {

		conn = new ServidorOPC();

		try {
			conn.connectAndRegister();
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

		String[] servidores = conn.getServidores();

		if (servidores.length > 0) {
			selecionador = new Selecionador(servidores);
		} else {
			System.out
					.println("Não foi encontrando nenhum pivo. Foi o servidor da ATOS instalado?");
			throw new NoPivotFoundException("");
		}

	}
}
