package cuki;

import cuki.bin.BitField;

public class TesteBitField {

	public static void main(String[] args) {

		BitField byte1 = new BitField(0);

		System.out.println(byte1.toString());

		try {
			byte1.setBit(15);
		} catch (IndexOutOfBoundsException e1) {
		}
		System.out.println(byte1.getByte());
		System.out.println(byte1.toString());

		try {
			byte1.setBit(5);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(byte1.toString());

		byte1.resetBit(5);

		System.out.println(byte1.toString());
	}

}
