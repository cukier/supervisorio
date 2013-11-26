package cuki.bin;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
	private static final int EXIT_SUCESS = 0;

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
					.println("Não foi encontrando nenhum pivo. Foi instalado o servidor da ATOS?");
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
						status.setword(conn.getword0(pivo),
								conn.getword4(pivo), conn.getword6(pivo));
						status.setAngulo(conn.getanguloAtual(pivo));
						status.getMostrador().setEstado(
								conn.getstatusPivo(pivo));
						status.getMostrador().setSetor(
								conn.getcontaSetor(pivo),
								conn.getnrSetores(pivo));
						status.getMostrador().setFase(conn.getcontaFase(pivo),
								conn.getnrFases(pivo));
						status.getMostrador().setBruta(conn.getlaminaGet(pivo));
						status.getMostrador().setDuracao(
								conn.gettempoRestanteHoras(pivo),
								conn.getTempoRestanteMinutos(pivo));
						status.getMostrador()
								.setCiclo(conn.getcicloAtual(pivo));
					} catch (ComponentNotFoundException e) {
						e.printStackTrace();
					} catch (SynchReadException e) {
						e.printStackTrace();
					} catch (VariantTypeException e) {
						e.printStackTrace();
					}
					status.repaint();
				}
			}
		}
	}

	public static void main(String[] args) throws NoPivotFoundException {

		Pivo lancador;

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		lancador = new Pivo();

		lancador.selecionador.setVisible(true);

		lancador.loop();

		try {
			lancador.conn.disconnect();
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
		} catch (UnableRemoveGroupException e) {
			e.printStackTrace();
		}

		lancador.conn = null;
		lancador.selecionador = null;

		System.exit(Pivo.EXIT_SUCESS);
	}
}
