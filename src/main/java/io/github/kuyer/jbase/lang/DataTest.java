package io.github.kuyer.jbase.lang;

/**
 * http://www.cnblogs.com/solq111/p/6445934.html
 * 1byte = 8bit = 0111 1111
 * 1short = 2byte
 * 1int = 4byte
 * @author rory.zhang
 */
public class DataTest {

	public static void main(String[] args) {
		byte a = 0b110;// 0b 表示二进制
		int b = 0b111;
		byte c = 0x2;// 0x 表示十六进制
		int d = 0xa;
		System.out.println(a+", "+b+", "+c+", "+d);
		
		int i1 = 1 >> 0;
		int i2 = 1 >> 1;//0001 -> 0000 -> 0
		int i3 = 1 << 1;//0001 -> 0010 -> 2
		int i4 = 1 << 2;//0001 -> 0100 -> 4
		int i5 = 1 << 3;//0001 -> 1000 -> 8
		System.out.println(i1);
		System.out.println(i2);
		System.out.println(i3);
		System.out.println(i4);
		System.out.println(i5);
		
		short s1 = 256;//0000 0001 0000 0000
		byte[] ss = new byte[2];
		ss[0] = (byte) (s1 >> 8);//0000 0001 0000 0000 -> 0000 0000 0000 0001 -> 1
		ss[1] = (byte) s1;//0000 0001 0000 0000 -> 0000 0000 -> 0; 1short = 2byte = 16bit; 1byte=8bit
		System.out.println("低8位："+ss[0] + "; 高8位：" + ss[1]);
		
		short s2 = 1;//0000 0000 0000 0001
		byte[] sx = new byte[2];
		sx[0] = (byte) (s2 >> 8);//0000 0000 0000 0001 -> 0000 0000 0000 0000 -> 0
		sx[1] = (byte) s2;////0000 0000 0000 0001 -> 0000 0001 -> 1
		System.out.println("低8位："+sx[0] + "; 高8位：" + sx[1]);
		
		int q0 = 1;// 01
		int q1 = 2;// 10 00:0 01:1 10:2 11:3
		int q2 = 3;// 11
		System.out.println(q0 & q1);// 01 10 -> 00 -> 0
		System.out.println(q0 & q2);// 01 11 -> 01 -> 1
		System.out.println(q1 & q2);// 10 11 -> 10 -> 2
		System.out.println(q1 | q2);// 10 11 -> 11 -> 3
	}

}
