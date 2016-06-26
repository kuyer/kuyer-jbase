package io.github.kuyer.jbase;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * Java的首个程序，和世界打个招呼，说句话吧
 * @author Rory.Zhang
 */
public class Hello {
	
	void aa() {
	}

	public static void main(String[] args) {
		System.out.println("hello, world!");
		
		int i = 1;
		int j = 1;
		System.out.println(i++);
		System.out.println(i);
		System.out.println(++j);
		System.out.println(j);
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(null, null);
		
		Map<String, String> hash = new Hashtable<String, String>();
		//hash.put(null, null);
		hash.put("hello", "rory");
		
		//byte b1 = 127;
		//byte b2 = -128;
		byte b3 = (byte) 128;
		System.out.println("b3="+b3);
		
		int aaa = 999;
		aaa--;
		++aaa;
		System.out.println(aaa);
		
		for(int m=4; m>0; m--) {
			int n = 0;
			do {
				n++;
				if(n == 2) {
					break;
				}
			} while (n<m);
			System.out.print(n);
		}
		System.out.println();
		
		System.out.println("what is your name?");
		// Scanner扫描器，从System.in中扫描信息
		Scanner console = new Scanner(System.in);
		while(console.hasNext()) {
			// 读取System.in中一行信息
			String name = console.nextLine().trim();
			if(name.equals("")) {
				continue;
			}
			if(name.equals("exit") || name.equals("quit")) {
				System.out.println("byebye!");
				break;
			}
			String message = String.format("hello, %s!", name);
			System.out.println(message);
			
			System.out.println("what is your name?");
		}
		console.close();
	}

}
