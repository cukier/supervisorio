package cuki;

import cuki.frame.IO;

public class TesteIO {

	public static void main(String[] args) {

		IO window = new IO();

		for (int cont = 0; cont < 10; cont++) {
			synchronized (window) {
				try {
					window.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
