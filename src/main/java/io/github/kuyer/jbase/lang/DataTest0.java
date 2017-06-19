package io.github.kuyer.jbase.lang;

/**
 * 正数右移，高位用0补 >>
 * 负数右移，高位用1补 >>
 * 当负数使用无符号右移时，用0进行部位，负数变成了正数 >>>
 * @author rory.zhang
 */
public class DataTest0 {

	public static void main(String[] args) {
		//000:0;001:1;010:2;011:3;100:4;101:5
		String s = "A5";
		byte[] bs = s.getBytes();
		for(byte b : bs) {
			System.out.println(b+":"+Integer.toBinaryString(b));
		}
		System.out.println("-5:"+Integer.toBinaryString(-5));
		/** 
		 * 左移或右移
		 * int a = 5;
		 * a <<= 2;
		 * System.out.println(a);
		 */
		System.out.println(5<<2);//101 -> 10100 -> 20
		System.out.println(5>>2);//101 -> 001 -> 1
		System.out.println(-5>>2);//11111111111111111111111111111011 -> 11111111111111111111111111111110 -> -2
		System.out.println(-5>>>2);//11111111111111111111111111111011 -> 00111111111111111111111111111110 -> 
		System.out.println(5>>>2);//101 -> 001 -> 1
		/** 位与 只有2个二进制数字都是1，才为1 **/
		System.out.println("5&3: "+(5&3));//101 & 011 -> 001 -> 1 
		/** 位或 2个二进制数字有一个是1，就为1 **/
		System.out.println(5|3);//101 | 011 -> 111 -> 7
		/** 位异或 2个二进制相同，为1，否则为0 **/
		System.out.println("5^3: "+(5^3));//101 && 011 -> 110 -> 6
		System.out.println("5^1: "+(5^1));//101 && 001 -> 100 -> 4
		/** 位非 **/
		System.out.println(~5);// 0000 0000 0000 0000 0000 0000 0000 0101 -> 1111 1111 1111 1111 1111 1111 1111 1010 -> -6
		
		int a = 5;
		a <<= 2;
		System.out.println(a);
	}

}
