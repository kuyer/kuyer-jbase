package io.github.kuyer.jbase.design;

public class Bins {
	
	public static void main(String[] args) throws Exception {
		binToChinese("爱i");
		char x = '爱';
		char i = 'i';
		System.out.println("xx1: "+Integer.toBinaryString(x));
		System.out.println("xx2: "+Integer.toBinaryString(i));
	}

	private static void binToChinese(String str) throws Exception {
		byte[] bs = str.getBytes("UTF-8");
		for(int i=0; i<bs.length; i++) {
			String ascii = Integer.toBinaryString(bs[i] & 0xff);
			System.out.println(ascii);
		}
	}

}
