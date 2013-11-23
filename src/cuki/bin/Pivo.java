package cuki.bin;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import javafish.clients.opc.exception.CoUninitializeException;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableBrowseBranchException;
import javafish.clients.opc.exception.UnableIBrowseException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.VariantTypeException;
import cuki.exception.NoPivotFoundException;
import cuki.frame.Selecionador;
import cuki.frame.Status;
import cuki.opc.ServidorOPC;

public class Pivo {

	private Selecionador selecionador;
	private ServidorOPC conn;

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
		} catch (CoUninitializeException e) {
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

	private void loop() {
		for (;;) {
			synchronized (this) {
				try {
					this.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (selecionador.isFinalizarPrograma()) {
				System.out.println("Terminando Programa");
				return;
			}

			for (String pivo : selecionador.getPivos()) {
				Status status = selecionador.getStatusPivo(pivo);
				if (status != null) {
					try {
						status.setAngulo(conn.getanguloAtual(pivo));
					} catch (ComponentNotFoundException e) {
						e.printStackTrace();
					} catch (SynchReadException e) {
						e.printStackTrace();
					} catch (VariantTypeException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NoPivotFoundException {

		Pivo window;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
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

		try {
			window = new Pivo();
		} catch (NoPivotFoundException e1) {
			throw e1;
		}

		window.selecionador.setVisible(true);

		window.loop();

		try {
			window.conn.disconnect();
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
		} catch (UnableRemoveGroupException e) {
			e.printStackTrace();
		}

		window.conn = null;
		window.selecionador = null;

		System.exit(0);
	}
}
