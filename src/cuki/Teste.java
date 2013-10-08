package cuki;

import cuki.OpcConn;

public class Teste {

	private static OpcConn conector = new OpcConn();

	public static void main(String[] args) {
		try {
			conector.connect();
			System.out.println(conector.getResp());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
