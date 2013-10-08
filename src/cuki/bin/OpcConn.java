package cuki.bin;

import javafish.clients.opc.browser.JOpcBrowser;
import javafish.clients.opc.exception.CoInitializeException;
import javafish.clients.opc.exception.HostException;
import javafish.clients.opc.exception.NotFoundServersException;

public class OpcConn {

	public static String[] connect() throws CoInitializeException,
			HostException, NotFoundServersException {
		JOpcBrowser.coInitialize();

		String[] opcServers = JOpcBrowser.getOpcServers("localhost");
		return opcServers;
	}
}