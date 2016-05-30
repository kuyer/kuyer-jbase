package io.github.kuyer.jbase;

import java.util.Scanner;

/**
 * Java的首个程序，和世界打个招呼，说句话吧
 * @author Rory.Zhang
 */
public class Hello {

	public static void main(String[] args) {
		System.out.println("hello, world!");
		
		int i = 1;
		int j = 1;
		System.out.println(i++);
		System.out.println(i);
		System.out.println(++j);
		System.out.println(j);
		
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
