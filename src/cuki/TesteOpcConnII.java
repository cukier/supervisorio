package cuki;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.exception.UnableRemoveGroupException;
import javafish.clients.opc.exception.VariantTypeException;
import cuki.bin.OpcConnII;

public class TesteOpcConnII {

	private static int tempoRestanteMinutos;
	private static int cicloAtual;
	private static int nrSetores;
	private static int contaFase;
	private static int contaSetor;
	private static int nrFases;
	private static int laminaGet;
	private static int tempoRestanteHoras;
	private static int byte4;
	private static int byte6;
	private static int anguloAtual;
	private int minutos = 5;
	private OpcConnII con = null;

	public TesteOpcConnII(int minutos) {
		this.minutos = minutos;
		System.out.println("Aguardando " + this.minutos + " minutos");
		con = new OpcConnII();
	}

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

		TesteOpcConnII test = new TesteOpcConnII(60);

		try {
			test.con.connectAndRegister();
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

			try {
				anguloAtual = test.con.getanguloAtual();
				tempoRestanteMinutos = test.con.getTempoRestanteMinutos();
				cicloAtual = test.con.getcicloAtuall();
				nrSetores = test.con.getnrSetores();
				contaFase = test.con.getcontaFase();
				contaSetor = test.con.getcontaSetor();
				nrFases = test.con.getnrFases();
				laminaGet = test.con.getlaminaGet();
				tempoRestanteHoras = test.con.gettempoRestanteHoras();
				byte4 = test.con.getbyte4();
				byte6 = test.con.getbyte6();
			} catch (ComponentNotFoundException e) {
				e.printStackTrace();
			} catch (SynchReadException e) {
				e.printStackTrace();
			} catch (VariantTypeException e) {
				e.printStackTrace();
			}

			System.out.println("------------------------------");
			System.out.println("Angulo Atual: " + anguloAtual);
			System.out.println("Ciclo Atual: " + cicloAtual);
			System.out.println("NrSetores: " + nrSetores);
			System.out.println("NrFases: " + nrFases);
			System.out.println("Lâmina: " + laminaGet);
			System.out.println("Fase: " + contaFase);
			System.out.println("Setor: " + contaSetor);
			System.out.println("Tempo estimado: " + tempoRestanteHoras + "h "
					+ tempoRestanteMinutos + "min.");
			System.out.println("Byte4: " + byte4);
			System.out.println("Byte6: " + byte6);
			System.out.println("");
		}
	}
}
