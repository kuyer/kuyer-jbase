package io.github.kuyer.jbase;

public class Test2 {
	
	static int count = 0;
	
	Test2() {
		while(count<10) new Test2(++count);
	}
	
	Test2(int x) {
		super();
	}

	public static void main(String[] args) {
		int i = 1;
		int j = i++;
		System.out.println("i="+i+"; j="+j);
		int k = ++j;
		System.out.println("j="+j+"; k="+k);
		
		new Test2();
		new Test2(count);
		System.out.println("count="+count);
	}

}
