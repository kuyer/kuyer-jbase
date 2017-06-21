package io.github.kuyer.jbase.sort.data;

/**
 * 斐波那契数列，又称黄金分割数列
 * 一个数列：0、1、1、2、3、5、8、13、21、……
 * 0:0, 1:1, 2:1, 3:2, 4:3, 5:5, 6:8, 7:13, 8:21, 9:34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368
 * 递归的方法定义：F0=0，F1=1，Fn=F(n-1)+F(n-2)（n>=2，n∈N*）。
 * @author rory.zhang
 */
public class Fibonacci {
	
	public static void main(String[] args) {
		long n = 45;
		long t1 = System.currentTimeMillis();
		System.out.println(fibonacci1(n));
		long t2 = System.currentTimeMillis();
		System.out.println("fibonacci1: "+(t2-t1));
		System.out.println(fibonacci0(n));
		long t3 = System.currentTimeMillis();
		System.out.println("fibonacci0: "+(t3-t2));
		
		
	}
	
	public static long fibonacci0(long n) {
		if(n == 0) {
			return 0;
		} else if(n == 1) {
			return 1;
		} else {
			return fibonacci0(n-1)+fibonacci0(n-2);
		}
	}
	
	public static long fibonacci1(long n) {
		if(n <= 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		long before = 0;//上上次（第n=0次）
		long behind = 1;//上次（第n=1次）
		long result = before + behind;//第n=2次
		for(long i=3; i<=n; i++) {//从第n=3次开始，到第n次结束
			before = behind;//上上次
			behind = result;//上次
			result = before + behind;//本次结果
		}
		return result;
	}

}
