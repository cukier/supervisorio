package cuki.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.SynchWriteException;
import cuki.opc.ServidorOPC;

public class MudarSentido implements ActionListener {

	private ServidorOPC servidor;
	private String pivo;

	public MudarSentido(ServidorOPC servidor, String pivo) {
		this.servidor = servidor;
		this.pivo = pivo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			servidor.setSentdido(pivo);
		} catch (ComponentNotFoundException | SynchWriteException
				| SynchReadException e1) {
			e1.printStackTrace();
		}
	}

}
