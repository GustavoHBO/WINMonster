package programaTestes;

import java.util.BitSet;

public class Principal {
	public static void main(String[] args) {
		String b = "ABCDE";
		byte[] bytes = b.getBytes();
		BitSet bit = BitSet.valueOf(bytes);
		bytes = bit.toByteArray();
		System.out.println((char)bytes[0]);
		
	}

}
