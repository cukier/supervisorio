package cuki.frame;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableRemoveGroupException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import cuki.bin.OpcConnII;

public class Pivo {

	private Status frame;
	private OpcConnII con;
	private boolean emLoop;

	protected void finalize() {
		try {
			con.disconnect();
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
		} catch (UnableRemoveGroupException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Pivo window = new Pivo();

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		window.frame.setVisible(true);

	}

	public Pivo() {
		frame = new Status();
		con = new OpcConnII();
	}

	@SuppressWarnings("unused")
	private void inicializaConector(Pivo window) {
		try {
			window.con.connectAndRegister();
		} catch (UnableAddGroupException e) {
			e.printStackTrace();
		} catch (UnableAddItemException e) {
			e.printStackTrace();
		} catch (ConnectivityException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void loop(Pivo window) {
		for (;;) {
			synchronized (window) {
				try {
					window.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			try {
				window.frame.setword(window.con.getword0(),
						window.con.getword4(), window.con.getword6());

				window.frame.setAngulo(window.con.getanguloAtual());
				window.frame.getMostrador().setEstado(
						window.con.getstatusPivo());
				window.frame.getMostrador().setSetor(
						window.con.getcontaSetor(), window.con.getnrSetores());
				window.frame.getMostrador().setFase(window.con.getcontaFase(),
						window.con.getnrFases());
				window.frame.getMostrador().setBruta(window.con.getlaminaGet());
				window.frame.getMostrador().setDuracao(
						window.con.gettempoRestanteHoras(),
						window.con.getTempoRestanteMinutos());
				window.frame.getMostrador().setCiclo(
						window.con.getcicloAtuall());
				System.out.println(window.con.getsetorIndice());
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			window.frame.repaint();
		}
	}

}
